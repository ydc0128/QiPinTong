package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.WXPwdEditTextActivity;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi.AmendPasswordFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi.VerifyTheMobilePhonefragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.AboutRongCloudActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/6.
 * 设置界面
 */

public class SettingUpFragment extends BaseFragment {


    @BindView(R.id.ll_s_u_amend_phone)
    LinearLayout llSUAmendPhone;
    @BindView(R.id.ll_s_u_password)
    LinearLayout llSUPassword;
    @BindView(R.id.bt_s_u_out)
    Button btSUOut;


    @BindView(R.id.ll_s_u_guanyu)
    LinearLayout llSUGuanyu;

    Unbinder unbinder;
    @BindView(R.id.ll_s_u_zhifu)
    LinearLayout llSUZhifu;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_setting_up, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("设置");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick({R.id.ll_s_u_amend_phone, R.id.ll_s_u_password, R.id.bt_s_u_out, R.id.ll_s_u_guanyu, R.id.ll_s_u_zhifu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_s_u_amend_phone:
                start(new VerifyTheMobilePhonefragment());
                break;
            case R.id.ll_s_u_password:
                start(new AmendPasswordFragment());
                break;
            case R.id.bt_s_u_out:
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); //关闭dialog
                        startActivity(new Intent(mActivity, LoginActivity.class));
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setMessage("是否退出");
                builder.show();
                break;
            case R.id.ll_s_u_guanyu:
                startActivity(new Intent(mActivity, AboutRongCloudActivity.class));
                break;
            case R.id.ll_s_u_zhifu:
                startActivity(new Intent(mActivity, WXPwdEditTextActivity.class));
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
