package com.example.qipintongzhongguozongbu.myqipintong.background.darenbang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释： 达人帮激活
 * 作者：碧血染银枪 on 2017/6/2 12:02
 * 邮箱：ydc_0128@163.com
 */

public class MasterActivationFragment extends BaseFragment {
    @BindView(R.id.bt_master_actovation)
    Button btMasterActovation;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_master_activation, null));
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

    @OnClick(R.id.bt_master_actovation)
    public void onViewClicked() {
        start(new MasterFormFragment());
    }
}
