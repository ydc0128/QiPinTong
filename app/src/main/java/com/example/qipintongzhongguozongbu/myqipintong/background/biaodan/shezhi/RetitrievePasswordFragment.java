package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/2/25.
 * 找回密码
 */

public class RetitrievePasswordFragment extends BaseFragment {
    @BindView(R.id.et_rp_mod_phone_number)
    LinearLayout etRpModPhoneNumber;
    @BindView(R.id.bt_rp_auth_code)
    Button btRpAuthCode;
    @BindView(R.id.bt_rp_next_step)
    Button btRpNextStep;
    @BindView(R.id.et_rp_auth_code)
    EditText etRpAuthCode;

    Unbinder unbinder;
    public void onSupportVisible() {
        mTvTitle.setText("找回密码");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.backgrounf_mene_retrieve_password, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.et_rp_mod_phone_number, R.id.bt_rp_auth_code, R.id.bt_rp_next_step, R.id.et_rp_auth_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_rp_mod_phone_number:
//                手机号
                break;
            case R.id.et_rp_auth_code:
//                验证码
                break;
            case R.id.bt_rp_auth_code:
//                点击验证码
                break;
            case R.id.bt_rp_next_step:
//                下一步
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
