package com.example.qipintongzhongguozongbu.myqipintong.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 网络图片假数据
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/10 下午2:53
 */
public class ImageList {


    public static ArrayList getImageList() {
        ArrayList<String> list = new ArrayList<String>();
//        list.add("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=228721674,2223896312&fm=58");
//        list.add(" https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=4193844022,4006475838&fm=58");
//        list.add("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=3846984781,2756051209&fm=58");
//        list.add("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2788647422,3315659137&fm=58");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495719322435&di=fe1c9f3e46400c8ac4ab838a8d7277d7&imgtype=0&src=http%3A%2F%2Fimg-download.pchome.net%2Fdownload%2F1k1%2Fbn%2F2v%2Fokgxow-iwe.jpg");
        list.add("http://www.bz55.com/uploads/allimg/170510/140-1F5101F027.jpg");
        list.add("http://www.bz55.com/uploads/allimg/170510/140-1F5101F025.jpg");
        list.add("http://www.bz55.com/uploads/allimg/170510/140-1F5101F026.jpg");
        list.add("http://www.bz55.com/uploads/allimg/170510/140-1F5101F031.jpg");
        list.add("http://www.bz55.com/uploads/allimg/170113/139-1F113105S0.jpg");
        list.add("http://www.bz55.com/uploads/allimg/170113/139-1F113105S3.jpg");
        list.add("http://www.bz55.com/uploads/allimg/170113/139-1F113105T2.jpg");
        list.add("http://www.bz55.com/uploads/allimg/170113/139-1F113105U5.jpg");
        list.add("http://www.bz55.com/uploads/allimg/161102/139-161102112311.jpg");
        list.add("http://www.bz55.com/uploads/allimg/160907/139-160ZG53I4.jpg");
        list.add("http://www.bz55.com/uploads/allimg/160907/139-160ZG53I7.jpg");
        list.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/16/c5/20997352_1368694364636.jpg");
        list.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1305/16/c5/20997352_1368694364648.jpg");
        return list;
    }


    public static ArrayList getFourList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("http://t1.mmonly.cc/uploads/tu/sm/201601/19/97.jpg");
        list.add("http://t1.mmonly.cc/uploads/tu/sm/201601/19/101.jpg");
        list.add("http://preview.quanjing.com/bjirm001/bji01750176.jpg");
        return list;
    }


    public static ArrayList getOneList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("http://img0.imgtn.bdimg.com/it/u=2183177165,3251001641&fm=23&gp=0.jpg");
        return list;
    }

    public static ArrayList getEmptyList() {
        ArrayList<String> list = new ArrayList<String>();
        return list;
    }


    private static String[] mUrls = new String[]{"http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://img3.fengniao.com/forum/attachpics/537/165/21472986.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=ea218b2c5566d01661199928a729d498/a08b87d6277f9e2fd4f215e91830e924b999f308.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg"};


    public static List<FriendDateMode> getListData() {

        List<FriendDateMode> mList = new ArrayList<>();

        FriendDateMode model1 = new FriendDateMode();
        model1.urlList.add(mUrls[0]);
        mList.add(model1);

        FriendDateMode model2 = new FriendDateMode();
        model2.urlList.add(mUrls[4]);
        mList.add(model2);

        FriendDateMode model3 = new FriendDateMode();
        model3.urlList.add(mUrls[2]);
        mList.add(model3);

        FriendDateMode model4 = new FriendDateMode();
        for (int i = 0; i < mUrls.length; i++) {
            model4.urlList.add(mUrls[i]);
        }
        model4.isShowAll = false;
        mList.add(model4);

        FriendDateMode model5 = new FriendDateMode();
        for (int i = 0; i < mUrls.length; i++) {
            model5.urlList.add(mUrls[i]);
        }
        model5.isShowAll = true;//显示全部图片
        mList.add(model5);

        FriendDateMode model6 = new FriendDateMode();
        for (int i = 0; i < 9; i++) {
            model6.urlList.add(mUrls[i]);
        }
        mList.add(model6);

        FriendDateMode model7 = new FriendDateMode();
        for (int i = 3; i < 7; i++) {
            model7.urlList.add(mUrls[i]);
        }
        mList.add(model7);

        FriendDateMode model8 = new FriendDateMode();
        for (int i = 3; i < 6; i++) {
            model8.urlList.add(mUrls[i]);
        }
        mList.add(model8);


        return mList;
    }

}
