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
 * Description: 美食
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/6 上午10:53
 */
public class DeliciousPagerFragment extends BaseFragment {
    @BindView(R.id.bb_delicious)
    Banner bbDelicious;
    @BindView(R.id.rv_delicious)
    RecyclerView rvDelicious;
    @BindView(R.id.rf_delicious)
    PullToRefreshLayout rfDelicious;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragmenet_pager_delicious, null));
    }

    public static BaseFragment getInstance() {
        return new DeliciousPagerFragment();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setRefreshDate(rfDelicious);

        setBannerImage(bbDelicious);

        setRecyclerViewAdapter();

        super.onLazyInitView(savedInstanceState);
    }


    private void setRecyclerViewAdapter() {

        rvDelicious.setLayoutManager(new LinearLayoutManager(mActivity));

        rvDelicious.setAdapter(new NewsPagerAdapter(R.layout.item_news, ImageList.getImageList()));
        //添加头布局

        rvDelicious.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                WebDetalisFragment webDetalisFragment = new WebDetalisFragment();
                webDetalisFragment.setUrlDate("http://www.jianshu.com/p/5b36ad3166b1/");
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
