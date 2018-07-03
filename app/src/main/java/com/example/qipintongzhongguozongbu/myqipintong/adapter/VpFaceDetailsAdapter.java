package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.WrapContentViewPager;

import java.util.ArrayList;

/**
 * Description: 个人详情的页面适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/17 上午10:23
 */
public class VpFaceDetailsAdapter extends FragmentPagerAdapter {


    private final ArrayList<BaseFragment> fragmentList;

    public VpFaceDetailsAdapter(FragmentManager fragmentManager, ArrayList<BaseFragment> fragmentList) {
        super(fragmentManager);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}
