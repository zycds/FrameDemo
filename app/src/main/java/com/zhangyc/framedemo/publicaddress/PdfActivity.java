package com.zhangyc.framedemo.publicaddress;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.zhangyc.framedemo.R;
import com.zhangyc.framedemo.annotations.InjectPresenter;
import com.zhangyc.framedemo.base.BaseActivity;
import com.zhangyc.framedemo.databinding.ActivityPdfBinding;
import com.zhangyc.framedemo.publicaddress.contacts.PdfContact;
import com.zhangyc.framedemo.publicaddress.contacts.PdfPresenter;

public class PdfActivity extends BaseActivity implements PdfContact.IPdfView {

    @InjectPresenter
    PdfPresenter mPdfPresenter;

    ActivityPdfBinding mBinding;


    @Override
    protected void init() {

    }


    @Override
    protected void initViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_pdf);
    }

    @Override
    protected void initData() {
        mPdfPresenter.loadPdf();
    }

    @Override
    protected void unInit() {

    }

    @Override
    protected void systemBack() {
        finish();
        overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(Throwable throwable) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public ImageView getDisplayImageView() {
        return mBinding.imagePdf;
    }
}
