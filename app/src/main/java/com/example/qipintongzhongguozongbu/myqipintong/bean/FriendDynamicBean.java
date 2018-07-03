package com.example.qipintongzhongguozongbu.myqipintong.bean;

import com.jph.takephoto.model.TImage;

import java.util.ArrayList;

/**
 * Description: 朋友动态数据模型类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/23 下午2:44
 */
public class FriendDynamicBean {

    public String Name;
    public String Location;
    public String Time;
    public String InputText;
    public Integer Icon;
    public ArrayList <TImage>ImageList;
    private int[] mPhotoDate;

    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return "FriendDynamicBean{" +
                "Name='" + Name + '\'' +
                ", Location='" + Location + '\'' +
                ", Time='" + Time + '\'' +
                ", InputText='" + InputText + '\'' +
                '}';
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getInputText() {
        return InputText;
    }

    public void setInputText(String inputText) {
        InputText = inputText;
    }

    public Integer getIcon() {
        return Icon;
    }

    public void setIcon(Integer icon) {
        Icon = icon;
    }

    public ArrayList getImageList() {
        return ImageList;
    }

    public void setImageList(ArrayList imageList) {
        ImageList = imageList;
    }


    public void setImageList(int[] mPhotoDate) {
        this.mPhotoDate = mPhotoDate;
    }
}
