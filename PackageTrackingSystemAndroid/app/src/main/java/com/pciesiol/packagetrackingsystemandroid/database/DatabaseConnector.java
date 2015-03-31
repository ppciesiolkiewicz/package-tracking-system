package com.pciesiol.packagetrackingsystemandroid.database;


import android.content.Context;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;
import com.pciesiol.packagetrackingsystemandroid.StaticConstants;

import java.util.List;

public class DatabaseConnector {

    //should be called only once
    public void initialize(Context c) {
        Parse.enableLocalDatastore(c);
        Parse.initialize(c, StaticConstants.APPLICATION_ID, StaticConstants.CLIENT_KEY);
    }

    public void login(String username, String password, LogInCallback callback) {
        ParseUser.logInInBackground(username, password, callback);
    }

    public void logout() {

    }

    public boolean isUserLogged() {
        ParseUser user = ParseUser.getCurrentUser();
        return user != null;
    }

    /*
    If any other courier has this package at the moment, this
    operation will remove it from his list
     */
    public void assignPackage(String code) {

    }


    public void dropPackage(String code) {

    }

    public List<Package> getMyPackages() {
        return null;
    }

    public List<String> getMyPackagesIds() {
        return null;
    }
}
