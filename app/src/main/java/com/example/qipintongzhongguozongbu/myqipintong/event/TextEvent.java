package com.example.qipintongzhongguozongbu.myqipintong.event;

/**
 * Description: 用于传递一个字符串
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/4 下午4:20
 */
public class TextEvent {

    private final String str;

    public String getStr() {
        return str;
    }

    public TextEvent(String str) {
        this.str = str;
    }


}
