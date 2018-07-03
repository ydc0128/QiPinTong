package com.example.qipintongzhongguozongbu.myqipintong.fragment.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.schoolpager.EasyknowSchoolFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description: web页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/27 下午4:24
 */
public class WebFragment extends BaseFragment {
    @BindView(R.id.wv_web)
    WebView wvWeb;
    Unbinder unbinder;
    private int position;

    /**
     * function   : 获取点击位置 第几条数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/18  上午10:41
     */
    public void setPosition(int position) {
        this.position = position;
    }

    public static WebFragment getInstance() {
        return new WebFragment();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_web, null));
    }

    @Override
    public void initData() {

        wvWeb.loadUrl("http://www.jianshu.com/p/4705e9f49e55");

        super.initData();
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
    public void onSupportVisible() {

        mTvTitle.setText("文章内容");
        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }


    @OnClick({R.id.ll_web_comment, R.id.ll_web_praise})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_web_comment:
                //评论
                break;
            case R.id.ll_web_praise:
                //点赞
                break;
        }
    }


}
