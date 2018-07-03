package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.amap.api.services.core.PoiItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.ArrayList;

/**
 * Description: 选择定位地址的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/3 上午10:10
 */
public class RvCityAdapter extends BaseQuickAdapter<PoiItem, BaseViewHolder> {


    public RvCityAdapter(int layoutResId, ArrayList<PoiItem> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, PoiItem item) {

        int position = helper.getAdapterPosition();

        if (position == 0) {
            helper.setText(R.id.tv_item_place_title, "不显示位置").setVisible(R.id.tv_item_place_body, false);
        } else if (position == 1) {
            helper.setText(R.id.tv_item_place_title, item.getCityName() + "").setTextColor(R.id.tv_item_place_title, R.color.colorBlack).setVisible(R.id.tv_item_place_body, false);
        } else {
            helper.setText(R.id.tv_item_place_title, item.getTitle() + "").setTextColor(R.id.tv_item_place_title, R.color.colorBlack).setText(R.id.tv_item_place_body, item.getSnippet() + "");//+item.getIndoorData()+item.getIndoorData()+item.getPoiExtension()+item.getBusinessArea());
        }
    }
}
