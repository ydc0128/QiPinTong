package com.example.qipintongzhongguozongbu.myqipintong.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Description: 保存图片到相册的工具类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/6 上午11:28
 */
public class ScannerUtils {

    // 扫描的三种方式
    public static enum ScannerType {
        RECEIVER, MEDIA
    }

    // 首先保存图片
    public static void saveImageToGallery(Context context, Bitmap bitmap, ScannerType type) {
        File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "HouSheng");
        if (!appDir.exists()) {
            // 目录不存在 则创建
            appDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos); // 保存bitmap至本地
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (type == ScannerType.RECEIVER) {
                ScannerByReceiver(context, file.getAbsolutePath());
            } else if (type == ScannerType.MEDIA) {
                ScannerByMedia(context, file.getAbsolutePath());
            }
            if (!bitmap.isRecycled()) {
                // bitmap.recycle(); 当存储大图片时，为避免出现OOM ，及时回收Bitmap
                System.gc(); // 通知系统回收
            }
            Toast.makeText(context, "图片保存为" + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiver扫描更新图库图片
     **/

    private static void ScannerByReceiver(Context context, String path) {
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"
                + path)));
        Log.v("TAG", "receiver scanner completed");
    }

    /**
     * MediaScanner 扫描更新图片图片
     **/

    private static void ScannerByMedia(Context context, String path) {
        MediaScannerConnection.scanFile(context, new String[]{path}, null, null);
        Log.v("TAG", "media scanner completed");
    }
}
