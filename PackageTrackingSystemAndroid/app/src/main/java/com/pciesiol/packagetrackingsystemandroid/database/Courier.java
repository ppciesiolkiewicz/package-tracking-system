package com.pciesiol.packagetrackingsystemandroid.database;

import android.location.Location;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;

@ParseClassName("_User")
public class Courier extends ParseUser {

    public Courier() {}

    public static Courier getCurrentCourier() {
        return (Courier) ParseUser.getCurrentUser();
    }

    public static void updateCurrentCourierPosition(Location location) {
        ParseGeoPoint geoPoint = new ParseGeoPoint(location.getLatitude(), location.getLongitude());
        Courier c = Courier.getCurrentCourier();
        c.put("position", geoPoint);
        c.saveInBackground();
        System.out.println(location.getLatitude() + " " + location.getLongitude());
    }

    public String getCourierId() { return this.getObjectId(); }
}
