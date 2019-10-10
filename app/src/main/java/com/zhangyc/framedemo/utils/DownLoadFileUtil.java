package com.zhangyc.framedemo.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class DownLoadFileUtil {

    public static final String TAG = DownLoadFileUtil.class.getSimpleName();

    public static final BroadcastReceiver downLoadFile(Context context, String downLoadUrl, String fileName, final DownLoadCallback downLoadCallback) {
        if (fileName == null ) throw new NullPointerException();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downLoadUrl));
        request.setDestinationInExternalPublicDir("/Pictures/", fileName);
        final DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        final long taskId = downloadManager.enqueue(request);
        Log.i(TAG, "downLoadFile: taskId : " + taskId);
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(taskId);
                Cursor c = downloadManager.query(query);
                if (c.moveToFirst()) {
                    int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    if (downLoadCallback != null) downLoadCallback.downLoadStatusListener(status);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        context.registerReceiver(broadcastReceiver, intentFilter);
        return broadcastReceiver;
    }

    public interface DownLoadCallback {
        void downLoadStatusListener(int status);
    }

}
