package com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：
 * 作者：碧血染银枪 on 2017/6/12 09:27
 * 邮箱：ydc_0128@163.com
 */

public class KuHuKUFragmeng extends BaseFragment {
    @BindView(R.id.lv_keheku_jinrixinzeng)
    LinearLayout lvKehekuJinrixinzeng;
    @BindView(R.id.lv_keheku_mingxi)
    LinearLayout lvKehekuMingxi;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.background_kehuke, null);
        return swipeBackView(view);
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
    @Override
    public void onSupportVisible() {
        mTvTitle.setText("客户库");
        mIvBack.setVisibility(View.GONE);
        super.onSupportVisible();
    }

    @OnClick({R.id.lv_keheku_jinrixinzeng, R.id.lv_keheku_mingxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lv_keheku_jinrixinzeng:
                start(new TeamKeHuFragment());
                break;
            case R.id.lv_keheku_mingxi:
                start(new TeamKeHuFragment());
                break;
        }
    }
}
