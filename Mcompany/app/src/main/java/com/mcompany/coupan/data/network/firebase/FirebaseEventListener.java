package com.mcompany.coupan.data.network.firebase;

import com.google.firebase.database.ValueEventListener;

public interface FirebaseEventListener<T> extends ValueEventListener {

    void onSuccess(T t);
}
