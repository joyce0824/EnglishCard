package com.practice.englishcards.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     *  jsonString = ["Android","Java","PHP"]
     */
    public static <T> ArrayList<T> listFromJson(String jsonString){
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<ArrayList<T>>(){}.getType());
    }

    /**
     * [ {},{},{}]
     */
    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }
    /**
     * 物件轉成JSON傳遞
     */
//    public static void toJson(Class<T> type){
//        String strJSON = new Gson().toJson(type);
//    }

    /**
     * JSON轉回JAVA物件時
     */
//    private void toObj(String jsonString, Class<T> type){
//        Class<T> yourClassList = new Gson().fromJson(jsonString, type);
//    }

    /**
     * 轉成集合
     */
//    private void toList(String jsonString){
//        Type listType = new TypeToken<ArrayList<T>>(){}.getType();
//        List<T> yourClassList = new Gson().fromJson(jsonString, listType);
//    }

    // 处理 data 为 object 的情况
    public static <T> Result<T> fromJsonObject(Reader reader, Class<T> clazz) {}
    // 处理 data 为 array 的情况
    public static <T> Result<List<T>> fromJsonArray(Reader reader, Class<T> clazz){}
}
