package com.zhangyc.framedemo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;

import java.io.File;
import java.io.IOException;

public class CustomPdfRenderer {

    public static final String TAG = CustomPdfRenderer.class.getSimpleName();

    private int mPdfPageCount;

    private int mCurrentPage;

    private ImageView mImageView;

    private int mPdfWidth, mPdfHeight;

    private PdfRenderer mPdfRenderer;

    public CustomPdfRenderer(int pdfWidth, int pdfHeight) {
        mPdfWidth = pdfWidth;
        mPdfHeight = pdfHeight;
    }

    public static class Builder {
        private int w;
        private int h;

        public Builder width(int w) {
            this.w = w;
            return this;
        }

        public Builder height(int h) {
            this.h = h;
            return this;
        }

        public CustomPdfRenderer build(){
            return new CustomPdfRenderer(w, h);
        }
    }

    public static void init (InitializationConfig initializationConfig){
        if (initializationConfig == null) throw new NullPointerException();
        NoHttp.initialize(initializationConfig);
    }


    public void loadPdf(String url, final ImageView imageView) {
        if (url == null) throw new NullPointerException("url is null.");
        String fileName = url.substring(url.lastIndexOf("/"));
        DownloadRequest downloadRequest = NoHttp.createDownloadRequest(url, NoHttp.getContext().getFilesDir().getAbsolutePath(), fileName, true, false);
        NoHttp.getDownloadQueueInstance().add(0, downloadRequest, new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                Log.e(TAG, "onDownloadError: ");
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                Log.i(TAG, "onStart: ");
            }

            @Override
            public void onProgress(int what, int progress, long fileCount, long speed) {
                Log.i(TAG, "onProgress: progress = " + progress);
            }

            @Override
            public void onFinish(int what, String filePath) {
                Log.i(TAG, "onFinish: filePath = " + filePath);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    into(filePath, imageView);
                }
            }

            @Override
            public void onCancel(int what) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private PdfRenderer open(String filePath) {
        if (filePath == null) throw new NullPointerException("open pdf file path is null.");
        File file = new File(filePath);
        if (!file.exists()) {
            Log.e(TAG, "filePath : '" + filePath + "', is not exits");
            return null;
        }
        PdfRenderer pdfRenderer = null;
        try {
            pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfRenderer;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void into(String filePath, ImageView imageView) {
        mPdfRenderer = open(filePath);
        mPdfPageCount = mPdfRenderer != null ? mPdfRenderer.getPageCount() : 0;
        PdfRenderer.Page page = mPdfRenderer != null ? mPdfRenderer.openPage(0) : null;
        if (page == null) return;
        mImageView = imageView;
        imageView.setImageBitmap(pageToBitmap(page));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Bitmap pageToBitmap(PdfRenderer.Page page){
        if (mPdfWidth <= 0) mPdfWidth = page.getWidth();
        if (mPdfHeight <= 0) mPdfHeight = page.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(mPdfWidth, mPdfHeight, Bitmap.Config.ARGB_8888);
        if (bitmap == null) return null;
        Canvas canvas = new Canvas(bitmap);
        Rect rect = new Rect(0, 0 ,mPdfWidth, mPdfHeight);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        page.render(bitmap, rect, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        page.close();
        return bitmap;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void gotoPage(int pageIndex) {
        if (mPdfRenderer == null) throw new NullPointerException("mPdfRenderer is Null.");
        if (pageIndex < 0 || pageIndex >= mPdfPageCount) throw new IllegalArgumentException("pageIndex is indexOf. pageCount = " + mPdfPageCount
                    + ", pageIndex = " + pageIndex);
        PdfRenderer.Page page = mPdfRenderer.openPage(pageIndex);
        if (mImageView == null) throw new NullPointerException("imageView is Null.");
        mImageView.setImageBitmap(pageToBitmap(page));
        mCurrentPage = pageIndex;
    }

    public int getPdfPageCount() {
        return mPdfPageCount;
    }

    public int getCurrentPage() {
        return mCurrentPage;
    }
}
