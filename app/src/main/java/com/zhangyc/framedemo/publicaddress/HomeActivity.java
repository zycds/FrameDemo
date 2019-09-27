package com.zhangyc.framedemo.publicaddress;

import android.content.Context;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.zhangyc.framedemo.R;
import com.zhangyc.framedemo.adapter.HomeAdapter;
import com.zhangyc.framedemo.annotations.InjectPresenter;
import com.zhangyc.framedemo.base.BaseActivity;
import com.zhangyc.framedemo.databinding.ActivityHomeBinding;
import com.zhangyc.framedemo.publicaddress.contacts.HomeContact;
import com.zhangyc.framedemo.publicaddress.contacts.HomePresenter;


public class HomeActivity extends BaseActivity implements HomeContact.HomeView {

    private ActivityHomeBinding mHomeBinding;

    @InjectPresenter
    HomePresenter mHomePresenter;

    @Override
    protected void init() {

    }

    @Override
    protected void initViews() {
        mHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mHomeBinding.recyclerViewPublicAddress.setLayoutManager(new LinearLayoutManager(getContext()));
        HomeAdapter homeAdapter = new HomeAdapter(getContext());
        mHomeBinding.recyclerViewPublicAddress.setAdapter(homeAdapter);
    }

    @Override
    protected void initData() {
        mHomePresenter.requestBanner();
        mHomePresenter.requestPublicAddressList();
    }

    @Override
    protected void unInit() {
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mHomeBinding.recyclerViewPublicAddress;
    }

    @Override
    public ViewPager getViewPager() {
        return mHomeBinding.viewPager;
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
        mHomeBinding.recyclerViewPublicAddress.setVisibility(View.GONE);
        mHomeBinding.progressCircular.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoadingDialog() {
        mHomeBinding.progressCircular.setVisibility(View.GONE);
        mHomeBinding.recyclerViewPublicAddress.setVisibility(View.VISIBLE);
    }

}
