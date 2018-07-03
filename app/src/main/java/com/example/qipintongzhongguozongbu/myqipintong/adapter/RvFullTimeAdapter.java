package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 全职速聘的数据适配
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/7 下午2:54
 */
public class RvFullTimeAdapter extends RecyclerView.Adapter {

    private final Activity mActivity;
    private MyViewHolder myViewHolder;
    private OnItemClickListener onItemClickListener;

    public RvFullTimeAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setOnItemClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_full_time_job, null);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        myViewHolder.setOnItemClick(onItemClickListener);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.siv_full_time_icon)
        SquareImageView sivFullTimeIcon;
        @BindView(R.id.tv_full_time_post)
        TextView tvFullTimePost;
        @BindView(R.id.tv_full_time_salary)
        TextView tvFullTimeSalary;
        @BindView(R.id.tv_full_time_company)
        TextView tvFullTimeCompany;
        @BindView(R.id.iv_full_time_vip)
        ImageView ivFullTimeVip;
        @BindView(R.id.tv_full_time_shangshi)
        TextView tvFullTimeShangshi;
        @BindView(R.id.tv_full_time_education)
        TextView tvFullTimeEducation;
        @BindView(R.id.iv_full_time_time)
        ImageView ivFullTimeTime;
        @BindView(R.id.tv_full_time_year)
        TextView tvFullTimeYear;
        @BindView(R.id.tv_full_time_location)
        TextView tvFullTimeLocation;
        @BindView(R.id.tv_full_time_see_number)
        TextView tvFullTimeSeeNumber;
        private OnItemClickListener onItemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AutoUtils.autoSize(itemView);
            itemView.setOnClickListener(this);
        }

        public void setOnItemClick(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(getAdapterPosition());
            }

        }
    }

}
