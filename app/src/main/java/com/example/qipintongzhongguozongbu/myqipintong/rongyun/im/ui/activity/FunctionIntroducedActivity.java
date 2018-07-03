package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.qipintongzhongguozongbu.myqipintong.R;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressLint("SetJavaScriptEnabled")
public class FunctionIntroducedActivity extends BaseAActivity {


    @BindView(R.id.function_introdiced_webview)
    WebView functionIntrodicedWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_introduced);
        ButterKnife.bind(this);
        setTitle(R.string.function_introduce);

    }
}
