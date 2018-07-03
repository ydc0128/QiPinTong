package com.example.qipintongzhongguozongbu.myqipintong.labei;

import android.content.ContentValues;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/9 下午2:53
 */
public interface ChannelDaoInface {
    public boolean addCache(ChannelItem item);

    public boolean deleteCache(String whereClause, String[] whereArgs);

    public boolean updateCache(ContentValues values, String whereClause,
                               String[] whereArgs);

    public Map<String, String> viewCache(String selection,
                                         String[] selectionArgs);

    public List<Map<String, String>> listCache(String selection,
                                               String[] selectionArgs);

    public void clearFeedTable();
}
