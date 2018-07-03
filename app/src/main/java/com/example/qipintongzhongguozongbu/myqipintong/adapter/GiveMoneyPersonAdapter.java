package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: 创投天下 投资人适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/13 上午10:45
 */
public class GiveMoneyPersonAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {


    public GiveMoneyPersonAdapter(int item_give_person_money, List personDate) {
        super(item_give_person_money, personDate);

    }


    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        Glide.with(mContext).load(item).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror).into((ImageView) helper.getView(R.id.iv_give_person_icon));
    }
}
