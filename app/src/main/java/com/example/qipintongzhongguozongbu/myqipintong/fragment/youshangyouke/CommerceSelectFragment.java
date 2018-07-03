package com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.CommerceSelectAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:有商有客的全部频道页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/17 下午5:38
 */
public class CommerceSelectFragment extends BaseFragment {
    @BindView(R.id.rv_youshangyouke)
    RecyclerView mRv;
    @BindView(R.id.srl_commerce)
    SwipeRefreshLayout srlCommerce;
    Unbinder unbinder;


    private String[] IconName = {
            "美食天地", "休闲娱乐", "丽人圈", "酒店预订",
            "洗衣店", "药店", "医院", "眼镜店",
            "便利店", "水果店", "服装店", "鞋包店",
            "礼品店", "珠宝首饰", "花店", "摄影",
            "文具书店", "家纺布艺", "家具家居", "装潢建材",
            "五金店", "开锁", "房产中介", "家政服务",
            "电动车", "汽车4s店", "生活缴费"};
    private int[] iconImage = {
            R.mipmap.delicious, R.mipmap.relaxation, R.mipmap.beautiful, R.mipmap.grogshop,
            R.mipmap.laundry, R.mipmap.pharmacy, R.mipmap.hospital, R.mipmap.eyeglass,
            R.mipmap.bianlidain, R.mipmap.fruit, R.mipmap.fuzhuang, R.mipmap.shoe_shop,
            R.mipmap.lipin, R.mipmap.jewelry_store, R.mipmap.flower, R.mipmap.sheying,
            R.mipmap.wenju, R.mipmap.buyi, R.mipmap.jiaju, R.mipmap.decorative,
            R.mipmap.metals, R.mipmap.kaisuo, R.mipmap.fangchan, R.mipmap.jiazheng,
            R.mipmap.diandongcar, R.mipmap.car_4s, R.mipmap.life_feiyong};


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_recyclerview, null));
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
    public void initData() {

        srlCommerce.setEnabled(false);

        setAdapter();

        super.initData();
    }

    private void setAdapter() {

        mRv.setLayoutManager(new GridLayoutManager(mActivity, 4));

        mRv.setAdapter(new CommerceSelectAdapter(R.layout.item_icon_type, getIconDate()));

        mRv.addItemDecoration(new DividerGridItemDecoration());
    }

    public List getIconDate() {

        ArrayList<HomeIconBean> list = new ArrayList<>();

        for (int i = 0; i < iconImage.length; i++) {
            HomeIconBean bean = new HomeIconBean();
            bean.setIcon(iconImage[i]);
            bean.setTitle(IconName[i]);
            list.add(bean);
        }

        return list;
    }
}
