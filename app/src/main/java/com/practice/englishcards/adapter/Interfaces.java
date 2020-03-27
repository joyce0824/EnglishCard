package com.practice.englishcards.adapter;

import android.view.View;

public class Interfaces {
    public interface OnPlayListener {
        void onPlayListener(View v, int position);
    }

    public interface OnAnswerListener {
        void showOptionDialog(int position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnBugClickListener{
        void onBugClick(View v, int position);
    }
}
