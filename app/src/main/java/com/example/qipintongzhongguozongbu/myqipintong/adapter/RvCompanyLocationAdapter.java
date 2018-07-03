package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/18 下午7:31
 */
public class RvCompanyLocationAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public RvCompanyLocationAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_text, item);
    }
}