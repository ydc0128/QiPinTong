package com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/13.
 * mls
 */

public class BeautifulSaidFragment extends BaseFragment {

    @BindView(R.id.webview_beautifulsaid)
    WebView webviewBeautifulsaid;
    @BindView(R.id.pb_beautiful)
    ProgressBar pbBeautiful;
    private Unbinder unbinder;

    private static final String Url = "http://m.meilishuo.com/?tabnum=0";

    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_beautiful_said, null));
    }


    public static BaseFragment getInstance() {
        return new BeautifulSaidFragment();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        initWebView();
        super.onLazyInitView(savedInstanceState);
    }


    private void initWebView() {

        webviewBeautifulsaid.loadUrl(Url);

        webviewBeautifulsaid.getSettings().setJavaScriptEnabled(true);  // js启动在后
        webviewBeautifulsaid.getSettings().setUseWideViewPort(true); //自适应屏幕

        webviewBeautifulsaid.setWebViewClient(new WebViewClient() {
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
                pbBeautiful.setVisibility(View.GONE);
                super.onPageFinished(webView, s);
            }
        });
        //进度条
        webviewBeautifulsaid.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pbBeautiful.setProgress(newProgress);
                LogUtils.e(newProgress+"进度");
            }
        });
    }

    public void onSupportVisible() {
        mTvTitle.setText("美丽说");
        mIvBack.setVisibility(View.VISIBLE);
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

    /**
     * function   : 监听返回按键 如果当先显示网页与原始网页不一样 就回退上一个网页 如果是第一个网页 就回退到上个界面
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/19  上午11:17
     */
    public boolean onBackPressedSupport() {

        String currentUrl = webviewBeautifulsaid.getUrl();
        //获取当前页面的url
        //这里去掉地址最后的一个斜杠

        LogUtils.e(currentUrl);

        if (!Url.equals(currentUrl)) {
            webviewBeautifulsaid.goBack();
            return true;
        }
        return false;
    }

}
