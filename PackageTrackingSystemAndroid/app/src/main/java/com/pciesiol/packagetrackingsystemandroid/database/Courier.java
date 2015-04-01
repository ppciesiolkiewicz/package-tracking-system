package com.pciesiol.packagetrackingsystemandroid.database;

import com.parse.ParseClassName;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("_User")
public class Courier extends ParseUser {

    public Courier() {}


    public List<String> getPackagesIds() {
        JSONArray jArray = this.getJSONArray("packages");
        List<String> packages = new ArrayList<>();
        if (jArray != null) {
            for (int i=0;i<jArray.length();i++){
                try {
                    packages.add(jArray.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return packages;
    }


    public static Courier getCurrentCourier() {
        return (Courier) ParseUser.getCurrentUser();
    }
}
