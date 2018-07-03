package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

/**
 * Description: ??????????????
 * Copyright  : Copyright (c) 2016
 * Author     : ???????
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/7 ??5:19
 */
public class CommerceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CommerceAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        GlideUtils.loadImage(mContext,item,(ImageView) helper.getView(R.id.iv_item_food));

    }
}
