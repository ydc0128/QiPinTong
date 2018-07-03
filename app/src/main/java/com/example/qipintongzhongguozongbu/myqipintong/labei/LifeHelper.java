package com.example.qipintongzhongguozongbu.myqipintong.labei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Description: 生活圈的数据库
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/5 下午4:39
 */
public class LifeHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "database.db";// 数据库名称
    public static final int VERSION = 1;
    public static final String TABLE_CHANNEL = "life";//数据表
    public static final String ID = "id";//
    public static final String NAME = "name";
    public static final String ORDERID = "orderId";
    public static final String SELECTED = "selected";
    private Context context;
    public LifeHelper(Context context) {
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
