package com.zhangyc.framedemo.publicaddress.contacts;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.zhangyc.framedemo.adapter.ArticleAdapter;
import com.zhangyc.framedemo.base.BaseRecyclerAdapter;
import com.zhangyc.framedemo.entity.ArticleList;
import com.zhangyc.framedemo.mvp.IBaseView;
import com.zhangyc.framedemo.publicaddress.ArticleListActivity;
import com.zhangyc.framedemo.publicaddress.WebActivity;
import com.zhangyc.framedemo.serverapi.HttpApiManager;


import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ArticlePresenter implements ArticleContact.IArticlePresenter {

    public static final String TAG = ArticlePresenter.class.getSimpleName();

    private Disposable mDisposable;
    private ArticleContact.IArticleView mIArticleView;

    @Override
    public void attachView(IBaseView iBaseView) {
        mIArticleView = (ArticleContact.IArticleView) iBaseView;
    }

    @Override
    public void detachView() {
        if (mDisposable != null && mDisposable.isDisposed()) mDisposable.dispose();
    }

    @Override
    public void getArticleList(int page) {
        mIArticleView.showLoadingDialog();
        mDisposable = HttpApiManager.getInstance().getArticleList(page)
                .subscribe(new Consumer<ArticleList>() {
                    @Override
                    public void accept(final ArticleList articleList) {
                        RecyclerView.Adapter adapter = mIArticleView.getRecyclerView().getAdapter();
                        ArticleAdapter articleAdapter = null;
                        if (adapter instanceof ArticleAdapter) articleAdapter = (ArticleAdapter) adapter;
                        assert articleAdapter != null;
                        articleAdapter.setListData(articleList.getDatas());
                        articleAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void itemClick(int position) {
                                Intent intent = new Intent(mIArticleView.getContext(), WebActivity.class);
                                intent.putExtra("url", articleList.getDatas().get(position).getLink());
//                                mIArticleView.getContext().startActivity(intent);
                                ((ArticleListActivity)mIArticleView.getContext()).startIntentRigthToLeft(intent);
                            }
                        });
                        articleAdapter.notifyDataSetChanged();
                        mIArticleView.onSuccess();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mIArticleView.onFail(throwable);
                    }
                });
    }

}
