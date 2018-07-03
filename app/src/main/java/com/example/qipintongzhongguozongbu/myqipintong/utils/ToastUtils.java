package com.example.qipintongzhongguozongbu.myqipintong.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 一脸懵逼
 * E-mail     :anber1229423614@163.com
 * Date       : 2016/11/7 14:01
 */

public class ToastUtils {

    public static void showToast(Context ctx, String text) {
        Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
    }

    // 在子线程展示Toast
    public static void showToastOnUiThread(final Activity activity,
                                           final String text) {
        if (activity == null) {
            return;
        }

        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
