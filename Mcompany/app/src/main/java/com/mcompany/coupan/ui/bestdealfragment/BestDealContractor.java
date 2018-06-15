package com.mcompany.coupan.ui.bestdealfragment;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.dtos.Merchants;
import com.mcompany.coupan.ui.base.BasePresenter;
import com.mcompany.coupan.ui.base.BaseView;

public interface BestDealContractor {

    interface BestDealView extends BaseView {
        void onSuccess(Merchants merchants);

        void onError(DatabaseError databaseError);
    }

    interface BestDealPresenter extends BasePresenter {
        void fetchData();

        void onSuccessFetchData(Merchants merchants);

        void onErrorFetchData(DatabaseError databaseError);

        void onDestroy();
    }

    interface BestDealInteractor {
        void onFetchData();
    }
}
