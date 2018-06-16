package com.mcompany.coupan.ui.fooddeals;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;

public class FoodDealPresenterImpl implements FoodDealContractor.FoodDealPresenter {

    private FoodDealContractor.FoodDealView foodDealView;
    private FoodDealContractor.FoodDealInteractor foodDealInteractor;


    public FoodDealPresenterImpl(FoodDealContractor.FoodDealView foodDealView) {
        this.foodDealView = foodDealView;
        foodDealInteractor = new FoodDealInteractorImpl(this);
    }

    @Override
    public void fetchData() {
        onShowLoader();
        foodDealInteractor.onFetchData();
    }

    @Override
    public void onSuccessFetchData(Merchants merchants) {
        onHideLoader();
        if (null != foodDealView) {
            foodDealView.onSuccess(merchants);
        }
    }

    @Override
    public void onErrorFetchData(DatabaseError databaseError) {
        onHideLoader();
        if (null != foodDealView) {
            foodDealView.onError(databaseError);
        }
    }

    @Override
    public void onDestroy() {
        if (null != foodDealView) {
            foodDealView = null;
        }
    }

    @Override
    public void onShowLoader() {
        if (null != foodDealView) {
            foodDealView.setShowLoader();
        }
    }

    @Override
    public void onHideLoader() {
        if (null != foodDealView) {
            foodDealView.setHideLoader();
        }
    }
}
