package com.mcompany.coupan.ui.fooddeals;

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
import com.mcompany.coupan.appcommon.constants.Constants;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FoodDealsFragment extends BaseFragment implements FoodDealContractor.FoodDealView {

    private Unbinder mUnbinder;

    @BindView(R.id.recycler_view_best_deals)
    RecyclerView recyclerViewFoodDeals;

    @BindView(R.id.progressbar_bestdeal)
    ProgressBar progressBar;

    private DealsAdapter adapter;
    private List<Deal> foodDealList;
    private GridSpacingItemDecoration gridSpacingItemDecoration;
    private FoodDealContractor.FoodDealPresenter bestDealPresenter;

    @BindView(R.id.apptv_emptyscreenview)
    AppTextView appTextViewEmptyScreen;

    public FoodDealsFragment() {
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
        bestDealPresenter = new FoodDealPresenterImpl(this);
        bestDealPresenter.fetchData();
        foodDealList = new ArrayList<>();
        gridSpacingItemDecoration = new GridSpacingItemDecoration(mActivity, 2, 10, true);
//        createData();
//        setViewData();
        return view;
    }

    private void setViewData() {

        if(Utility.isCollectionNullOrEmpty(foodDealList)){
            appTextViewEmptyScreen.setVisibility(View.VISIBLE);
            return;
        }else {
            appTextViewEmptyScreen.setVisibility(View.GONE);
        }

        recyclerViewFoodDeals.setVisibility(View.VISIBLE);
        adapter = new DealsAdapter(mActivity, foodDealList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerViewFoodDeals.setLayoutManager(mLayoutManager);
        recyclerViewFoodDeals.removeItemDecoration(gridSpacingItemDecoration);
        recyclerViewFoodDeals.addItemDecoration(gridSpacingItemDecoration);
//        recyclerViewFoodDeals.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFoodDeals.setAdapter(adapter);

        recyclerViewFoodDeals.addOnItemTouchListener(
                new RecyclerItemClickListener(mActivity, recyclerViewFoodDeals, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Deal deal = foodDealList.get(position);
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

    public void createData() {
        Deal deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages5.shoppersstop.com/sys-master/images/h1d/h78/11070618828830/app_20180601_HOMEWARE.jpg");
        foodDealList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages2.shoppersstop.com/sys-master/root/h6d/hc9/11048830107678/app_2018052_topsNtees.jpg");
        foodDealList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages4.shoppersstop.com/sys-master/images/h84/hd1/11044924358686/banner_only%26veromoda_static_20180525_app.jpg");
        foodDealList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages2.shoppersstop.com/sys-master/images/he5/hc4/11044923375646/casio-app_20180525.jpg");
        foodDealList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages3.shoppersstop.com/sys-master/root/h15/ha7/11048836726814/App_watches_20180523.jpg");
        foodDealList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages5.shoppersstop.com/sys-master/root/h59/h34/10974954422302/banner_app_20180507_carousal_grid_edhardy.jpg");
        foodDealList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages2.shoppersstop.com/sys-master/images/hc4/h77/10812822061086/app_20180323_-30%25_carousal_grid_louis-philleps.jpg");
        foodDealList.add(deal);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onSuccess(Merchants merchants) {
        foodDealList.clear();

        for (Merchant merchant : merchants.getMerchants()) {
            String type = merchant.getType();
            if (!TextUtils.isEmpty(type) && Constants.DEAL_TYPE_FOOD.equalsIgnoreCase(type)) {
                foodDealList.addAll(merchant.getDeals());
            }
        }
        setViewData();
    }

    @Override
    public void onError(DatabaseError databaseError) {

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