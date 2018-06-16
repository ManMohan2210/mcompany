package com.mcompany.coupan.ui.dealfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mcompany.coupan.R;
import com.mcompany.coupan.ui.base.BaseFragment;
import com.mcompany.coupan.ui.bestdealfragment.BestDealsFragment;
import com.mcompany.coupan.ui.OneFragment;
import com.mcompany.coupan.ui.adapters.ViewPagerAdapter;
import com.mcompany.coupan.ui.fooddeals.FoodDealsFragment;
import com.mcompany.coupan.ui.moviedeals.MovieDealsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.apend.slider.model.Slide;
import ir.apend.slider.ui.Slider;

public class DealsFragment extends BaseFragment {

    private Unbinder mUnbinder;
    @BindView(R.id.slider)
    Slider slider;

    @BindView(R.id.tab_category_deals)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    public DealsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deals, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setCresoulView();
        setTabLayout();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void setCresoulView() {
        List<Slide> slideList = new ArrayList<>();
//        slideList.add(new Slide(0, "http://cssslider.com/sliders/demo-20/data1/images/picjumbo.com_img_4635.jpg", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
//        slideList.add(new Slide(1, "http://cssslider.com/sliders/demo-12/data1/images/picjumbo.com_hnck1995.jpg", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
//        slideList.add(new Slide(2, "http://cssslider.com/sliders/demo-19/data1/images/picjumbo.com_hnck1588.jpg", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
//        slideList.add(new Slide(3, "http://wowslider.com/sliders/demo-18/data1/images/shanghai.jpg", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));

        slideList.add(new Slide(0, "https://sslimages5.shoppersstop.com/sys-master/images/h1d/h78/11070618828830/app_20180601_HOMEWARE.jpg", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
        slideList.add(new Slide(1, "https://sslimages2.shoppersstop.com/sys-master/root/h6d/hc9/11048830107678/app_2018052_topsNtees.jpg", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
        slideList.add(new Slide(2, "https://sslimages4.shoppersstop.com/sys-master/images/h84/hd1/11044924358686/banner_only%26veromoda_static_20180525_app.jpg", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));
        slideList.add(new Slide(3, "https://sslimages2.shoppersstop.com/sys-master/images/he5/hc4/11044923375646/casio-app_20180525.jpg", getResources().getDimensionPixelSize(R.dimen.slider_image_corner)));


        slider.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        slider.addSlides(slideList);
    }

    public void setTabLayout() {
        viewPager.setOffscreenPageLimit(6);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        Fragment fragment = new BestDealsFragment();
        adapter.addFrag(fragment, getString(R.string.deals_best));

        fragment = new FoodDealsFragment();
        adapter.addFrag(fragment, getString(R.string.deals_foods));

        fragment = new MovieDealsFragment();
        adapter.addFrag(fragment, getString(R.string.deals_movies));

        fragment = new OneFragment();
        adapter.addFrag(fragment, getString(R.string.deals_travel));
        ((OneFragment) fragment).setName(getString(R.string.deals_travel) +"\n" +getString(R.string.under_development));

        fragment = new OneFragment();
        adapter.addFrag(fragment, getString(R.string.deals_fashion));
        ((OneFragment) fragment).setName(getString(R.string.deals_fashion) +"\n" +getString(R.string.under_development));

        fragment = new OneFragment();
        adapter.addFrag(fragment, getString(R.string.deals_electronic));
        ((OneFragment) fragment).setName(getString(R.string.deals_electronic) +"\n" +getString(R.string.under_development));

        viewPager.setAdapter(adapter);
    }
}