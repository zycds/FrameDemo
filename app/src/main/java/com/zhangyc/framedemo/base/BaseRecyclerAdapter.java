package com.zhangyc.framedemo.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder, TData> extends RecyclerView.Adapter<VH> {

    public static final String TAG = BaseRecyclerAdapter.class.getSimpleName();

    private Context mContext;

    protected List<TData> mTData = new ArrayList<>();

    public BaseRecyclerAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(getLayoutResId(), parent, false);
        return createVH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        setItemData(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) mOnItemClickListener.itemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: " + mTData);
       return mTData.size();
    }

    public abstract int getLayoutResId();

    public abstract void setItemData(VH vh, int position);

    public void setListData(List<TData> tDataList) {
        mTData.clear();
        mTData.addAll(tDataList);
    }

    public abstract VH createVH(View view);

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void itemClick(int position);
    }

}
