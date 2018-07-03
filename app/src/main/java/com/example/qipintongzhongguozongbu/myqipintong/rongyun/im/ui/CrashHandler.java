package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.rong.common.FileUtils;
import io.rong.common.RLog;

/**
 * 注释：崩溃时日志工具
 * 作者：碧血染银枪 on 2017/5/4 15:54
 * 邮箱：ydc_0128@163.com
 */


public class CrashHandler implements Thread.UncaughtExceptionHandler {


    //系统默认的UncaughtException处理类

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    //CrashHandler实例

    private static CrashHandler instance;

    //程序的Context对象

    private Context mContext;


    public CrashHandler(Context context) {

        init(context);

    }


    /**
     * 获取CrashHandler实例
     */

    public static synchronized CrashHandler getInstance(Context context) {

        if (instance == null) {

            instance = new CrashHandler(context);

        }

        return instance;

    }


    /**
     * 初始化
     */

    private void init(Context context) {

        mContext = context;

        //获取系统默认的UncaughtException处理器

        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        //设置该CrashHandler为程序的默认处理器

        Thread.setDefaultUncaughtExceptionHandler(this);

    }

    @Override

    public void uncaughtException(Thread thread, Throwable ex) {
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String formatTime = sdf.format(date);
        String path = FileUtils.getCachePath(this.mContext, "ronglog") + "/" + "crash_" + formatTime + ".log";
        File crashFile = new File(path);

        if (!handleException(ex) && mDefaultHandler != null) {

            //如果用户没有处理则让系统默认的异常处理器来处理

            mDefaultHandler.uncaughtException(thread, ex);

        } else {

            try {
                crashFile.createNewFile();
                FileOutputStream e = new FileOutputStream(crashFile);
                PrintStream printStream = new PrintStream(e);
                ex.printStackTrace(printStream);
                printStream.close();
                Thread.sleep(3000);

            }catch (FileNotFoundException var12) {
                var12.printStackTrace();
            } catch (IOException var13) {
                var13.printStackTrace();
            } catch (SecurityException var14) {
                var14.printStackTrace();
            } catch (InterruptedException e) {

//                Log.e(TAG, "error : ", e);

            }

            //退出程序

            android.os.Process.killProcess(android.os.Process.myPid());

            System.exit(1);

        }

        RLog.e("RongyunExceptionHandler", "uncaughtException", ex);
        System.exit(2);
    }


    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     */

    private boolean handleException(Throwable ex) {

        if (ex == null) {

            return false;

        }

        Writer writer = new StringWriter();

        PrintWriter printWriter = new PrintWriter(writer);

        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();

        while (cause != null) {

            cause.printStackTrace(printWriter);

            cause = cause.getCause();

        }

        printWriter.close();

        String result = writer.toString();

        Log.i("----nxtvhd----", "Crash：" + result);

        return true;

    }

}





