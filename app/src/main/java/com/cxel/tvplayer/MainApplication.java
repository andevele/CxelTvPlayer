package com.cxel.tvplayer;

import android.app.Application;
import android.content.Context;

import com.cxel.tvplayer.tvmenu.TvMenuBean;
import com.cxel.tvplayer.tvmenu.TvMenuFragment;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainApplication extends Application {
    private static Context context = null;
    Map<String, List<TvMenuBean>> dataMap = new HashMap<String, List<TvMenuBean>>();

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static synchronized Context getContext() {
        return context;
    }

    public Map<String, List<TvMenuBean>> getDataMap() {
        return dataMap;
    }

    public void setDataMap(List<TvMenuBean> list) {
        dataMap.put(TvMenuFragment.class.getName(),list);
    }
}
