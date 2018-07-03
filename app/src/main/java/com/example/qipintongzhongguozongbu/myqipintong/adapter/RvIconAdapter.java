package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia.CreateWorldFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager.SelectHostTltieFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.jianzhidaren.TimeJobFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian.FamousCompanyFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.quanzhisupin.FullTimeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.xiaoyuanzhipin.SchoolPagerFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FaceAndTalentFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.PageRecyclerView.CallBack;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.util.ArrayList;

/**
 * Description: 首页图标的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/19 下午3:17
 */
public class RvIconAdapter implements CallBack {

    private final  BaseFragment fragment;
    private final Activity mActivity;
    private final ArrayList<HomeIconBean> iconList;

    public RvIconAdapter(Activity mActivity, BaseFragment fragment, ArrayList<HomeIconBean> iconList) {
        this.fragment = fragment;
        this.mActivity = mActivity;
        this.iconList = iconList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_icon_type, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyHolder) holder).tvName.setText(iconList.get(position).getTitle());
        Glide.with(mActivity).load(iconList.get(position).getIcon()).into(((MyHolder) holder).mIvIcon);
    }

    @Override
    public void onItemClickListener(View view, int position) {
        switch (position) {
            case 0://全职速聘
                fragment.start(new FullTimeFragment());
                break;
            case 1://兼职达人
                fragment.start(new TimeJobFragment());
                break;
            case 2://校园直聘
                fragment.start(new SchoolPagerFragment());
                break;
            case 3://名企在线
                fragment.start(new FamousCompanyFragment());
                break;
            case 4://有才有貌
                fragment.start(new FaceAndTalentFragment());
                break;
            case 5://创孵天下
                fragment.start(new CreateWorldFragment());
                break;
            case 6://有商有客
                fragment.start(new CommerceFragment());
                break;
            case 7://全部
                fragment.start(new SelectHostTltieFragment());
                break;
        }
    }

    @Override
    public void onItemLongClickListener(View view, int position) {

    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tvName = null;
        public SquareImageView mIvIcon = null;

        public MyHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_type_name);
            mIvIcon = (SquareImageView) itemView.findViewById(R.id.iv_item_type_icon);
        }
    }
}
