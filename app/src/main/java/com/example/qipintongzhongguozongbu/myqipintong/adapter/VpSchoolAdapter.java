package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;

/**
 * Description: 校园365手动轮播图的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/22 下午5:21
 */
public class VpSchoolAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    private final Activity mActivity;
    private final ArrayList imageList;

    public VpSchoolAdapter(Activity mActivity, ArrayList imageList) {
        this.mActivity = mActivity;
        this.imageList = imageList;
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


        position %= imageList.size();
        if (position < 0) {
            position = imageList.size() + position;
        }
        ImageView iv = new ImageView(mActivity);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mActivity).load(imageList.get(position)).crossFade().placeholder(R.mipmap.loding).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.1f).error(R.mipmap.bannererror).centerCrop().into(iv);
        return iv;
    }

    @Override
    public int getItemPosition(Object object) {
        //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
        // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }
}

