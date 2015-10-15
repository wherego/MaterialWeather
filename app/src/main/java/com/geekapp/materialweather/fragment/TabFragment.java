package com.geekapp.materialweather.fragment;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.geekapp.materialweather.R;
import com.geekapp.materialweather.adapter.ViewPagerRunnableAdapter;

import butterknife.Bind;

public class TabFragment extends BaseFragment {

    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout vCollapsingToolbarLayout;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    public static TabFragment newInstance() {
        return new TabFragment();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_tab;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vCollapsingToolbarLayout.setTitleEnabled(false);

        ViewPagerRunnableAdapter adapter = new ViewPagerRunnableAdapter(getChildFragmentManager());
        adapter.addFragment(WeatherFragment.newInstance("Cat", 100,
                R.layout.fragment_header_spacing), "Cat");
        adapter.addFragment(WeatherFragment.newInstance("Dog", 100,
                R.layout.fragment_header_spacing), "Dog");
        adapter.addFragment(WeatherFragment.newInstance("Mouse", 100,
                R.layout.fragment_header_spacing), "Mouse");
        adapter.addFragment(WeatherFragment.newInstance("Chicken", 100,
                R.layout.fragment_header_spacing), "Chicken");

        mViewPager.setAdapter(adapter);
        //add animation to viewpager
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mTabLayout.setupWithViewPager(mViewPager);
        //set item ,MODE_SCROLLABLE = can scroll，MODE_FIXED = not
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
