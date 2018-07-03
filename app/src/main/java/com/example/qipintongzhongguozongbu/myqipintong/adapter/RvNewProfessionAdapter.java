package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.ArrayList;

/**
 * Description: 首页高薪兼职适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/11 上午10:23
 */
public class RvNewProfessionAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public RvNewProfessionAdapter(int item_school_job_time, ArrayList imageList) {
        super(item_school_job_time, imageList);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror).into((ImageView) helper.getView(R.id.siv_school_job_time_icon));
    }
}
