package com.example.qipintongzhongguozongbu.myqipintong.index;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Description: 顶部地址的模型类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/24 下午2:39
 */
public class LocationHeaderBean extends BaseIndexPinyinBean {

    private List<String> cityList;
    //悬停ItemDecoration显示的Tag
    private String suspensionTag;


    public LocationHeaderBean(List<String> cityList, String suspensionTag, String indexBarTag) {
        this.cityList = cityList;
        this.suspensionTag = suspensionTag;
        this.setBaseIndexTag(indexBarTag);
    }

    public List<String> getCityList() {
        return cityList;
    }

    public LocationHeaderBean setCityList(List<String> cityList) {
        this.cityList = cityList;
        return this;
    }

    public LocationHeaderBean setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public boolean isNeedToPinyin() {
        return false;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }


}
