package com.pciesiol.packagetrackingsystemandroid;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pciesiol.packagetrackingsystemandroid.database.Package;

import java.util.Map;

public class PackageDetailsActivity extends UpdatingCourierPositionActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFields();
    }

    private void initFields() {
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        Package pkg = MyStatusActivity.selectedPackage;
        for (Map.Entry<String, String> entry : pkg.getPackageInfo().entrySet())
        {
            TextView desc = new TextView(this);
            desc.setText(entry.getKey());
            desc.setTextSize(12);

            TextView val = new TextView(this);
            val.setText(entry.getValue());
            val.setTextSize(12);

            ll.addView(desc);
            ll.addView(val);
        }

        setContentView(ll);
    }
}
