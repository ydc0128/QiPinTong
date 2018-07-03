package com.example.qipintongzhongguozongbu.myqipintong.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description: 保存网络图片到本地的工具
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/15 上午9:52
 */
public class BitmapUtils {

    public static void saveImage(final Context context, final String path) {

        new Thread() {
            @Override
            public void run() {

                try {

                    final File file = new File(context.getCacheDir(), getFileName(path));

                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.connect();
                    if (conn.getResponseCode() == 200) {

                        InputStream is = conn.getInputStream();

                        FileOutputStream fos = new FileOutputStream(file);

                        byte[] b = new byte[1024];

                        int len = 0;

                        while ((len = is.read(b)) != -1) {
                            fos.write(b, 0, len);
                        }

                        fos.close();

                        Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());

                        saveImage(bm, context);


                    } else {

                        ToastUtils.showToastOnUiThread((Activity) context, "请检查网络...");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public static String getFileName(String path) {
        int index = path.lastIndexOf("/");
        return path.substring(index + 1);
    }

    public static void saveImage(Bitmap bmp, Context context) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "QPT/image");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();

            ToastUtils.showToastOnUiThread((Activity) context, "已保存到" + appDir.toString() + "目录下");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}