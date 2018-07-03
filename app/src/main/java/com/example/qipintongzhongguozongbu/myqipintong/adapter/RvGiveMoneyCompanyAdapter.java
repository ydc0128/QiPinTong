package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: 创投天下 投资人 投资机构列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/16 下午12:02
 */
public class RvGiveMoneyCompanyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvGiveMoneyCompanyAdapter(int item_give_money_company, List companyDate) {
        super(item_give_money_company, companyDate);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).crossFade().placeholder(R.mipmap.loding).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.1f).error(R.mipmap.lodingerror).centerCrop().into((ImageView) helper.getView(R.id.iv_give_money_company_icon));
    }

}
