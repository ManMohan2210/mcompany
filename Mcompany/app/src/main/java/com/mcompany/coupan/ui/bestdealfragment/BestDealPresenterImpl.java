package com.mcompany.coupan.ui.bestdealfragment;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;

public class BestDealPresenterImpl implements BestDealContractor.BestDealPresenter {

    private BestDealContractor.BestDealView bestDealView;
    private BestDealContractor.BestDealInteractor bestDealInteractor;


    public BestDealPresenterImpl(BestDealContractor.BestDealView bestDealView) {
        this.bestDealView = bestDealView;
        bestDealInteractor = new BestDealInteractorImpl(this);
    }

    @Override
    public void fetchData() {
        onShowLoader();
        bestDealInteractor.onFetchData();
    }

    @Override
    public void onSuccessFetchData(Merchants merchants) {
        onHideLoader();
        if (null != bestDealView) {
            bestDealView.onSuccess(merchants);
        }
    }

    @Override
    public void onErrorFetchData(DatabaseError databaseError) {
        onHideLoader();
        if (null != bestDealView) {
            bestDealView.onError(databaseError);
        }
    }

    @Override
    public void onDestroy() {
        if (null != bestDealView) {
            bestDealView = null;
        }
    }

    @Override
    public void onShowLoader() {
        if (null != bestDealView) {
            bestDealView.setShowLoader();
        }
    }

    @Override
    public void onHideLoader() {
        if (null != bestDealView) {
            bestDealView.setHideLoader();
        }
    }
}
