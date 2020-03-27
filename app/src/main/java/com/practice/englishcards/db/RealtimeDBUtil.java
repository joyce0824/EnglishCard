package com.practice.englishcards.db;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

/** 官網 ： https://firebase.google.com/docs/database/android/read-and-write
 *
 * FirebaseDatabase : 存放 FirebaseDatabase 的實體
 * DatabaseReference : 存放參考 Firebase 資料庫的節點
 * Realtime database 用監聽的方式，去接收資料
 * MutableList : list 存放資料庫撈下來的資料
 */
public class RealtimeDBUtil {

    private String TAG = RealtimeDBUtil.class.getSimpleName();
    private FirebaseDatabase fireDB;
    private DatabaseReference dbRootRef;

    public RealtimeDBUtil() {
        fireDB = FirebaseDatabase.getInstance();
        dbRootRef = FirebaseDatabase.getInstance().getReference();
    }

    /** 單次監聽
     * addListenerForSingleValueEvent : 方法只會執行一次而且是立即執行
     * -> onDataChange 事件改變時callback，可以得到目前資料庫上的資料
     *
     * @param path :
     *            若為根目錄 ： ref = FirebaseDatabase.getInstance().getReference(); //引導到FirebaseDatabase 最根目錄 englishcards-44d65
     *            若為子目錄 ： ref = FirebaseDatabase.getInstance().getReference(path);
     */
    public void setSingleValueEvent(String path, final IRealtimeCallback callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(path);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                KLog.i("TAG", "dataSnapshot :" + dataSnapshot.exists());
                if (dataSnapshot.exists()) {
                    callback.onSuccess(dataSnapshot);
                }else {
                    callback.onFailed("dataSnapshot is not exists");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailed(databaseError);
            }
        });
    }

    /**
     * 連續監聽，有變化時才會觸發
     * 新增:會觸發 onChildAdded ，再把得到的值從雲端存到我們的 list 中，再刷新畫面。
     * 刪除:會觸發 onChildRemoved ，這裡使用 lambda 去找出那個擁有那個 uid 的 user，然後從 list 中移除，刷新畫面。
     */
    public void setValueEventListener(String path, final IRealtimeCallback callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                KLog.i(TAG,dataSnapshot.getValue());
                if (dataSnapshot.exists()) {
                    callback.onSuccess(dataSnapshot);
                }else {
                    callback.onFailed("dataSnapshot is not exists");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onFailed(databaseError);
            }
        });
    }

    /**
     * 子節點的監聽
     * @param ref
     */
    public void setChildEventListener(DatabaseReference ref, final IRealtimeCallback callback){
            ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                KLog.i(TAG,dataSnapshot + " = " + s);
                if (dataSnapshot.exists()) {
                    callback.onSuccess(dataSnapshot);
                }else {
                    callback.onFailed("dataSnapshot is not exists");
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                KLog.i(TAG,dataSnapshot + " = " + s);
                if (dataSnapshot.exists()) {
                    callback.onSuccess(dataSnapshot);
                }else {
                    callback.onFailed("dataSnapshot is not exists");
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                KLog.i(TAG,dataSnapshot );
                if (dataSnapshot.exists()) {
                    callback.onSuccess(dataSnapshot);
                }else {
                    callback.onFailed("dataSnapshot is not exists");
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                KLog.i(TAG,dataSnapshot + " = " + s);
                if (dataSnapshot.exists()) {
                    callback.onSuccess(dataSnapshot);
                }else {
                    callback.onFailed("dataSnapshot is not exists");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                KLog.i(TAG, " error = " + databaseError);
                callback.onFailed(databaseError);
            }
        });
    }

    public void updateValue(String path, int value,
                         final IRealtimeCallback callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childRef = ref.child(path);
        childRef.setValue(value, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                    callback.onFailed(databaseError.getMessage());
                } else {
                    callback.onSuccess("");
                }
            }
        });
    }

    public void updateValue(String path, HashMap<String,String> hashMap, final IRealtimeCallback callback){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childRef = ref.child(path);
        childRef.setValue(hashMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                KLog.i("TAG","databaseError :"+databaseError +"\n:"+databaseReference.toString());
                if (databaseError != null) {
                    callback.onFailed(databaseError.getMessage());
                } else {
                    callback.onSuccess("");
                }
            }
        });
    }

    /**
     * 儲存資料
     *
     * @param columnName
     * @param key
     * @param value
     * @param valueEventListener 連續監聽
     */

    public void SaveDate(String columnName, String key, String value,
                         ValueEventListener valueEventListener) {
        if (columnName != null && key != null) {
            DatabaseReference databaseReference = fireDB.getReference(columnName);
            databaseReference.child(key).setValue(value);
            databaseReference.addValueEventListener(valueEventListener);
        }
    }

    /**
     * 儲存資料
     *
     * @param key
     * @param columnName
     * @param value
     * @param valueEventListener 連續監聽
     */
    public void SaveDateSingleValueEvent(String columnName, String key, String value,
                                         ValueEventListener valueEventListener) {
        if (columnName != null && key != null) {
            DatabaseReference databaseReference = fireDB.getReference(columnName);
            databaseReference.child(key).setValue(value);
            databaseReference.addListenerForSingleValueEvent(valueEventListener);
        }
    }





    /**
     * 移除全部的資料
     */
    private void clearAll() {
        dbRootRef.removeValue();
    }


    //show on Activity
//    private void show() {
//        iview.show(list);
//    }

    public interface IRealtimeCallback{
        void onSuccess(Object o); //dataSnapshot
        void onFailed(Object s);
    }
}
