package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeBean;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager.HomeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian.CompanyDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.List;

/**
 * Description: 首页手动轮播图的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/18 上午10:05
 */
public class VpBannerAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    private final Activity mActivity;
    private final List<HomeBean.MidBannersBean> list;
    private final HomeFragment homeFragment;


    public VpBannerAdapter(HomeFragment homeFragment, Activity mActivity, List<HomeBean.MidBannersBean> list) {
        this.mActivity = mActivity;
        this.list = list;
        this.homeFragment = homeFragment;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.tab_guide, container, false);
        }
        return convertView;
    }


    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {


        position %= list.size();
        if (position < 0) {
            position = list.size() + position;
        }


        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.fragment_viewpager, container, false);
            ImageView iv = (ImageView) convertView.findViewById(R.id.iv_vp_fragment);
            TextView tv = (TextView) convertView.findViewById(R.id.tv_vp_fragment);

            tv.setText(list.get(position).getTitle() + "");
            GlideUtils.loadImage(mActivity,GlobalConstants.QPT_URl + list.get(position).getImgUrl(),iv);
           // Glide.with(mActivity).load(GlobalConstants.QPT_URl + list.get(position).getImgUrl()).placeholder(R.mipmap.loding).thumbnail(0.1f).error(R.mipmap.bannererror).into(iv);
        }


        LogUtils.e(position + list.get(position).getImgUrl());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.start(new CompanyDetailsFragment());
            }
        });

        return convertView;
    }

    @Override
    public int getItemPosition(Object object) {
        //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
        // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
