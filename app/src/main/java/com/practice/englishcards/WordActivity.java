package com.practice.englishcards;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.practice.englishcards.adapter.RvWordAdapter;
import com.practice.englishcards.module.DataEntry;
import com.practice.englishcards.utils.SpeakUtil;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class WordActivity extends AppCompatActivity {

    private RecyclerView rvWord;
    private List<DataEntry.Vocabulary> word;
    private RvWordAdapter adapter;
    private SpeakUtil util;
    private ArrayList<String> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        rvWord = findViewById(R.id.rv_word);
        word = (List<DataEntry.Vocabulary>) getIntent().getSerializableExtra("class");
        util = SpeakUtil.getIns(getApplicationContext());

        for (int i = 0; i < word.size(); i++) {
            options.add(word.get(i).Ch);
        }
        KLog.i("jojo", "word :" + word.size());
        KLog.i("jojo", "options :" + options.size());
        adapter = new RvWordAdapter(word);
        adapter.setPlayListener(new RvWordAdapter.OnPlayListener() {
            @Override
            public void onPlayListener(View v, int position) {
                String speech = word.get(position).En;
                KLog.i("", "eng" + speech.toString());
                util.speakEn(speech);
            }
        });

        adapter.setOnAnswerListener(new RvWordAdapter.onAnswerListener() {
            @Override
            public void showOptionDialog(int position) {
                DataEntry.Vocabulary vocabulary = word.get(position);
                String answer = vocabulary.Ch;
                ArrayList<String> currentOptions = initAnswerOpts(answer);
                KLog.e("UUUU", "currentOptions" + currentOptions.toString());
                if (optionDialog == null || !optionDialog.isShowing()) {
                    setOptionsDialog(sandBoxOpts(answer, currentOptions), vocabulary);
                }
            }
        });

        rvWord.setLayoutManager(new LinearLayoutManager(this));
        rvWord.setAdapter(adapter);
    }

    private ArrayList<String> initAnswerOpts(String answer) {
        ArrayList<String> currentOptions = new ArrayList<>();
        currentOptions.addAll(options);
        currentOptions.remove(answer);
        return currentOptions;
    }

    private ArrayList<String> sandBoxOpts(String answer, ArrayList<String> currentOptions) {
        Random rand = new Random();
        Set<String> choices = new HashSet<String>();
        choices.add(answer);
        while (choices.size() < 4) {
            int i = rand.nextInt(4);
            choices.add(currentOptions.get(i));
        }
        KLog.e("UUUU", "choices" + choices.toString() + choices.size());

        ArrayList<String> setToArray = new ArrayList<>();

        setToArray.addAll( new ArrayList<String>(choices));
        KLog.e("UUUU", "choices" + setToArray.toString() + setToArray.size());
        Collections.shuffle(setToArray);
        KLog.e("UUUU", "choices" + setToArray.toString() + " = ");
        return setToArray;
    }

    private AlertDialog optionDialog;

    public void setOptionsDialog(ArrayList<String> options, final DataEntry.Vocabulary vocabulary) {
        final View viewOptions = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.dialog_options, null);
        final RadioGroup rgOptions = viewOptions.findViewById(R.id.rg_options);
        final RadioButton rBtn1 = viewOptions.findViewById(R.id.rBtn_opt1);
        final RadioButton rBtn2 = viewOptions.findViewById(R.id.rBtn_opt2);
        final RadioButton rBtn3 = viewOptions.findViewById(R.id.rBtn_opt3);
        final RadioButton rBtn4 = viewOptions.findViewById(R.id.rBtn_opt4);

        rBtn1.setText(options.get(0));
        rBtn2.setText(options.get(1));
        rBtn3.setText(options.get(2));
        rBtn4.setText(options.get(3));

        rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selectedId = group.getCheckedRadioButtonId();
                RadioButton rBtnSelected = (RadioButton) viewOptions.findViewById(selectedId);
                boolean isCurrent = rBtnSelected.getText().equals(vocabulary.Ch);
                if (isCurrent) {
                    String currentDate = new SimpleDateFormat("MM/dd", Locale.getDefault()).format(new Date());
                    KLog.e("TAG", "days: " + currentDate);
                    vocabulary.setStudyDay(currentDate);
//                    adapter.setDays(vocabulary.getStudyDay());
                    util.speakEn(vocabulary.En);
                }else {
                    util.speakCH("GG 答錯了, 答案是" + vocabulary.Ch);
                }
                String toast = (isCurrent) ? "答對了" : "GG 答錯了, 答案是" + vocabulary.Ch;
                Toast.makeText(WordActivity.this, toast, Toast.LENGTH_LONG).show();
                optionDialog.dismiss();

            }
        });
        optionDialog = new AlertDialog.Builder(this)
                .setView(viewOptions)
                .setTitle(vocabulary.En)
                .setIcon(R.drawable.ic_g_translate_black_24dp)
                .create();
        if (!optionDialog.isShowing()) {
            optionDialog.show();
        }
    }
}
