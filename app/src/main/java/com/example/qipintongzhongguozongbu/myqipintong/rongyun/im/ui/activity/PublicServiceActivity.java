package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.SlideLayout;


public class PublicServiceActivity extends BaseAActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pub_list);
        new SlideLayout(this).bind();
        setTitle(R.string.de_actionbar_set_public);
        Button rightButton = getHeadRightButton();
        rightButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.de_ic_add));
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublicServiceActivity.this, PublicServiceSearchActivity.class);
                startActivity(intent);
            }
        });
    }


}
