package com.pciesiol.packagetrackingsystemandroid.database;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.Serializable;

@ParseClassName("Package")
public class Package extends ParseObject implements Serializable {

    private String descriptionKey = "description";
    private String weightKey = "weight";
    private String courierId = "courierId";

    public Package() {}

    public String getDescription() {
        return this.getString(descriptionKey);
    }

    public Integer getWeight() {
        return this.getInt(weightKey);
    }

    public String getPackageId() { return this.getObjectId(); }

    public String getCourierId() { return this.getString(courierId); }

    public String toString() {
        return getObjectId()+": "+getString(descriptionKey);
    }

    public static ParseQuery<Package> getQuery() {
        return ParseQuery.getQuery(Package.class);
    }

    public static ParseQuery<Package> getCourierPackagesQuery(String courierId) {
        ParseQuery<Package> query = ParseQuery.getQuery("Package");
        query.whereEqualTo("courierId", courierId);

        return query;
    }
}
