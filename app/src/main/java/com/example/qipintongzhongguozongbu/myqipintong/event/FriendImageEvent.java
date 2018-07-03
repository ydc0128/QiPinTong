package com.example.qipintongzhongguozongbu.myqipintong.event;

import java.io.File;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/2 下午3:44
 */
public class FriendImageEvent {

    private final File file;

    public File getFile() {
        return file;
    }

    public FriendImageEvent(File file){
        this.file = file;
    }
}
