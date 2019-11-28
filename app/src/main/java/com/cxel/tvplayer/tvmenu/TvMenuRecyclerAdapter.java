package com.cxel.tvplayer.tvmenu;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cxel.tvplayer.R;

import java.util.ArrayList;
import java.util.List;

public class TvMenuRecyclerAdapter extends RecyclerView.Adapter {
    private static final String TAG = TvMenuRecyclerAdapter.class.getSimpleName();
    private final Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private List<TvMenuBean> dataList;

    public TvMenuRecyclerAdapter(Context context) {
        this.dataList = new ArrayList<>();
        this.mContext = context;
    }

    public void setData(List<TvMenuBean> list) {
        if(list == null) {
            Log.d(TAG,"tv menu list is null");
            return;
        }
        this.dataList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TvMenuViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.tv_menu_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TvMenuViewHolder viewHolder = (TvMenuViewHolder) holder;
        viewHolder.icon.setImageResource(dataList.get(position).getImageId());
        viewHolder.name.setText(dataList.get(position).getName());
        viewHolder.itemlayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                Log.d("zhulf","====hasFocus" + hasFocus);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
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

    public static class TvMenuViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout itemlayout;
        private ImageView icon;
        private TextView name;

        public TvMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemlayout = (LinearLayout) itemView.findViewById(R.id.item_layout);
            icon = (ImageView) itemView.findViewById(R.id.item_icon);
            name = (TextView) itemView.findViewById(R.id.item_name);
        }
    }
}
