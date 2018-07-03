package com.example.qipintongzhongguozongbu.myqipintong.background.wangye;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by L on 2017/3/13.
 * 京东商城
 */

public class JDComFragment extends BaseFragment {
    @BindView(R.id.webview_JD)
    WebView webviewJD;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_jd_com, null));
    }

    public void initData() {
        URL();
        super.initData();
    }

    private void URL() {
        // 设置WebView的客户端
        webviewJD.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;// 返回false
            }
        });

        WebSettings webSettings = webviewJD.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(14);
        webSettings.setLoadWithOverviewMode(true);

        webviewJD.loadUrl("https://www.jd.com/?cu=true&utm_source=haosou-pinzhuan&utm_medium=cpc&utm_campaign=t_288551095_haosoupinzhuan&utm_term=0a875d61c5fe47d8bc48679132932d23_0_ea85c8ed3d5943069e81cd3860028365");

    }

    public void onSupportVisible() {
        mTvTitle.setText("京东商城");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;

    }

}


