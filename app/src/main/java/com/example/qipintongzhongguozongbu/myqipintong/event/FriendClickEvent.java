package com.example.qipintongzhongguozongbu.myqipintong.event;

import java.util.ArrayList;

/**
 * Description: 朋友圈列表页面点击事件的传递
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/14 下午5:03
 */
public class FriendClickEvent {
    private final int position;
    private final int type;
    private final ArrayList<String> list;

    public ArrayList<String> getList() {
        return list;
    }

    public int getPosition() {
        return position;
    }

    public int getType() {
        return type;
    }

    public FriendClickEvent(int position, int type, ArrayList<String> list) {
        this.position = position;

        this.type = type;
        this.list = list;
    }

}
