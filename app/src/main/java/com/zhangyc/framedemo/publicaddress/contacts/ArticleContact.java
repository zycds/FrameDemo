package com.zhangyc.framedemo.publicaddress.contacts;

import androidx.recyclerview.widget.RecyclerView;

import com.zhangyc.framedemo.mvp.IBasePresenter;
import com.zhangyc.framedemo.mvp.IBaseView;

public class ArticleContact {

    public interface IArticleView extends IBaseView {
        RecyclerView getRecyclerView();
    }

    public interface IArticlePresenter  extends IBasePresenter {
        void getArticleList(int page);
    }

}
