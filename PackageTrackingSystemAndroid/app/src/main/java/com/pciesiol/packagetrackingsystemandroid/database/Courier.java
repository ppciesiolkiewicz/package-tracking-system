package com.pciesiol.packagetrackingsystemandroid.database;

import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("_User")
public class Courier extends ParseUser {

    public Courier() {}

    public static Courier getCurrentCourier() {
        return (Courier) ParseUser.getCurrentUser();
    }

    public String getCourierId() { return this.getObjectId(); }
}
