package com.geekapp.materialweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyWeatherRespond {

    /**
     * city : {"id":2643743,"name":"London","coord":{"lon":-0.12574,"lat":51.50853},"country":"GB","population":0}
     * cod : 200
     * message : 0.0338
     * cnt : 5
     * list : [
     * {"dt":1444820400,"temp":{"day":13.17,"min":6.91,"max":13.17,"night":9.72,"eve":11.31,"morn":6.91},"pressure":1029.27,"humidity":0,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":6.22,"deg":16,"clouds":33,"rain":0.22},
     * {"dt":1444906800,"temp":{"day":11.94,"min":5.3,"max":12.86,"night":11.11,"eve":11.56,"morn":5.3},"pressure":1030.07,"humidity":88,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":3.3,"deg":3,"clouds":44},
     * {"dt":1444993200,"temp":{"day":13.23,"min":10.4,"max":13.23,"night":10.4,"eve":11.56,"morn":11.18},"pressure":1030.79,"humidity":81,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":7.01,"deg":16,"clouds":88},
     * {"dt":1445079600,"temp":{"day":12.29,"min":9.92,"max":12.58,"night":9.92,"eve":11.47,"morn":10.62},"pressure":1029.28,"humidity":84,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":6.46,"deg":11,"clouds":92,"rain":0.61},
     * {"dt":1445166000,"temp":{"day":13.33,"min":8.53,"max":13.33,"night":8.53,"eve":11.4,"morn":9.76},"pressure":1026.05,"humidity":0,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":4.26,"deg":52,"clouds":51,"rain":0.41}]
     */

    @SerializedName("city")
    public City city;
    @SerializedName("cod")
    public int code;
    @SerializedName("message")
    public double message;
    @SerializedName("cnt")
    public int cnt;
    @SerializedName("list")
    public List<CityEntity> cityEntity;

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

    public class CityEntity {
        //{"dt":1444820400,"temp":{"day":13.17,"min":6.91,"max":13.17,"night":9.72,"eve":11.31,"morn":6.91},"pressure":1029.27,"humidity":0,
        // "weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"speed":6.22,"deg":16,"clouds":33,"rain":0.22},
        @SerializedName("dt")
        public long dt;
        @SerializedName("temp")
        public Temp temp;
        @SerializedName("pressure")
        public double pressure;
        @SerializedName("humidity")
        public double humidity;
        @SerializedName("weather")
        public List<Weather> weather;
        @SerializedName("speed")
        public double speed;
        @SerializedName("deg")
        public int deg;
        @SerializedName("clouds")
        public int clouds;
        @SerializedName("rain")
        public double rain;
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

    public class Temp {
        //"temp":{"day":13.17,"min":6.91,"max":13.17,"night":9.72,"eve":11.31,"morn":6.91}
        @SerializedName("day")
        public double day;
        @SerializedName("min")
        public double min;
        @SerializedName("max")
        public double max;
        @SerializedName("night")
        public double night;
        @SerializedName("eve")
        public double eve;
        @SerializedName("morn")
        public double morn;
    }
}
