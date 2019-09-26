package com.zhangyc.framedemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhangyc.framedemo.proxy.ProxyActivity;

public abstract class BaseActivity extends ProxyActivity {

    public static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initViews();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unInit();
    }


    protected abstract void init();

    protected abstract void initViews();

    protected abstract void initData();

    protected abstract void unInit();

}
