package com.example.qipintongzhongguozongbu.myqipintong.bean;

/**
 * Description: 招聘会条目模型类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/9 下午5:38
 */
public class RecruitListDate {
    private String Time;
    private String Location;

    public void setTime(String time) {
        Time = time;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public String getLocation() {
        return Location;
    }

    @Override
    public String toString() {
        return "RecruitListDate{" +
                "Time='" + Time + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }
}
