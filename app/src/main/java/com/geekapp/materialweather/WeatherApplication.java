package com.geekapp.materialweather;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.WeathericonsModule;

/**
 * Created by james.li on 2016/1/20.
 */
public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new WeathericonsModule());
    }
}
