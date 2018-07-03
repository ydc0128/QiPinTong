package com.example.qipintongzhongguozongbu.myqipintong.index;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

/**
 * Description: 地址数据实体类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/24 下午2:43
 */
public class LocationBean extends BaseIndexPinyinBean {
    private String city;//城市名字
    public LocationBean() {
    }
    public LocationBean(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "LocationBean{" +
                "city='" + city + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public LocationBean setCity(String city) {
        this.city = city;
        return this;
    }

    @Override
    public String getTarget() {
        return city;
    }
}
