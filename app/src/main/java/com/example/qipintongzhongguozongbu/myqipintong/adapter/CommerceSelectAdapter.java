package com.example.qipintongzhongguozongbu.myqipintong.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;

import java.util.List;


/**
 * Description: ?????????????
 * Copyright  : Copyright (c) 2016
 * Author     : ???????
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/18 ??9:26
 */
public class CommerceSelectAdapter extends BaseQuickAdapter<HomeIconBean, BaseViewHolder> {

    public CommerceSelectAdapter(int layoutResId, List<HomeIconBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeIconBean item) {

        Glide.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv_item_type_icon));

        helper.setText(R.id.tv_item_type_name, item.getTitle().toString());

    }
}
