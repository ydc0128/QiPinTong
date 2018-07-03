package com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFoodAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: 美食天下页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/7 下午5:02
 */
public class FoodListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_youshangyouke)
    RecyclerView rvYoushangyouke;
    Unbinder unbinder;
    @BindView(R.id.srl_commerce)
    SwipeRefreshLayout srlCommerce;

    private static final int TOTAL_COUNTER = 20;//总条目数

    private static final int PAGE_SIZE = 6;

    private int delayMillis = 1000;

    private int mCurrentCounter = 0;//所有数据的总条目

    private boolean isErr;
    private boolean mLoadMoreEndGone = false;

    private boolean isLoadMore = true;//是否在加载更多
    private RvFoodAdapter rvFoodAdapter;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_recyclerview, null));
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("美食天下");

        super.onSupportVisible();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setRefreshListener();

        setRecyclerAdapter();

        super.onLazyInitView(savedInstanceState);
    }

    /**
     * function   : 上拉加载 下滑更多
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/8  上午10:55
     */

    private void setRefreshListener() {
        srlCommerce.setOnRefreshListener(this);
    }


    private void setRecyclerAdapter() {

        rvYoushangyouke.setLayoutManager(new LinearLayoutManager(mActivity));

//        mCommerceAdapter = new CommerceAdapter(R.layout.item_shenghuo, getDate());

        rvFoodAdapter = new RvFoodAdapter(R.layout.item_commerce_food, ImageList.getImageList());

        rvFoodAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

         rvFoodAdapter.setOnLoadMoreListener(this);//加载更多监听

         rvFoodAdapter.setEnableLoadMore(isLoadMore);//判断当前是否是下拉刷新状态才决定要不要记载更多

        rvYoushangyouke.setAdapter( rvFoodAdapter);

        mCurrentCounter =  rvFoodAdapter.getData().size();

        rvYoushangyouke.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new FoodDetailsFragment());
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

    @Override
    public void onRefresh() {

        isLoadMore = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                 rvFoodAdapter.setNewData(getDate());
                isErr = false;
                mCurrentCounter = PAGE_SIZE;
                srlCommerce.setRefreshing(false);
                 rvFoodAdapter.setEnableLoadMore(true);

            }
        }, delayMillis);

        isLoadMore = true;
    }

    @Override
    public void onLoadMoreRequested() {
        srlCommerce.setEnabled(false);
        rvYoushangyouke.postDelayed(new Runnable() {
            @Override
            public void run() {
                if ( rvFoodAdapter.getData().size() < PAGE_SIZE) {
                     rvFoodAdapter.loadMoreEnd(true);
                } else {
                    if (mCurrentCounter >= TOTAL_COUNTER) {
//                    pullToRefreshAdapter.loadMoreEnd();//default visible
                         rvFoodAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
                    } else {
                        if (isErr) {
                             rvFoodAdapter.addData(getDate());
                            mCurrentCounter =  rvFoodAdapter.getData().size();
                             rvFoodAdapter.loadMoreComplete();
                        } else {
                            isErr = true;
                            ToastUtils.showToastOnUiThread(mActivity, "没有更多啦");
                             rvFoodAdapter.loadMoreFail();

                        }
                    }
                    if (srlCommerce!=null) {
                        srlCommerce.setEnabled(true);
                    }
                }
            }

        }, delayMillis);
    }


    public List getDate() {

        ArrayList Images = new ArrayList<String>();

        for (int i = 0; i < 20; i++) {

            Images.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/30/c2/21447640_1369886947383.jpg");
        }

        return Images;
    }
}
