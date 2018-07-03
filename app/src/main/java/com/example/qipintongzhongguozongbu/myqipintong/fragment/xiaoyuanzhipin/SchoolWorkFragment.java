package com.example.qipintongzhongguozongbu.myqipintong.fragment.xiaoyuanzhipin;

import android.view.View;

import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;


/**
 * Description: 校园365就业指南的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/16 下午5:23
 */
public class SchoolWorkFragment extends BaseFragment {
    @Override
    public View initView() {
        return swipeBackView(null);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onSupportVisible() {

        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("就业指南");

        super.onSupportVisible();

    }
}
