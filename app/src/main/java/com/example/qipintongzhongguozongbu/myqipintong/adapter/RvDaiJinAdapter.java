package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description: 有商有客店铺的代金券列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/9 上午11:00
 */
public class RvDaiJinAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvDaiJinAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final String item) {
        helper.setText(R.id.tv_item_daijin, item);

        helper.addOnClickListener(R.id.tv_item_get);

        helper.getView(R.id.tv_item_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setText(R.id.tv_item_get, "已领取");
            }
        });

    }
}
