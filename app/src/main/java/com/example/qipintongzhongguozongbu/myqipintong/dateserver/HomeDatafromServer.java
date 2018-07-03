package com.example.qipintongzhongguozongbu.myqipintong.dateserver;

import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeBean;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 首页数据处理类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/20 下午2:27
 */
public class HomeDatafromServer {


    private final HomeBean bean;

    public HomeDatafromServer(String response) {
        Gson gson = new Gson();
        bean = gson.fromJson(response, HomeBean.class);
    }


    /**
     * function   : 有才有貌数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/20  下午3:15
     */
    public List<HomeBean.MemListBean> getFaceList() {

        List<HomeBean.MemListBean> memList = bean.getMemList();

        return memList;
    }





    /**
     * function   : 名企在线数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/20  下午3:15
     */
    public List<HomeBean.ComListBean> getComList() {

        List<HomeBean.ComListBean> comList = bean.getComList();

        return comList;


    }








    /**
     * function   : 全职速聘数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/20  下午3:15
     */
    public List<HomeBean.FjobListBean> getJobList() {

        List<HomeBean.FjobListBean> fjobList = bean.getFjobList();

        return fjobList;
    }


    /**
     * function   : 向上翻滚的文字集合
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/20  下午3:06
     */
    public ArrayList<String> getRollTexts() {

        List<HomeBean.TopArticlesBean> topArticles = bean.getTopArticles();
        ArrayList<String> RollTextList = new ArrayList<>();

        for (HomeBean.TopArticlesBean bean : topArticles) {
            String title = bean.getTitle();
            RollTextList.add(title);
        }
        return RollTextList;
    }

    /**
     * function   : 向上翻滚的文字对应链接
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/20  下午3:06
     */
    public ArrayList<String> getRollUrl() {

        List<HomeBean.TopArticlesBean> topArticles = bean.getTopArticles();
        ArrayList<String> RollUrlList = new ArrayList<>();

        for (HomeBean.TopArticlesBean bean : topArticles) {
            String href = bean.getHref();
            RollUrlList.add(GlobalConstants.QPT_URl + href);
        }
        return RollUrlList;
    }

    /**
     * function   : 顶部轮播图
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/20  下午3:06
     */
    public ArrayList<String> getTopBanners() {

        List<HomeBean.TopBannersBean> topBanners = bean.getTopBanners();
        ArrayList<String> mBannerList = new ArrayList<>();

        for (HomeBean.TopBannersBean banner : topBanners) {
            String imgUrl = banner.getImgUrl();
            mBannerList.add(GlobalConstants.QPT_URl + imgUrl);
        }
        return mBannerList;
    }

    /*
     * function   : 手动轮播图
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/20  下午3:06
     */
    public List<HomeBean.MidBannersBean> getMidBanners() {

        List<HomeBean.MidBannersBean> midBanners = bean.getMidBanners();
        ArrayList<String> list = new ArrayList<>();

        for (HomeBean.MidBannersBean banner : midBanners
                ) {
            String imgUrl = banner.getImgUrl();
            list.add(GlobalConstants.QPT_URl + imgUrl);
        }


        return midBanners;

    }
}

