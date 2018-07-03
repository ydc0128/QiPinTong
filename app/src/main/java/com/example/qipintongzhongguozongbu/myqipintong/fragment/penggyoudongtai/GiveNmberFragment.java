package com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvGiveNumberAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Description: 朋友圈几人打赏的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/22 下午3:58
 */
public class GiveNmberFragment extends BaseFragment {
    @BindView(R.id.rv_youshangyouke)
    RecyclerView rv;
    @BindView(R.id.srl_commerce)
    SwipeRefreshLayout srl;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_recyclerview, null));
    }

    @Override
    public void initData() {

        srl.setEnabled(false);

        setAdapter();

        super.initData();
    }

    private void setAdapter() {

        rv.setLayoutManager(new LinearLayoutManager(mActivity));

        rv.setAdapter(new RvGiveNumberAdapter(R.layout.item_give_number, ImageList.getImageList()));

    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("打赏记录");
        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();
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
