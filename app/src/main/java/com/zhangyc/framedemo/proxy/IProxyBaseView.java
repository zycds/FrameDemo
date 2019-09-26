package com.zhangyc.framedemo.proxy;

import com.zhangyc.framedemo.mvp.IBaseView;

public interface IProxyBaseView {

    void binderPresenter(IBaseView baseView);

    void unBinderPresenter();

}
