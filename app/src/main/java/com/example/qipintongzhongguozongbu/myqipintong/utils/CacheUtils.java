package com.example.qipintongzhongguozongbu.myqipintong.utils;

import android.content.Context;

/**
 * Description: 缓存工具
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/21 上午11:06
 */
public class CacheUtils {
    //写缓存
    //以url为key(标识),以json为值, 保存在本地sp
    public static void setCache(Context ctx, String url, String json) {
        //有时也会保存在本地文件中, 以MD5(url)为文件名,以json为文件内容保存在sdcard中
        PrefUtils.putString(ctx, url, json);
    }

    //读缓存
    public static String getCache(Context ctx, String url) {
        //读MD5(url)文件名
        return PrefUtils.getString(ctx, url, null);
    }

}
