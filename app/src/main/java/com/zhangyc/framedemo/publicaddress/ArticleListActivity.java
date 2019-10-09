package com.zhangyc.framedemo.publicaddress;

import android.content.Context;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhangyc.framedemo.R;
import com.zhangyc.framedemo.adapter.ArticleAdapter;
import com.zhangyc.framedemo.annotations.InjectPresenter;
import com.zhangyc.framedemo.base.BaseActivity;
import com.zhangyc.framedemo.databinding.ActivityArticleListBinding;
import com.zhangyc.framedemo.publicaddress.contacts.ArticleContact;
import com.zhangyc.framedemo.publicaddress.contacts.ArticlePresenter;

public class ArticleListActivity extends BaseActivity implements ArticleContact.IArticleView {

    @InjectPresenter
    ArticlePresenter mArticlePresenter;

    private ActivityArticleListBinding mArticleListBinding;

    @Override
    protected void init() {

    }

    @Override
    protected void initViews() {
        mArticleListBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_list);
        mArticleListBinding.recyclerViewArticleList.setLayoutManager(new LinearLayoutManager(getContext()));
//        mArticleListBinding.recyclerViewArticleList.addItemDecoration(new MyDividerItemDecoration(10, 10 , 10 , 10 ));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mArticleListBinding.recyclerViewArticleList.addItemDecoration(dividerItemDecoration);
        final ArticleAdapter articleAdapter = new ArticleAdapter(getContext());
        mArticleListBinding.recyclerViewArticleList.setAdapter(articleAdapter);
    }

    @Override
    protected void initData() {
        mArticlePresenter.getArticleList(0);
    }

    @Override
    protected void unInit() {

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
        mArticleListBinding.recyclerViewArticleList.setVisibility(View.GONE);
        mArticleListBinding.progressCircularArticle.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoadingDialog() {
        mArticleListBinding.progressCircularArticle.setVisibility(View.GONE);
        mArticleListBinding.recyclerViewArticleList.setVisibility(View.VISIBLE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void systemBack() {
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mArticleListBinding.recyclerViewArticleList;
    }
}
