package com.zhangyc.framedemo.proxy;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zhangyc.framedemo.mvp.IBaseView;

public abstract class ProxyActivity extends AppCompatActivity implements IBaseView {

    protected ProxyImpl mProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProxy = new ProxyImpl();
        mProxy.binderPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.unBinderPresenter();
    }
}
