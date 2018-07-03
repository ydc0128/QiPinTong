package com.example.qipintongzhongguozongbu.myqipintong.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 注释：数据库信息类
 * 作者：碧血染银枪 on 2017/6/8 16:21
 * 邮箱：ydc_0128@163.com
 */

public class DBInfo {
    private SQLiteDatabase xingmeng;
    private Context context;
    private final int BUFFER_SIZE = 400000;


    public static class DB {
        // 数据库名称
        public static final String DB_NAME = "xingming.sql";
        // 数据库的版本号
        public static final int DB_VERSION = 1;
    }

    /**
     * 数据库表的信息
     *
     * @author loonggg
     */
    public static class Table {
        public static final String USER_INFO_TB_NAME = "user_table";
        public static final String USER_INFO_CREATE = "CREATE TABLE IF NOT EXISTS "
                + USER_INFO_TB_NAME
                + " ( _id INTEGER PRIMARY KEY,userId text,userName text)";
        public static final String USER_INFO_DROP = "DROP TABLE"
                + USER_INFO_TB_NAME;
    }
    private SQLiteDatabase openDatabase(String dbfile) {

        try {
            if (!(new File(dbfile).exists())) {
                //判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = this.context.getResources().openRawResource(
                        R.raw.xingming); //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }

            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
            return db;

        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }

    public void closeDatabase() {
        this.xingmeng.close();

    }
}





