package com.mcompany.coupan.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.mcompany.coupan.R;
import com.mcompany.coupan.ui.adapters.*;

public class TabActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_deals_nearme);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "ONE");
        viewPager.setAdapter(adapter);
    }


}
