package com.zhangyc.framedemo.publicaddress.contacts;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.zhangyc.framedemo.mvp.IBaseView;
import com.zhangyc.framedemo.serverapi.NoHttpUtil;
import com.zhangyc.framedemo.utils.CustomPdfRenderer;
import com.zhangyc.framedemo.utils.DownLoadFileUtil;
import com.zhangyc.framedemo.utils.PdfUtil;

import java.io.File;
import java.util.ArrayList;

public class PdfPresenter implements PdfContact.IPdfPresenter {

    public static final String TAG = PdfPresenter.class.getSimpleName();

    public static final String url = "http://global-questions.oss-cn-shanghai.aliyuncs.com/2019/104010001/ec3259f5-b406-47f8-ab44-cfbb5f065ee1/DAAN_2019-8-21_11-21-10.pdf";

    public static final String urlBook = "http://espo.oss-cn-shanghai.aliyuncs.com/leke/book/preview/3Qki2Gt83hkj6DBzA2HWh7im4yz2EEtT.pdf";

    private PdfContact.IPdfView mPdfView;

    private BroadcastReceiver mBroadcastReceiver;

    @Override
    public void attachView(IBaseView iBaseView) {
        mPdfView = (PdfContact.IPdfView) iBaseView;
    }

    @Override
    public void detachView() {
        if (mBroadcastReceiver != null) mPdfView.getContext().unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void loadPdf() {
        /*mBroadcastReceiver = DownLoadFileUtil.downLoadFile(mPdfView.getContext(), url, "a.pdf", new DownLoadFileUtil.DownLoadCallback() {
            @Override
            public void downLoadStatusListener(int status) {
                Log.i(TAG, "downLoadStatusListener: "  + status);
                switch (status) {
                    case DownloadManager.STATUS_PAUSED:
                        break;
                    case DownloadManager.STATUS_FAILED:
                        break;
                    case DownloadManager.STATUS_SUCCESSFUL:
                        File file = new File("sdcard/Pictures/a.pdf");
                        Log.i(TAG, "downLoadStatusListener: " + file.exists());
                        ArrayList<Bitmap> bitmapArrayList = PdfUtil.pdfToBitmaps(mPdfView.getContext(), file);
                        if (bitmapArrayList.size() > 0) mPdfView.getDisplayImageView().setImageBitmap(bitmapArrayList.get(0));
                        break;
                    case DownloadManager.STATUS_RUNNING:

                        break;
                }
            }
        });*/



        final CustomPdfRenderer customPdfRenderer = new CustomPdfRenderer.Builder().width(mPdfView.getContext().getResources().getDisplayMetrics().widthPixels).height(mPdfView.getContext().getResources().getDisplayMetrics().heightPixels).build();
        customPdfRenderer.loadPdf(urlBook, mPdfView.getDisplayImageView());
        mPdfView.getDisplayImageView().setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (customPdfRenderer.getCurrentPage() < customPdfRenderer.getPdfPageCount() - 1)
                    customPdfRenderer.gotoPage(customPdfRenderer.getCurrentPage() + 1);
            }
        });


        /*NoHttpUtil.getInstance().download(url, "sdcard/Pictures/", "b.pdf", new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

            }

            @Override
            public void onProgress(int what, int progress, long fileCount, long speed) {

            }

            @Override
            public void onFinish(int what, String filePath) {

                CustomPdfRenderer customPdfRenderer = new CustomPdfRenderer.Builder().width(1000).height(960).build();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    customPdfRenderer.into(filePath, mPdfView.getDisplayImageView());
                }


//                File file = new File(filePath);
//                Bitmap bitmap = PdfUtil.pdfToBitmap(mPdfView.getContext(), file, 0);
//                if (bitmap != null) mPdfView.getDisplayImageView().setImageBitmap(bitmap);
            }

            @Override
            public void onCancel(int what) {

            }
        });*/
    }
}
