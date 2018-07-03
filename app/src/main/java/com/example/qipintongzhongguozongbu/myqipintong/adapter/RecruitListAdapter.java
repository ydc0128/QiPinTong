package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.RecruitListDate;

import java.util.ArrayList;

/**
 * Description: 招聘会条目数据适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/9 下午3:33
 */
public class RecruitListAdapter extends BaseQuickAdapter<RecruitListDate,BaseViewHolder> {


    public RecruitListAdapter(int item_recruit_list, ArrayList data) {
        super(item_recruit_list, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, RecruitListDate item) {
        helper.setText(R.id.tv_recruit_list_time,item.getTime().toString());
        //helper.setText(R.id.tv_recruit_list_title, item.getLocation().toString());
        helper.setText(R.id.tv_recruit_list_location, item.getLocation().toString());
        Glide.with(mContext).load(R.drawable.iv_2).into((ImageView) helper.getView(R.id.iv_recruit_list_icon));
    }
}
