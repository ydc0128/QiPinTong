package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvTrtTopAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by L on 2017/3/14.
 * 每日排行
 */
public class TodayRankingTopfragment extends BaseFragment {
    @BindView(R.id.rv_trt_top)
    RecyclerView rvTrtTop;
    @BindView(R.id.rf_trt_top)
    BGARefreshLayout rfTrtTop;

    Unbinder unbinder;

    public View initView() {
        return swipeBackView( View.inflate(mActivity, R.layout.background_today_ranking_top, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void initData() {
        setRvTrtTopAdapter();
        super.initData();
    }

    private void setRvTrtTopAdapter() {

        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        rvTrtTop.setLayoutManager(LayoutManager);
        rvTrtTop.setAdapter(new RvTrtTopAdapter(mActivity));
    }

    public void onSupportVisible() {

        mTvTitle.setText("每日排行");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
