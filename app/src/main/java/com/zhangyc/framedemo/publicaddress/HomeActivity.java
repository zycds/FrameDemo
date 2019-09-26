package com.zhangyc.framedemo.publicaddress;

import android.content.Context;
import android.util.Log;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        Log.d(TAG, "initViews: ");
        mHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mHomeBinding.recyclerViewPublicAddress.setLayoutManager(new LinearLayoutManager(getContext()));
        HomeAdapter homeAdapter = new HomeAdapter(getContext());
        mHomeBinding.recyclerViewPublicAddress.setAdapter(homeAdapter);
    }

    @Override
    protected void initData() {
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

}
