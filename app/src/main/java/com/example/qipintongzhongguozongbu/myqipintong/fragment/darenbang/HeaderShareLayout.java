package com.example.qipintongzhongguozongbu.myqipintong.fragment.darenbang;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCityListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFansListAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 达人帮列表的悬浮选项卡
 * Created by Administrator on 2017/6/6.
 */

public class HeaderShareLayout extends LinearLayout {


    private String headers[] = {"区域", "粉丝量"};
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String fans[] = {"1", "2", "200", "33", "000", "99", "100"};
    private List<View> popupViews = new ArrayList<>();
    private DropDownMenu selectExpert;
    private TextView mTv;

    public HeaderShareLayout(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.share_header, this);
        selectExpert = (DropDownMenu) view.findViewById(R.id.dropDownMenu);
        mTv = (TextView) view.findViewById(R.id.tv_select);
        initDropDown(context);
    }

    //量取view此时Y轴的距离
    public int getDistanceY() {
        int[] location = new int[2];
        selectExpert.getLocationOnScreen(location);
        int y = location[1];
        return y;
    }


    private void initDropDown(Context mActivity) {

        RecyclerView cityView = new RecyclerView(mActivity);
        cityView.setLayoutManager(new LinearLayoutManager(mActivity));
        cityView.setAdapter(new RvCityListAdapter(R.layout.item_text, Arrays.asList(citys)));


        RecyclerView fansView = new RecyclerView(mActivity);
        fansView.setLayoutManager(new LinearLayoutManager(mActivity));
        fansView.setAdapter(new RvFansListAdapter(R.layout.item_text, Arrays.asList(fans)));


        popupViews.add(cityView);
        popupViews.add(fansView);


        ViewGroup parent = (ViewGroup) mTv.getParent();
        parent.removeView(mTv);


        selectExpert.setDropDownMenu(Arrays.asList(headers), popupViews, mTv);


    }
}
