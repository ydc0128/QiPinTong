package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qipintongzhongguozongbu.myqipintong.activity.BootActivity;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;

/**
 * Description: 引导页面的数据适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/3 下午7:23
 */
public class VpBootViewPager extends PagerAdapter {

    private final BootActivity bootActivity;

    public VpBootViewPager(BootActivity bootActivity) {
        this.bootActivity = bootActivity;
    }

    @Override
    public int getCount() {
        return GlobalConstants.mIvList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(bootActivity);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(bootActivity).load(GlobalConstants.mIvList[position]).into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
