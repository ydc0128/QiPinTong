package com.example.qipintongzhongguozongbu.myqipintong.bean;

/**
 * Description: 创投天下 投资机构的数据模型类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/10 下午5:20
 */
public class GiveMoneyCompanyDate {

    private String Title;
    private int number;
    private String seenumber;
    private String location;
    private int image;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSeenumber() {
        return seenumber;
    }

    public void setSeenumber(String seenumber) {
        this.seenumber = seenumber;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
