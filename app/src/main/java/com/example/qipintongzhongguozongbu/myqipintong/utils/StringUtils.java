package com.example.qipintongzhongguozongbu.myqipintong.utils;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/21 上午11:23
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.equals("") || str.equals("null") || str.equals("NULL");
    }
}
