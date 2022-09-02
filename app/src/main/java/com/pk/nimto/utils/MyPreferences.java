package com.pk.nimto.utils;
import android.content.Context;
import android.content.SharedPreferences;

/*
Created by alan on 7/8/21
*/

public class MyPreferences {
    private static String myPrefrences = "BBC_APP_PREFERENCES";

    public  static void setStringPrefrences(String key,String value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(myPrefrences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();

    }
    public static String getStringPrefrences(String key,Context context){
        SharedPreferences preferences = context.getSharedPreferences(myPrefrences, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }
    public static  void setBooleanPrefrences(String key,boolean value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(myPrefrences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public static boolean getBooleanPrefrences(String key,Context context){
        SharedPreferences prefrences = context.getSharedPreferences(myPrefrences, Context.MODE_PRIVATE);
        return prefrences.getBoolean(key, false);
    }

    public static  void setIntPrefrences(String key,int value,Context context){
        SharedPreferences preferences = context.getSharedPreferences(myPrefrences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public static int getIntPrefrences(String key,Context context){
        SharedPreferences prefrences = context.getSharedPreferences(myPrefrences, Context.MODE_PRIVATE);
        return prefrences.getInt(key, -1);
    }

    public static void resetAllPreferences(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences(myPrefrences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
