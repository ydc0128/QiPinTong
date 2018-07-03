package com.example.qipintongzhongguozongbu.myqipintong.helper;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.TakePhotoOptions;

/**
 * Description: 相机的帮助类 处理图片的配置类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/22 下午2:29
 */
public class CustomHelper {

    public CustomHelper() {
    }


    public void init(TakePhoto takePhoto) {
        configTakePhotoOption(takePhoto);
        configCompress(takePhoto);
    }

    //是否从自带相册选
    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();

        takePhoto.setTakePhotoOptions(new TakePhotoOptions.Builder().setWithOwnGallery(true).create());

        builder.setWithOwnGallery(false);
        //使用系统自带相册
        builder.setCorrectImage(true);
        //纠正照片旋转角度
        takePhoto.setTakePhotoOptions(builder.create());

        takePhoto.onPickMultiple(9);
        //最多选择几张
        CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
    }

    private void configCompress(TakePhoto takePhoto) {

        boolean showProgressBar = true;
        //显示亚索进度条

        boolean enableRawFile = true;
        //拍照压缩后保存原图

        CompressConfig config;

        config = new CompressConfig.Builder()
                .setMaxSize(102400)
                .setMaxPixel(800 >= 800 ? 800 : 800)
                .enableReserveRaw(enableRawFile)
                .create();
        //使用自带亚索工具进行压缩 图片的寛高等信息

        takePhoto.onEnableCompress(config, showProgressBar);


    }
}