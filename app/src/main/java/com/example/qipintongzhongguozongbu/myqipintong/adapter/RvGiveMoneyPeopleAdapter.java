package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: 创投天下 投资机构详情 投资人列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/16 下午4:10
 */
public class RvGiveMoneyPeopleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {



    public RvGiveMoneyPeopleAdapter(int item_give_money_people, List<String> peopleDate) {
        super(item_give_money_people, peopleDate);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_give_money_people_name, item);
    }


}
