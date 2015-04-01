package com.pciesiol.packagetrackingsystemandroid.database;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Package")
public class Package extends ParseObject {

    private String descriptionKey = "description";
    private String weightKey = "weight";



    public String getDescription() {
        return this.getString(descriptionKey);
    }

    public int getWeight() {
        return this.getInt(weightKey);
    }

    public static ParseQuery<Package> getQuery() {
        return ParseQuery.getQuery(Package.class);
    }
}
