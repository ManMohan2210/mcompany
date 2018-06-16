package com.mcompany.coupan.ui.neardealfragment;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;

public class NearDealPresenterImpl implements NearDealContractor.NearDealPresenter {

    private NearDealContractor.NearDealView nearDealView;
    private NearDealContractor.NearDealInteractor nearDealInteractor;


    public NearDealPresenterImpl(NearDealContractor.NearDealView nearDealView) {
        this.nearDealView = nearDealView;
        nearDealInteractor = new NearDealInteractorImpl(this);
    }

    @Override
    public void fetchData() {
        onShowLoader();
        nearDealInteractor.onFetchData();
    }

    @Override
    public void onSuccessFetchData(Merchants merchants) {
        onHideLoader();
        if (null != nearDealView) {
            nearDealView.onSuccess(merchants);
        }
    }

    @Override
    public void onErrorFetchData(DatabaseError databaseError) {
        onHideLoader();
        if (null != nearDealView) {
            nearDealView.onError(databaseError);
        }
    }

    @Override
    public void onDestroy() {
        if (null != nearDealView) {
            nearDealView = null;
        }
    }

    @Override
    public void onShowLoader() {
        if (null != nearDealView) {
            nearDealView.setShowLoader();
        }
    }

    @Override
    public void onHideLoader() {
        if (null != nearDealView) {
            nearDealView.setHideLoader();
        }
    }
}
