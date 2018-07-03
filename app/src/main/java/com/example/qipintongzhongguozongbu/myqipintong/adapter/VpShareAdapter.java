package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import java.util.ArrayList;

/**
 * 共享记录的vp适配器
 * Created by Administrator on 2017/6/3.
 */

public class VpShareAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<BaseFragment> mFragments;

    public VpShareAdapter(FragmentManager fragmentManager, ArrayList<BaseFragment> mFragments) {
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
