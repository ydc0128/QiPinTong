package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
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
 * Created by L on 2017/3/27.
 * 修改手机
 */
public class ModifyThePhoneFragment extends BaseFragment {
    @BindView(R.id.tv_v_t_m_p_shouji)
    TextView tvVTMPShouji;
    @BindView(R.id.et_v_t_m_p_phone_m)
    EditText etVTMPPhoneM;
    @BindView(R.id.et_v_t_m_p_phone_verify_m)
    EditText etVTMPPhoneVerifyM;
    @BindView(R.id.bt_v_t_m_p_phone_verify_m)
    Button btVTMPPhoneVerifyM;
    @BindView(R.id.btt_v_t_m_p_phone_confirm_m)
    Button bttVTMPPhoneConfirmM;

    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_modify_the_phone, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }
    public void onSupportVisible() {
        mTvTitle.setText("修改手机");
//        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick({R.id.bt_v_t_m_p_phone_verify_m, R.id.btt_v_t_m_p_phone_confirm_m})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_v_t_m_p_phone_verify_m:
//                获取验证码
                break;
            case R.id.btt_v_t_m_p_phone_confirm_m:
//                确定
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
