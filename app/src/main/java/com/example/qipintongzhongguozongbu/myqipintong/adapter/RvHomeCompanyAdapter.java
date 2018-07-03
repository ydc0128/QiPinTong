package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager.HomeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 名企在线的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/5 下午4:07
 */
public class RvHomeCompanyAdapter extends RecyclerView.Adapter {

    private final Activity mActivity;
    private final HomeFragment homeFragment;
    private final List date;
    private MyViewHolder myViewHolder;
    private OnItemClickListener onItemClickListener;


    public RvHomeCompanyAdapter(Activity mActivity, HomeFragment homeFragment, List date) {
        this.mActivity = mActivity;
        this.homeFragment = homeFragment;
        this.date = date;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_home_company, parent, false);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        myViewHolder.setOnItemClickListener(onItemClickListener);

        if (ImageList.getImageList().size() == position) {//这里是一张查看更多的图片
            Glide.with(mActivity).load(R.drawable.icon).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror).into(myViewHolder.sivHomeCompany);

        } else {
            Glide.with(mActivity).load(ImageList.getImageList().get(position)).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror).into(myViewHolder.sivHomeCompany);
        }

    }

    @Override
    public int getItemCount() {
        return ImageList.getImageList().size() + 1;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.tv_company_location)
        TextView tvCompanyLocation;
        @BindView(R.id.siv_home_company)
        SquareImageView sivHomeCompany;
        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.iv_company_vip)
        ImageView ivCompanyVip;
        @BindView(R.id.tv_company_see_number)
        TextView tvCompanySeeNumber;
        @BindView(R.id.iv_company_eye)
        ImageView ivCompanyEye;
        @BindView(R.id.iv_company_location)
        ImageView ivCompanyLocation;
        @BindView(R.id.tv_company_year)
        TextView tvCompanyYear;
        @BindView(R.id.tv_company_education)
        TextView tvCompanyEducation;
        @BindView(R.id.iv_company_education)
        ImageView ivCompanyEducation;
        @BindView(R.id.rl_company)
        RelativeLayout rlCompany;

        OnItemClickListener mOnItemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AutoUtils.autoSize(itemView);
            itemView.setOnClickListener(this);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
