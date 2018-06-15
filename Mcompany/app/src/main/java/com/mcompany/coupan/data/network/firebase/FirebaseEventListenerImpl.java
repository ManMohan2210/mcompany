package com.mcompany.coupan.data.network.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class FirebaseEventListenerImpl<T> implements FirebaseEventListener<T> {

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
