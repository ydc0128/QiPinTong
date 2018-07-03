package com.example.qipintongzhongguozongbu.myqipintong.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 注释：数据库帮助类
 * 作者：碧血染银枪 on 2017/6/8 16:17
 * 邮箱：ydc_0128@163.com
 */

public class NameHelper extends SQLiteOpenHelper {
    public NameHelper(Context context) {
            super(context, DBInfo.DB.DB_NAME, null, DBInfo.DB.DB_VERSION);
         }

       @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(DBInfo.Table.USER_INFO_CREATE);
       }

         @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DBInfo.Table.USER_INFO_DROP);
            onCreate(db);
        }


}
