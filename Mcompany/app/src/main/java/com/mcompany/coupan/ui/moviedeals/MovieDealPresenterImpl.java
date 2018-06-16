package com.mcompany.coupan.ui.moviedeals;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;

public class MovieDealPresenterImpl implements MovieDealContractor.MovieDealPresenter {

    private MovieDealContractor.MovieDealView foodDealView;
    private MovieDealContractor.MovieDealInteractor foodDealInteractor;


    public MovieDealPresenterImpl(MovieDealContractor.MovieDealView foodDealView) {
        this.foodDealView = foodDealView;
        foodDealInteractor = new MovieDealInteractorImpl(this);
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
