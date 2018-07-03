package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: 创投天下 投资机构 投资案例条目适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/17 上午10:22
 */
public class RvGiveMoneyCaseAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvGiveMoneyCaseAdapter(int item_give_money_case, List<String> peopleDate) {
        super(item_give_money_case, peopleDate);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_give_money_case_title, item);
    }
}
