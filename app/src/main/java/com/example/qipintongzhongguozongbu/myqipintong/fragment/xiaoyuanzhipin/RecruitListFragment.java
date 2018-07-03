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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RecruitListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.RecruitListDate;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Description: 校招专场的列表页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/9 下午3:18
 */
public class RecruitListFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.rv_recruit_list)
    RecyclerView rvRecruitList;
    private View headerView;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_recruit_list, null));
    }

    /**
     * function   : 获取头布局
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/9  下午4:24
     */
    public View getHeaderView() {
        View view = mActivity.getLayoutInflater().inflate(R.layout.banner_heade, (ViewGroup) rvRecruitList.getParent(), false);
        return view;
    }

    @Override
    public void initData() {

        headerView = getHeaderView();

        Banner bb = (Banner) headerView.findViewById(R.id.bb_banner);

        setBannerImage(bb);

        setRecyclerViewAdapter();

        super.initData();

    }

    private void setRecyclerViewAdapter() {

        rvRecruitList.setLayoutManager(new LinearLayoutManager(mActivity));

        RecruitListAdapter recruitListAdapter = new RecruitListAdapter(R.layout.item_recruit_list, getData(50));
        //设置条目数据
        rvRecruitList.setAdapter(recruitListAdapter);

        recruitListAdapter.addHeaderView(headerView);

        recruitListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //动画效果
        rvRecruitList.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                start(new RecruitDetailsFragment());
                //招聘会详情页面
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

    /**
     * function   : 列表的条目数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/9  下午5:11
     */
    private ArrayList getData(int size) {
        ArrayList<RecruitListDate> data = new ArrayList(size);

        for (int i = 0; i < size; i++) {
            RecruitListDate recruitListDate = new RecruitListDate();
            recruitListDate.setTime("明年今日" + i);
            recruitListDate.setLocation("天涯海角" + i);
            data.add(recruitListDate);
        }
        return data;
    }

    @Override
    public void onSupportInvisible() {

        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportInvisible();

    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("招聘单位");
        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();

    }
}
