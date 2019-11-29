package com.cxel.tvplayer.tvmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cxel.tvplayer.channel.ChannelFragment;
import com.cxel.tvplayer.manager.ControlManager;
import com.cxel.tvplayer.picture.PictureModeFragment;
import com.cxel.tvplayer.setting.SettingFragment;
import com.cxel.tvplayer.sound.SoundModeFragment;
import com.cxel.tvplayer.time.TimeFragment;
import com.cxel.tvplayer.util.Constant;
import com.cxel.tvplayer.vga.VGAFragment;

public class TvMenuItem {
    private Context mContext;
    private Class<?> mClass;
    private String mTag;
    private Fragment mFragment;

    public TvMenuItem(Context context) {
        this.mContext = context;
    }

    public void init(String name) {
        switch (name) {
            case Constant.TV_MENU_HOME:
                break;
            case Constant.TV_MENU_SOURCE:
                break;
            case Constant.TV_MENU_PICTURE:
                mClass = PictureModeFragment.class;
                break;
            case Constant.TV_MENU_SOUND:
                mClass = SoundModeFragment.class;
                break;
            case Constant.TV_MENU_CHANNEL:
                mClass = ChannelFragment.class;
                break;
            case Constant.TV_MENU_PC_ADJUST:
                mClass = VGAFragment.class;
                break;
            case Constant.TV_MENU_SETTING:
                mClass = SettingFragment.class;
                break;
            case Constant.TV_MENU_TIME:
                mClass = TimeFragment.class;
                break;
            default:
                break;
        }
        if(mClass != null) {
            mTag = mClass.getName();
        }
    }

    public Class<?> getFragmentClass() {
        return mClass;
    }

    public String getTag() {
        return mTag;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }
}
