package com.zhangyc.framedemo.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.zhangyc.framedemo.R;
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

    protected abstract void systemBack();

    @Override
    public void onBackPressed() {
        systemBack();
    }

    public void startIntentLeftToRight(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    public void startIntentRigthToLeft(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }

    public void startIntent(Intent intent, int enterAnim, int exitAnim) {
        startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }
}
