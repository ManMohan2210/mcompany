package com.mcompany.coupan.ui.fooddeals;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;
import com.mcompany.coupan.ui.base.BasePresenter;
import com.mcompany.coupan.ui.base.BaseView;

public interface FoodDealContractor {

    interface FoodDealView extends BaseView {
        void onSuccess(Merchants merchants);

        void onError(DatabaseError databaseError);
    }

    interface FoodDealPresenter extends BasePresenter {
        void fetchData();

        void onSuccessFetchData(Merchants merchants);

        void onErrorFetchData(DatabaseError databaseError);

        void onDestroy();
    }

    interface FoodDealInteractor {
        void onFetchData();
    }
}
