package com.mcompany.coupan.ui.neardealfragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.data.network.firebase.FirebaseEventListener;
import com.mcompany.coupan.data.network.firebase.FirebaseManager;
import com.mcompany.coupan.dtos.Merchants;

public class NearDealInteractorImpl implements NearDealContractor.NearDealInteractor {

    private NearDealContractor.NearDealPresenter nearDealPresenter;

    public NearDealInteractorImpl(NearDealContractor.NearDealPresenter nearDealPresenter) {
        this.nearDealPresenter = nearDealPresenter;
    }


    @Override
    public void onFetchData() {
        FirebaseManager.fetchAllMerchants(new FirebaseEventListener() {
            @Override
            public void onSuccess(Object o) {
                nearDealPresenter.onSuccessFetchData((Merchants) o);
            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nearDealPresenter.onSuccessFetchData((Merchants) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                nearDealPresenter.onErrorFetchData(databaseError);
            }
        });
    }
}

