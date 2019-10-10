package com.zhangyc.framedemo.publicaddress;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.zhangyc.framedemo.utils.PermissionUtil;


public class HomeActivity extends BaseActivity implements HomeContact.HomeView {

    private ActivityHomeBinding mHomeBinding;

    @InjectPresenter
    HomePresenter mHomePresenter;

    private String[] mPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_NETWORK_STATE
    };


    @Override
    protected void init() {
        PermissionUtil.requestPermission(this, mPermissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtil.REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    Log.e(TAG, "onRequestPermissionsResult: " + permissions[i]);
                } else {
                    Log.i(TAG, "onRequestPermissionsResult: " + permissions[i]);
                }
            }
        }
    }

    @Override
    protected void initViews() {
        mHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mHomeBinding.recyclerViewPublicAddress.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mHomeBinding.recyclerViewPublicAddress.addItemDecoration(dividerItemDecoration);

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
    protected void systemBack() {
        finish();
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
