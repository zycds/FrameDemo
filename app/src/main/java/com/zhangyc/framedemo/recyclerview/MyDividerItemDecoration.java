package com.zhangyc.framedemo.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    private int top, left, right, bottom;

    private Drawable mDrawable;

    private int mResColorId;

    private Color mColor;

    public MyDividerItemDecoration(int top, int left, int right, int bottom) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public MyDividerItemDecoration(Drawable drawable) {
        mDrawable = drawable;
    }

    public MyDividerItemDecoration(Color color) {
        mColor = color;
    }

    public MyDividerItemDecoration(int resColorId) {
        mResColorId = resColorId;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(left, top, right, bottom);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        if (mColor != null) {

        }
        c.drawRect(0, 100, 3000, 0 , paint);
    }
}
