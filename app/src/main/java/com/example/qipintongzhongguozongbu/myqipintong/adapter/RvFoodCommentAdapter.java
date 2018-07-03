package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 食品的评论列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/10 下午3:04
 */
public class RvFoodCommentAdapter extends RecyclerView.Adapter {

    private final Activity mActivity;
    private MyViewHolder myViewHolder;

    public RvFoodCommentAdapter(Activity mActivity) {
        this.mActivity = mActivity;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_food_comment, null);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_person_icon)
        CircleImageView civPersonIcon;
        @BindView(R.id.tv_person_name)
        TextView tvPersonName;
        @BindView(R.id.tv_person_time)
        TextView tvPersonTime;
        @BindView(R.id.tv_food_discuss)
        TextView tvFoodDiscuss;

        public MyViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
