package com.practice.englishcards.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.englishcards.DataEntry;
import com.practice.englishcards.R;

import java.util.ArrayList;

public class RvClassAdapter extends RecyclerView.Adapter<RvClassAdapter.ClassViewHolder> {

    private ArrayList<DataEntry> entries;
    private OnPlayListener playListener;
    public RvClassAdapter(ArrayList<DataEntry> data) {
        entries = data;

    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_vocabulary, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, final int position) {
        ((ClassViewHolder) holder).tvClassName.setText(entries.get(position).getClassName());
        ((ClassViewHolder) holder).iBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playListener.onPlayListener(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void setPlayListener(OnPlayListener listener){
        playListener = listener;
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder{
        public ImageButton iBtnPlay;
        public TextView tvClassName;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            iBtnPlay =  itemView.findViewById(R.id.iBtn_play);
            tvClassName = itemView.findViewById(R.id.tv_class);
        }
    }

    public interface OnPlayListener{
        void onPlayListener(View v, int position);
    }
}
