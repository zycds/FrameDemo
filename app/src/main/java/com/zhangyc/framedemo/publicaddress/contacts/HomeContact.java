package com.zhangyc.framedemo.publicaddress.contacts;

import androidx.recyclerview.widget.RecyclerView;

import com.zhangyc.framedemo.mvp.IBasePresenter;
import com.zhangyc.framedemo.mvp.IBaseView;


public class HomeContact {

    public interface HomeView extends IBaseView {
        void showLoadingDialog();

        void dismissLoadingDialog();

        RecyclerView getRecyclerView();
    }

    public interface IHomePresenter extends IBasePresenter {
        void requestPublicAddressList();
    }

}
