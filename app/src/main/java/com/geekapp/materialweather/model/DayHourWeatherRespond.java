package com.geekapp.materialweather.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DayHourWeatherRespond {

    @SerializedName("city")
    public City city;
    @SerializedName("cod")
    public String cod;
    @SerializedName("message")
    public double message;
    @SerializedName("cnt")
    public int cnt;
    @SerializedName("list")
    public List<ListEntity> list;

    public static class ListEntity {
        @SerializedName("dt")
        public long dt;
        @SerializedName("main")
        public MainEntity main;
        @SerializedName("clouds")
        public CloudsEntity clouds;
        @SerializedName("wind")
        public WindEntity wind;
        @SerializedName("sys")
        public SysEntity sys;
        @SerializedName("dt_txt")
        public String dt_txt;
        @SerializedName("weather")
        public List<WeatherEntity> weather;
    }

    public static class MainEntity {
        @SerializedName("temp")
        public double temp;
        @SerializedName("temp_min")
        public double temp_min;
        @SerializedName("temp_max")
        public double temp_max;
        @SerializedName("pressure")
        public double pressure;
        @SerializedName("sea_level")
        public double sea_level;
        @SerializedName("grnd_level")
        public double grnd_level;
        @SerializedName("humidity")
        public int humidity;
        @SerializedName("temp_kf")
        public double temp_kf;
    }

    public static class SysEntity {
        @SerializedName("pod")
        public String pod;
    }

    public static class WeatherEntity {
        @SerializedName("id")
        public int id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;
    }

    public class City {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("coord")
        public Coord coord;
        @SerializedName("country")
        public String country;
        @SerializedName("population")
        public int population;
    }

    public class Coord {
        @SerializedName("lon")
        public double lon;
        @SerializedName("lat")
        public double lat;
    }

    public class CloudsEntity {
        @SerializedName("all")
        public int all;
    }

    public class WindEntity {
        @SerializedName("speed")
        public double speed;
        @SerializedName("deg")
        public double deg;
    }
}
