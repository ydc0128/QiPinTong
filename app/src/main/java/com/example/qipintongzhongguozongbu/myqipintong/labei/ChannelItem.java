package com.example.qipintongzhongguozongbu.myqipintong.labei;

import java.io.Serializable;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/9 下午2:47
 */
public class ChannelItem implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6465237897027410019L;
    /**
     * 栏目对应ID
     *  */
    public Integer id;
    /**
     * 栏目对应NAME
     *  */
    public String name;
    /**
     * 栏目在整体中的排序顺序  rank
     *  */
    public Integer orderId;
    /**
     * 栏目是否选中
     *  */
    public Integer selected;

    public ChannelItem() {
    }

    public ChannelItem(int id, String name, int orderId,int selected) {
        this.id = Integer.valueOf(id);
        this.name = name;
        this.orderId = Integer.valueOf(orderId);
        this.selected = Integer.valueOf(selected);
    }

    public int getId() {
        return this.id.intValue();
    }

    public String getName() {
        return this.name;
    }

    public int getOrderId() {
        return this.orderId.intValue();
    }

    public Integer getSelected() {
        return this.selected;
    }

    public void setId(int paramInt) {
        this.id = Integer.valueOf(paramInt);
    }

    public void setName(String paramString) {
        this.name = paramString;
    }

    public void setOrderId(int paramInt) {
        this.orderId = Integer.valueOf(paramInt);
    }

    public void setSelected(Integer paramInteger) {
        this.selected = paramInteger;
    }

    public String toString() {
        return "ChannelItem [id=" + this.id + ", name=" + this.name
                + ", selected=" + this.selected + "]";
    }

}
