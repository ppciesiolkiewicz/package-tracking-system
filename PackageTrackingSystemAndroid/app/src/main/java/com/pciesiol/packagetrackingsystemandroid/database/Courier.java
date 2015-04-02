package com.pciesiol.packagetrackingsystemandroid.database;

import com.parse.ParseClassName;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("_User")
public class Courier extends ParseUser {

    public Courier() {}

    public static Courier getCurrentCourier() {
        return (Courier) ParseUser.getCurrentUser();
    }

    public ParseQuery<Package> getCourierPackagesQuery() {
        ParseQuery<Package> query = ParseQuery.getQuery("Package");
        query.whereEqualTo("courierId", this.getObjectId());

        return query;
    }
}
