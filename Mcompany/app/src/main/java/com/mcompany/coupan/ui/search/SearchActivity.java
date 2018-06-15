package com.mcompany.coupan.ui.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.SearchView;
import android.widget.TextView;

import com.mcompany.coupan.R;
import com.mcompany.coupan.dtos.Deal;
import com.mcompany.coupan.ui.base.AppBaseActivity;
import com.mcompany.coupan.ui.adapters.GridSpacingItemDecoration;
import com.mcompany.coupan.ui.adapters.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppBaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view_search)
    RecyclerView mRecyclerView;

    @BindView(R.id.emptyView)
    TextView mEmptyView;

    @BindView(R.id.searchview)
    SearchView mSearchView;

    private List<Deal> bestDealList;

    private MyRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(this, 2, 10, true));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bestDealList = new ArrayList<>();
        createData();
        mAdapter = new MyRecyclerAdapter(this, bestDealList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createData() {
        Deal deal = new Deal();
        deal.setMessage("Making Offers in air Tickets : Grab Rs 800 off on Domestic flights");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages5.shoppersstop.com/sys-master/images/h1d/h78/11070618828830/app_20180601_HOMEWARE.jpg");
        bestDealList.add(deal);

        deal = new Deal();
        deal.setMessage("GrabOn-Medlife Exclusive: Get Up To 30% OFF On Medicines + Flat 70% eCash Points");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages2.shoppersstop.com/sys-master/root/h6d/hc9/11048830107678/app_2018052_topsNtees.jpg");
        bestDealList.add(deal);

        deal = new Deal();
        deal.setMessage("Father's Day - Upto 60% OFF On Fashion, Gadgets, Health, Books, Accessories & More");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages4.shoppersstop.com/sys-master/images/h84/hd1/11044924358686/banner_only%26veromoda_static_20180525_app.jpg");
        bestDealList.add(deal);

        deal = new Deal();
        deal.setMessage("25th Anniversary Celebration Sale: Book Flight Ticket & Win Exciting Prizes");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages2.shoppersstop.com/sys-master/images/he5/hc4/11044923375646/casio-app_20180525.jpg");
        bestDealList.add(deal);

        deal = new Deal();
        deal.setMessage("NNNOW Clearance Sale: Get Flat 50% - 60% OFF On Both Men & Women's Fashion");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages3.shoppersstop.com/sys-master/root/h15/ha7/11048836726814/App_watches_20180523.jpg");
        bestDealList.add(deal);

        deal = new Deal();
        deal.setMessage("Goomo Summer Sale: Flat Rs 1000 OFF On Domestic Flight Tickets");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages5.shoppersstop.com/sys-master/root/h59/h34/10974954422302/banner_app_20180507_carousal_grid_edhardy.jpg");
        bestDealList.add(deal);

        deal = new Deal();
        deal.setMessage("Goomo Summer Sale: Flat Rs 1000 OFF On Domestic Flight Tickets");
        deal.setEndDate("Ends on 31 Jun 2018");
        deal.setImageUrl("https://sslimages2.shoppersstop.com/sys-master/images/hc4/h77/10812822061086/app_20180323_-30%25_carousal_grid_louis-philleps.jpg");
        bestDealList.add(deal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search, menu);
//
//        MenuItem mSearch = menu.findItem(R.id.action_search);
//
//        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return false;//super.onCreateOptionsMenu(menu);
    }
}
