package com.cxel.tvplayer.manager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.view.View;

import androidx.core.view.ViewCompat;

import com.cxel.tvplayer.MainApplication;
import com.cxel.tvplayer.util.Constant;
import com.cxel.tvplayer.util.LogUtils;
import com.mstar.android.tv.TvCommonManager;

import java.util.List;

public class ControlManager {
    private static final String TAG = ControlManager.class.getSimpleName();
    private static ControlManager INSTANCE = null;
    private final TvCommonManager mTvCommonmanager;

    public ControlManager() {
        mTvCommonmanager = TvCommonManager.getInstance();
    }

    public static ControlManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ControlManager();
        }
        return INSTANCE;
    }

    public void switchSource(int pos, List<String> dataList) {
        String sourceName = dataList.get(pos);
        LogUtils.v(TAG, "sourceName: " + sourceName);
        switch (sourceName) {
            case Constant.INPUT_SOURCE_NAME_ATV:
                switchSource(TvCommonManager.INPUT_SOURCE_ATV);
                break;
            case Constant.INPUT_SOURCE_NAME_DTV:
                switchSource(TvCommonManager.INPUT_SOURCE_DTV);
                break;
            case Constant.INPUT_SOURCE_NAME_AV1:
                switchSource(TvCommonManager.INPUT_SOURCE_CVBS);
                break;
            case Constant.INPUT_SOURCE_NAME_AV2:
                switchSource(TvCommonManager.INPUT_SOURCE_CVBS2);
                break;
            case Constant.INPUT_SOURCE_NAME_HDMI:
                switchSource(TvCommonManager.INPUT_SOURCE_HDMI);
                break;
            case Constant.INPUT_SOURCE_NAME_HDMI1:
                switchSource(TvCommonManager.INPUT_SOURCE_HDMI);
                break;
            case Constant.INPUT_SOURCE_NAME_HDMI2:
                switchSource(TvCommonManager.INPUT_SOURCE_HDMI2);
                break;
            case Constant.INPUT_SOURCE_NAME_VGA:
                switchSource(TvCommonManager.INPUT_SOURCE_VGA);
                break;
            case Constant.INPUT_SOURCE_NAME_MEDIA:
                //switchSource(TvCommonManager.INPUT_SOURCE_STORAGE);
                switchToStorage();
                break;
        }
    }

    private void switchSource(final int sourceIndex) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(
                        "com.mstar.tv.tvplayer.ui.intent.action.SOURCE_CHANGE");
                intent.putExtra("inputSrc", sourceIndex);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                startActivity(intent);
                //updateInputSource(sourceIndex);
            }
        }).start();
    }

    private void updateInputSource(int inputSourceIndex) {
        long ret = -1;
        ContentValues vals = new ContentValues();
        vals.put("enInputSourceType", inputSourceIndex);
        LogUtils.v(TAG, "inputSourceIndex: " + inputSourceIndex);

        try {
            ret = MainApplication.getContext().getContentResolver().update(
                    Uri.parse("content://mstar.tv.usersetting/systemsetting"),
                    vals, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void switchToStorage() {
//        Intent intent = new Intent("com.mstar.android.intent.action.START_MEDIA_BROWSER", null);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//        startActivity(intent);
        startActivity("com.mstar.android.intent.action.START_MEDIA_BROWSER");
    }

    public void startActivity(String action) {
        Intent intent = new Intent(action);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(intent);
    }

    public void goHomePage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(intent);
    }

    public void startActivity(Intent intent) {
        MainApplication.getContext().startActivity(intent);
    }

    public void setInputSource(int sourceIndex) {
        TvCommonManager.getInstance().setInputSource(sourceIndex);
    }

    public void startAnimator(View view, boolean hasFocus, float scaleX, float scaleY) {
        view.animate().cancel();
        if (hasFocus) {
            ViewCompat.animate(view)
                    .scaleX(scaleX)
                    .scaleY(scaleY)
                    .setDuration(200)
                    .start();
        } else {
            ViewCompat.animate(view)
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(200)
                    .start();
        }
    }
}
