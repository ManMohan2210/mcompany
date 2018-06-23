package com.mcompany.coupan.data.network.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcompany.coupan.dtos.Merchants;

public class FirebaseManager {

    private static final String DB_MERCHANTS = "merchants";
    private static DatabaseReference rootDbRef;

    public static void initialize() {
        // Write a message to the database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        rootDbRef = database.getReference();
    }

    public static void initialize(FirebaseDatabase database) {
        // Write a message to the database
        rootDbRef = database.getReference();
    }

    public static void fetchAllMerchants(final FirebaseEventListener firebaseEventListener) {
        DatabaseReference chieldRef = rootDbRef.child(DB_MERCHANTS);

        /*
         * Below method will be called only first time
         */
        rootDbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                firebaseEventListener.onSuccess(dataSnapshot.getValue(Merchants.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                firebaseEventListener.onCancelled(databaseError);
            }
        });

        /*
         * Below method will be called when data is get changed and  first time as well
         */
        rootDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                firebaseEventListener.onSuccess(dataSnapshot.getValue(Merchants.class));
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                firebaseEventListener.onCancelled(databaseError);
            }
        });
    }
}
