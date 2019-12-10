package com.cxel.tvplayer;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.KeyEvent;

import com.cxel.tvplayer.base.activity.BaseActivity;
import com.cxel.tvplayer.tvmenu.TvMenuFragment;

public class MainActivity extends BaseActivity {

    private TvMenuFragment tvMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        tvMenuFragment = (TvMenuFragment) fragmentManager.findFragmentById(R.id.tv_menu);
        tvMenuFragment.setUp(this, fragmentManager, R.id.main_container);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (tvMenuFragment.isHidden()) {
                tvMenuFragment.show();
            } else {
                tvMenuFragment.hide();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
