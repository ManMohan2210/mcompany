package com.mcompany.coupan.ui.search;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.data.network.firebase.FirebaseEventListener;
import com.mcompany.coupan.data.network.firebase.FirebaseManager;
import com.mcompany.coupan.dtos.Merchants;

public class SearchDealInteractorImpl implements SearchDealContractor.SearchDealInteractor {

    private SearchDealContractor.SearchDealPresenter searchDealPresenter;

    public SearchDealInteractorImpl(SearchDealContractor.SearchDealPresenter searchDealPresenter) {
        this.searchDealPresenter = searchDealPresenter;
    }


    @Override
    public void onFetchData() {
        FirebaseManager.fetchAllMerchants(new FirebaseEventListener() {
            @Override
            public void onSuccess(Object o) {
                searchDealPresenter.onSuccessFetchData((Merchants) o);
            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                searchDealPresenter.onSuccessFetchData((Merchants) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                searchDealPresenter.onErrorFetchData(databaseError);
            }
        });
    }
}

