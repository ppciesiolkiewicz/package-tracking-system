package com.pciesiol.packagetrackingsystemandroid;

import com.parse.Parse;
import com.parse.ParseObject;

public class Main extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(com.pciesiol.packagetrackingsystemandroid.database.Courier.class);
        ParseObject.registerSubclass(com.pciesiol.packagetrackingsystemandroid.database.Package.class);
        Parse.initialize(this, StaticConstants.APPLICATION_ID, StaticConstants.CLIENT_KEY);
    }
}
