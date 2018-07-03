package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.ArrayList;

/**
 * Description: 名企在线的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/20 上午11:57
 */
public class RvFamousAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public RvFamousAdapter(int item_person_photo, ArrayList imageList) {
        super(item_person_photo, imageList);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        GlideUtils.loadImage(mContext,item,(ImageView) helper.getView(R.id.siv_item_youming));
    }
}
