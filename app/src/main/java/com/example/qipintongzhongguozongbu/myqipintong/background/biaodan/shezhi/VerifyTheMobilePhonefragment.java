package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.WXPwdEditTextActivity;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/7.
 * 我--sz--验证手机
 */

public class VerifyTheMobilePhonefragment extends BaseFragment {

    @BindView(R.id.et_v_t_m_p_phone)
    EditText etVTMPPhone;
    @BindView(R.id.et_v_t_m_p_phone_verify)
    EditText etVTMPPhoneVerify;
    @BindView(R.id.bt_v_t_m_p_phone_verify)
    Button btVTMPPhoneVerify;
    @BindView(R.id.btt_v_t_m_p_phone_confirm)
    Button bttVTMPPhoneConfirm;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_verify_the_mobile_phone, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("验证手机号");
//        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.et_v_t_m_p_phone, R.id.et_v_t_m_p_phone_verify, R.id.bt_v_t_m_p_phone_verify, R.id.btt_v_t_m_p_phone_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_v_t_m_p_phone:
                break;
            case R.id.et_v_t_m_p_phone_verify:
                break;
            case R.id.bt_v_t_m_p_phone_verify:
                break;
            case R.id.btt_v_t_m_p_phone_confirm:
                startActivity(new Intent(mActivity, WXPwdEditTextActivity.class));
                break;
        }
    }
}
