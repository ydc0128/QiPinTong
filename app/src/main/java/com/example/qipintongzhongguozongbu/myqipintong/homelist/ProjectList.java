package com.example.qipintongzhongguozongbu.myqipintong.homelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNHezuoAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian.CompanyDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Description: 首页好项目列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/17 下午4:29
 */
public class ProjectList extends BaseFragment {
    @BindView(R.id.id_stickynavlayout_innerscrollview)
    RecyclerView idStickynavlayoutInnerscrollview;
    Unbinder unbinder;

    @Override
    public View initView() {
        return View.inflate(mActivity, R.layout.item_home_list, null);
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


    public static BaseFragment getInstance() {
        return new ProjectList();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setRvAdapter();

        super.onLazyInitView(savedInstanceState);
    }

    private void setRvAdapter() {

        idStickynavlayoutInnerscrollview.setLayoutManager(new LinearLayoutManager(mActivity));

        idStickynavlayoutInnerscrollview.setAdapter(new RvNHezuoAdapter(R.layout.item_hehuoren, ImageList.getImageList()));

        idStickynavlayoutInnerscrollview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                ((SupportFragment) getParentFragment()).start(new CompanyDetailsFragment());
            }
        });

    }
}
