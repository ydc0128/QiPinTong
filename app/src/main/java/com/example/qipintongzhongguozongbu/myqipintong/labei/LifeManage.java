package com.example.qipintongzhongguozongbu.myqipintong.labei;

import android.database.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/5 下午4:48
 */
public class LifeManage {
    private static LifeManage lifeManage;
    /**
     * 默认的用户选择频道列表
     */
    public static List<ChannelItem> defaultUserChannels;
    /**
     * 默认的其他频道列表
     */
    public static List<ChannelItem> defaultOtherChannels;
    private ChannelDao channelDao;
    /**
     * 判断数据库中是否存在用户数据
     */
    private boolean userExist = false;

    static {
        defaultUserChannels = new ArrayList();
        defaultOtherChannels = new ArrayList();
        defaultUserChannels.add(new ChannelItem(0, "有商有客", 1, 1));
        defaultUserChannels.add(new ChannelItem(1, "京东商城", 2, 1));
        defaultUserChannels.add(new ChannelItem(2, "美 丽 说", 3, 1));
        defaultUserChannels.add(new ChannelItem(3, "美团外卖", 4, 1));
        defaultUserChannels.add(new ChannelItem(4, "大众点评", 5, 1));
        defaultUserChannels.add(new ChannelItem(5, "滴滴出行", 6, 1));
        defaultUserChannels.add(new ChannelItem(6, "同城旅游", 7, 1));

        defaultOtherChannels.add(new ChannelItem(7, "途牛旅游", 1, 0));
    }


    private LifeManage(LifeHelper paramDBHelper) throws SQLException {
        if (channelDao == null)
            channelDao = new ChannelDao(paramDBHelper.getContext());
        return;
    }

    /**
     * 初始化频道管理类
     *
     * @throws SQLException
     */
    public static LifeManage getLifeManage(LifeHelper dbHelper) throws SQLException {
        if (lifeManage == null) {
            lifeManage = new LifeManage(dbHelper);
        }
        return lifeManage;
    }

    /**
     * 清除所有的频道
     */
    public void deleteAllChannel() {
        channelDao.clearFeedTable();
    }

    /**
     * 获取其他的频道
     *
     * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
     */
    public List<ChannelItem> getUserChannel() {
        Object cacheList = channelDao.listCache(LifeHelper.SELECTED + "= ?", new String[]{"1"});
        if (cacheList != null && !((List) cacheList).isEmpty()) {
            userExist = true;
            List<Map<String, String>> maplist = (List) cacheList;
            int count = maplist.size();
            List<ChannelItem> list = new ArrayList<ChannelItem>();
            for (int i = 0; i < count; i++) {
                ChannelItem navigate = new ChannelItem();
                navigate.setId(Integer.valueOf(maplist.get(i).get(LifeHelper.ID)));
                navigate.setName(maplist.get(i).get(LifeHelper.NAME));
                navigate.setOrderId(Integer.valueOf(maplist.get(i).get(LifeHelper.ORDERID)));
                navigate.setSelected(Integer.valueOf(maplist.get(i).get(LifeHelper.SELECTED)));
                list.add(navigate);
            }
            return list;
        }
        initDefaultChannel();
        return defaultUserChannels;
    }

    /**
     * 获取其他的频道
     *
     * @return 数据库存在用户配置 ? 数据库内的其它频道 : 默认其它频道 ;
     */
    public List<ChannelItem> getOtherChannel() {
        Object cacheList = channelDao.listCache(LifeHelper.SELECTED + "= ?", new String[]{"0"});
        List<ChannelItem> list = new ArrayList<ChannelItem>();
        if (cacheList != null && !((List) cacheList).isEmpty()) {
            List<Map<String, String>> maplist = (List) cacheList;
            int count = maplist.size();
            for (int i = 0; i < count; i++) {
                ChannelItem navigate = new ChannelItem();
                navigate.setId(Integer.valueOf(maplist.get(i).get(LifeHelper.ID)));
                navigate.setName(maplist.get(i).get(LifeHelper.NAME));
                navigate.setOrderId(Integer.valueOf(maplist.get(i).get(LifeHelper.ORDERID)));
                navigate.setSelected(Integer.valueOf(maplist.get(i).get(LifeHelper.SELECTED)));
                list.add(navigate);
            }
            return list;
        }
        if (userExist) {
            return list;
        }
        cacheList = defaultOtherChannels;
        return (List<ChannelItem>) cacheList;
    }

    /**
     * 保存用户频道到数据库
     *
     * @param userList
     */
    public void saveUserChannel(List<ChannelItem> userList) {
        for (int i = 0; i < userList.size(); i++) {
            ChannelItem channelItem = (ChannelItem) userList.get(i);
            channelItem.setOrderId(i);
            channelItem.setSelected(Integer.valueOf(1));
            channelDao.addCache(channelItem);
        }
    }

    /**
     * 保存其他频道到数据库
     *
     * @param otherList
     */
    public void saveOtherChannel(List<ChannelItem> otherList) {
        for (int i = 0; i < otherList.size(); i++) {
            ChannelItem channelItem = (ChannelItem) otherList.get(i);
            channelItem.setOrderId(i);
            channelItem.setSelected(Integer.valueOf(0));
            channelDao.addCache(channelItem);
        }
    }

    /**
     * 初始化数据库内的频道数据
     */
    private void initDefaultChannel() {
        deleteAllChannel();
        saveUserChannel(defaultUserChannels);
        saveOtherChannel(defaultOtherChannels);
    }
}
