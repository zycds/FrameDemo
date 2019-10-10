package com.zhangyc.framedemo.serverapi;

import android.content.Context;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.cache.DiskCacheStore;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadQueue;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.zhangyc.framedemo.constant.Constants;


public class NoHttpUtil {

    public static final String TAG = NoHttpUtil.class.getSimpleName();

    private static NoHttpUtil instance;

    public static final int DOWNLOAD_WHAT = 0;

    private RequestQueue mRequestQueue;

    private DownloadQueue mDownloadQueue;

    private NoHttpUtil() {
        mRequestQueue = NoHttp.getRequestQueueInstance();
        mDownloadQueue = NoHttp.getDownloadQueueInstance();

        Logger.setDebug(true);
        Logger.setTag(TAG);
    }

    public static NoHttpUtil getInstance() {
        if (instance == null) {
            synchronized (NoHttpUtil.class) {
                if (instance == null) instance = new NoHttpUtil();
            }
        }
        return instance;
    }


    public void init(Context context) {
        InitializationConfig initializationConfig = InitializationConfig.newBuilder(context)
                .connectionTimeout(Constants.SERVER_TIMEOUT)
                .readTimeout(Constants.SERVER_TIMEOUT)
                .retry(Constants.RETRY_COUNT)
                .cacheStore(new DiskCacheStore(context))
                .build();
        NoHttp.initialize(initializationConfig);
    }

    public DownloadRequest downloadRequest (String url , String fileFolder, String fileName, boolean isRange, boolean isDeleteOld, DownloadListener downloadListener) {
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(url, fileFolder, fileName, isRange, isDeleteOld);
        mDownloadQueue.add(DOWNLOAD_WHAT, downloadRequest, downloadListener);
        return downloadRequest;
    }

    public void download(String url, String fileFolder, String fileName, DownloadListener downloadListener) {
        downloadRequest(url, fileFolder, fileName, true, false, downloadListener);
    }


}
