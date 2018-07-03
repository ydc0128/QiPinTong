package com.example.qipintongzhongguozongbu.myqipintong.bean;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/20 上午11:07
 */
public class HomeBeanTest {


    /**
     * errcode : 0
     * errmsg : null
     * topBanners : [{"title":null,"imgUrl":"/assets/js/kindeditor/attached/image/20161114/20161114150030_0003.png","id":39,"href":"#"},{"title":null,"imgUrl":"/assets/js/kindeditor/attached/image/20161114/20161114145642_5831_1.png","id":40,"href":"#"},{"title":null,"imgUrl":"/assets/js/kindeditor/attached/image/20161114/20161114145705_0472_1.png","id":41,"href":"#"}]
     */

    private int errcode;
    private Object errmsg;
    private List<TopBannersBean> topBanners;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public Object getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(Object errmsg) {
        this.errmsg = errmsg;
    }

    public List<TopBannersBean> getTopBanners() {
        return topBanners;
    }

    public void setTopBanners(List<TopBannersBean> topBanners) {
        this.topBanners = topBanners;
    }

    public static class TopBannersBean {
        /**
         * title : null
         * imgUrl : /assets/js/kindeditor/attached/image/20161114/20161114150030_0003.png
         * id : 39
         * href : #
         */

        private Object title;
        private String imgUrl;
        private int id;
        private String href;

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }
}
