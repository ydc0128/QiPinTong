package com.example.qipintongzhongguozongbu.myqipintong.adapter;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

/**
 * Description: 首页商家的列表适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/9 上午11:55
 */
public class RvCommerceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public RvCommerceAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        GlideUtils.loadImage(mContext, item.toString(), (ImageView) helper.getView(R.id.siv_item_host_food));
    }
}
