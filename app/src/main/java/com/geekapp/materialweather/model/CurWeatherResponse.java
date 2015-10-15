package com.geekapp.materialweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurWeatherResponse {

    /**
     * coord : {"lon":114.07,"lat":22.55}
     * weather : [{"id":502,"main":"Rain","description":"heavy intensity rain","icon":"10d"}]
     * base : stations
     * main : {"temp":301.64,"pressure":1017,"humidity":61,"temp_min":294.26,"temp_max":308.71}
     * visibility : 8000
     * wind : {"speed":4.1,"deg":20}
     * rain : {"1h":16}
     * clouds : {"all":20}
     * dt : 1444717387
     * sys : {"type":1,"id":7904,"message":0.0161,"country":"CN","sunrise":1444688354,"sunset":1444730432}
     * id : 1795565
     * name : Shenzhen
     * cod : 200
     */

    @SerializedName("coord")
    public Coord coord;
    @SerializedName("weather")
    public List<Weather> weather;
    @SerializedName("base")
    public String base;
    @SerializedName("main")
    public Main main;
    @SerializedName("visibility")
    public int visibility;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("rain")
    public Rain rain;
    @SerializedName("clouds")
    public Cloud cloud;
    @SerializedName("dt")
    public long dt;
    @SerializedName("sys")
    public Sys sys;
    @SerializedName("name")
    public String name;

    public class Coord {
        @SerializedName("lon")
        public double lon;
        @SerializedName("lat")
        public double lat;
    }

    public class Weather {
        //  * weather : [{"id":502,"main":"Rain","description":"heavy intensity rain","icon":"10d"}]
        @SerializedName("id")
        public int id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;
    }

    public class Main {
        //main : {"temp":301.64,"pressure":1017,"humidity":61,"temp_min":294.26,"temp_max":308.71}
        @SerializedName("temp")
        public double temp;
        @SerializedName("pressure")
        public int pressure;
        @SerializedName("humidity")
        public int humidity;
        @SerializedName("temp_min")
        public double temp_min;
        @SerializedName("temp_max")
        public double temp_max;
    }

    public class Wind {
        //wind : {"speed":4.1,"deg":20}
        @SerializedName("speed")
        public double speed;
        @SerializedName("deg")
        public double deg;
    }

    public class Rain {
        @SerializedName("1h")
        public double rain;
    }

    public class Sys {
        //sys : {"type":1,"id":7904,"message":0.0161,"country":"CN","sunrise":1444688354,"sunset":1444730432}
        @SerializedName("type")
        public int type;
        @SerializedName("id")
        public int id;
        @SerializedName("message")
        public double message;
        @SerializedName("country")
        public String country;
        @SerializedName("sunrise")
        public long sunrise;
        @SerializedName("sunset")
        public long sunset;
    }

    public class Cloud {
        @SerializedName("all")
        public int all;
    }
}

