package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;

import java.util.ArrayList;

/**
 * Description: 首页图标的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/17 下午3:57
 */
public class RvHomeIconAdapter extends BaseQuickAdapter<HomeIconBean, BaseViewHolder> {

    public RvHomeIconAdapter(int item_home_icon, ArrayList iconDate) {
        super(item_home_icon, iconDate);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeIconBean item) {

        Glide.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv_item_type_icon));

        helper.setText(R.id.tv_item_type_name, item.getTitle().toString());

    }
}
