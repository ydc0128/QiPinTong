package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: 创投天下 投资人 投资偏好列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/16 上午11:10
 */
public class RvGiveMoneyPersonAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvGiveMoneyPersonAdapter(int item_give_money_person, List date) {
        super(item_give_money_person, date);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_give_money_person_title, item);//.setText(R.id.tv_item_give_money_person_body, item);
    }
}
