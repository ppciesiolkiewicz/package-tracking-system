package com.pciesiol.packagetrackingsystemandroid.database;

import com.parse.ParseUser;

import java.util.List;


public class Courier extends ParseUser {

    public List<Package> getPackages() {
        throw new UnsupportedOperationException();
    }

    public List<String> getPackagesIds() {
        throw new UnsupportedOperationException();
    }
}
