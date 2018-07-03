package com.example.qipintongzhongguozongbu.myqipintong.schoolpager;

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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.NewsPagerAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.webview.WebDetalisFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Description: 职场页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/4 下午5:40
 */
public class WorkPagerFragment extends BaseFragment {
    @BindView(R.id.bb_work)
    Banner bbWork;
    @BindView(R.id.rv_work)
    RecyclerView rvWork;
    @BindView(R.id.rf_work)
    PullToRefreshLayout rfWork;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_pager_work, null));
    }


    public static WorkPagerFragment fragment = null;

    public static WorkPagerFragment getInstance() {

        if (fragment == null) {
            fragment = new WorkPagerFragment();
        }
        return fragment;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setRefreshDate(rfWork);

        setBannerImage(bbWork);

        setRecyclerViewAdapter();

        super.onLazyInitView(savedInstanceState);
    }


    private void setRecyclerViewAdapter() {

        rvWork.setLayoutManager(new LinearLayoutManager(mActivity));

        rvWork.setAdapter(new NewsPagerAdapter(R.layout.item_news, ImageList.getImageList()));
        //添加头布局

        rvWork.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebDetalisFragment webDetalisFragment = new WebDetalisFragment();
                webDetalisFragment.setUrlDate("https://www.baidu.com/");
                ((SupportFragment) getParentFragment()).start(webDetalisFragment);
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
