package com.geekapp.materialweather.fragment;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekapp.materialweather.R;
import com.geekapp.materialweather.activity.MainActivity;
import com.geekapp.materialweather.adapter.ViewPagerRunnableAdapter;
import com.geekapp.materialweather.util.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        initUIViews();
    }

    public void initUIViews(){
        vCollapsingToolbarLayout.setTitleEnabled(false);
        MainActivity.activity.setToolbarToDrawerLayout(mToolbar);

        final ViewPagerRunnableAdapter adapter = new ViewPagerRunnableAdapter(getChildFragmentManager());
        adapter.addFragment(WeatherFragment.newInstance("weather",
                R.layout.fragment_header_spacing), "Weather");
        adapter.addFragment(WeatherFragment.newInstance("news",
                R.layout.fragment_header_spacing), "News");
        adapter.addFragment(WeatherFragment.newInstance("photos",
                R.layout.fragment_header_spacing), "Photos");

        mViewPager.setAdapter(adapter);
        //add animation to viewpager
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mTabLayout.setupWithViewPager(mViewPager);
        //set item ,MODE_SCROLLABLE = can scroll，MODE_FIXED = not
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
