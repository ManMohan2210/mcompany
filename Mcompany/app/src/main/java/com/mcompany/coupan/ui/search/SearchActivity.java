package com.mcompany.coupan.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.google.firebase.database.DatabaseError;
import com.mcompany.coupan.R;
import com.mcompany.coupan.appcommon.constants.IntentKeyConstants;
import com.mcompany.coupan.appcommon.listeners.RecyclerItemClickListener;
import com.mcompany.coupan.appcommon.listeners.SearchQueryListener;
import com.mcompany.coupan.appcommon.utility.Utility;
import com.mcompany.coupan.dtos.Deal;
import com.mcompany.coupan.dtos.Merchant;
import com.mcompany.coupan.dtos.Merchants;
import com.mcompany.coupan.ui.adapters.GridSpacingItemDecoration;
import com.mcompany.coupan.ui.adapters.MyRecyclerAdapter;
import com.mcompany.coupan.ui.base.AppBaseActivity;
import com.mcompany.coupan.ui.dealdetails.DealsDetailActivity;
import com.mcompany.coupan.views.AppTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppBaseActivity implements SearchDealContractor.SearchDealView, SearchQueryListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler_view_search)
    RecyclerView mRecyclerView;

    @BindView(R.id.apptv_emptyscreenview)
    AppTextView appTextViewEmptyScreen;

    @BindView(R.id.searchview)
    SearchView mSearchView;

    @BindView(R.id.progressbar_search)
    ProgressBar progressBar;

    @BindView(R.id.iv_disappointed)
    ImageView imageViewDisappointed;


    private List<Deal> mListAllDeal;

    private MyRecyclerAdapter mAdapter;

    private GridSpacingItemDecoration gridSpacingItemDecoration;
    private SearchDealContractor.SearchDealPresenter searchDealPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchDealPresenter = new SearchDealPresenterImpl(this);
        gridSpacingItemDecoration = new GridSpacingItemDecoration(this, 2, 10, true);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mSearchView.onActionViewExpanded();
        mListAllDeal = new ArrayList<>();
        searchDealPresenter.fetchData();
//        setViewData();
    }

    private void setViewData() {

        if (Utility.isCollectionNullOrEmpty(mListAllDeal)) {
            appTextViewEmptyScreen.setVisibility(View.VISIBLE);
            imageViewDisappointed.setVisibility(View.VISIBLE);
            return;
        } else {
            appTextViewEmptyScreen.setVisibility(View.GONE);
            imageViewDisappointed.setVisibility(View.GONE);
        }
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.removeItemDecoration(gridSpacingItemDecoration);
        mRecyclerView.addItemDecoration(gridSpacingItemDecoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MyRecyclerAdapter(this, mListAllDeal, this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Deal deal = mListAllDeal.get(position);
                        Intent intent = new Intent(SearchActivity.this, DealsDetailActivity.class);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (null != mAdapter) {
                    Filter filter = mAdapter.getFilter();
                    if (null != filter) {
                        filter.filter(newText);
                    }
                }
                return true;
            }
        });
        return false;//super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onSuccess(Merchants merchants) {
        mListAllDeal.clear();

        if (null != merchants) {
            List<Merchant> merchantList = merchants.getMerchants();
            if (!Utility.isCollectionNullOrEmpty(merchantList)) {
                for (Merchant merchant : merchants.getMerchants()) {
                    mListAllDeal.addAll(merchant.getDeals());
                }
            }
        }
        setViewData();
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

    @Override
    public void handleEmptyView(boolean isResultFound) {
        if (isResultFound) {
            imageViewDisappointed.setVisibility(View.GONE);
            appTextViewEmptyScreen.setVisibility(View.GONE);
        } else if (!isResultFound && appTextViewEmptyScreen.getVisibility() == View.GONE) {
            appTextViewEmptyScreen.setVisibility(View.VISIBLE);
            imageViewDisappointed.setVisibility(View.VISIBLE);
        }
    }
}
