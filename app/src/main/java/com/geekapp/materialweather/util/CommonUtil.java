package com.geekapp.materialweather.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonUtil {

    public static final String FORMAT_NORMAL = "yyyyMMddHHmmss";
    public static final String FORMAT_DAY_OF_MONTH = "yyyy-MM-dd HH-mm-ss";
    public static final String LOCATE = ",cn";

    public static String formatTimeNormal(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_NORMAL, Locale.getDefault());
        return format.format(date);
    }

    public static String cityNameAppendWithCountry(String cityName) {
        return cityName + LOCATE;
    }

    public static String formatDayAndMonth(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DAY_OF_MONTH, Locale.getDefault());
        return simpleDateFormat.format(new Date((long) 1444897963));
    }
}
