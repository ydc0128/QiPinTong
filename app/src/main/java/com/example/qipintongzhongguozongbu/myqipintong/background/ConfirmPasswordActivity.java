package com.example.qipintongzhongguozongbu.myqipintong.background;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.view.PwdEditText;

/**
 * 注释：确认密码
 * 作者：碧血染银枪 on 2017/6/9 11:28
 * 邮箱：ydc_0128@163.com
 *
 */

public class ConfirmPasswordActivity extends Activity {
   private PwdEditText petConfirmPwd;
  private   Button btConfirmPwd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixin_confirm_passwoed);
        setTitle("设置密码");
        petConfirmPwd=(PwdEditText)findViewById(R.id.pet_confirm_pwd) ;
        btConfirmPwd=(Button)findViewById(R.id.bt_confirm_pwd) ;
        petConfirmPwd.setOnInputFinishListener(new PwdEditText.OnInputFinishListener() {
            @Override
            public void onInputFinish(String password) {
                btConfirmPwd.setVisibility(View.VISIBLE);
            }
        });
        btConfirmPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfirmPasswordActivity.this, "设置成功", Toast.LENGTH_LONG).show();


            }
        });
    }
}
