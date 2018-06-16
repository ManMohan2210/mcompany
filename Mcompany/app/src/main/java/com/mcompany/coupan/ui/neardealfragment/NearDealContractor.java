package com.mcompany.coupan.ui.neardealfragment;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;
import com.mcompany.coupan.ui.base.BasePresenter;
import com.mcompany.coupan.ui.base.BaseView;

public interface NearDealContractor {

    interface NearDealView extends BaseView {
        void onSuccess(Merchants merchants);

        void onError(DatabaseError databaseError);
    }

    interface NearDealPresenter extends BasePresenter {
        void fetchData();

        void onSuccessFetchData(Merchants merchants);

        void onErrorFetchData(DatabaseError databaseError);

        void onDestroy();
    }

    interface NearDealInteractor {
        void onFetchData();
    }
}
