package com.pciesiol.packagetrackingsystemandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.pciesiol.packagetrackingsystemandroid.database.Package;

public class PackageDetailsActivity extends Activity {

    private TextView packageIdTextView;
    private TextView packageDescriptionTextView;
    private TextView packageWeightTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);

        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        //Package pkg = (Package) intent.getSerializableExtra("package");
        Package pkg = (Package) bundle.getSerializable("package");
        initFields();
        setPackageInfo(pkg);
    }

    private void initFields() {
        this.packageIdTextView = (TextView) findViewById(R.id.packageIdTextView);
        this.packageDescriptionTextView = (TextView) findViewById(R.id.packageDescriptionTextView);
        this.packageWeightTextView = (TextView) findViewById(R.id.packageWeightTextView);
    }

    public void setPackageInfo(Package packageInfo) {
        this.packageIdTextView.setText(packageInfo.getPackageId() == null ? packageInfo.getPackageId() : "nima");
        this.packageDescriptionTextView.setText(packageInfo.getDescription() == null ? packageInfo.getDescription() : "nima");
        this.packageWeightTextView.setText(packageInfo.getWeight() == null ? Integer.toString(packageInfo.getWeight()) : "nima");
    }
}
