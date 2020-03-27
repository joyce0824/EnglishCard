package com.practice.englishcards.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.englishcards.R;
import com.practice.englishcards.db.entry.WordItem;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class LvCalendarAdapter extends BaseAdapter {

    private List<WordItem> entries;
    private LayoutInflater mLayInf;
    private Interfaces.OnPlayListener playListener;

    public LvCalendarAdapter(Context c, List<WordItem> entries) {
        mLayInf = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.entries = entries;
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int i) {
        return entries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = mLayInf.inflate(R.layout.item_lv_word, viewGroup, false);

        ImageButton iBtnPlay =  v.findViewById(R.id.iBtn_play);
        TextView tvEn = (TextView) v.findViewById(R.id.tv_en);
        TextView tvCh = (TextView) v.findViewById(R.id.tv_ch);
        iBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playListener.onPlayListener(view, i);
            }
        });

        tvCh.setText(entries.get(i).ch);
        tvEn.setText(entries.get(i).en);
        return v;
    }

    public void setPlayListener(Interfaces.OnPlayListener listener) {
        playListener = listener;
    }
}
