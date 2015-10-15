package com.geekapp.materialweather.db;

/**
 * Created by james.li on 2015/10/15.
 */
public class CityInfo {
    private String cityName;
    private String updateTime;
    private String cityImage;
    private String curWeather;
    private String dailyWeather;
    private String dayHourWeather;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCityImage() {
        return cityImage;
    }

    public void setCityImage(String cityImage) {
        this.cityImage = cityImage;
    }

    public String getCurWeather() {
        return curWeather;
    }

    public void setCurWeather(String curWeather) {
        this.curWeather = curWeather;
    }

    public String getDailyWeather() {
        return dailyWeather;
    }

    public void setDailyWeather(String dailyWeather) {
        this.dailyWeather = dailyWeather;
    }

    public String getDayHourWeather() {
        return dayHourWeather;
    }

    public void setDayHourWeather(String dayHourWeather) {
        this.dayHourWeather = dayHourWeather;
    }
}
