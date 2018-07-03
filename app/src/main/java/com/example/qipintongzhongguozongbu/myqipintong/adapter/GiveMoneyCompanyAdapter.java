package com.example.qipintongzhongguozongbu.myqipintong.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.GiveMoneyCompanyDate;

import java.util.List;


/**
 * Description: 投资机构的条目适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/10 下午4:10
 */
public class GiveMoneyCompanyAdapter extends BaseQuickAdapter<GiveMoneyCompanyDate, BaseViewHolder> {


    public GiveMoneyCompanyAdapter(int item_give_money, List date) {
        super(item_give_money, date);
    }


    @Override
    protected void convert(BaseViewHolder helper, GiveMoneyCompanyDate item) {
        helper.setText(R.id.tv_give_money_title, item.getTitle().toString()).setText(R.id.tv_give_money_number, item.getNumber() + "");
        Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.iv_item_give_money));
    }
}
