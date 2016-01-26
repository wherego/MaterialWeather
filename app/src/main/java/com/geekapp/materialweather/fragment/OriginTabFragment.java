package com.geekapp.materialweather.fragment;

import android.os.Bundle;
import android.view.View;

import com.geekapp.materialweather.R;

public class OriginTabFragment extends BaseFragment {

    @Override
    public int getContentLayout() {
        return R.layout.fragment_origin_tab;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUIViews();
    }

    public void initUIViews(){

    }
}
