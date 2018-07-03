package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

/**
 * Description: 首页职位列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/17 下午3:47
 */
public class PositionListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PositionListAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideUtils.loadImage(mContext, item, (ImageView) helper.getView(R.id.siv_full_time_icon));
    }
}
