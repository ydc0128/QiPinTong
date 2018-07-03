package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/27.
 * 设置
 */

public class SetFragment extends BaseFragment {
    @BindView(R.id.ll_s_u_amend_phone_m)
    LinearLayout llSUAmendPhoneM;
    @BindView(R.id.ll_s_u_password_m)
    LinearLayout llSUPasswordM;
    @BindView(R.id.bt_s_u_out)
    Button btSUOut;

    Unbinder unbinder;

    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_se, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.ll_s_u_amend_phone_m, R.id.ll_s_u_password_m, R.id.bt_s_u_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_s_u_amend_phone_m:
               start (new ModifyThePhoneFragment());
                break;
            case R.id.ll_s_u_password_m:
                start (new ChangePasswordFragment());
                break;
            case R.id.bt_s_u_out:
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
