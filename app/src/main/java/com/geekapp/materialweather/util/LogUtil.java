package com.geekapp.materialweather.util;

import android.util.Log;

import com.geekapp.materialweather.BuildConfig;

/**
 * Created by james.li on 2015/9/29.
 */
public class LogUtil {

    /**
     * @return 当前的类名(simpleName)
     */
    private static String getClassName() {
        String result;
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1, result.length());
        return result;
    }

    /**
     * @return 当前的类名（全名）
     */
    private static String getFullClassName() {
        String result;
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[1];
        result = thisMethodStack.getClassName();
        return result;
    }

    /**
     * ver log
     *
     * @param msg
     */
    public static void v(String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(getClassName(), msg);
        }
    }

    /**
     * debug log
     *
     * @param msg
     */
    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(getClassName(), msg);
        }
    }

    /**
     * warning log
     *
     * @param msg
     */
    public static void w(String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(getClassName(), msg);
        }
    }

    /**
     * info log
     *
     * @param msg
     */
    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(getClassName(), msg);
        }
    }

    /**
     * error log
     *
     * @param msg
     */
    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(getClassName(), msg);
        }
    }
}
