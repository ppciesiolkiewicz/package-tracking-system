package com.pciesiol.packagetrackingsystemandroid;


import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.pciesiol.packagetrackingsystemandroid.database.Courier;
import com.pciesiol.packagetrackingsystemandroid.gpstracking.GPSTracker;

public class UpdatingCourierPositionActivity extends Activity {
    protected GPSTracker gps;
    protected Thread updateLocationThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gps = new GPSTracker(UpdatingCourierPositionActivity.this);
        updateLocationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if (gps.canGetLocation()) {
                        Location location = gps.getLocation();
                        if(location == null) //TODO always null - something is wrong
                            System.out.println("Location is NULL!");
                        else
                            Courier.updateCurrentCourierPosition(location);
                    } else {
                        // Ask user to enable GPS/network in settings
                        gps.showSettingsAlert();
                    }

                    try {
                        Thread.sleep(1000*60); //60sec
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        updateLocationThread.start();
    }

}
