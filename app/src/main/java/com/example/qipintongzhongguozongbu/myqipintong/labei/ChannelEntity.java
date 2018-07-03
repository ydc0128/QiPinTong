package com.example.qipintongzhongguozongbu.myqipintong.labei;

import java.io.Serializable;

/**
 * Description: 标签的模型类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/25 下午4:46
 */
public class ChannelEntity implements Serializable {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

