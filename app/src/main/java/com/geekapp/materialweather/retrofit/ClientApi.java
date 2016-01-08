package com.geekapp.materialweather.retrofit;

import com.geekapp.materialweather.model.CurWeatherResponse;
import com.geekapp.materialweather.model.DailyWeatherRespond;
import com.geekapp.materialweather.model.DayHourWeatherRespond;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface ClientApi {

    public static final String API_URL = "http://api.openweathermap.org/data/2.5";
    public static final String APPID = "c80bd8332dc294a3b678a42c4ca97e2c";
    public static final String LAN_EN = "en";
    public static final String LAN_CN = "zh_cn";
    public static final String UNITS_M = "metric";
    public static final String UNITS_I = "imperial";

    //http://openweathermap.org/find?q=
    @GET("/find")
    Observable<CurWeatherResponse> findCity(@Query("q") String cityName,String appId);

    //api.openweathermap.org/data/2.5/weather?q=London
    @GET("/weather")
    Observable<CurWeatherResponse> getWeatherByCityName(@Query("q") String cityName, @Query("lang") String language,
                                                        @Query("units") String units, @Query("APPID") String appId);

    //api.openweathermap.org/data/2.5/weather?lat=35&lon=139
    @GET("/weather")
    Observable<CurWeatherResponse> getWeatherByLocate(@Query("lat") double lat, @Query("lon") double lon, @Query("units") String units,
                                                      @Query("lang") String language, @Query("APPID") String appId);


    //http://api.openweathermap.org/data/2.5/forecast/daily?q=London&mode=JSON&units=metric&cnt=5&appid=bd82977b86bf27fb59a04b61b657fb6f
    @GET("/forecast/daily")
    Observable<DailyWeatherRespond> getDailyWeatherByCityName(@Query("q") String cityName, @Query("units") String units,
                                                              @Query("cnt") String cnt, @Query("lang") String language, @Query("APPID") String appId);

    //api.openweathermap.org/data/2.5/forecast/daily?lat=35&lon=139&cnt=10
    @GET("/forecast/daily")
    Observable<DailyWeatherRespond> getDailyWeatherByLocate(@Query("lat") double lat, @Query("lon") double lon, @Query("units") String units,
                                                            @Query("cnt") String cnt, @Query("lang") String language, @Query("APPID") String appId);

    //api.openweathermap.org/data/2.5/forecast?q=London,us&mode=json
    @GET("/forecast")
    Observable<DayHourWeatherRespond> getDayHourWeatherByCityName(@Query("q") String cityName, @Query("units") String units,
                                                                  @Query("lang") String language, @Query("APPID") String appId);

    //api.openweathermap.org/data/2.5/forecast?lat=35&lon=139
    @GET("/forecast")
    Observable<DayHourWeatherRespond> getDayHourWeatherByLocate(@Query("lat") double lat, @Query("lon") double lon, @Query("units") String units,
                                                                @Query("lang") String language, @Query("APPID") String appId);
}
