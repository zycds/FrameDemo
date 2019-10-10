package com.zhangyc.framedemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PdfUtil {

    public static final String TAG = PdfUtil.class.getSimpleName();


    private static PdfUtil instance;

    private PdfRenderer mPdfRenderer;

    private int mPdfPageCount;

    private PdfUtil() {
    }

    public static PdfUtil getInstance() {
        if (instance == null) {
            synchronized (PdfUtil.class) {
                if (instance == null) instance = new PdfUtil();
            }
        }
        return instance;
    }

    private PdfRenderer open(String filePath){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            File pdfFile = new File(filePath);
            try {
                mPdfRenderer =  new PdfRenderer(ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY));
                mPdfPageCount = mPdfRenderer.getPageCount();
                return mPdfRenderer;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Bitmap pdfToBitmap (int w, int h, int pageIndex){

        if (mPdfRenderer == null) throw new NullPointerException();
        Bitmap bitmap = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PdfRenderer.Page page = mPdfRenderer.openPage(pageIndex);

            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap, 0 , 0, null);
            Rect rect = new Rect(0, 0 ,w, h);
            page.render(bitmap, rect, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

            page.close();
        }
        return bitmap;
    }

    public void into(ImageView imageView) {

    }

    public static final ArrayList<Bitmap> pdfToBitmaps (Context context, File pdfFile) {
        ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                PdfRenderer pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY));
                Bitmap bitmap;
                int pageCount = pdfRenderer.getPageCount();
                for (int i = 0; i < pageCount; i++) {
                    PdfRenderer.Page page = pdfRenderer.openPage(i);
                    int w = context.getResources().getDisplayMetrics().densityDpi / 72 * page.getWidth();
                    int h = context.getResources().getDisplayMetrics().densityDpi / 72  * page.getHeight();
                    Log.i(TAG, "pdfToBitmaps: " + w + "   h : " + h);
                    bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    if (bitmap == null) {
                        Log.e(TAG, "pdfToBitmaps: bitmap is null.");
                        page.close();
                        continue;
                    }
                    Canvas canvas = new Canvas(bitmap);
                    canvas.drawColor(Color.WHITE);
                    canvas.drawBitmap(bitmap, 0 ,0, null);
                    Rect rect = new Rect(0, 0, w, h);
                    page.render(bitmap, rect, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                    bitmapArrayList.add(bitmap);
                    page.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmapArrayList;
    }


    public static Bitmap pdfToBitmap (Context context, File pdfFile, int pageIndex) {
        Bitmap bitmap = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                PdfRenderer pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY));
                int pageCount = pdfRenderer.getPageCount();
                if (pageCount > pageIndex){
                    PdfRenderer.Page page = pdfRenderer.openPage(pageIndex);
                    int w = context.getResources().getDisplayMetrics().densityDpi / 72 * page.getWidth();
                    int h = context.getResources().getDisplayMetrics().densityDpi / 72  * page.getHeight();
                    Log.i(TAG, "pdfToBitmaps: " + w + "   h : " + h + "  page Width : " + page.getWidth() + "   page Height : " + page.getHeight() + "  pageIndex: " + page.getIndex() + " pageCount :" + pageCount);
                    bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    if (bitmap == null) {
                        Log.e(TAG, "pdfToBitmaps: bitmap is null.");
                    } else {
                        Canvas canvas = new Canvas(bitmap);
                        canvas.drawColor(Color.WHITE);
                        canvas.drawBitmap(bitmap, 0 ,0, null);
                        Rect rect = new Rect(0, 0, w, h);
                        page.render(bitmap, rect, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                    }
                    page.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
