package com.zhangyc.framedemo.publicaddress;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;

import androidx.databinding.DataBindingUtil;

import com.zhangyc.framedemo.R;
import com.zhangyc.framedemo.annotations.InjectPresenter;
import com.zhangyc.framedemo.base.BaseActivity;
import com.zhangyc.framedemo.databinding.ActivityWebviewBinding;
import com.zhangyc.framedemo.publicaddress.contacts.WebPresenter;
import com.zhangyc.framedemo.publicaddress.contacts.WebViewContact;

public class WebActivity extends BaseActivity implements WebViewContact.IWebView {

    @InjectPresenter
    WebPresenter mWebPresenter;

    private String url;
    private ActivityWebviewBinding mBinding;

    @Override
    protected void init() {
        url = getIntent().getStringExtra("url");
    }

    @Override
    protected void initViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
    }

    @Override
    protected void initData() {
        mWebPresenter.loadUrl(url);
    }

    @Override
    protected void unInit() {

    }

    @Override
    protected void systemBack() {
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    @Override
    public void onSuccess() {
        dismissLoadingDialog();
    }

    @Override
    public void onFail(Throwable throwable) {
        dismissLoadingDialog();
    }

    @Override
    public void showLoadingDialog() {
        mBinding.webView.setVisibility(View.GONE);
        mBinding.progressWeb.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoadingDialog() {
        mBinding.webView.setVisibility(View.VISIBLE);
        mBinding.progressWeb.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public WebView getWebView() {
        return mBinding.webView;
    }
}
