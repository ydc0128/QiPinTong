package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/2/25.
 * 注册页面
 */

public class RegisterFragmnet extends BaseFragment {
    @BindView(R.id.et_register_mold_choice)
    EditText etRegisterMoldChoice;
    @BindView(R.id.tv_register_mod_phone_number)
    TextView tvRegisterModPhoneNumber;
    @BindView(R.id.et_register_mod_phone_number)
    EditText etRegisterModPhoneNumber;
    @BindView(R.id.et_register_auth_code)
    EditText etRegisterAuthCode;
    @BindView(R.id.tv_register_assword)
    TextView tvRegisterAssword;
    @BindView(R.id.et_register_assword)
    EditText etRegisterAssword;
    @BindView(R.id.tv_confirm_your_password)
    TextView tvConfirmYourPassword;
    @BindView(R.id.et_register_confirm_your_password)
    EditText etRegisterConfirmYourPassword;
    @BindView(R.id.bt_register_register)
    Button btRegisterRegister;
    @BindView(R.id.bt_register_auth_code)
    Button btRegisterAuthCode;
    @BindView(R.id.tv_register_login)
    TextView tvRegisterLogin;

    Unbinder unbinder;
    public static RegisterFragmnet getInstance() {
        return new RegisterFragmnet();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_menu_register, null));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("注册");
//        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    @OnClick({R.id.et_register_mold_choice, R.id.bt_register_auth_code, R.id.et_register_mod_phone_number, R.id.et_register_auth_code,
            R.id.et_register_assword, R.id.et_register_confirm_your_password, R.id.bt_register_register, R.id.tv_register_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_register_mold_choice:
                new AlertDialog.Builder(mActivity,
                        R.style.transparentFrameWindowStyle).setTitle("类型")
                        .setMultiChoiceItems(new String[]{"个人", "企业"}, null, null)
                        .setPositiveButton("确定", null).
                        setNegativeButton("取消", null).
                        show();
//                类型选择
                break;
            case R.id.et_register_mod_phone_number:
//                手机号
                break;
            case R.id.et_register_auth_code:
//                验证码
                break;
            case R.id.et_register_assword:
//                密码
                break;
            case R.id.et_register_confirm_your_password:
//                重复密码
                break;
            case R.id.bt_register_register:
//                注册
                break;
            case R.id.bt_register_auth_code:
//                获取验证码
                break;

            case R.id.tv_register_login:
                start(new LoginFragment());
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
