package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.qipintongzhongguozongbu.myqipintong.R;


public class PublicServiceInfoActivity extends BaseAActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("", "---------PublicServiceInfoActivity------------");
        setContentView(R.layout.pub_account_info);
        setTitle("公众号信息");

    }

}
