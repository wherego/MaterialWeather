package com.geekapp.materialweather.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekapp.materialweather.R;
import com.geekapp.materialweather.adapter.SimpleAdapter;
import com.geekapp.materialweather.db.CityDBHelper;
import com.geekapp.materialweather.model.CurWeatherResponse;
import com.geekapp.materialweather.model.DailyWeatherRespond;
import com.geekapp.materialweather.model.DayHourWeatherRespond;
import com.geekapp.materialweather.retrofit.ClientApi;
import com.geekapp.materialweather.retrofit.ServiceGenerator;
import com.geekapp.materialweather.util.CommonUtil;
import com.geekapp.materialweather.util.LogUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.henrytao.recyclerview.BaseAdapter;
import me.henrytao.recyclerview.SimpleRecyclerViewAdapter;
import me.henrytao.smoothappbarlayout.utils.ResourceUtils;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, ObservableScrollView {

    private static final String ARG_HEADER_LAYOUT = "ARG_HEADER_LAYOUT";
    private static final String ARG_TITLE = "ARG_TITLE";
    public RecyclerView.Adapter mAdapter;
    public SimpleAdapter mSimpleAdapter;
    public List<DailyWeatherRespond.CityEntity> dailyList = new ArrayList<>();
    public CityDBHelper mDatabase;
    @Bind(android.R.id.list)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    public WeatherFragment() {
    }

    public static WeatherFragment newInstance(String title) {
        return WeatherFragment.newInstance(title, 0);
    }

    public static WeatherFragment newInstance(String title, @LayoutRes int headerLayout) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        bundle.putInt(ARG_HEADER_LAYOUT, headerLayout);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_weather;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUIViews();
        //getWeatherOfCurCity();
    }

    @Override
    public void onResume() {
        super.onResume();
        String data = mDatabase.getCityDailyInfoByName(getArgTitle());
        if (mDatabase.dataTypeExist(getArgTitle()) && !TextUtils.isEmpty(data)) {
            dailyList.clear();
            LogUtil.d("data is not empty");
            dailyList = new Gson().fromJson(data, DailyWeatherRespond.class).cityEntity;
            mSimpleAdapter.setData(dailyList);
        } else {
            LogUtil.d("data is empty");
            mSwipeRefreshLayout.setRefreshing(true);
            getWeatherOfCurCity();
        }
    }

    public void initUIViews(){

        mSimpleAdapter = new SimpleAdapter<>(getActivity(), dailyList, null);
        mAdapter = new SimpleRecyclerViewAdapter(mSimpleAdapter) {
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
        mRecyclerView.setAdapter(mAdapter);

        int actionBarSize = ResourceUtils.getActionBarSize(getContext());
        int progressViewStart = getResources().getDimensionPixelSize(R.dimen.app_bar_height) - actionBarSize;
        int progressViewEnd = progressViewStart + (int) (actionBarSize * 1.5f);
        mSwipeRefreshLayout.setProgressViewOffset(true, progressViewStart, progressViewEnd);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //add swipe icon background color
        mSwipeRefreshLayout.setColorSchemeColors(android.R.color.holo_purple, android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);
        //mSwipeRefreshLayout.setRefreshing(true);

        //database
        mDatabase = CityDBHelper.getInstance(getActivity());
    }

    @Override
    public void onRefresh() {
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isAdded() && mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 3000);*/
        dailyList.clear();
        mSimpleAdapter.setData(dailyList);
        getWeatherOfCurCity();
    }

    @Override
    public View getScrollView() {
        if (isAdded()) {
            return mRecyclerView;
        }
        return null;
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

    public void getWeatherOfCurCity() {
        ClientApi clientApi = ServiceGenerator.createService(ClientApi.class, ClientApi.API_URL);
        //get cur weather
        /*clientApi.getWeatherByCityName(getArgTitle(), ClientApi.LAN_CN, ClientApi.UNITS_M, ClientApi.APPID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurWeatherResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CurWeatherResponse curWeatherResponse) {
                        Log.i("App", "description:" + curWeatherResponse.weather.get(0).description);
                        Log.i("App", "lat:" + curWeatherResponse.coord.lat + "lon:" + curWeatherResponse.coord.lon);
                        Log.i("App", "city:" + curWeatherResponse.name);
                    }
                });*/
        //get daily weather
        clientApi.getDailyWeatherByCityName(getArgTitle(), ClientApi.UNITS_M, "5", ClientApi.LAN_CN, ClientApi.APPID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyWeatherRespond>() {
                    @Override
                    public void onCompleted() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        LogUtil.d("get daily weather error");
                    }

                    @Override
                    public void onNext(DailyWeatherRespond dailyWeatherRespond) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mSimpleAdapter.setData(dailyWeatherRespond.cityEntity);
                        mDatabase.commitCityInfoByType(getArgTitle(), new Gson().toJson(dailyWeatherRespond), CityDBHelper.CityColumns.DAILY_WEATHER);

                        LogUtil.d(dailyWeatherRespond.city.name + CommonUtil.formatDayAndMonth(dailyWeatherRespond.cityEntity.get(0).dt));
                    }
                });
        //get day hour weather
        /*clientApi.getDayHourWeatherByCityName(getArgTitle(), ClientApi.UNITS_M, ClientApi.LAN_CN, ClientApi.APPID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DayHourWeatherRespond>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("get day hour weather error");
                    }

                    @Override
                    public void onNext(DayHourWeatherRespond dayHourWeatherRespond) {
                        LogUtil.d(dayHourWeatherRespond.city.name + dayHourWeatherRespond.list.get(0).dt_txt);
                    }
                });*/
    }


}
