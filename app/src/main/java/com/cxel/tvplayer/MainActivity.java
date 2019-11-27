package com.cxel.tvplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cxel.tvplayer.base.activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
