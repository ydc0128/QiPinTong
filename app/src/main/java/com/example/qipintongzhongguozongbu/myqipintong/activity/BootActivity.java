package com.example.qipintongzhongguozongbu.myqipintong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.VpBootViewPager;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description: 引导页
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵的
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/3 ??11:41
 */
public class  BootActivity extends AppCompatActivity {


    @BindView(R.id.vp_boot)
    ViewPager vpBoot;
    @BindView(R.id.bt_goin)
    Button btGoin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        ButterKnife.bind(this);
        vpBoot.setAdapter(new VpBootViewPager(this));
        vpBoot.setOnPageChangeListener(new PageChangeListener());
    }

    @OnClick(R.id.bt_goin)
    public void onClick() {
        startActivity(new Intent(BootActivity.this, HomeActivity.class));
        finish();
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (GlobalConstants.mIvList.length - 1 == position) {
                btGoin.setVisibility(View.VISIBLE);
            } else {
                btGoin.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
