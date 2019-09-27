package com.zhangyc.framedemo.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zhangyc.framedemo.R;
import com.zhangyc.framedemo.base.BaseRecyclerAdapter;
import com.zhangyc.framedemo.databinding.ItemHomeListBinding;
import com.zhangyc.framedemo.entity.PublicAddress;


public class HomeAdapter extends BaseRecyclerAdapter<HomeViewHolder, PublicAddress> {

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_home_list;
    }

    @Override
    public void setItemData(HomeViewHolder viewHolder, int position) {
        viewHolder.itemHomeListBinding.setPublicAddress(mTData.get(position));
    }

    @Override
    public HomeViewHolder createVH(View view) {
        return new HomeViewHolder(view);
    }
}

class HomeViewHolder extends RecyclerView.ViewHolder {
    ItemHomeListBinding itemHomeListBinding;
    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        itemHomeListBinding = DataBindingUtil.bind(itemView);
    }
}