package com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCityListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFansListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.PageIndicatorView;
import com.youth.banner.Banner;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 有商有客头布局
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/17 上午10:55
 */
public class HeaderCommerceLayout extends LinearLayout {

    private DropDownMenu mDropDownMenu;
    private String headers[] = {"区域", "粉丝量"};
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String fans[] = {"1", "2", "200", "33", "000", "99", "100"};
    private List<View> popupViews = new ArrayList<>();
    private TextView mTv;

    public HeaderCommerceLayout(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.commerce_header, this);
        mDropDownMenu = (DropDownMenu) view.findViewById(R.id.dropDownMenu);
        mTv = (TextView) view.findViewById(R.id.tv_select);
        initDropDown(context);
    }

    //量取view此时Y轴的距离
    public int getDistanceY() {
        int[] location = new int[2];
        mDropDownMenu.getLocationOnScreen(location);
        int y = location[1];
        return y;
    }


    private void initDropDown(Context mActivity) {

        RecyclerView cityView = new RecyclerView(mActivity);
        cityView.setLayoutManager(new LinearLayoutManager(mActivity));
        cityView.setAdapter(new RvCityListAdapter(R.layout.item_text, Arrays.asList(citys)));
        cityView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });


        RecyclerView fansView = new RecyclerView(mActivity);
        fansView.setLayoutManager(new LinearLayoutManager(mActivity));
        fansView.setAdapter(new RvFansListAdapter(R.layout.item_text, Arrays.asList(fans)));
        fansView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDropDownMenu.setTabText(position == 0 ? headers[1] : fans[position]);
                mDropDownMenu.closeMenu();
            }
        });


        popupViews.add(cityView);
        popupViews.add(fansView);


        ViewGroup parent = (ViewGroup) mTv.getParent();
        parent.removeView(mTv);


        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, mTv);

    }

}
