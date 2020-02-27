package com.practice.englishcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.practice.englishcards.adapter.RvClassAdapter;
import com.practice.englishcards.databinding.ActivityMainBinding;
import com.practice.englishcards.utils.GsonUtil;
import com.practice.englishcards.utils.SpeakUtil;
import com.socks.library.KLog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvClass ;
    private RvClassAdapter adapter ;
    private ArrayList<DataEntry> entries;
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
        rvClass = findViewById(R.id.rv_class);
        util = SpeakUtil.getIns();
    }

    private void dummyData(){
        String data = "[{\"className\":\"class_1\",\"data\":[{\"En\":\"American\",\"Ch\":\"美國1\"},{\"En\":\"American\",\"Ch\":\"美國2\"},{\"En\":\"American\",\"Ch\":\"美國3\"},{\"En\":\"American\",\"Ch\":\"美國4\"},{\"En\":\"American\",\"Ch\":\"美國5\"},{\"En\":\"American\",\"Ch\":\"美國6\"},{\"En\":\"American\",\"Ch\":\"美國7\"},{\"En\":\"American\",\"Ch\":\"美國8\"},{\"En\":\"American\",\"Ch\":\"美國9\"},{\"En\":\"American\",\"Ch\":\"美國10\"}]},{\"className\":\"class_2\",\"data\":[{\"En\":\"American\",\"Ch\":\"美國1\"},{\"En\":\"American\",\"Ch\":\"美國2\"},{\"En\":\"American\",\"Ch\":\"美國3\"},{\"En\":\"American\",\"Ch\":\"美國4\"},{\"En\":\"American\",\"Ch\":\"美國5\"},{\"En\":\"American\",\"Ch\":\"美國6\"},{\"En\":\"American\",\"Ch\":\"美國7\"},{\"En\":\"American\",\"Ch\":\"美國8\"},{\"En\":\"American\",\"Ch\":\"美國9\"},{\"En\":\"American\",\"Ch\":\"美國10\"}]}]";
        entries = GsonUtil.listFromJson(data);
    }

    private void setRvClass() {
        adapter = new RvClassAdapter(entries);
        adapter.setPlayListener(new RvClassAdapter.OnPlayListener() {
            @Override
            public void onPlayListener(View v, int position) {
                List<DataEntry.Vocabulary> speech = entries.get(position).getData();
                ArrayList<String> eng = new ArrayList<>();
                for(DataEntry.Vocabulary s : speech){
                    eng.add(s.En);
                }
                KLog.i("","eng"+ eng.toString());
                util.speak(getApplicationContext(),eng.toString());
            }
        });

        rvClass.setAdapter(adapter);
    }


}
