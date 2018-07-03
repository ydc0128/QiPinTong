package com.example.qipintongzhongguozongbu.myqipintong.index;

import android.view.View;
import android.view.ViewGroup;

/**
 * Description: 通用的RecyclerView 的ItemClickListener，包含点击和长按
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/24 下午2:59
 */
public interface OnItemClickListener<T>
{
    void onItemClick(ViewGroup parent, View view, T t, int position);
    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}