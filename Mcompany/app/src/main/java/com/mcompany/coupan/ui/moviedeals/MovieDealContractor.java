package com.mcompany.coupan.ui.moviedeals;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;
import com.mcompany.coupan.ui.base.BasePresenter;
import com.mcompany.coupan.ui.base.BaseView;

public interface MovieDealContractor {

    interface MovieDealView extends BaseView {
        void onSuccess(Merchants merchants);

        void onError(DatabaseError databaseError);
    }

    interface MovieDealPresenter extends BasePresenter {
        void fetchData();

        void onSuccessFetchData(Merchants merchants);

        void onErrorFetchData(DatabaseError databaseError);

        void onDestroy();
    }

    interface MovieDealInteractor {
        void onFetchData();
    }
}
