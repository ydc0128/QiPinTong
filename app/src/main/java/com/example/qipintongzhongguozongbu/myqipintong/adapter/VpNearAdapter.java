package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import java.util.ArrayList;

/**
 * Description: 附近的vp适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/27 下午12:04
 */
public class VpNearAdapter extends FragmentStatePagerAdapter {


    private final ArrayList<BaseFragment> mFragments;

    public VpNearAdapter(FragmentManager fm, ArrayList<BaseFragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
