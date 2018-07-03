package com.example.qipintongzhongguozongbu.myqipintong.twofold;

/**
 * 注释：选择
 * 作者：碧血染银枪 on 2017/6/9 17:21
 * 邮箱：ydc_0128@163.com
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;

public class ValuePickerMockActivity extends Activity implements View.OnClickListener {

    public static final String SELECTED_LEFT = "SELECTED_LEFT";
    public static final String SELECTED_RIGHT = "SELECTED_RIGHT";

    private ValuePicker vpTest;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_mock);

        vpTest = (ValuePicker) findViewById(R.id.vpTest);
        vpTest.setButtonOnClickListener(this);

        //set the selected view
        Intent intent = getIntent();
        String leftValue = intent.getStringExtra(SELECTED_LEFT);
        String rightValue = intent.getStringExtra(SELECTED_RIGHT);

        vpTest.setLeftValue(leftValue);
        vpTest.setRightValue(rightValue);

        vpTest.initialize();
    }

    @Override
    public void onClick(View arg0) {
        String rightValue = vpTest.getRightValue();
        if(rightValue == null){
            Toast.makeText(this, "请选择右边的值", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(SELECTED_LEFT, vpTest.getLeftVaue());
        data.putExtra(SELECTED_RIGHT, vpTest.getRightValue());
        setResult(RESULT_OK, data);
        finish();
    }

}
