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
 * Description: 易通学院新闻页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/8 下午5:18
 */
public class NewsPagerFragment extends BaseFragment {
    @BindView(R.id.bb_news)
    Banner bbNews;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    Unbinder unbinder;
    @BindView(R.id.rf_news)
    PullToRefreshLayout rfNews;


    public static BaseFragment getInstance() {
        return new NewsPagerFragment();
    }

    @Override
    public View initView() {
        return View.inflate(mActivity, R.layout.fragment_news, null);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setRefreshDate(rfNews);

        setBannerImage(bbNews);

        setRecyclerViewAdapter();

        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initData() {


        super.initData();

    }

    private void setRecyclerViewAdapter() {

        rvNews.setLayoutManager(new LinearLayoutManager(mActivity));

        rvNews.setAdapter( new NewsPagerAdapter(R.layout.item_news, ImageList.getImageList()));
        //添加头布局

        rvNews.addOnItemTouchListener(new OnItemClickListener() {
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
