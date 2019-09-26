package com.zhangyc.framedemo.publicaddress.contacts;


import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.zhangyc.framedemo.adapter.HomeAdapter;
import com.zhangyc.framedemo.base.BaseRecyclerAdapter;
import com.zhangyc.framedemo.entity.PublicAddress;
import com.zhangyc.framedemo.mvp.IBaseView;
import com.zhangyc.framedemo.serverapi.HttpApiManager;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HomePresenter implements HomeContact.IHomePresenter {

    public static final String TAG = HomePresenter.class.getSimpleName();

    private HomeContact.HomeView mIBaseView;
    private Disposable mDisposable;

    @Override
    public void requestPublicAddressList() {
        mIBaseView.showLoadingDialog();
        mDisposable = HttpApiManager.getInstance().getPublicAddressList()
                .subscribe(new Consumer<List<PublicAddress>>() {
                    @Override
                    public void accept(List<PublicAddress> publicAddresses) {
                        mIBaseView.dismissLoadingDialog();
                        if (mIBaseView != null) mIBaseView.onSuccess();
                        refreshAdapterData(publicAddresses);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mIBaseView.dismissLoadingDialog();
                        if (mIBaseView != null) mIBaseView.onFail(throwable);
                    }
                });
    }

    private void refreshAdapterData(List<PublicAddress> publicAddresses) {
        RecyclerView.Adapter adapter = mIBaseView.getRecyclerView().getAdapter();
        HomeAdapter homeAdapter = null;
        if (adapter instanceof HomeAdapter) homeAdapter = (HomeAdapter) adapter;
        assert homeAdapter != null;
        Log.i(TAG, "refreshAdapterData: size : " + publicAddresses.size());
        homeAdapter.setListData(publicAddresses);
        homeAdapter.notifyDataSetChanged();
        homeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {

            }
        });
    }

    @Override
    public  void attachView(IBaseView iBaseView) {
        mIBaseView = (HomeContact.HomeView) iBaseView;
    }

    @Override
    public void detachView() {
        if (mDisposable != null && mDisposable.isDisposed()) mDisposable.dispose();
        mDisposable = null;
    }

}
