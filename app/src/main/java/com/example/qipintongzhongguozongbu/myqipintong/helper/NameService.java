package com.example.qipintongzhongguozongbu.myqipintong.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 注释：数据库操作类
 * 作者：碧血染银枪 on 2017/6/8 16:36
 * 邮箱：ydc_0128@163.com
 */

public class NameService {
    private NameHelper dbHelper = null;

          public NameService(Context context) {
             dbHelper = new NameHelper(context);
       }
    /**
        * 查询某条记录是否存在
         *
      * @param name
      * @return
        */
   public boolean find(String name) {
             SQLiteDatabase db = dbHelper.getReadableDatabase();
           Cursor cursor = db.rawQuery(
                            "select * from user_table where userName = ?",
                             new String[] { name });
             boolean result = cursor.moveToNext();
               db.close();
                return result;
        }
    public boolean findAndroid(String name) {
             SQLiteDatabase db = dbHelper.getReadableDatabase();
              Cursor cursor = db.query(DBInfo.Table.USER_INFO_TB_NAME, null, "userName = ?",
                           new String[] { name }, null, null, null);
              boolean result = cursor.moveToNext();// true代表查找到了
              db.close();
             return result;
         }

    /**
     * 返回所有的数据库信息
     *
     * @return
     */
    public List<HashMap<String, String>> findAll() {
        List<HashMap<String, String>> list = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        if (cursor.getCount() > 0) {
            list = new ArrayList<HashMap<String, String>>();
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("userId"));
                String name = cursor.getString(cursor
                        .getColumnIndex("userName"));
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", id);
                map.put("name", name);
                list.add(map);
            }
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<HashMap<String, String>> findAllAndroid() {
        List<HashMap<String, String>> list = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBInfo.Table.USER_INFO_TB_NAME, new String[]{
                "userId", "userName"}, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            list = new ArrayList<HashMap<String, String>>();
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("userId"));
                String name = cursor.getString(cursor
                        .getColumnIndex("userName"));
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", id);
                map.put("name", name);
                list.add(map);
            }
        }
        cursor.close();
        db.close();
        return list;
    }

}
