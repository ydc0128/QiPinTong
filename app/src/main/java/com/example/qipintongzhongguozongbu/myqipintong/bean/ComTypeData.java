package com.example.qipintongzhongguozongbu.myqipintong.bean;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/24 上午10:43
 */
public class ComTypeData implements MultiItemEntity {

    public static final int IMAGE_TYPE = 1;
    public static final int MORE_TYPE = 2;
    private final int itemtype;
    private static List<HomeBean.ComListBean> comList = null;

    public ComTypeData(int itemtype, List<HomeBean.ComListBean> comList) {
        this.itemtype = itemtype;
        this.comList = comList;
    }

    @Override
    public int getItemType() {
        return itemtype;
    }


    public static List<HomeBean.ComListBean> getList() {
        return comList;
    }
}