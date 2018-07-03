package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.qipintongzhongguozongbu.myqipintong.R;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressLint("SetJavaScriptEnabled")
public class UpdateLogActivity extends BaseAActivity {
    @BindView(R.id.update_log_webview)
    WebView updateLogWebview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_log);
        ButterKnife.bind(this);
        setTitle(R.string.update_log);

    }


}
