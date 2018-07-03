package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;

import java.util.List;

/**
 * Description: 首页定制列表的自家服务列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/11 上午10:26
 */
public class QptLableAdapter extends BaseQuickAdapter<HomeIconBean, BaseViewHolder> {

    public QptLableAdapter(int layoutResId, List<HomeIconBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeIconBean item) {

        Glide.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv_item_home_icon));

        helper.setText(R.id.tv_item_home_name, item.getTitle());

    }
}
