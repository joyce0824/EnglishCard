package com.practice.englishcards.words;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.practice.englishcards.R;
import com.practice.englishcards.db.RealtimeDBManager;

import com.practice.englishcards.db.entry.Classes;
import com.practice.englishcards.db.entry.WordItem;
import com.practice.englishcards.utils.SpeakUtil;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class ExamActivity extends AppCompatActivity implements IMainView {

    private List<WordItem> word;
    private String classesName;
    private SpeakUtil util;
    private ArrayList<String> options = new ArrayList<>();
    private TextView tvEn, tvOp1, tvOp2, tvOp3, tvOp4, tvItem, tvFreq;
    private ImageButton ibtnPlay, ibtnBugs;
    private int currentIndex = 0;
    private WordItem vocabulary;
    private List<String> wrongList = new ArrayList<>();
    private List<String> rightList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        initView();
        word = (List<WordItem>) getIntent().getSerializableExtra("class");
        classesName = (String) getIntent().getSerializableExtra("classesName");
        Collections.shuffle(word);
        wrongList = Arrays.asList(getResources().getStringArray(R.array.wrong_answer));
        rightList = Arrays.asList(getResources().getStringArray(R.array.right_answer));

        util = SpeakUtil.getIns(getApplicationContext());
        initClassOpts();
        setVocabulary();

        findViewById(R.id.ibtn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private boolean isClick = false;

    private void setVocabulary() {
        setIbtnVisible();
        vocabulary = word.get(currentIndex);
        tvEn.setText(vocabulary.en);
        initOpts();
        View.OnClickListener optClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex >= word.size()) return;
                if (isClick) return;
                checkAnswer(view);
            }
        };
        String freq = "次數 : " + vocabulary.frequency + "/ 21";
        tvFreq.setText(freq);
        tvOp1.setOnClickListener(optClick);
        tvOp2.setOnClickListener(optClick);
        tvOp3.setOnClickListener(optClick);
        tvOp4.setOnClickListener(optClick);
        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speech = vocabulary.en;
                KLog.i("", "eng" + speech.toString());
                util.speakEn(speech);
            }
        });

        ibtnBugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWrongAnswerList();
            }
        });
    }

    private void setIbtnVisible() {
        ibtnBugs.setVisibility((currentIndex == word.size()) ? View.VISIBLE : View.INVISIBLE);
    }

    private void initOpts() {
        String answer = vocabulary.ch;
        ArrayList<String> currentOptions = initAnswerOpts(answer);
        ArrayList<String> sandOpt = sandBoxOpts(answer, currentOptions);
        tvOp1.setText(sandOpt.get(0));
        tvOp2.setText(sandOpt.get(1));
        tvOp3.setText(sandOpt.get(2));
        tvOp4.setText(sandOpt.get(3));
        String itemNo = currentIndex + "/" + (word.size() - 1);
        tvItem.setText(itemNo);
    }

    private void initView() {
        tvEn = findViewById(R.id.tv_en);
        tvItem = findViewById(R.id.tv_item);
        tvOp1 = findViewById(R.id.tv_opt1);
        tvOp2 = findViewById(R.id.tv_opt2);
        tvOp3 = findViewById(R.id.tv_opt3);
        tvOp4 = findViewById(R.id.tv_opt4);
        tvFreq = findViewById(R.id.tv_freq);

        ibtnPlay = findViewById(R.id.iBtn_play);
        ibtnBugs = findViewById(R.id.ibtn_bugs);
    }

    private ArrayList<String> initAnswerOpts(String answer) {
        ArrayList<String> currentOptions = new ArrayList<>();
        currentOptions.addAll(options);
        currentOptions.remove(answer);
        return currentOptions;
    }

    private void initClassOpts() {
        for (int i = 0; i < word.size(); i++) {
            options.add(word.get(i).ch);
        }
    }

    private ArrayList<String> sandBoxOpts(String answer, ArrayList<String> currentOptions) {
        Random rand = new Random();
        Set<String> choices = new HashSet<String>();
        choices.add(answer);
        while (choices.size() < 4) {
            int i = rand.nextInt(currentOptions.size());
            choices.add(currentOptions.get(i));
        }
        ArrayList<String> setToArray = new ArrayList<>();
        setToArray.addAll(new ArrayList<String>(choices));
        Collections.shuffle(setToArray);
        return setToArray;
    }

    private void checkAnswer(View view) {
        isClick = true;
        TextView clickView = (TextView) view;
        boolean isCurrent = clickView.getText().equals(vocabulary.ch);
        vocabulary.setSuc(isCurrent);
        if (isCurrent) {
            Collections.shuffle(rightList);
            String right = rightList.get(0);
            util.speakEn(right + vocabulary.en);

            int freq = vocabulary.frequency;
            KLog.i("TAG", "frequency :" + freq);
            vocabulary.setFrequency((freq == 0) ? 0 : freq - 1);
            KLog.i("TAG", "frequency after :" + vocabulary.frequency);
            saveWordItem(vocabulary);

        } else {
            Collections.shuffle(wrongList);
            String wrong = wrongList.get(0);
            util.speakCH(wrong + vocabulary.ch + vocabulary.en);
        }
        currentIndex++;
        if (currentIndex >= word.size()) {
            setIbtnVisible();
            saveGrade();
        } else {
            try {
                new Thread().sleep(3500);
                setVocabulary();
                isClick = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showWrongAnswerList() {
        final ArrayAdapter<WordItem> bugs = new ArrayAdapter<WordItem>(ExamActivity.this, android.R.layout.select_dialog_singlechoice);
        for (WordItem v : word) {
            if (!v.isSuc()) {
                bugs.add(v);
            }
        }
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(ExamActivity.this);
        builderSingle.setIcon(R.drawable.ic_bug_report_red_24dp);
        builderSingle.setTitle("Bugs");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(bugs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                util.speakEn(bugs.getItem(which).en);
            }
        });
        builderSingle.show();
    }

    private void saveGrade() {
        HashMap<String, String> bugs = new HashMap<>();
        for (WordItem v : word) {
            if (!v.isSuc()) {
                bugs.put(v.en, v.ch);
            }
        }
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        KLog.d("TAG", "currentDate" + currentDate);
        RealtimeDBManager dbManager = new RealtimeDBManager(this);
        dbManager.updateExam(classesName, currentDate, bugs);
    }

    private void saveWordItem(WordItem wordItem) {
        RealtimeDBManager dbManager = new RealtimeDBManager(this);
        dbManager.updateFrequency(classesName, wordItem.en, wordItem.frequency);
    }

    @Override
    public void show(List<Classes> list) {

    }

    @Override
    public void showError(Object msg) {

    }
}
