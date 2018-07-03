package com.example.qipintongzhongguozongbu.myqipintong.event;

import com.example.qipintongzhongguozongbu.myqipintong.labei.ChannelItem;

import java.util.ArrayList;

/**
 * Description: 易通学院标题
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/4 上午11:11
 */
public class SchoolTatleEvent {

    private final ArrayList<ChannelItem> list;


    public ArrayList<ChannelItem> getList() {
        return list;
    }

    public SchoolTatleEvent(ArrayList<ChannelItem> list) {
        this.list = list;
    }


}
