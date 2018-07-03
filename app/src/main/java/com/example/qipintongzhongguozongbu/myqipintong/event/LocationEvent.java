package com.example.qipintongzhongguozongbu.myqipintong.event;

/**
 * Description: 传递用户选择地址数据的类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/24 下午4:09
 */
public class LocationEvent {

    private final String location;

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {

        return "LocationEvent{" +
                "location='" + location + '\'' +
                '}';
    }

    public LocationEvent(String location){
        this.location = location;
    }
}
