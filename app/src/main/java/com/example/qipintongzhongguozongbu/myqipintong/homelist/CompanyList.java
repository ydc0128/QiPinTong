package com.example.qipintongzhongguozongbu.myqipintong.homelist;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCommerceAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Description: 首页商户列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/17 下午4:35
 */
public class CompanyList extends BaseFragment {
    @BindView(R.id.id_stickynavlayout_innerscrollview)
    RecyclerView idStickynavlayoutInnerscrollview;
    Unbinder unbinder;
    private boolean mNoData = true;
    private RvCommerceAdapter adapter;
    private View noDateView;
    private View lodingView;

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
        return new CompanyList();
    }



    private void initRefresh() {

        lodingView = mActivity.getLayoutInflater().inflate(R.layout.loding_view, null);

        noDateView = mActivity.getLayoutInflater().inflate(R.layout.empty_view, null);
        noDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });


    }


    private void onRefresh() {
        adapter.setEmptyView(lodingView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mNoData) {
                    adapter.setNewData(ImageList.getImageList());
                    mNoData = false;
                }
            }
        }, 1000);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        initRefresh();

        setRvAdapter();

        onRefresh();

        super.onLazyInitView(savedInstanceState);
    }

    private void setRvAdapter() {

        idStickynavlayoutInnerscrollview.setLayoutManager(new LinearLayoutManager(mActivity));

        adapter = new RvCommerceAdapter(R.layout.item_host_food, ImageList.getEmptyList());

        idStickynavlayoutInnerscrollview.setAdapter(adapter);

        idStickynavlayoutInnerscrollview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                ((SupportFragment) getParentFragment()).start(new CommerceDetailsFragment());
            }
        });

    }
}
