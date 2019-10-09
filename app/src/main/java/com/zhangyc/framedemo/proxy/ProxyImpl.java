package com.zhangyc.framedemo.proxy;

import android.util.Log;

import com.zhangyc.framedemo.annotations.InjectPresenter;
import com.zhangyc.framedemo.mvp.IBasePresenter;
import com.zhangyc.framedemo.mvp.IBaseView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProxyImpl implements IProxyBaseView {

    public static final String TAG = ProxyImpl.class.getSimpleName();

    private List<IBasePresenter> mInjectPresenters;


    public List<IBasePresenter> getInjectPresenters() {
        return mInjectPresenters;
    }

    @Override
    public void binderPresenter(IBaseView baseView) {
        mInjectPresenters = new ArrayList<>();
        Field[] fields = baseView.getClass().getDeclaredFields();
        Log.e(TAG, "binderPresenter: " + Arrays.toString(fields));
        for (Field f : fields) {
            InjectPresenter annotation = f.getAnnotation(InjectPresenter.class);
            if (annotation != null) {
                try {
                    Class<? extends IBasePresenter> type = (Class<? extends IBasePresenter>) f.getType();
                    IBasePresenter basePresenter = type.newInstance();
                    basePresenter.attachView(baseView);
                    f.setAccessible(true);
                    f.set(baseView, basePresenter);
                    mInjectPresenters.add(basePresenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void unBinderPresenter() {
        if (mInjectPresenters != null) {
            for (IBasePresenter p : mInjectPresenters) {
                p.detachView();
            }
            mInjectPresenters.clear();
        }
        mInjectPresenters = null;
    }
}
