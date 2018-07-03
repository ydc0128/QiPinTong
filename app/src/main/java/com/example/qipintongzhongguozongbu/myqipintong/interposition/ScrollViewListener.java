package com.example.qipintongzhongguozongbu.myqipintong.interposition;

import com.example.qipintongzhongguozongbu.myqipintong.view.ObservableScrollView;

/**
 * Description: ScrollView的接口 获取滑动的距离
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/1/13 上午10:29
 */
public interface ScrollViewListener {

    void onScrollChanged(ObservableScrollView scrollView, int x, int y);

}
