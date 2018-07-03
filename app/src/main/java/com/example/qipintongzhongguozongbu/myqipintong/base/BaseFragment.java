package com.example.qipintongzhongguozongbu.myqipintong.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.image.GlideImageLoader;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.SealAction;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.network.async.AsyncTaskManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.network.async.OnDataListener;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.network.http.HttpException;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.NToast;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.flyco.tablayout.CommonTabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;


/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/3 下午8:57
 */
public abstract class BaseFragment extends SwipeBackFragment {
    public Activity mActivity;
    public TextView mTvTitle;
    public ImageView mIvBack;
    public CommonTabLayout mButton;
    public RelativeLayout mTop;
    public Context context;
    protected Context mContext;
    public AsyncTaskManager mAsyncTaskManager;
    protected SealAction action;

    // 创建Fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        context = getContext();
        setRetainInstance(true);
    }

    /**
     * function   : 此方法实现侧滑返回上一个界面
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/3  下午7:23
     */
    public View swipeBackView(View view) {
        return attachToSwipeBack(view);
    }


    // 返回fragment所需要展示的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = initView();
        mTvTitle = (TextView) mActivity.findViewById(R.id.tv_home);
        mIvBack = (ImageView) mActivity.findViewById(R.id.iv_back);
        mTop = (RelativeLayout) mActivity.findViewById(R.id.rl_hometop);
        mButton=(CommonTabLayout)mActivity.findViewById(R.id.ll_button);
        return view;//侧滑回退上一个fragment
    }

    // 当Fragment所依附的Activity创建完成之后会进行的回调
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    /**
     * function   : 开启fragment转场动画结束后会执行
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/3  下午4:17
     */
    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        initData();
        super.onEnterAnimationEnd(savedInstanceState);
    }

    /**
     * function   : 懒加载 在第一次对用户可见时执行
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/3  下午5:46
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }

    //由于父类不知道子类所需要展示的布局究竟是什么样子的，所以把initView进行抽象，让子类必须实现
    public abstract View initView();

    //初始化数据的操作
    public void initData() {

    }

    /**
     * function   : 轮播图
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/11  下午2:09
     */
    public void setBannerImage(Banner view) {

        ArrayList list = new ArrayList<>();
        list.add("http://mob.qipintong.com/assets/js/kindeditor/attached/image/20161114/20161114150030_0003.png");
        list.add("http://mob.qipintong.com/assets/js/kindeditor/attached/image/20161114/20161114145642_5831.png");
        list.add("http://mob.qipintong.com/assets/js/kindeditor/attached/image/20161114/20161114145705_0472.png");
        //设置指示器风格
        if (view != null) {
            view.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置图片加载器
            view.setImageLoader(new GlideImageLoader());
            //设置图片集合
            view.setImages(list);
            //设置banner动画效果
            view.setBannerAnimation(Transformer.Default);
            //设置自动轮播，默认为true
            view.isAutoPlay(true);
            //设置轮播时间
            view.setDelayTime(2500);
            //设置指示器位置（当banner模式中有指示器时）
            view.setIndicatorGravity(BannerConfig.RIGHT);
            //设置图片点击
            //banner设置方法全部调用完毕时最后调用
            view.start();
        }
    }
    /**
     * function   : 轮播图
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/11  下午2:09
     */
    public void setBannerImage(Banner view, ArrayList List) {

        //设置指示器风格
        if (view != null) {
            view.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

            //设置图片加载器
            view.setImageLoader(new GlideImageLoader());
            //设置图片集合
            view.setImages(List);
            //设置banner动画效果
            view.setBannerAnimation(Transformer.Default);
            //设置自动轮播，默认为true
            view.isAutoPlay(true);
            //设置轮播时间
            view.setDelayTime(2500);
            //设置指示器位置（当banner模式中有指示器时）
            view.setIndicatorGravity(BannerConfig.RIGHT);
            //设置图片点击
            //banner设置方法全部调用完毕时最后调用
            view.start();
        }
    }
    /**
     * 发送请求（需要检查网络）
     *
     * @param requestCode 请求码
     */
    public void request(int requestCode) {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.request(requestCode, (OnDataListener) this);
        }
    }

    /**
     * 发送请求（需要检查网络）
     *
     * @param id 请求数据的用户ID或者groupID
     * @param requestCode 请求码
     */
    public void request(String id , int requestCode) {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.request(id, requestCode, (OnDataListener) this);
        }
    }

    /**
     * 发送请求
     *
     * @param requestCode    请求码
     * @param isCheckNetwork 是否需检查网络，true检查，false不检查
     */
    public void request(int requestCode, boolean isCheckNetwork) {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.request(requestCode, isCheckNetwork, (OnDataListener) this);
        }
    }

    /**
     * 取消所有请求
     */
    public void cancelRequest() {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.cancelRequest();
        }
    }

    public Object doInBackground(int requestCode, String id) throws HttpException {
        return null;
    }

    public void onSuccess(int requestCode, Object result) {

    }

    public void onFailure(int requestCode, int state, Object result) {
        switch (state) {
            // 网络不可用给出提示
            case AsyncTaskManager.HTTP_NULL_CODE:
                NToast.shortToast(mContext, "当前网络不可用");
                break;

            // 网络有问题给出提示
            case AsyncTaskManager.HTTP_ERROR_CODE:
                NToast.shortToast(mContext, "网络问题请稍后重试");
                break;

            // 请求有问题给出提示
            case AsyncTaskManager.REQUEST_ERROR_CODE:
                // NToast.shortToast(mContext, R.string.common_request_error);
                break;
        }
    }


    /**
     * function   : 刷新数据方法
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/2  下午5:02
     */

    /**
     * function   : 刷新数据方法
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/2  下午5:02
     */
    public void setRefreshDate(PullToRefreshLayout Refresh) {

        Refresh.setOnRefreshListener(
                new PullToRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                        new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                // 千万别忘了告诉控件刷新完毕了哦！
                                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                                ToastUtils.showToast(mActivity, "我刷新完啦");
                            }
                        }.sendEmptyMessageDelayed(0, 1000);
                    }

                    @Override
                    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                        new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                // 千万别忘了告诉控件加载完毕了哦！
                                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                                ToastUtils.showToast(mActivity, "我加载完啦");
                            }
                        }.sendEmptyMessageDelayed(0, 1000);
                    }
                }

        );
    }


}