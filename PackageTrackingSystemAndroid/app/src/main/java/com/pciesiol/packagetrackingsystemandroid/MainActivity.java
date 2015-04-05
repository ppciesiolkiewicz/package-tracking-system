package com.pciesiol.packagetrackingsystemandroid;

import android.IntentIntegrator;
import android.IntentResult;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.pciesiol.packagetrackingsystemandroid.database.Courier;
import com.pciesiol.packagetrackingsystemandroid.database.Package;

public class MainActivity extends UpdatingCourierPositionActivity {
    private Button accountInfoButton;
    private Button scanButton;
    private Button logoutButton;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        accountInfoButton = (Button) findViewById(R.id.button_account_info);
        accountInfoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyStatusActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        scanButton = (Button) findViewById(R.id.button_scan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                scan();
            }
        });

        logoutButton = (Button) findViewById(R.id.button_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Courier.logOut();
                Intent intent = new Intent(MainActivity.this, DispatchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void scan() {
        //instantiate ZXing integration class
        IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
        //start scanning
        scanIntegrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //ZXing is the only activity resulting here.
        //retrieve result of scanning - instantiate ZXing object
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        String scanContent;
        if (scanningResult != null && (scanContent = scanningResult.getContents()) != null) {
            //valid scan data
            processCode(scanContent);
        } else {
            //invalid scan data or scan canceled
            Toast toast = Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.msg_no_scan_data), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void processCode(String packageCode) {
        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage(getString(R.string.progress_checking_the_code));
        dialog.show();

        Package.getQuery().getInBackground(packageCode, new GetCallback<Package>() {
            public void done(Package pkg, ParseException e) {
                dialog.dismiss();
                if (e == null) { //no error
                    Toast toast;
                    if(doIHaveThatPackage(pkg)) {
                        pkg.leavePackage();
                        toast = Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.you_dropped_the_package), Toast.LENGTH_SHORT);
                    }
                    else {
                        pkg.assignToNewCourier(Courier.getCurrentCourier().getCourierId());
                        toast = Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.you_get_the_package), Toast.LENGTH_SHORT);
                    }
                    toast.show();
                } else {
                    showDialog(getResources().getString(R.string.invalid_package_code));
                }
            }
        });
    }

    private boolean doIHaveThatPackage(Package pkg) {
         String packageOwnerId = pkg.getCourierId();
         return packageOwnerId != null ? Courier.getCurrentCourier().getCourierId().equals(packageOwnerId) : false;
    }

    private void showDialog(String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}