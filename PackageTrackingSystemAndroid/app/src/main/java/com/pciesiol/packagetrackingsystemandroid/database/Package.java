package com.pciesiol.packagetrackingsystemandroid.database;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Package")
public class Package extends ParseObject {
    private String code;
    private String description;
    private int weight;

    private String codeKey ="code";
    private String descriptionKey = "description";
    private String weightKey = "weight";

    public Package() {
        code = this.getString(codeKey);
        description = this.getString(descriptionKey);
        weight = this.getInt(weightKey);
    }

    public void setValues(String code_, String description_, int weight_) {
        code = code_;
        this.put("code", code_);

        description = description_;
        this.put("description", description);

        weight = weight_;
        this.put("weight", weight);
    }

    public static ParseQuery<Package> getQuery() {
        return ParseQuery.getQuery(Package.class);
    }
}
