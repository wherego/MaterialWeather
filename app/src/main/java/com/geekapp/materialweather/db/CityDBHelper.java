package com.geekapp.materialweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.geekapp.materialweather.util.LogUtil;

import java.util.ArrayList;
import java.util.List;


public class CityDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "city.db";
    private static final int DATABASE_Version = 1;
    private static final String TABLE_NAME = "cityInfo";
    private static CityDBHelper instance;
    private static SQLiteDatabase mDatabase;
    private Context mContext;

    public CityDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    public static CityDBHelper getInstance(Context context) {

        if (instance == null) {
            instance = new CityDBHelper(context);
        }
        mDatabase = instance.getWritableDatabase();
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + BaseColumns._ID
                + "  INTEGER PRIMARY KEY autoincrement,"
                + CityColumns.CITY_NAME + " varchar(40),"
                + CityColumns.UPDATE_TIME + " varchar(40),"
                + CityColumns.CUR_WEATHER + " vargraphic(10000),"
                + CityColumns.DAILY_WEATHER + " vargraphic(10000),"
                + CityColumns.DAY_HOUR_WEATHER + " vargraphic(10000),"
                + CityColumns.CITY_IMAGE + " varchar(100)" + ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = " DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }

    public List<CityInfo> getCitiesInfo() {
        SQLiteDatabase db = instance.getWritableDatabase();
        ArrayList<CityInfo> infoList = new ArrayList<CityInfo>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, CityColumns._ID + " desc");
        if (cursor == null) {
            return infoList;
        }
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                CityInfo info = new CityInfo();
                info.setCityName(cursor.getString(cursor.getColumnIndex(CityColumns.CITY_NAME)));
                info.setUpdateTime(cursor.getString(cursor.getColumnIndex(CityColumns.UPDATE_TIME)));
                info.setCurWeather(cursor.getString(cursor.getColumnIndex(CityColumns.CUR_WEATHER)));
                info.setDailyWeather(cursor.getString(cursor.getColumnIndex(CityColumns.DAILY_WEATHER)));
                info.setDayHourWeather(cursor.getString(cursor.getColumnIndex(CityColumns.DAY_HOUR_WEATHER)));
                info.setCityImage(cursor.getString(cursor.getColumnIndex(CityColumns.CITY_IMAGE)));
                infoList.add(info);
            }
        }

        cursor.close();
        //db.close();
        return infoList;
    }

    public String getCityDailyInfoByName(String name) {
        SQLiteDatabase db = instance.getWritableDatabase();
        String dailyWeather = "";
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, CityColumns._ID + " desc");
        if (cursor == null) {
            return "";
        }
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (name.equals(cursor.getString(cursor.getColumnIndex(CityColumns.CITY_NAME)))) {
                    dailyWeather = cursor.getString(cursor.getColumnIndex(CityColumns.DAILY_WEATHER));
                    LogUtil.d("name:" + name + dailyWeather);
                    break;
                }
            }
        }

        cursor.close();
        //db.close();
        return dailyWeather;
    }

    public boolean dataTypeExist(String name) {

        SQLiteDatabase db = instance.getWritableDatabase();
        Boolean flag = false;
        Cursor cursor = db.query(TABLE_NAME, null, CityColumns.CITY_NAME
                + "=?", new String[]{name}, null, null, null);
        flag = cursor.moveToFirst();
        LogUtil.d(flag.toString());
        cursor.close();

        return flag;
    }

    public void commitCityInfoByType(String name, String data, String type) {

        if (dataTypeExist(name)) {
            updateCityInfoByType(name, data, type);
        } else {
            addCityInfoByType(name, data, type);
        }
    }

    public void addCityInfoByType(String name, String data, String type) {
        SQLiteDatabase db = instance.getWritableDatabase();
        LogUtil.d("add data");
        ContentValues cv = new ContentValues();
        cv.put(CityColumns.CITY_NAME, name);
        if (data != null) {
            cv.put(type, data);
        }
        db.insert(TABLE_NAME, null, cv);
    }

    public void updateCityInfoByType(String name, String data, String type) {
        SQLiteDatabase db = instance.getWritableDatabase();
        LogUtil.d("add data");
        ContentValues cv = new ContentValues();
        cv.put(CityColumns.CITY_NAME, name);
        cv.put(type, data);
        db.update(TABLE_NAME, cv, CityColumns.CITY_NAME + "=?", new String[]{name});
    }

    public void deleteCityInfoByName(String name) {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.delete(TABLE_NAME, CityColumns.CITY_NAME + "=?", new String[]{name});
    }

    public static class CityColumns implements BaseColumns {
        public static final String CITY_NAME = "cityName";
        public static final String UPDATE_TIME = "updateTime";
        public static final String CUR_WEATHER = "curWeather";
        public static final String DAILY_WEATHER = "dailyWeather";
        public static final String DAY_HOUR_WEATHER = "dayHourWeather";
        public static final String CITY_IMAGE = "cityImage";
    }
}
