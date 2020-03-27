package com.practice.englishcards.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.englishcards.db.entry.Classes;
import com.practice.englishcards.R;

import java.util.List;

public class RvClassAdapter extends RecyclerView.Adapter<RvClassAdapter.ClassViewHolder> {

//    private List<ClassItem> entries;
    private List<Classes> dbeEntries;
    private Interfaces.OnPlayListener playListener;
    private Interfaces.OnItemClickListener onItemClickListener;
    private Interfaces.OnBugClickListener onBugClickListener;

//    public RvClassAdapter(List<ClassItem> data) {
//        entries = data;
//    }

    public RvClassAdapter(List<Classes> data) {
        dbeEntries = data;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_class, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, final int position) {
//        holder.tvClassName.setText(entries.get(position).getClassName());
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

        holder.ibtnBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBugClickListener.onBugClick(view,position);
            }
        });

        holder.tvClassName.setText(dbeEntries.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return dbeEntries.size();
    }

    public void setPlayListener(Interfaces.OnPlayListener listener){
        playListener = listener;
    }

    public void setOnItemClickListener(Interfaces.OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public void setOnBugClickListener(Interfaces.OnBugClickListener listener){
        onBugClickListener = listener;
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder{
        public ImageButton iBtnPlay ,ibtnBug;
        public TextView tvClassName;
        public View itemView;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iBtnPlay =  itemView.findViewById(R.id.iBtn_play);
            ibtnBug = itemView.findViewById(R.id.iBtn_bug);
            tvClassName = itemView.findViewById(R.id.tv_class);
        }
    }
}
