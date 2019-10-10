package com.zhangyc.framedemo.publicaddress.contacts;

import android.widget.ImageView;

import com.zhangyc.framedemo.mvp.IBasePresenter;
import com.zhangyc.framedemo.mvp.IBaseView;

public class PdfContact {

    public interface IPdfPresenter extends IBasePresenter {
        void loadPdf();
    }

    public interface IPdfView extends IBaseView {
        ImageView getDisplayImageView();
    }

}
