package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 注释：绑定手机号
 * 作者：碧血染银枪 on 2017/5/19 11:22
 * 邮箱：ydc_0128@163.com
 */

public class BindingMobilePhoneFragment extends BaseFragment {

    @BindView(R.id.ll_binding_mobile_mane)
    LinearLayout llBindingMobileMane;
    @BindView(R.id.ll_binding_mobile_age)
    LinearLayout llBindingMobileAge;
    @BindView(R.id.tv_binding_city)
    TextView tvBindingCity;
    @BindView(R.id.ll_binding_mobile_city)
    LinearLayout llBindingMobileCity;
    @BindView(R.id.ll_binding_mobile_number)
    LinearLayout llBindingMobileNumber;
    @BindView(R.id.bt_binding_phone_verify)
    Button btBindingPhoneVerify;
    @BindView(R.id.ll_binding_mobile_verification)
    LinearLayout llBindingMobileVerification;
    @BindView(R.id.bt_biding_phone_confirm)
    Button btBidingPhoneConfirm;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_binding_mobile, null));

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
}
