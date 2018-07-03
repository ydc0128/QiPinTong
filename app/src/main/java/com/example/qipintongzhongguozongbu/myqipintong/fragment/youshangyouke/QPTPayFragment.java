package com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke.CommercialTenanyAccountFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：企聘通支付
 * 作者：碧血染银枪 on 2017/6/2 11:26
 * 邮箱：ydc_0128@163.com
 */

public class QPTPayFragment extends BaseFragment {
    @BindView(R.id.tv_go_wallet)
    TextView tvGoWallet;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.background_qpt_pay, null);
        return view;
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

    @OnClick(R.id.tv_go_wallet)
    public void onViewClicked() {
        start(new CommercialTenanyAccountFragment());
    }
}
