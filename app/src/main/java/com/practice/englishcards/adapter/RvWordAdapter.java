package com.practice.englishcards.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.englishcards.module.DataEntry;
import com.practice.englishcards.R;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class RvWordAdapter extends RecyclerView.Adapter<RvWordAdapter.ClassViewHolder> {

    private List<DataEntry.Vocabulary> entries;
    private OnPlayListener playListener;
    private onAnswerListener onAnswerListener;

    public RvWordAdapter(List<DataEntry.Vocabulary> data) {
        entries = data;
        KLog.i("JOJO", "getClassName :" + entries.size());
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        KLog.i("JOJO", "getClassName :" + entries.size());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_word, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, final int position) {
        DataEntry.Vocabulary vocabulary = entries.get(position);
        holder.tvEn.setText(vocabulary.En);
        holder.tvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playListener.onPlayListener(view, position);
            }
        });

        holder.tvEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerListener.showOptionDialog(position);
            }
        });

        holder.answer = vocabulary.Ch;
        holder.setDays(vocabulary.getStudyDay());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void setPlayListener(OnPlayListener listener) {
        playListener = listener;
    }

    public void setOnAnswerListener(onAnswerListener listener) {
        onAnswerListener = listener;
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        public ImageButton tvPlay;
        public TextView tvEn;
        public View w1, w2, w3;
        public String answer = "";
        public View itemView;
        private ArrayList<TextView> tvDays = new ArrayList<>();

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvPlay = itemView.findViewById(R.id.iBtn_play);
            tvEn = itemView.findViewById(R.id.tv_en);
            w1 = itemView.findViewById(R.id.v_week1);
            w2 = itemView.findViewById(R.id.v_week2);
            w3 = itemView.findViewById(R.id.v_week3);
            setDays();
        }

        public void setDays(final List<String> days) {
            KLog.i("kkkkk","days"+days.size());
            for(int i = 0 ; i < days.size() ; i++){
                if(!days.get(i).equals("")){
                    tvDays.get(i).setText(days.get(i));
                }
            }
        }

        private void setDays() {
            tvDays.addAll(initDays(w1));
            tvDays.addAll(initDays(w2));
            tvDays.addAll(initDays(w3));
        }

        private ArrayList<TextView> initDays(View v) {
            ArrayList<TextView> tvIDs = new ArrayList<TextView>();
            int[] tvId = {R.id.tv_day1, R.id.tv_day2, R.id.tv_day3, R.id.tv_day4, R.id.tv_day5, R.id.tv_day6, R.id.tv_day7};
            for (int i : tvId) {
                tvIDs.add((TextView) v.findViewById(i));
            }
            return tvIDs;
        }

    }

    public interface OnPlayListener {
        void onPlayListener(View v, int position);
    }

    public interface onAnswerListener {
        void showOptionDialog(int position);
    }


}
