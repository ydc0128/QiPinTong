package com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.NewsPagerAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: 首页标签对应的列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/16 上午11:53
 */
public class HostListFragment extends BaseFragment {


    @BindView(R.id.id_stickynavlayout_innerscrollview)
    RecyclerView idStickynavlayoutInnerscrollview;
    Unbinder unbinder;

    @Override
    public View initView() {
        return View.inflate(mActivity, R.layout.item_home_list, null);
    }


    public static BaseFragment getInstance() {
        return new HostListFragment();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setRvAdapter();

        super.onLazyInitView(savedInstanceState);
    }

    private void setRvAdapter() {

        idStickynavlayoutInnerscrollview.setLayoutManager(new LinearLayoutManager(mActivity));

        idStickynavlayoutInnerscrollview.setAdapter(new NewsPagerAdapter(R.layout.item_news, ImageList.getImageList()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
