package com.example.qipintongzhongguozongbu.myqipintong.background;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.view.PwdEditText;

/**
 * 注释：支付密码
 * 作者：碧血染银枪 on 2017/5/26 11:21
 * 邮箱：ydc_0128@163.com
 */

public class WXPwdEditTextActivity extends Activity {
    private PwdEditText mPetPwd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixin_pwdittext);
        mPetPwd = (PwdEditText) findViewById(R.id.pet_pwd);
        setTitle("设置密码");
        mPetPwd.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {

            @Override
            public void onInputFinish(String password) {
                startActivity(new Intent(WXPwdEditTextActivity.this, ConfirmPasswordActivity.class));
                Toast.makeText(WXPwdEditTextActivity.this, password, 1).show();
            }
        });
    }


}