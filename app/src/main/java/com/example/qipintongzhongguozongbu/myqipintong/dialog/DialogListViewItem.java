package com.example.qipintongzhongguozongbu.myqipintong.dialog;

/**
 * Description:ButtomDialog中ListView数据应用的实体类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/6 上午11:04
 */
public class DialogListViewItem {
    CharSequence text;

    public DialogListViewItem(CharSequence text) {
        this.text = text;
    }

    public CharSequence getText() {
        return text;
    }

    public void setText(CharSequence text) {
        this.text = text;
    }
}
