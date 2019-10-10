package com.zhangyc.framedemo;

import android.app.Application;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.cache.DiskCacheStore;
import com.zhangyc.framedemo.constant.Constants;
import com.zhangyc.framedemo.utils.CustomPdfRenderer;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        NoHttpUtil.getInstance().init(this);
        InitializationConfig initializationConfig = InitializationConfig.newBuilder(this)
                .connectionTimeout(Constants.SERVER_TIMEOUT)
                .readTimeout(Constants.SERVER_TIMEOUT)
                .retry(Constants.RETRY_COUNT)
                .cacheStore(new DiskCacheStore(this))
                .build();
        CustomPdfRenderer.init(initializationConfig);
    }
}
