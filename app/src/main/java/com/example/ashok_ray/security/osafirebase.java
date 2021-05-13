package com.example.ashok_ray.security;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ashok_Ray on 14-01-2019.
 */

public class osafirebase extends Application {
public static DatabaseReference databaseReferenceOSA;
public static FirebaseAuth firebaseAuthOSA;



    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance("https://e-osa-78ae4-default-rtdb.firebaseio.com").setPersistenceEnabled(true);
        databaseReferenceOSA =FirebaseDatabase.getInstance("https://e-osa-78ae4-default-rtdb.firebaseio.com").getReference("OSA");



    }
}
