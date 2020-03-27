package com.practice.englishcards.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.practice.englishcards.adapter.Interfaces;
import com.practice.englishcards.adapter.LvCalendarAdapter;
import com.practice.englishcards.databinding.ActivityCalendarBinding;
import com.practice.englishcards.db.entry.WordItem;
import com.practice.englishcards.utils.SpeakUtil;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class CalendarActivity extends AppCompatActivity {

    private ActivityCalendarBinding binding;
    private Map<String, ArrayList<WordItem>> exams;
    private List<WordItem> words = new ArrayList<>();
    private LvCalendarAdapter adapter;
    private SpeakUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        exams = (Map<String, ArrayList<WordItem>>) getIntent().getSerializableExtra("class");
        util = SpeakUtil.getIns(getApplicationContext());
        setCalendarView();
        setLvHistory();
        String defaultDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(binding.calendarView.getDate()));
        KLog.i("TAG", "currentDate" + defaultDate);
        getWords(defaultDate);

        binding.ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void setCalendarView() {
        binding.calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                String date = new StringBuilder().append(year).append("-").
                        append(compareToTen(month + 1)).append("-").append(compareToTen(dayOfMonth)).toString();
                KLog.i("TTTT", "date:" + date);
                KLog.i("TTTT", "word:" + exams.get(date));
                getWords(date);
            }
        });
    }

    private void setLvHistory() {
        adapter = new LvCalendarAdapter(this,
                words);
        adapter.setPlayListener(new Interfaces.OnPlayListener() {
            @Override
            public void onPlayListener(View v, int position) {
                String speech = words.get(position).en;
                KLog.i("", "eng" + speech.toString());
                util.speakEn(speech);
            }
        });
        binding.lvHistory.setAdapter(adapter);
    }

    private void getWords(String date) {
        words.clear();
        if (exams.get(date) != null) {
            words.addAll(exams.get(date));
        }
        notifyWord();
    }

    private String compareToTen(int d) {
        return (d >= 10) ? "" + (d) : "0" + (d);
    }

    private void notifyWord() {
        if (words.size() == 0) {
            binding.tvNoData.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoData.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }
}
