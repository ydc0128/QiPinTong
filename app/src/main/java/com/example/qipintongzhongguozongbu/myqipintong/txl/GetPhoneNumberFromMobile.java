package com.example.qipintongzhongguozongbu.myqipintong.txl;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释：读取通讯录
 * 作者：碧血染银枪 on 2017/5/24 11:38
 * 邮箱：ydc_0128@163.com
 */

public class GetPhoneNumberFromMobile {
    private List<PhoneInfo> list;

    public List<PhoneInfo> getPhoneNumberFromMobile(Context context) {
        // TODO Auto-generated constructor stub
        list = new ArrayList<PhoneInfo>();
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        //moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            PhoneInfo phoneInfo = new PhoneInfo(name, number);
            list.add(phoneInfo);
        }
        return list;
    }

}
