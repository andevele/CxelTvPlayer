package com.cxel.tvplayer.base.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TvMenuRecyclerAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public TvMenuRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, long itemId);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position, long itemId);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.onItemLongClickListener = itemLongClickListener;
    }
}
