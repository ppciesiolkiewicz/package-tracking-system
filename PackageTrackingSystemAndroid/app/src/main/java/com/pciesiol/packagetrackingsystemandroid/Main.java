package com.pciesiol.packagetrackingsystemandroid;

import com.parse.Parse;

public class Main extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, StaticConstants.APPLICATION_ID, StaticConstants.CLIENT_KEY);

    }
}
