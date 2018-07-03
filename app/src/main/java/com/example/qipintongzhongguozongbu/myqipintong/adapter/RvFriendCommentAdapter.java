package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: 朋友圈互动页评论的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/1 下午5:12
 */
public class RvFriendCommentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvFriendCommentAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).placeholder(R.mipmap.loding).thumbnail(0.1f).error(R.mipmap.lodingerror).centerCrop()
                .into((ImageView) helper.getView(R.id.iv_city_road_head_portrait));

    }
}
