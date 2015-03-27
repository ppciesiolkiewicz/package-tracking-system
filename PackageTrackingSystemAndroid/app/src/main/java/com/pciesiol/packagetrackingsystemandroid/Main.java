package com.pciesiol.packagetrackingsystemandroid;

public class Main extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseConnector db = new DatabaseConnector();
        db.initialize(this);

    }
}
