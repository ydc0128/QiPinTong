package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 高薪兼职的数据适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/7 下午4:09
 */
public class RvProfessionAdapter extends RecyclerView.Adapter {

    private final Activity mActivity;
    private MyViewHolder myViewHolder;


    public RvProfessionAdapter(Activity mActivity) {
        this.mActivity = mActivity;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_school_job_time, null);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.siv_school_job_time_icon)
        SquareImageView sivSchoolJobTimeIcon;
        @BindView(R.id.tv_item_school_post)
        TextView tvItemSchoolPost;
        @BindView(R.id.tv_item_school_tag)
        TextView tvItemSchoolTag;
        @BindView(R.id.tv_item_school_salary)
        TextView tvItemSchoolSalary;
        @BindView(R.id.tv_item_school_company)
        TextView tvItemSchoolCompany;
        @BindView(R.id.iv_item_school_vip)
        ImageView ivItemSchoolVip;
        @BindView(R.id.tv_item_school_shangshi)
        TextView tvItemSchoolShangshi;
        @BindView(R.id.tv_item_school_industry)
        TextView tvItemSchoolIndustry;
        @BindView(R.id.tv_item_school_recruit)
        TextView tvItemSchoolRecruit;
        @BindView(R.id.tv_item_school_person_number)
        TextView tvItemSchoolPersonNumber;
        @BindView(R.id.tv_item_school_education)
        TextView tvItemSchoolEducation;
        @BindView(R.id.tv_item_school_location)
        TextView tvItemSchoolLocation;
        @BindView(R.id.tv_item_school_see_number)
        TextView tvItemSchoolSeeNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AutoUtils.autoSize(itemView);
        }
    }
}
