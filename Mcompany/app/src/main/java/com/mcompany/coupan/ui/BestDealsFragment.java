package com.mcompany.coupan.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcompany.coupan.R;
import com.mcompany.coupan.dtos.Deal;
import com.mcompany.coupan.ui.adapters.DealsAdapter;
import com.mcompany.coupan.ui.adapters.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BestDealsFragment extends BaseFragment {

    private Unbinder mUnbinder;

    @BindView(R.id.recycler_view_best_deals)
    RecyclerView recyclerViewBestDeals;

    private DealsAdapter adapter;
    private List<Deal> albumList;

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

        albumList = new ArrayList<>();
        createData();
        adapter = new DealsAdapter(mActivity, albumList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerViewBestDeals.setLayoutManager(mLayoutManager);
        recyclerViewBestDeals.addItemDecoration(new GridSpacingItemDecoration(mActivity, 2, 10, true));
//        recyclerViewBestDeals.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBestDeals.setAdapter(adapter);

        return view;
    }

    public void createData() {
        Deal deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages5.shoppersstop.com/sys-master/images/h1d/h78/11070618828830/app_20180601_HOMEWARE.jpg");
        albumList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages2.shoppersstop.com/sys-master/root/h6d/hc9/11048830107678/app_2018052_topsNtees.jpg");
        albumList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages4.shoppersstop.com/sys-master/images/h84/hd1/11044924358686/banner_only%26veromoda_static_20180525_app.jpg");
        albumList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages2.shoppersstop.com/sys-master/images/he5/hc4/11044923375646/casio-app_20180525.jpg");
        albumList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages3.shoppersstop.com/sys-master/root/h15/ha7/11048836726814/App_watches_20180523.jpg");
        albumList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages5.shoppersstop.com/sys-master/root/h59/h34/10974954422302/banner_app_20180507_carousal_grid_edhardy.jpg");
        albumList.add(deal);

        deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages2.shoppersstop.com/sys-master/images/hc4/h77/10812822061086/app_20180323_-30%25_carousal_grid_louis-philleps.jpg");
        albumList.add(deal);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}