package com.geekapp.materialweather.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekapp.materialweather.R;
import com.geekapp.materialweather.adapter.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.henrytao.recyclerview.BaseAdapter;
import me.henrytao.recyclerview.SimpleRecyclerViewAdapter;

public class WeatherFragment extends Fragment implements ObservableScrollView {

    private static final String ARG_COUNT = "ARG_COUNT";

    private static final String ARG_HEADER_LAYOUT = "ARG_HEADER_LAYOUT";

    private static final String ARG_TITLE = "ARG_TITLE";

    @Bind(android.R.id.list)
    RecyclerView mRecyclerView;

    public WeatherFragment() {
    }

    public static WeatherFragment newInstance(String title, int count) {
        return WeatherFragment.newInstance(title, count, 0);
    }

    public static WeatherFragment newInstance(String title, int count, @LayoutRes int headerLayout) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        bundle.putInt(ARG_COUNT, count);
        bundle.putInt(ARG_HEADER_LAYOUT, headerLayout);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.Adapter adapter = new SimpleRecyclerViewAdapter(new SimpleAdapter<>(getSampleData(getArgTitle(), getArgCount()), null)) {
            @Override
            public RecyclerView.ViewHolder onCreateFooterViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public RecyclerView.ViewHolder onCreateHeaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
                int headerLayout = getArgHeaderLayout();
                if (headerLayout > 0) {
                    return new BaseAdapter.HeaderHolder(layoutInflater, viewGroup, headerLayout);
                }
                return null;
            }
        };
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public View getScrollView() {
        if (isAdded()) {
            Log.d("test", "is added");
            return mRecyclerView;
        }
        return null;
    }

    protected List<String> getSampleData(String title, int count) {
        List<String> data = new ArrayList<>();
        int i = 0;
        for (int n = count; i < n; i++) {
            data.add(String.format("%s %d", title, i));
        }
        return data;
    }

    private int getArgCount() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle.getInt(ARG_COUNT);
        }
        return 0;
    }

    private int getArgHeaderLayout() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle.getInt(ARG_HEADER_LAYOUT);
        }
        return 0;
    }

    private String getArgTitle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle.getString(ARG_TITLE);
        }
        return "";
    }

}
