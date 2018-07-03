package com.example.qipintongzhongguozongbu.myqipintong.fragment.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description: 展示网页内容的详情页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/18 下午3:50
 */
public class WebDetalisFragment extends BaseFragment {


    @BindView(R.id.tv_school_zan_number)
    TextView tvSchoolZanNumber;
    @BindView(R.id.tv_school_message_number)
    TextView tvSchoolMessageNumber;
    @BindView(R.id.et_school)
    EditText etSchool;
    Unbinder unbinder;
    @BindView(R.id.ll_school_action)
    LinearLayout llSchoolAction;
    @BindView(R.id.tv_school_submit)
    TextView tvSchoolSubmit;
    @BindView(R.id.ll_wv)
    RelativeLayout llWv;
    @BindView(R.id.wv_school)
    WebView wvSchool;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    private String url;
    private String title;


    public void setUrlDate(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_school_details, null));
    }

    @Override
    public void initData() {

        mTvTitle.setText(title);

        initWebView();

        initEditText();

        super.initData();
    }

    private void initEditText() {

        etSchool.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    llSchoolAction.setVisibility(View.GONE);
                    tvSchoolSubmit.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void initWebView() {

        wvSchool.loadUrl(url);

        wvSchool.getSettings().setJavaScriptEnabled(true);  // js启动在后
        wvSchool.getSettings().setUseWideViewPort(true); //自适应屏幕

        wvSchool.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url != null) {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                LogUtils.e("打印日志", "网页加载失败");
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
                super.onPageFinished(webView, s);
            }
        });
        //进度条
        wvSchool.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (progressBar != null) {
                    progressBar.setProgress(newProgress);
                }
                LogUtils.e(newProgress + "进度");
            }
        });
    }

    @Override
    public void onSupportVisible() {

        mButton.setVisibility(View.GONE);
        mTop.setVisibility(View.VISIBLE);
        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("新闻详情");

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


    @OnClick({R.id.iv_school_zan, R.id.tv_school_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_school_zan://点赞


                break;
            case R.id.tv_school_submit://提交

                String userInput = etSchool.getText().toString();

                if (userInput != null) {

                    //这里上传内容到服务器
                    etSchool.setText("");
                } else {
                    ToastUtils.showToast(mActivity, "您还没有输入内容");
                }

                break;
        }
    }

    /**
     * function   : 监听返回按键 如果当先显示网页与原始网页不一样 就回退上一个网页 如果是第一个网页 就回退到上个界面
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/19  上午11:17
     */
    public boolean onBackPressedSupport() {

        String currentUrl = wvSchool.getUrl();
        //获取当前页面的url
        String cUrl = currentUrl.substring(0, currentUrl.length() - 1);
        //这里去掉地址最后的一个斜杠
        LogUtils.e("当前" + currentUrl);
        LogUtils.e("原始" + url);
        if (!url.equals(currentUrl)) {
            wvSchool.goBack();
            return true;
        }
        return false;
    }
}




