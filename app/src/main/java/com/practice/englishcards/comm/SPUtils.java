package com.practice.englishcards.comm;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

public class SPUtils {

    private static final Map<String, SPUtils> SP_UTILS_MAP = new HashMap<>();
    private SharedPreferences sp;

    private SPUtils(Context context, final String spName) {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public static SPUtils getInstance(Context context,String spName) {
        SPUtils spUtils = SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            synchronized (SPUtils.class) {
                spUtils = SP_UTILS_MAP.get(spName);
                if (spUtils == null) {
                    spUtils = new SPUtils(context,spName);
                    SP_UTILS_MAP.put(spName, spUtils);
                }
            }
        }
        return spUtils;
    }

    public String getString(@NonNull final String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public String getString(@NonNull final String key) {
        String defaultValue = "";
        KLog.i("wwww","key:"+ key + "Value :"+sp.getString(key, defaultValue));
        return sp.getString(key, defaultValue);
    }

    public void putString(@NonNull final String key, final String value) {
        sp.edit().putString(key, value).apply();
        KLog.i("wwww","key:"+ key +" = " + value + "Value :"+sp.getString(key, ""));
    }

    public int getInt(@NonNull final String key, int defaultValue ) {
        return sp.getInt(key, defaultValue);
    }

    public int getInt(@NonNull final String key) {
        int defaultValue = -1;
        return sp.getInt(key, defaultValue);
    }

    public void putInt(@NonNull final String key, final int value) {
        sp.edit().putInt(key, value).apply();
    }

    public boolean getBoolean(@NonNull final String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public boolean getBoolean(@NonNull final String key) {
        boolean defaultValue = false;
        return sp.getBoolean(key, defaultValue);
    }

    public void putBoolean(@NonNull final String key, final boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }


    public float getFloat(@NonNull final String key) {
        float defaultValue = 0.0F;
        return sp.getFloat(key, defaultValue);
    }

    public void putFloat(@NonNull final String key, final float value) {
        sp.edit().putFloat(key, value).apply();
    }


}
