package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: QQ表情的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/6 上午10:33
 */
public class RvqqAdapter extends BaseQuickAdapter<Integer,BaseViewHolder> {
    public RvqqAdapter(int layoutResId, List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        Glide.with(mContext).load(item).placeholder(R.mipmap.loding).thumbnail(0.1f)
                .error(R.mipmap.lodingerror).centerCrop().into((ImageView) helper.getView(R.id.imageView));
    }
}
