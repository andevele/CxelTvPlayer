package com.cxel.tvplayer.tvmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TvMeuItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint;
    private Drawable mDivider;
    private int mTopMargin = 2;
    private int mMargin = 130;
    private int mDividerwidth = 3;
    private int mDividerWidth = 20;
    private int mOrientation;
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};


    public TvMeuItemDecoration(Context context, int orientation) {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("please input true value！");
        }
        mOrientation = orientation;

        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    public TvMeuItemDecoration(Context context, int orientation, int drawableId) {
        this(context, orientation);
        mDivider = ContextCompat.getDrawable(context, drawableId);
        mDividerwidth = mDivider.getIntrinsicWidth();
    }

    public TvMeuItemDecoration(Context context, int orientation, int mDividerWidth, int dividerColor) {
        //this(context, orientation);
        mOrientation = orientation;
        mDividerwidth = mDividerWidth;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int visualPos = parent.getChildAdapterPosition(view);
//        if(visualPos == 0) {
//            outRect.top = 5;
//        }
        int count = parent.getAdapter().getItemCount();
        if (visualPos == 0) {
            outRect.left = mMargin;
            //outRect.right = mDividerWidth;
        } else if (visualPos == count - 1) {
            outRect.right = mMargin;
        } else {
            //outRect.right = mDividerWidth;
        }
//        if(visualPos == 0) {
//            outRect.top = mTopMargin;
//            outRect.bottom = mDividerHeight;
//        } else {
//            outRect.bottom = mDividerHeight;
//        }
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        if (mOrientation == LinearLayoutManager.VERTICAL) {
//            drawVertical(c, parent);
//        } else {
//            drawHorizontal(c, parent);
//        }
    }
}
