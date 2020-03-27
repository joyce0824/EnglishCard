package com.practice.englishcards.words;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.practice.englishcards.R;
import com.practice.englishcards.adapter.Interfaces;
import com.practice.englishcards.adapter.RvClassAdapter;
import com.practice.englishcards.calendar.CalendarActivity;
import com.practice.englishcards.db.RealtimeDBManager;
import com.practice.englishcards.db.RealtimeDBUtil;
import com.practice.englishcards.db.entry.Classes;

import com.practice.englishcards.db.entry.WordItem;
import com.practice.englishcards.utils.SpeakUtil;

import com.socks.library.KLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IMainView {

    private RecyclerView rvClass;
    private RvClassAdapter adapter;
    private List<Classes> dbEntries = new ArrayList<>();
    private SpeakUtil util;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getDB();
        KLog.init(true);
    }

    private void init() {
        rvClass = findViewById(R.id.rv_class);
        util = SpeakUtil.getIns(getApplicationContext());
    }

    private void getDB() {
        RealtimeDBManager dbManager = new RealtimeDBManager(this);
        dbManager.getClasses();
    }

//    private void dummyData() {
//        List<String > fileName = new ArrayList<>();
//        try {
//            fileName = AssetsUtil.getFileNames(getApplicationContext(),"");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for(String s :fileName) {
//            KLog.i("JOJO", "fileName :" + s);
//            if(s.contains(".json")) {
//                String jsonFileString = AssetsUtil.getJsonFromAssets(getApplicationContext(), s);
//                ClassItem item = GsonUtil.fromJson(jsonFileString, ClassItem.class);
//                entries.add(item);
//            }
//        }
//    }

    private void setRvClass() {
        adapter = new RvClassAdapter(dbEntries);
        adapter.setPlayListener(new Interfaces.OnPlayListener() {
            @Override
            public void onPlayListener(View v, int position) {
                List speech = dbEntries.get(position).getData();
                util.speak(speech);
            }
        });

        adapter.setOnItemClickListener(new Interfaces.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                List<WordItem> word = dbEntries.get(position).getData();
//                Intent it = new Intent(MainActivity.this, WordActivity.class) ;
                Intent it = new Intent(MainActivity.this, ExamActivity.class);
                it.putExtra("class", (Serializable) word);
                it.putExtra("classesName", dbEntries.get(position).getName());
                startActivity(it);
            }
        });

        adapter.setOnBugClickListener(new Interfaces.OnBugClickListener() {
            @Override
            public void onBugClick(View v, int position) {
                Map<String, ArrayList<WordItem>> word = dbEntries.get(position).getExam();
                Intent it = new Intent(MainActivity.this, CalendarActivity.class);
                it.putExtra("class", (Serializable) word);
                startActivity(it);
            }
        });
        rvClass.setLayoutManager(new LinearLayoutManager(this));
        rvClass.setAdapter(adapter);
    }

    @Override
    public void show(List<Classes> list) {
        dbEntries.clear();
        dbEntries.addAll(list);
        if (adapter == null) {
            setRvClass();
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(Object msg) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        util.shutdown();
    }
}
