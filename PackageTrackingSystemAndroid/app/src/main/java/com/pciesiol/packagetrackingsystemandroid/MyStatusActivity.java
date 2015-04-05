package com.pciesiol.packagetrackingsystemandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.pciesiol.packagetrackingsystemandroid.database.Courier;
import com.pciesiol.packagetrackingsystemandroid.database.Package;

import java.util.List;

public class MyStatusActivity extends UpdatingCourierPositionActivity {
    private List<Package> packages;
    private ListView packagesListView;
    private ArrayAdapter<Package> packagesAdapter;
    private MyStatusActivity self; //this

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_status);
        self = this;
        packagesListView = (ListView) findViewById(R.id.packagesListView);


        Package.getCourierPackagesQuery(Courier.getCurrentCourier().getCourierId()).findInBackground(new FindCallback<Package>() {
            @Override
            public void done(List<Package> packages_, ParseException e) {
                packages = packages_;
                packagesAdapter = new ArrayAdapter<>(self, android.R.layout.simple_list_item_1, packages);
                packagesListView.setAdapter(packagesAdapter);
            }
        });

        packagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Package pkg = packagesAdapter.getItem(position);
                Intent intent = new Intent(MyStatusActivity.this,PackageDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("package", pkg);
                //intent.putExtra("package", pkg);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
