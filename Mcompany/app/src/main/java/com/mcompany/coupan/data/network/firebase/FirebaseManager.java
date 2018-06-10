package com.mcompany.coupan.data.network.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {

    private static FirebaseManager singletonInstance = null;
    private final String DB_NAME = "mcompdb";

    private FirebaseManager() {

    }

    public static FirebaseManager getInstance() {
        if (singletonInstance == null) {
            synchronized (FirebaseManager.class) {
                if (singletonInstance == null) {
                    singletonInstance = new FirebaseManager();
                }
            }
        }
        return singletonInstance;
    }

    public void initialize() {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
//        myRef.
//
//        myRef.setValue("Hello, World!");
    }
}
