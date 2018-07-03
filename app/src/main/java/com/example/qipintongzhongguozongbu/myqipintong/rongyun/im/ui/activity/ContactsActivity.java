package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.fragment.ContactsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 注释：通讯录
 * 作者：碧血染银枪 on 2017/5/9 16:24
 * 邮箱：ydc_0128@163.com
 */

public class ContactsActivity extends SupportActivity {
    @BindView(R.id.contacts_activity_my)
    FrameLayout contactsActivityMy;
    public FragmentManager supportFragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contaacts_activity);
        ButterKnife.bind(this);
        supportFragmentManager = getSupportFragmentManager();
        replaceLoadRootFragment(R.id.contacts_activity_my, ContactsFragment.getInstance(), false);
    }

}
