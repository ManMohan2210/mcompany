package com.mcompany.coupan.ui.bestdealfragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.data.network.firebase.FirebaseEventListener;
import com.mcompany.coupan.data.network.firebase.FirebaseManager;
import com.mcompany.coupan.dtos.Merchants;

public class BestDealInteractorImpl implements BestDealContractor.BestDealInteractor {

    BestDealContractor.BestDealPresenter bestDealPresenter;

    public BestDealInteractorImpl(BestDealContractor.BestDealPresenter bestDealPresenter) {
        this.bestDealPresenter = bestDealPresenter;
    }


    @Override
    public void onFetchData() {
        FirebaseManager.fetchAllMerchants(new FirebaseEventListener() {
            @Override
            public void onSuccess(Object o) {
                bestDealPresenter.onSuccessFetchData((Merchants) o);
            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bestDealPresenter.onSuccessFetchData((Merchants) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                bestDealPresenter.onErrorFetchData(databaseError);
            }
        });
    }
}

