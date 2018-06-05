package com.mcompany.coupan.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Base Fragment class
 */
public abstract class BaseFragment extends Fragment {

    protected AppBaseActivity mActivity;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (AppBaseActivity) getActivity();
        setHasOptionsMenu(true);

    }

    public void showToast(String msg) {
        (mActivity).showToast(msg);
    }

    public void showToast(int resId) {
        (mActivity).showToast(resId);
    }

    public void showLoader() {
        (mActivity).showLoader();

    }

    public void hideLoader() {

        if (mActivity != null) {
            (mActivity).hideLoader();
        }
    }


    public void loadData() {
    }

}

