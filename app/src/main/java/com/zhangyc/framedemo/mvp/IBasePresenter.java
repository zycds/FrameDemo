package com.zhangyc.framedemo.mvp;


public interface IBasePresenter {
    void attachView(IBaseView iBaseView);
    void detachView();
}
