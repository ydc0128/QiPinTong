package com.example.qipintongzhongguozongbu.myqipintong.fragment.jianzhidaren;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvTimeJobAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FacePositionDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 兼职达人页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/22 下午2:25
 */
public class TimeJobFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.rf_time_job)
    PullToRefreshLayout rfTimeJob;
    @BindView(R.id.bb_time_job)
    Banner bbTimeJob;
    @BindView(R.id.bt_time_job_join)
    Button btTimeJobJoin;
    @BindView(R.id.rv_time_job)
    RecyclerView rvTimeJob;


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_time_job, null));
    }


    @Override
    public void initData() {

        setRefreshDate(rfTimeJob);

        setBannerImage(bbTimeJob);

        setTimeJobAdapter();

        super.initData();
    }

    /**
     * function   : 热门兼职适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/22  下午2:55
     */
    private void setTimeJobAdapter() {

        rvTimeJob.setLayoutManager(new LinearLayoutManager(mActivity));
        rvTimeJob.setAdapter(new RvTimeJobAdapter(R.layout.item_time_job, ImageList.getImageList()));
        rvTimeJob.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new FacePositionDetailsFragment());

            }
        });

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

    @OnClick(R.id.bt_time_job_join)
    public void onClick() {
        //我要加入
    }


    @Override
    public void onSupportVisible() {

        mTvTitle.setText("兼职达人");
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }
}
