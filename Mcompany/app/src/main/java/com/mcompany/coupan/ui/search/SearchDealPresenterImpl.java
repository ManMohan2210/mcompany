package com.mcompany.coupan.ui.search;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;

public class SearchDealPresenterImpl implements SearchDealContractor.SearchDealPresenter {

    private SearchDealContractor.SearchDealView searchDealView;
    private SearchDealContractor.SearchDealInteractor searchDealInteractor;


    public SearchDealPresenterImpl(SearchDealContractor.SearchDealView searchDealView) {
        this.searchDealView = searchDealView;
        searchDealInteractor = new SearchDealInteractorImpl(this);
    }

    @Override
    public void fetchData() {
        onShowLoader();
        searchDealInteractor.onFetchData();
    }

    @Override
    public void onSuccessFetchData(Merchants merchants) {
        onHideLoader();
        if (null != searchDealView) {
            searchDealView.onSuccess(merchants);
        }
    }

    @Override
    public void onErrorFetchData(DatabaseError databaseError) {
        onHideLoader();
        if (null != searchDealView) {
            searchDealView.onError(databaseError);
        }
    }

    @Override
    public void onDestroy() {
        if (null != searchDealView) {
            searchDealView = null;
        }
    }

    @Override
    public void onShowLoader() {
        if (null != searchDealView) {
            searchDealView.setShowLoader();
        }
    }

    @Override
    public void onHideLoader() {
        if (null != searchDealView) {
            searchDealView.setHideLoader();
        }
    }
}
