package com.example.qipintongzhongguozongbu.myqipintong.helper;

import android.net.Uri;
import android.os.Environment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

/**
 * Description: 朋友圈背景墙的选择帮助类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/2 下午3:15
 */
public class PhotoHelper {


    private final TakePhoto takePhoto;

    public PhotoHelper(TakePhoto takePhoto) {
        this.takePhoto = takePhoto;
    }

    /**
     * function   : 相册图片处理类
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/3  上午11:09
     */
    public void configTakePhotoOption() {

        takePhoto.onPickMultiple(1);
       //takePhoto.onPickMultipleWithCrop(1, getCropOptions());

        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();

        builder.setWithOwnGallery(false);//使用takephoto自带相册

        takePhoto.setTakePhotoOptions(builder.create());

        takePhoto.onEnableCompress(null, true);//压缩

        CompressConfig compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();

    }


    /**
     * function   : 拍照后处理类
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/3  上午11:09
     */
    public void  configTakePhoto() {

        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
    }



    //裁剪相关
    public CropOptions getCropOptions() {

        boolean withWonCrop = false;//使用第三方裁剪工具

        CropOptions.Builder builder = new CropOptions.Builder();

        builder.setWithOwnCrop(withWonCrop);

        return builder.create();
    }

}
