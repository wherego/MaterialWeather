package com.geekapp.materialweather.retrofit;

import com.geekapp.materialweather.model.WeatherResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface ClientApi {
    public static final String API_URL = "http://api.openweathermap.org/data/2.5";
    public static final String APPID = "c80bd8332dc294a3b678a42c4ca97e2c";

    //use Retrofit and RxJava
    @GET("/weather")
    Observable<WeatherResponse> getWeather(@Query("q") String cityName, @Query("APPID") String appId);

    //just use Retrofit
    @GET("/weather")
    void getWeather(@Query("q") String cityName, @Query("APPID") String appId, Callback<WeatherResponse> callback);
}
