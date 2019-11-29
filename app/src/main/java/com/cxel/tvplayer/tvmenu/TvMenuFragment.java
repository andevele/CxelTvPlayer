package com.cxel.tvplayer.tvmenu;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cxel.tvplayer.MainApplication;
import com.cxel.tvplayer.R;
import com.cxel.tvplayer.manager.ControlManager;
import com.cxel.tvplayer.base.fragment.BaseFragment;
import com.cxel.tvplayer.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class TvMenuFragment extends BaseFragment implements
TvMenuRecyclerAdapter.OnItemClickListener,TvMenuRecyclerAdapter.OnItemFocusChangeListener{
    private TvMenuRecyclerAdapter mAdapter;
    private RecyclerView recyclerView;
    private List<TvMenuItem> tvMenuItemList = new ArrayList<TvMenuItem>();
    protected String CACHE_KEY = getClass().getName();
    private static final int TV_MENU_ITEM_COUNT = 8;
    private final int[] tvMenuImages = {
            R.drawable.icon_home, R.drawable.icon_source,
            R.drawable.icon_picture_mode, R.drawable.icon_sound_mode,
            R.drawable.icon_channel, R.drawable.icon_pc,
            R.drawable.icon_setting, R.drawable.icon_time};
    private Context mContext;
    private FragmentManager mFragmentManager;
    private int mContainerId;
    private TvMenuItem currentTvMenuItem;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tvmenu;
    }

    public void setUp(Context context, FragmentManager fragmentManager, int containerId) {
        mContext = context;
        mFragmentManager = fragmentManager;
        mContainerId = containerId;
        clearOldFragment();
    }

    private void clearOldFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        List<Fragment> fragments = mFragmentManager.getFragments();
        if(fragmentTransaction == null || fragments == null || fragments.size() == 0) {
            return;
        }
        boolean isCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this && fragment != null) {
                fragmentTransaction.remove(fragment);
                isCommit = true;
            }
        }
        if(isCommit) {
            fragmentTransaction.commitNow();
        }
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
        mAdapter.setOnItemFocusChangeListener(this);
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
            createTvMenuItemFragment(tempList);
        } else {
            mAdapter.setData(list);
            createTvMenuItemFragment(list);
        }

    }

    private void createTvMenuItemFragment(List<TvMenuBean> list) {
        List<TvMenuItem> menuItemList = new ArrayList<>();
        for(int i = 0;i < TV_MENU_ITEM_COUNT; i++) {
            TvMenuItem tvMenuItem = new TvMenuItem(getActivity());
            tvMenuItem.init(list.get(i).getName());
            menuItemList.add(i,tvMenuItem);
        }
        tvMenuItemList.addAll(menuItemList);
    }

    private TvMenuRecyclerAdapter getTvmenuAdapter() {
        return new TvMenuRecyclerAdapter(this.getContext());
    }

    @Override
    public void onItemClick(int position,String name) {
        switch (name) {
            case Constant.TV_MENU_HOME:
                ControlManager.getInstance().goHomePage();
                break;
            case Constant.TV_MENU_SOURCE:
                break;
            case Constant.TV_MENU_PICTURE:
            case Constant.TV_MENU_SOUND:
            case Constant.TV_MENU_CHANNEL:
            case Constant.TV_MENU_PC_ADJUST:
            case Constant.TV_MENU_SETTING:
            case Constant.TV_MENU_TIME:
                doFragmentChanged(tvMenuItemList.get(position));
                break;
                default:break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus, float scaleX, float scaleY) {
        ControlManager.getInstance().startAnimator(view,hasFocus,scaleX,scaleY);
    }

    private void doFragmentChanged(TvMenuItem newTvMenuItem) {
        TvMenuItem oldTvMenuItem = null;
        if(currentTvMenuItem != null) {
            oldTvMenuItem = currentTvMenuItem;
            if(oldTvMenuItem == newTvMenuItem) {
                return;
            }

        }
        doFragmentChanged(oldTvMenuItem,newTvMenuItem);
        currentTvMenuItem = newTvMenuItem;
    }

    private void doFragmentChanged(TvMenuItem oldTvMenuItem, TvMenuItem newTvMenuItem) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if(oldTvMenuItem != null) {
            if(oldTvMenuItem.getFragment() != null) {
                ft.detach(oldTvMenuItem.getFragment());
            }
        }
        if(newTvMenuItem != null) {
            if(newTvMenuItem.getFragment() == null) {
                Log.d("zhulf","===600");
                Fragment fragment = Fragment.instantiate(mContext,
                        newTvMenuItem.getFragmentClass().getName(),null);
                ft.add(mContainerId, fragment,newTvMenuItem.getTag());
                newTvMenuItem.setFragment(fragment);
            } else {
                Log.d("zhulf","======601");
                ft.attach(newTvMenuItem.getFragment());
            }
        }
        ft.commit();
    }
}
