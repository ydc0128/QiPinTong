package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.rong.common.FileUtils;
import io.rong.common.RLog;

/**
 * 注释：异常处理
 * 作者：碧血染银枪 on 2017/5/4 15:21
 * 邮箱：ydc_0128@163.com
 */

public class RongyunExceptionHandler implements Thread.UncaughtExceptionHandler {
    Context mContext;

    public RongyunExceptionHandler(Context context) {
        this.mContext = context;
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formatTime = sdf.format(date);
        String path = FileUtils.getCachePath(this.mContext, "ronglog") + "/" + "crash_" + formatTime + ".log";
        File crashFile = new File(path);

        try {
            crashFile.createNewFile();
            FileOutputStream e = new FileOutputStream(crashFile);
            PrintStream printStream = new PrintStream(e);
            ex.printStackTrace(printStream);
            printStream.close();
        } catch (FileNotFoundException var12) {
            var12.printStackTrace();
        } catch (IOException var13) {
            var13.printStackTrace();
        } catch (SecurityException var14) {
            var14.printStackTrace();
        }

        RLog.e("RongyunExceptionHandler", "uncaughtException", ex);
        System.exit(2);
    }
}
