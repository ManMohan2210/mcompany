package com.mcompany.coupan.ui.fooddeals;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.data.network.firebase.FirebaseEventListener;
import com.mcompany.coupan.data.network.firebase.FirebaseManager;
import com.mcompany.coupan.dtos.Merchants;

public class FoodDealInteractorImpl implements FoodDealContractor.FoodDealInteractor {

    FoodDealContractor.FoodDealPresenter foodDealPresenter;

    public FoodDealInteractorImpl(FoodDealContractor.FoodDealPresenter foodDealPresenter) {
        this.foodDealPresenter = foodDealPresenter;
    }


    @Override
    public void onFetchData() {
        FirebaseManager.fetchAllMerchants(new FirebaseEventListener() {
            @Override
            public void onSuccess(Object o) {
                foodDealPresenter.onSuccessFetchData((Merchants) o);
            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foodDealPresenter.onSuccessFetchData((Merchants) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                foodDealPresenter.onErrorFetchData(databaseError);
            }
        });
    }
}

