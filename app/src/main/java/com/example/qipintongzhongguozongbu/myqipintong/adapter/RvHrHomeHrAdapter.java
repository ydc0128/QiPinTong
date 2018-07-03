package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: HR HOME页面 HR 条目
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/18 下午5:04
 */
public class RvHrHomeHrAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public RvHrHomeHrAdapter(int item_hr_home_hr, List<String> date) {
        super(item_hr_home_hr, date);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_hr_home_name, item);
    }

}
