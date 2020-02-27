package com.practice.englishcards.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * 參考網址
 * https://segmentfault.com/a/1190000009523164
 *
 */

public class GsonUtil {

    public GsonUtil() {
    }

    public static <T>T fromJson(String json,Class<T> type){
        Gson gson = new Gson();
        return gson.fromJson(json,type);
    }

    public static <T> ArrayList<T> listFromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<ArrayList<T>>(){}.getType());
    }
}
