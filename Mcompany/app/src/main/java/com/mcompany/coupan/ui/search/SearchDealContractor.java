package com.mcompany.coupan.ui.search;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;
import com.mcompany.coupan.ui.base.BasePresenter;
import com.mcompany.coupan.ui.base.BaseView;

public interface SearchDealContractor {

    interface SearchDealView extends BaseView {
        void onSuccess(Merchants merchants);

        void onError(DatabaseError databaseError);
    }

    interface SearchDealPresenter extends BasePresenter {
        void fetchData();

        void onSuccessFetchData(Merchants merchants);

        void onErrorFetchData(DatabaseError databaseError);

        void onDestroy();
    }

    interface SearchDealInteractor {
        void onFetchData();
    }
}
