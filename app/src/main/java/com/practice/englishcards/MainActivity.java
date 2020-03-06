package com.practice.englishcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.practice.englishcards.adapter.RvClassAdapter;
import com.practice.englishcards.module.DataEntry;
import com.practice.englishcards.utils.GsonUtil;
import com.practice.englishcards.utils.SpeakUtil;
import com.socks.library.KLog;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvClass ;
    private RvClassAdapter adapter ;
    private List<DataEntry> entries;
    private SpeakUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        dummyData();
        setRvClass();
    }

    private void init() {
        KLog.i("JOJO","init");
        rvClass = findViewById(R.id.rv_class);
        util = SpeakUtil.getIns(getApplicationContext());
    }

    private void dummyData(){
        KLog.i("JOJO","dummyData");
        String data = "[{\"className\":\"class_1\",\"data\":[{\"En\":\"place\",\"Ch\":\"地方\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"地方\"]},{\"En\":\"party\",\"Ch\":\"舞會\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"舞會\"]},{\"En\":\"poster\",\"Ch\":\"海報\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"海報\"]},{\"En\":\"present\",\"Ch\":\"禮物\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"禮物\"]},{\"En\":\"fault\",\"Ch\":\"錯誤\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"錯誤\"]},{\"En\":\"idea\",\"Ch\":\"想法\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"想法\"]},{\"En\":\"joke\",\"Ch\":\"笑話\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"笑話\"]},{\"En\":\"question\",\"Ch\":\"問題\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"問題\"]},{\"En\":\"story\",\"Ch\":\"故事\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"故事\"]},{\"En\":\"sentence\",\"Ch\":\"句子\",\"studyDay\":[\"02/03\",\"\"],\"options\":[\"op1\",\"op2\",\"op3\",\"句子\"]}]},{\"className\":\"class_2\",\"data\":[{\"En\":\"American\",\"Ch\":\"美國1\"},{\"En\":\"American\",\"Ch\":\"美國2\"},{\"En\":\"American\",\"Ch\":\"美國3\"},{\"En\":\"American\",\"Ch\":\"美國4\"},{\"En\":\"American\",\"Ch\":\"美國5\"},{\"En\":\"American\",\"Ch\":\"美國6\"},{\"En\":\"American\",\"Ch\":\"美國7\"},{\"En\":\"American\",\"Ch\":\"美國8\"},{\"En\":\"American\",\"Ch\":\"美國9\"},{\"En\":\"American\",\"Ch\":\"美國10\"}]}]";
        entries = GsonUtil.stringToArray(data,DataEntry[].class);
    }

    private void setRvClass() {
        KLog.i("JOJO","setRvClass :"+entries.size() + "entries :"+entries);
        adapter = new RvClassAdapter(entries);

        adapter.setPlayListener(new RvClassAdapter.OnPlayListener() {
            @Override
            public void onPlayListener(View v, int position) {
                List speech = entries.get(position).getData();
                KLog.i("","eng"+ speech.toString());
                util.speak(getApplicationContext(),speech);
            }
        });

        adapter.setOnItemClickListener(new RvClassAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                List<DataEntry.Vocabulary> word = entries.get(position).getData();
                Intent it = new Intent(MainActivity.this, WordActivity.class) ;
                it.putExtra("class", (Serializable) word);
                startActivity(it);
            }
        });

        rvClass.setLayoutManager(new LinearLayoutManager(this));
        rvClass.setAdapter(adapter);
    }
}
