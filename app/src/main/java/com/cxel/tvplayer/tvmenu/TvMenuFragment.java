package com.cxel.tvplayer.tvmenu;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cxel.tvplayer.MainApplication;
import com.cxel.tvplayer.R;
import com.cxel.tvplayer.tvmenu.TvMenuRecyclerAdapter;
import com.cxel.tvplayer.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TvMenuFragment extends BaseFragment implements
TvMenuRecyclerAdapter.OnItemClickListener{
    private TvMenuRecyclerAdapter mAdapter;
    private RecyclerView recyclerView;
    protected String CACHE_KEY = getClass().getName();
    private static final int TV_MENU_ITEM_COUNT = 8;
    private final int[] tvMenuImages = {
            R.drawable.icon_home, R.drawable.icon_source,
            R.drawable.icon_picture_mode, R.drawable.icon_sound_mode,
            R.drawable.icon_channel, R.drawable.icon_pc,
            R.drawable.icon_setting, R.drawable.icon_time};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tvmenu;
    }

    @Override
    protected void initViews(View root) {
        recyclerView = (RecyclerView) root.findViewById(R.id.tv_menu_list);
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setFocusable(true);

    }

    @Override
    protected void initData() {
        mAdapter = getTvmenuAdapter();
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new TvMeuItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL,
                0,0));
        MainApplication mainApplication = (MainApplication) getActivity().getApplication();
        List<TvMenuBean> list = mainApplication.getDataMap().get(CACHE_KEY);
        if(list == null || list.size() < 0) {
            String[] tvMenuName = getActivity().getResources().getStringArray(R.array.tv_menu_name);
            List<TvMenuBean> tempList = new ArrayList<TvMenuBean>();
            for(int i = 0;i < TV_MENU_ITEM_COUNT; i++) {
                TvMenuBean tvMenuBean = new TvMenuBean();
                tvMenuBean.setName(tvMenuName[i]);
                tvMenuBean.setImageId(tvMenuImages[i]);
                tempList.add(i,tvMenuBean);
            }
            mAdapter.clearData();
            mAdapter.setData(tempList);
            mainApplication.setDataMap(tempList);
        } else {
            mAdapter.setData(list);
        }
    }

    private TvMenuRecyclerAdapter getTvmenuAdapter() {
        return new TvMenuRecyclerAdapter(this.getContext());
    }

    @Override
    public void onItemClick(int position, long itemId) {

    }
}
