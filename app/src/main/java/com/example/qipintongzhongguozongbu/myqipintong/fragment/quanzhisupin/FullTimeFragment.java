package com.example.qipintongzhongguozongbu.myqipintong.fragment.quanzhisupin;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.Volley.VolleyHandler;
import com.example.qipintongzhongguozongbu.myqipintong.Volley.VolleyHttpRequest;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFullRecruitAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeBean;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.dateserver.HomeDatafromServer;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FacePositionDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.utils.CacheUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.StringUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: 全职速聘的列表页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/20 上午9:44
 */
public class FullTimeFragment extends BaseFragment {
    @BindView(R.id.rv_full_time)
    RecyclerView rvFullTime;
    @BindView(R.id.rf_full_time)
    PullToRefreshLayout rfFullTime;
    Unbinder unbinder;


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_full_time, null));
    }

    @Override
    public void initData() {

        setRefresh();

        super.initData();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

       // getDataServer();

        setRvAdapter();

        super.onLazyInitView(savedInstanceState);
    }

    private void setRvAdapter() {

        rvFullTime.setLayoutManager(new LinearLayoutManager(mActivity));

        RvFullRecruitAdapter rvFullRecruitAdapter = new RvFullRecruitAdapter(R.layout.item_full_time_job, ImageList.getImageList());

        rvFullRecruitAdapter.addHeaderView(getHeaderView(ImageList.getFourList()));

        rvFullRecruitAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);

        rvFullTime.setAdapter(rvFullRecruitAdapter);

        rvFullTime.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new FacePositionDetailsFragment());
            }
        });


    }

    @Override
    public void onSupportVisible() {

        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("全职速聘");
        mButton.setVisibility(View.GONE);
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

    public View getHeaderView(ArrayList<String> topBanners) {
        View view = mActivity.getLayoutInflater().inflate(R.layout.banner_heade, (ViewGroup) rvFullTime.getParent(), false);
        Banner bbBanner = (Banner) view.findViewById(R.id.bb_banner);
        setBannerImage(bbBanner, topBanners);
        return view;
    }


    /**
     * function   : 这里去获取数据 如果有缓存 先从缓存取 在访问网络 保证数据最新
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/21  上午11:28
     */
    public void getDataServer() {

        String cache = CacheUtils.getCache(mActivity, GlobalConstants.HOME_URl);
        if (!StringUtils.isEmpty(cache)) {
            processData(cache);
        }

       // getDataFromServer();
    }


    /**
     * function   : 获取网络数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/20  上午9:40
     */
    public void getDataFromServer() {

        Map<String ,String > map = new HashMap<>();
        map.put("full","full");

        VolleyHttpRequest.Volley_StringRequest(Request.Method.POST, GlobalConstants.FULL_URl, map, new VolleyHandler<String>() {

                    @Override
                    public void reqSuccess(String response) {

                        LogUtils.e(response);
                        CacheUtils.setCache(mActivity, GlobalConstants.FULL_URl, response);

                        processData(response);

                    }

                    @Override
                    public void reqError(String error) {
                        LogUtils.e(error);
                        ToastUtils.showToast(mActivity, "您的网络离家出走了");
                    }
                }
        );
    }


    /**
     * function   : 解析数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/21  上午11:28
     */
    private void processData(String response) {

        HomeDatafromServer mServer = new HomeDatafromServer(response);

        ArrayList<String> topBanners = mServer.getTopBanners();

        List<HomeBean.FjobListBean> jobList = mServer.getJobList();

       // setRvAdapter(topBanners, jobList);

    }

    private void setRefresh() {

        if (rfFullTime != null) {

            rfFullTime.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                    new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            // 千万别忘了告诉控件刷新完毕了哦！
                            getDataFromServer();
                            rfFullTime.refreshFinish(PullToRefreshLayout.SUCCEED);
                            ToastUtils.showToast(mActivity, "我刷新完啦");
                        }
                    }.sendEmptyMessageDelayed(0, 1000);
                }

                @Override
                public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                    new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            // 千万别忘了告诉控件刷新完毕了哦！
                            getDataFromServer();
                            rfFullTime.refreshFinish(PullToRefreshLayout.SUCCEED);
                            ToastUtils.showToast(mActivity, "我刷新完啦");
                        }
                    }.sendEmptyMessageDelayed(0, 1000);
                }
            });
        }

    }
}
