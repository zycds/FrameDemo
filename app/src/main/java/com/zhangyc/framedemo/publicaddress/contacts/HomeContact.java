package com.zhangyc.framedemo.publicaddress.contacts;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.zhangyc.framedemo.mvp.IBasePresenter;
import com.zhangyc.framedemo.mvp.IBaseView;


public class HomeContact {

    public interface HomeView extends IBaseView {
        RecyclerView getRecyclerView();
        ViewPager getViewPager();
    }

    public interface IHomePresenter extends IBasePresenter {
        void requestBanner();
        void requestPublicAddressList();
    }

}
