package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import java.util.ArrayList;

/**
 * Description: 易通学院的fragment vp适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/9 上午9:22
 */
public class VpEasyKnowAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<BaseFragment> mFragments;

    public VpEasyKnowAdapter(FragmentManager fragmentManager, ArrayList<BaseFragment> mFragments) {
        super(fragmentManager);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}