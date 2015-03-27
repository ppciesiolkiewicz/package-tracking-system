package com.pciesiol.packagetrackingsystemandroid;


import android.content.Context;

import com.parse.Parse;

public class DatabaseConnector {

    //should be called only once
    public void initialize(Context c) {
        Parse.enableLocalDatastore(c);
        Parse.initialize(c, StaticConstants.APPLICATION_ID, StaticConstants.CLIENT_KEY);
    }
}
