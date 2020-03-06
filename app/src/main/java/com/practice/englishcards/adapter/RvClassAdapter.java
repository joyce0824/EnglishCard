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

import java.util.List;

public class RvClassAdapter extends RecyclerView.Adapter<RvClassAdapter.ClassViewHolder> {

    private List<DataEntry> entries;
    private OnPlayListener playListener;
    private OnItemClickListener onItemClickListener;
    public RvClassAdapter(List<DataEntry> data) {
        entries = data;
        KLog.i("JOJO","getClassName :"+entries.size());
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        KLog.i("JOJO","getClassName :"+entries.size());
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_class, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, final int position) {
        KLog.i("JOJO","getClassName :"+entries.get(position).getClassName());
        holder.tvClassName.setText(entries.get(position).getClassName());
        holder.iBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playListener.onPlayListener(view,position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        KLog.i("JOJO","size :"+entries.size());
        return entries.size();
    }

    public void setPlayListener(OnPlayListener listener){
        playListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder{
        public ImageButton iBtnPlay;
        public TextView tvClassName;
        public View itemView;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iBtnPlay =  itemView.findViewById(R.id.iBtn_play);
            tvClassName = itemView.findViewById(R.id.tv_class);

        }
    }

    public interface OnPlayListener{
        void onPlayListener(View v, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



}
