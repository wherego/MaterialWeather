package com.geekapp.materialweather.retrofit;

import com.geekapp.materialweather.model.WeatherResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface ClientApi {
    public static final String API_URL = "http://open.weather.com.cn";
    public static final String APPID = "b82a9c10f8f857eb";
    public static final String INDEX_NORMAL = "index_v";
    public static final String FORECAST_NORMAL = "forecast_v";
    //use Retrofit and RxJava
    @GET("/data/")
    Observable<WeatherResponse> getWeatherData(@Query("areaid") String areaId, @Query("type") String type, @Query("date") String date, @Query("appid") String appId, @Query("key") String key);

    //just use Retrofit
    @GET("/data/")
    void getWeatherData(@Query("areaid") String areaId, @Query("type") String type, @Query("date") String date, @Query("appid") String appId, @Query("key") String key, Callback<WeatherResponse> callback);
}
