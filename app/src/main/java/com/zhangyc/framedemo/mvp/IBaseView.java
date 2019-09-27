package com.zhangyc.framedemo.mvp;


import android.content.Context;

public interface IBaseView  {
    void onSuccess();
    void onFail(Throwable throwable);
    void showLoadingDialog();
    void dismissLoadingDialog();
    Context getContext();

}
