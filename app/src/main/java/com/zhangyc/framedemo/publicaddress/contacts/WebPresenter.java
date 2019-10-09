package com.zhangyc.framedemo.publicaddress.contacts;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhangyc.framedemo.mvp.IBaseView;

public class WebPresenter implements WebViewContact.IWebPresenter {

    public static final String TAG = WebPresenter.class.getSimpleName();

    private WebViewContact.IWebView mIWebView;

    @Override
    public void attachView(IBaseView iBaseView) {
        mIWebView = (WebViewContact.IWebView) iBaseView;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void loadUrl(String url) {
        if (url == null) throw new NullPointerException();
        WebViewClient webViewClient = new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i(TAG, "onPageStarted: " + url);
                mIWebView.showLoadingDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mIWebView.onSuccess();
            }

        };

        WebChromeClient webChromeClient = new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.i(TAG, "onProgressChanged: " + newProgress);
                if (newProgress == 100)  mIWebView.onSuccess();
                else mIWebView.showLoadingDialog();
            }
        };
        mIWebView.getWebView().setWebChromeClient(webChromeClient);


//        mIWebView.getWebView().setWebViewClient(webViewClient);
        mIWebView.getWebView().loadUrl(url);
    }
}
