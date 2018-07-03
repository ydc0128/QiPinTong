package com.example.qipintongzhongguozongbu.myqipintong.bean;


import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Description: 首页有才有貌的两种布局
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/10 上午10:45
 */
public class ItemTypeDate implements MultiItemEntity {
    public static final int IMAGE_TYPE = 1;
    public static final int MORE_TYPE = 2;
    private final int itemtype;
    private static List<HomeBean.MemListBean> faceList = null;

    public ItemTypeDate(int itemtype, List<HomeBean.MemListBean> faceList) {
        this.itemtype = itemtype;
        this.faceList = faceList;
    }

    @Override
    public int getItemType() {
        return itemtype;
    }


    public static List<HomeBean.MemListBean> getList() {
        return faceList;
    }

}