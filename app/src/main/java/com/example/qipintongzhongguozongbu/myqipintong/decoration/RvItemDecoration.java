package com.example.qipintongzhongguozongbu.myqipintong.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/17 下午9:20
 */
public class RvItemDecoration extends RecyclerView.ItemDecoration {


    private final int i;

    public RvItemDecoration(int i) {
        this.i = i;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = i;
        outRect.left = i;
        outRect.top = i;
        outRect.bottom = i;
    }
}
