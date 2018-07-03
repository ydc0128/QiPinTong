package com.example.qipintongzhongguozongbu.myqipintong.fragment.xiaoyuanzhipin;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.SchoolStudyAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FacePositionDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Description: 校园365学生兼职的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/16 下午4:02
 */
public class SchoolStudyFragment extends BaseFragment {
    @BindView(R.id.bb_study)
    Banner bbStudy;
    @BindView(R.id.rv_school_study)
    RecyclerView rvSchoolStudy;
    Unbinder unbinder;
    @BindView(R.id.rf_school_study)
    PullToRefreshLayout rfSchoolStudy;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_school_study, null));
    }

    @Override
    public void initData() {

        setBannerImage(bbStudy);

        setRefreshDate(rfSchoolStudy);

        setRecyclerViewAdapter();

        super.initData();
    }

    @Override
    public void onSupportVisible() {

        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("学生兼职");

        super.onSupportVisible();

    }

    private void setRecyclerViewAdapter() {

        rvSchoolStudy.setLayoutManager(new LinearLayoutManager(mActivity));
        rvSchoolStudy.setAdapter(new SchoolStudyAdapter(R.layout.item_time_job, ImageList.getImageList()));

        rvSchoolStudy.addOnItemTouchListener(new OnItemClickListener() {
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
}
