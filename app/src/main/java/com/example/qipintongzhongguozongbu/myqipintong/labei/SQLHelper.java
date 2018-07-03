package com.example.qipintongzhongguozongbu.myqipintong.labei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Description: 易通学院的数据库帮助
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/9 下午2:52
 */
public class SQLHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "database.db";// 数据库名称
    public static final int VERSION = 1;
    public static final String TABLE_CHANNEL = "channel";//数据表
    public static final String ID = "id";//
    public static final String NAME = "name";
    public static final String ORDERID = "orderId";
    public static final String SELECTED = "selected";
    private Context context;
    public SQLHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 创建数据库后，对数据库的操作
        String sql = "create table if not exists "+TABLE_CHANNEL +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ID + " INTEGER , " +
                NAME + " TEXT , " +
                ORDERID + " INTEGER , " +
                SELECTED + " SELECTED)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 更改数据库版本的操作
        onCreate(db);
    }

}

