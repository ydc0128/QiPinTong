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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.SchoolTestAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FacePositionDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Description: 校园365我要实习的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/16 下午4:32
 */
public class SchoolTestFragment extends BaseFragment {
    @BindView(R.id.bb_test)
    Banner bbTest;
    @BindView(R.id.rv_school_test)
    RecyclerView rvSchoolTest;
    Unbinder unbinder;
    @BindView(R.id.rf_school_text)
    PullToRefreshLayout rfSchoolText;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_school_test, null));
    }

    @Override
    public void initData() {

        setBannerImage(bbTest);

        setRefreshDate(rfSchoolText);

        setRecyclerViewAdapter();

        super.initData();

    }

    private void setRecyclerViewAdapter() {

        rvSchoolTest.setLayoutManager( new LinearLayoutManager(mActivity));
        rvSchoolTest.setAdapter(new SchoolTestAdapter(R.layout.item_time_position, ImageList.getImageList()));

        rvSchoolTest.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new FacePositionDetailsFragment());
            }
        });

    }


    @Override
    public void onSupportVisible() {

        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("我要实习");

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
