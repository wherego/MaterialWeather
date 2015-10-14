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
import com.geekapp.materialweather.model.WeatherResponse;
import com.geekapp.materialweather.retrofit.ClientApi;
import com.geekapp.materialweather.retrofit.ServiceGenerator;
import com.geekapp.materialweather.util.CommonUtil;
import com.geekapp.materialweather.util.Encrypt;
import com.geekapp.materialweather.util.LogUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.henrytao.recyclerview.BaseAdapter;
import me.henrytao.recyclerview.SimpleRecyclerViewAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        initUIViews();
    }

    public void initUIViews(){
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
        getWeatherRequest();
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


    public void getWeatherRequest1(){

        ClientApi clientApi = ServiceGenerator.createService(ClientApi.class, ClientApi.API_URL);
        String date = CommonUtil.formatTimeNormal(new Date());
        String areaId = "101010100";
        clientApi.getWeatherData(areaId,ClientApi.FORECAST_NORMAL,date,ClientApi.APPID, Encrypt.standardURLEncoder(areaId,ClientApi.FORECAST_NORMAL,date))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.d("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("onError");
                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        LogUtil.d("onNext");
                    }
                });

    }

    public void getWeatherRequest(){

        ClientApi clientApi = ServiceGenerator.createService(ClientApi.class,ClientApi.API_URL);
        String date = CommonUtil.formatTimeNormal(new Date());
        String areaId = "101010100";
        clientApi.getWeatherData(areaId, ClientApi.INDEX_NORMAL, date, ClientApi.APPID, Encrypt.standardURLEncoder(areaId, ClientApi.INDEX_NORMAL, date),
                new Callback<WeatherResponse>() {
                    @Override
                    public void success(WeatherResponse weatherResponse, Response response) {
                        LogUtil.d("success");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        LogUtil.d("Error");
                    }
        });

    }

}
