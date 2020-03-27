package com.practice.englishcards.db;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.practice.englishcards.db.entry.Classes;
import com.practice.englishcards.words.IMainView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Root englishcards-44d65
 * |- Classes
 *      |-data
 *          -| WordItem
 *      |-ExamItem
 *
 */
public class RealtimeDBManager {
    private IMainView mainView;
    private RealtimeDBUtil dbUtil;

    public RealtimeDBManager(IMainView mainView) {
        this.mainView = mainView;
        dbUtil = new RealtimeDBUtil();
    }

    /**
     * get class
     */
    public void getClasses(){
        dbUtil.setSingleValueEvent("", new RealtimeDBUtil.IRealtimeCallback() {
            @Override
            public void onSuccess(Object o) {
                mainView.show(getList((DataSnapshot) o));
            }

            @Override
            public void onFailed(Object s) {
                mainView.showError(s);
            }
        });

        dbUtil.setValueEventListener("", new RealtimeDBUtil.IRealtimeCallback() {
            @Override
            public void onSuccess(Object o) {
                mainView.show(getList((DataSnapshot) o));
            }

            @Override
            public void onFailed(Object s) {
                mainView.showError(s);
            }
        });
    }

    private List<Classes> getList(DataSnapshot snapshot) {
        List<Classes> list = new ArrayList<>();
        Map<String, String> td = (HashMap<String, String>) snapshot.getValue();
        KLog.i("TAG", "td:" + td.keySet());
        for (String key : td.keySet()) {
            Classes c = new Classes(snapshot.child(key));
            list.add(c);
        }

        return list;
    }

    /**
     * get data
     */

    /**
     * update data
     */
    public void updateFrequency(String classesName,String key, int value){
        String path = classesName+"/data/"+key+"/frequency";
        dbUtil.updateValue(path, value, new RealtimeDBUtil.IRealtimeCallback() {
            @Override
            public void onSuccess(Object o) {
                KLog.i("TAG","frequency onSuccess"+o);
            }

            @Override
            public void onFailed(Object s) {
                KLog.i("TAG","frequency onFailed"+s);
            }
        });

    }

    /**
     * get exam
     */

    /**
     * update exam
     */
    public void updateExam(String classesName, String key, HashMap<String, String> map){
        String path = classesName +"/exam/"+ key;
        //        Map<String, User> users = new HashMap<>();
//        users.put("alanisawesome", new User("June 23, 1912", "Alan Turing"));
//        users.put("gracehop", new User("December 9, 1906", "Grace Hopper"));

        dbUtil.updateValue(path, map, new RealtimeDBUtil.IRealtimeCallback() {
            @Override
            public void onSuccess(Object o) {
                KLog.i("TAG","onSuccess"+o);
            }

            @Override
            public void onFailed(Object s) {
                KLog.i("TAG","onFailed"+s);
            }
        });


    }
}
