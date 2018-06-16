package com.mcompany.coupan.ui.bestdealfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.constants.IntentKeyConstants;
import com.mcompany.coupan.appcommon.listeners.RecyclerItemClickListener;
import com.mcompany.coupan.appcommon.utility.Utility;
import com.mcompany.coupan.dtos.Deal;
import com.mcompany.coupan.dtos.Merchant;
import com.mcompany.coupan.dtos.Merchants;
import com.mcompany.coupan.ui.adapters.DealsAdapter;
import com.mcompany.coupan.ui.adapters.GridSpacingItemDecoration;
import com.mcompany.coupan.ui.base.BaseFragment;
import com.mcompany.coupan.ui.dealdetails.DealsDetailActivity;
import com.mcompany.coupan.views.AppTextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BestDealsFragment extends BaseFragment implements BestDealContractor.BestDealView {

    private Unbinder mUnbinder;

    @BindView(R.id.recycler_view_best_deals)
    RecyclerView recyclerViewBestDeals;

    @BindView(R.id.progressbar_bestdeal)
    ProgressBar progressBar;

    @BindView(R.id.apptv_emptyscreenview)
    AppTextView appTextViewEmptyScreen;

    private DealsAdapter adapter;
    private List<Deal> bestDealList;
    private GridSpacingItemDecoration gridSpacingItemDecoration;
    private BestDealContractor.BestDealPresenter bestDealPresenter;
    private int mBestDealThreshold;

    public BestDealsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_best_deals, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        bestDealPresenter = new BestDealPresenterImpl(this);
        bestDealPresenter.fetchData();
        bestDealList = new ArrayList<>();
        gridSpacingItemDecoration = new GridSpacingItemDecoration(mActivity, 2, 10, true);
        return view;
    }

    private void setViewData() {
        if(Utility.isCollectionNullOrEmpty(bestDealList)){
            appTextViewEmptyScreen.setVisibility(View.VISIBLE);
            return;
        }else {
            appTextViewEmptyScreen.setVisibility(View.GONE);
        }
        recyclerViewBestDeals.setVisibility(View.VISIBLE);
        adapter = new DealsAdapter(mActivity, bestDealList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerViewBestDeals.setLayoutManager(mLayoutManager);
        recyclerViewBestDeals.removeItemDecoration(gridSpacingItemDecoration);
        recyclerViewBestDeals.addItemDecoration(gridSpacingItemDecoration);
        recyclerViewBestDeals.setAdapter(adapter);

        recyclerViewBestDeals.addOnItemTouchListener(
                new RecyclerItemClickListener(mActivity, recyclerViewBestDeals, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Deal deal = bestDealList.get(position);
                        Intent intent = new Intent(mActivity, DealsDetailActivity.class);
                        intent.putExtra(IntentKeyConstants.EXTRA_DEAL_DATA, deal);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onSuccess(Merchants merchants) {
        bestDealList.clear();

        if (null != merchants) {
            List<Merchant> merchantList = merchants.getMerchants();
            if (!Utility.isCollectionNullOrEmpty(merchantList)) {
                for (Merchant merchant : merchants.getMerchants()) {
                    bestDealList.addAll(merchant.getDeals());
                    String threshold = merchant.getBestDealThreshold();
                    if (!TextUtils.isEmpty(threshold)) {
                        mBestDealThreshold = Integer.parseInt(threshold);
                    }
                }
                filterBestDeals();
            }
        }
        setViewData();
    }

    private void filterBestDeals() {
        Iterator iterator = bestDealList.iterator();
        while (iterator.hasNext()) {
            Deal deal = (Deal) iterator.next();
            String rankStr = deal.getRank();
            if (!TextUtils.isEmpty(rankStr)) {
                int rank = Integer.parseInt(rankStr);
                if (rank < mBestDealThreshold) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public void onError(DatabaseError databaseError) {
showToast(databaseError.getMessage());
    }

    @Override
    public void setShowLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setHideLoader() {
        progressBar.setVisibility(View.GONE);
    }
}