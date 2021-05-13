package com.example.ashok_ray.security;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ashok_Ray on 14-01-2019.
 */

public class osafirebase extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
