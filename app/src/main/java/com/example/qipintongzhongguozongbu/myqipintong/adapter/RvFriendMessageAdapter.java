package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

import static com.example.qipintongzhongguozongbu.myqipintong.R.id.iv_item_friend_icon;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/23 上午10:16
 */
public class RvFriendMessageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvFriendMessageAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideUtils.loadImage(mContext,item, (ImageView) helper.getView(iv_item_friend_icon));
    }
}
