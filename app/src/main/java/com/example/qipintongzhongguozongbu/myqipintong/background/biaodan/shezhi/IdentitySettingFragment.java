package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.TheCompanFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke.CommercialTenanyrEgister;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager.MeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/6.
 * 身份选择
 */

public class IdentitySettingFragment extends BaseFragment {

    @BindView(R.id.tv_regist_class_qiye)
    RelativeLayout tvRegistClassQiye;
    @BindView(R.id.tv_regist_class_geren)
    RelativeLayout tvRegistClassGeren;
    @BindView(R.id.rl_my_The_title)
    RelativeLayout rlMyTheTitle;
    @BindView(R.id.my_rc_p)
    CircleImageView myRcP;
    @BindView(R.id.tv_rc_name)
    TextView tvRcName;
    @BindView(R.id.iv_rc_iv_geren)
    ImageView ivRcIvGeren;
    @BindView(R.id.rc_geren)
    TextView rcGeren;
    @BindView(R.id.iv_rc_qiye)
    ImageView ivRcQiye;
    @BindView(R.id.rc_qiye)
    TextView rcQiye;
    @BindView(R.id.iv_rc_merchant)
    ImageView ivRcMerchant;
    @BindView(R.id.rc_merchant)
    TextView rcMerchant;
    @BindView(R.id.tv_regist_class_merchant)
    RelativeLayout tvRegistClassMerchant;

    Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_menu_regist_classes, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("身份选择");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    @OnClick({R.id.tv_regist_class_geren, R.id.tv_regist_class_qiye,R.id.tv_regist_class_merchant})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_regist_class_geren:
                start(new MeFragment());
                break;
            case R.id.tv_regist_class_qiye:
                start(new TheCompanFragment());
                break;
            case R.id.tv_regist_class_merchant:
                start(new CommercialTenanyrEgister());
//       2       start(new CommercialTenantMyFragment());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
