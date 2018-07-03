package com.example.qipintongzhongguozongbu.myqipintong.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Description: 加载图片的工具
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/21 上午10:28
 */
public class GlideUtils {

    public static void loadImage(Context context, String url, final ImageView imageView) {

        if (StringUtils.isEmpty(url)) {
            return;
        }

        final ImageView.ScaleType scaleType = imageView.getScaleType();

        //imageView.setScaleType(ImageView.ScaleType.CENTER);

        Glide.with(context)

                .load(url).thumbnail(0.1f)

                .error(R.mipmap.lodingerror)

                .diskCacheStrategy(DiskCacheStrategy.ALL)

                .placeholder(R.mipmap.loding)//加载中显示的图片

                .into(new GlideDrawableImageViewTarget(imageView) {

                    @Override

                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {

                        super.onResourceReady(drawable, anim);

                        //在这里添加一些图片加载完成的操作

                        imageView.setScaleType(scaleType);

                    }

                });

    }


    public static void loadImage(Context context, File file, final ImageView imageView) {

        if (file == null) {
            return;
        }

        final ImageView.ScaleType scaleType = imageView.getScaleType();

        //imageView.setScaleType(ImageView.ScaleType.CENTER);

        Glide.with(context)

                .load(file).thumbnail(0.1f)

                .error(R.mipmap.lodingerror)

                .diskCacheStrategy(DiskCacheStrategy.ALL)

                .placeholder(R.mipmap.loding)//加载中显示的图片

                .into(new GlideDrawableImageViewTarget(imageView) {

                    @Override

                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {

                        super.onResourceReady(drawable, anim);

                        //在这里添加一些图片加载完成的操作

                        imageView.setScaleType(scaleType);

                    }

                });

    }


}
