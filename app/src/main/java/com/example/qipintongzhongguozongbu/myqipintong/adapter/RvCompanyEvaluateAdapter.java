package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: 公司详情的面试评价列表适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/18 下午7:36
 */
public class RvCompanyEvaluateAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public RvCompanyEvaluateAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).crossFade().placeholder(R.mipmap.loding).diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f).error(R.mipmap.lodingerror).centerCrop().into((ImageView) helper.getView(R.id.civ_item_company_icon));
    }
}
