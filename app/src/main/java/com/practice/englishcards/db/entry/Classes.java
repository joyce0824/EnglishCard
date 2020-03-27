package com.practice.englishcards.db.entry;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.gson.annotations.SerializedName;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * {
 * "name":"class_01",
 * "time":"03/09~03/15",
 * "data":{"baby":"嬰兒","child":"小孩(單)","children":"小孩(複數)","person":"人","stranger":"陌生人","king":"國王","queen":"王后","foot":"腳","feet":"腳(複數)","hair":"頭髮","mouth":"嘴巴","stomach":"胃","tooth":"牙齒","teeth":"牙齒(複數)","arm":"手臂","back":"背部後面","body":"身體"},
 * "exam":{"2020-03-23":{"hair":"頭髮","stomach":"胃"},"2020-03-24":{"hair":"頭髮","stomach":"胃"},"2020-03-25":{"hair":"頭髮","stomach":"胃"}}}
 */
public class Classes {
    public String getTime() {
        return time;
    }

    // annotations : 當php 後台用 下劃線風格時，可以容錯 , 當多種情況同時出現，以最後一個出現的值為準
    @SerializedName(value = "time", alternate = {"start_time"})
    private String time = "";

    public String getName() {
        return name;
    }

    private String name = "";

    public ArrayList<WordItem> getData() {
        return data;
    }

    public Map<String, ArrayList<WordItem>> getExam() {
        return exam;
    }

    private ArrayList<WordItem> data = new ArrayList<>();
    private Map<String, ArrayList<WordItem>> exam = null;

    public Classes(DataSnapshot snapshot) {
        KLog.i(TAG, "snapshot:" + snapshot.getValue());
        this.time = snapshot.child("time").getValue().toString();
        this.name = snapshot.child("name").getValue().toString();
        data = setData(snapshot.child("data"));
        exam = setExam(snapshot.child("exam"));

    }

    private ArrayList<WordItem> setData(DataSnapshot snapshot) {
        Map<String, String> td = (HashMap<String, String>) snapshot.getValue();
        KLog.i("TAG", "td :" + td);
        ArrayList<WordItem> items = new ArrayList<>();
        for (String key : td.keySet()) {
            WordItem group = snapshot.child(key).getValue(WordItem.class);
            items.add(group);
        }
        return items;
    }

    private ArrayList<WordItem> setExamData(DataSnapshot snapshot) {
        Map<String, String> td = (HashMap<String, String>) snapshot.getValue();
        ArrayList<WordItem> items = new ArrayList<>();
        for (String key : td.keySet()) {
            WordItem w = new WordItem(key, td.get(key));
            items.add(w);
        }
        return items;
    }

    private Map<String, ArrayList<WordItem>> setExam(DataSnapshot snapshot) {
        Map<String, String> td = (HashMap<String, String>) snapshot.getValue();
        Map<String, ArrayList<WordItem>> examItems = new HashMap<>();
        for (String key : td.keySet()) {
            ArrayList<WordItem> items = setExamData(snapshot.child(key));
            examItems.put(key, items);
        }
        return examItems;
    }

    @NonNull
    @Override
    public String toString() {
        String log = String.format(Locale.US, "time : %s  ,data : %s , exam : %s", time, data, exam);
        return log;
    }
}
