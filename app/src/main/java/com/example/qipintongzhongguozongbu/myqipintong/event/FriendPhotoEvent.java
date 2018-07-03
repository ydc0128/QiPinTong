package com.example.qipintongzhongguozongbu.myqipintong.event;

import java.util.ArrayList;

/**
 * Description: 朋友圈选择图片完成后用于保存图片数据的类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/23 上午11:56
 */
public class FriendPhotoEvent {

    private final ArrayList images;
    private final String message;

    public FriendPhotoEvent(ArrayList Images,String message) {
        images = Images;
        this.message = message;
    }

    public ArrayList getImages() {
        return images;
    }

    public String getMessage() {
        return message;
    }
}
