package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：身份认证《个人》
 * 作者：碧血染银枪 on 2017/5/9 10:09
 * 邮箱：ydc_0128@163.com
 */

public class IdentityVerifyFragment extends BaseFragment {
    @BindView(R.id.rl_identity_verify_name)
    LinearLayout rlIdentityVerifyName;
    @BindView(R.id.rl_identity_verify_age)
    LinearLayout rlIdentityVerifyAge;
    @BindView(R.id.iv_identity_verify_div_open_close)
    ImageView ivIdentityVerifyDivOpenClose;
    @BindView(R.id.rl_identity_verify_sex)
    LinearLayout rlIdentityVerifySex;
    @BindView(R.id.rl_identity_verify_iphone)
    LinearLayout rlIdentityVerifyIphone;
    @BindView(R.id.rl_identity_verify_add)
    Button rlIdentityVerifyAdd;
    Unbinder unbinder;
    private boolean isOpen;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_identity_verify, null));
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

    @OnClick({R.id.iv_identity_verify_div_open_close, R.id.rl_identity_verify_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_identity_verify_div_open_close:
                if (isOpen) {
                    ivIdentityVerifyDivOpenClose.setImageResource(R.mipmap.nan_open);
                } else {
                    ivIdentityVerifyDivOpenClose.setImageResource(R.mipmap.close);
                }
                isOpen = !isOpen;
                break;
            case R.id.rl_identity_verify_add:
                break;
        }
    }
}
