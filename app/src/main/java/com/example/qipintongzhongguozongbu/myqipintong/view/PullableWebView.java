package com.example.qipintongzhongguozongbu.myqipintong.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.example.qipintongzhongguozongbu.myqipintong.interposition.Pullable;

/**
 * function   : 可刷新的webView
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/3/2  上午11:44
 */
public class PullableWebView extends WebView implements Pullable {

    public PullableWebView(Context context) {
        super(context);
    }

    public PullableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getScrollY() >= getContentHeight() * getScale()
                - getMeasuredHeight())
            return true;
        else
            return false;
    }
}
