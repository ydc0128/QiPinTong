package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;

import java.util.List;

/**
 * Description: 首页第三方服务的适配
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/11 上午11:31
 */
public class OtherLableAdapter extends BaseQuickAdapter<HomeIconBean, BaseViewHolder> {


    public OtherLableAdapter(int layoutResId, List<HomeIconBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final HomeIconBean item) {

        Glide.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv_item_select_icon));


        helper.setText(R.id.tv_item_select_name, item.getTitle());

        if (item.isShow()) {
            Glide.with(mContext).load(R.mipmap.jianhao).into((ImageView) helper.getView(R.id.iv_select_item));
        } else {
            Glide.with(mContext).load(R.mipmap.jiahao).into((ImageView) helper.getView(R.id.iv_select_item));
        }




    }
}
