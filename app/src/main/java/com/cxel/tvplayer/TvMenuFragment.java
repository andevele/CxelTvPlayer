package com.cxel.tvplayer;

import android.view.View;

import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cxel.tvplayer.base.adapter.TvMenuRecyclerAdapter;
import com.cxel.tvplayer.base.fragment.BaseFragment;

public class TvMenuFragment extends BaseFragment implements
TvMenuRecyclerAdapter.OnItemClickListener{
    private TvMenuRecyclerAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tvmenu;
    }

    @Override
    protected void initViews(View root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.tv_menu_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setFocusable(true);

    }

    @Override
    protected void initData() {
        mAdapter = getTvmenuAdapter();
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    private TvMenuRecyclerAdapter getTvmenuAdapter() {
        return new TvMenuRecyclerAdapter(this.getContext());
    }

    @Override
    public void onItemClick(int position, long itemId) {

    }
}
