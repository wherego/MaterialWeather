package com.geekapp.materialweather.fragment;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.geekapp.materialweather.R;
import com.geekapp.materialweather.adapter.ViewPagerRunnableAdapter;
import com.geekapp.materialweather.util.LogUtil;

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
        LogUtil.d("onViewCreated");
        vCollapsingToolbarLayout.setTitleEnabled(false);

        final ViewPagerRunnableAdapter adapter = new ViewPagerRunnableAdapter(getChildFragmentManager());
        adapter.addFragment(WeatherFragment.newInstance("shenzhen",
                R.layout.fragment_header_spacing), "深圳");
        adapter.addFragment(WeatherFragment.newInstance("guangzhou",
                R.layout.fragment_header_spacing), "广州");
        adapter.addFragment(WeatherFragment.newInstance("shanghai",
                R.layout.fragment_header_spacing), "上海");
        adapter.addFragment(WeatherFragment.newInstance("beijing",
                R.layout.fragment_header_spacing), "北京");

        mViewPager.setAdapter(adapter);
        //add animation to viewpager
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mTabLayout.setupWithViewPager(mViewPager);
        //set item ,MODE_SCROLLABLE = can scroll，MODE_FIXED = not
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
