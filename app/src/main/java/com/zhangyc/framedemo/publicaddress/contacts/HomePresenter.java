package com.zhangyc.framedemo.publicaddress.contacts;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.zhangyc.framedemo.adapter.HomeAdapter;
import com.zhangyc.framedemo.base.BaseRecyclerAdapter;
import com.zhangyc.framedemo.entity.BannerBean;
import com.zhangyc.framedemo.entity.PublicAddress;
import com.zhangyc.framedemo.mvp.IBaseView;
import com.zhangyc.framedemo.publicaddress.ArticleListActivity;
import com.zhangyc.framedemo.serverapi.HttpApiManager;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class HomePresenter implements HomeContact.IHomePresenter {

    public static final String TAG = HomePresenter.class.getSimpleName();

    private HomeContact.HomeView mIBaseView;
    private Disposable mDisposable;
    private Disposable mDisposable2;

    @Override
    public void requestBanner() {
        mDisposable2 = HttpApiManager.getInstance().getBanners()
                .subscribe(new Consumer<List<BannerBean>>() {
                    @Override
                    public void accept(List<BannerBean> bannerBeans) {
                        Log.i(TAG, "accept: " + bannerBeans.size());
                        refreshViewPagerAdapterData(bannerBeans);
                        if (mIBaseView != null) mIBaseView.onSuccess();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        if (mIBaseView != null) mIBaseView.onFail(throwable);
                    }
                });
    }

    @Override
    public void requestPublicAddressList() {
        mIBaseView.showLoadingDialog();
        mDisposable = HttpApiManager.getInstance().getPublicAddressList()
                .subscribe(new Consumer<List<PublicAddress>>() {
                    @Override
                    public void accept(List<PublicAddress> publicAddresses) {
                        if (mIBaseView != null) mIBaseView.onSuccess();
                        refreshAdapterData(publicAddresses);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        if (mIBaseView != null) mIBaseView.onFail(throwable);
                    }
                });
    }

    private void refreshAdapterData(List<PublicAddress> publicAddresses) {
        RecyclerView.Adapter adapter = mIBaseView.getRecyclerView().getAdapter();
        HomeAdapter homeAdapter = null;
        if (adapter instanceof HomeAdapter) homeAdapter = (HomeAdapter) adapter;
        assert homeAdapter != null;
        homeAdapter.setListData(publicAddresses);
        homeAdapter.notifyDataSetChanged();
        homeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                mIBaseView.getContext().startActivity(new Intent(mIBaseView.getContext(), ArticleListActivity.class));
            }
        });
    }

    private void refreshViewPagerAdapterData(final List<BannerBean> bannerBeans) {
        mIBaseView.getViewPager().setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return bannerBeans.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = new ImageView(mIBaseView.getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(mIBaseView.getContext()).load(bannerBeans.get(position).getImagePath()).into(imageView);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View)object);
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
        if (mDisposable2 != null && mDisposable2.isDisposed()) mDisposable2.dispose();
        mDisposable = null;
        mDisposable2 = null;
    }

}
