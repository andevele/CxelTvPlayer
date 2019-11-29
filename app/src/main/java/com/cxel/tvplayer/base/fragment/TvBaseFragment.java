package com.cxel.tvplayer.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public abstract class TvBaseFragment extends Fragment {
    private View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("zhulf","===700==onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("zhulf","===701==onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("zhulf","===702==onCreateView");
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
        } else {
            mView = inflater.inflate(getLayoutId(), container, false);
            initViews(mView);
            //initData();
        }
        return mView;
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View root);

    protected void initData() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("zhulf","====703==onActivityCreated: " + this);
    }

    @Override
    public void onResume() {
        Log.d("zhulf","======704===onResume");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("zhulf","======705===onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("zhulf","======706====onDetach");
    }
}
