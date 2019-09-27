package com.zhangyc.framedemo.publicaddress.contacts;

import android.webkit.WebView;

import com.zhangyc.framedemo.mvp.IBasePresenter;
import com.zhangyc.framedemo.mvp.IBaseView;

public class WebViewContact {

    public interface IWebView extends IBaseView {
        WebView getWebView();
    }

    public interface IWebPresenter extends IBasePresenter {
        void loadUrl(String url);
    }

}
