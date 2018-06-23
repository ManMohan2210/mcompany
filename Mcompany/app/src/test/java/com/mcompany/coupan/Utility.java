package com.mcompany.coupan;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.data.network.firebase.FirebaseEventListener;
import com.mcompany.coupan.data.network.firebase.FirebaseManager;
import com.mcompany.coupan.dtos.Merchants;

public class Utility {
    public static void availableDealSize(final AvailableDealSizeListener availableDealSizeListener) {

        FirebaseManager.fetchAllMerchants(new FirebaseEventListener() {
            @Override
            public void onSuccess(Object o) {
                Merchants o1 = (Merchants) o;
                availableDealSizeListener.countDealAvailable(o1.getMerchants().size());
            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Merchants o1 = (Merchants) dataSnapshot.getValue();
                availableDealSizeListener.countDealAvailable(o1.getMerchants().size());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                availableDealSizeListener.countDealAvailable(0);
            }
        });
    }
}
