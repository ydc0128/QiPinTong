package com.example.qipintongzhongguozongbu.myqipintong.txl;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释：通讯录显示界面
 * 作者：碧血染银枪 on 2017/5/24 14:48
 * 邮箱：ydc_0128@163.com
 */

public class AddTXLActicity extends Activity {
    private RecyclerView rctxl;
    private GetPhoneNumberFromMobile getPhoneNumberFromMobile;
    private List<PhoneInfo> list = new ArrayList<PhoneInfo>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txl_list);
        rctxl = (RecyclerView) findViewById(R.id.rc_txl);
        getPhoneNumberFromMobile = new GetPhoneNumberFromMobile();
        list = getPhoneNumberFromMobile.getPhoneNumberFromMobile(this);
        setMyAdapter();

    }
    public void setMyAdapter(){
        rctxl.setLayoutManager(new LinearLayoutManager(AddTXLActicity.this));
        rctxl.setAdapter(new MyAdapter(R.layout.phone_txl_item, ImageList.getImageList()));

        rctxl.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
              String number = list.get(position).getNumber();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                // TODO Auto-generated method stub
                String number = list.get(position).getNumber();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);

            }
        });
    }
}

