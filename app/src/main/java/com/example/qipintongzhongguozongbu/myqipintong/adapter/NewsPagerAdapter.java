package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: 易通学院新闻的列表适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/9 上午10:36
 */
public class NewsPagerAdapter extends BaseQuickAdapter {
    public NewsPagerAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
//        helper.setText(R.id.tv_news_comment, item.toString());
//        helper.setText(R.id.tv_news_time, item.toString());
//        helper.setVisible(R.id.tv_news_label,false);
        Glide.with(mContext).load(item).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror).into((ImageView) helper.getView(R.id.iv_news_icon));
    }

}
