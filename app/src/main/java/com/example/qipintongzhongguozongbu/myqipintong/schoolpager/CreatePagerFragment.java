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
 * Description: 创投页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/6 上午10:51
 */
public class CreatePagerFragment extends BaseFragment {
    @BindView(R.id.bb_create)
    Banner bbCreate;
    @BindView(R.id.rv_create)
    RecyclerView rvCreate;
    @BindView(R.id.rf_create)
    PullToRefreshLayout rfCreate;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_pager_creat, null));
    }

    public static BaseFragment getInstance() {
        return new CreatePagerFragment();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setRefreshDate(rfCreate);

        setBannerImage(bbCreate);

        setRecyclerViewAdapter();

        super.onLazyInitView(savedInstanceState);
    }


    private void setRecyclerViewAdapter() {

        rvCreate.setLayoutManager(new LinearLayoutManager(mActivity));

        rvCreate.setAdapter(new NewsPagerAdapter(R.layout.item_news, ImageList.getImageList()));
        //添加头布局

        rvCreate.addOnItemTouchListener(new OnItemClickListener() {
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
