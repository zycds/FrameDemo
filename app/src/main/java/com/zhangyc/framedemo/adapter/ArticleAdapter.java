package com.zhangyc.framedemo.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zhangyc.framedemo.R;
import com.zhangyc.framedemo.base.BaseRecyclerAdapter;
import com.zhangyc.framedemo.databinding.ItemArticleListBinding;
import com.zhangyc.framedemo.entity.Article;

public class ArticleAdapter extends BaseRecyclerAdapter<ViewHolder, Article> {

    public ArticleAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_article_list;
    }

    @Override
    public void setItemData(ViewHolder viewHolder, int position) {
        viewHolder.mBinding.setArticle(mTData.get(position));
    }

    @Override
    public ViewHolder createVH(View view) {
        return new ViewHolder(view);
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    ItemArticleListBinding mBinding;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }
}
