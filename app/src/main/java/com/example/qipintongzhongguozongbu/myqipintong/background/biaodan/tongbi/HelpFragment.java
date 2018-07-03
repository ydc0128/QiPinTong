package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.tongbi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：帮助与反馈
 * 作者：碧血染银枪 on 2017/5/25 13:50
 * 邮箱：ydc_0128@163.com
 */

public class HelpFragment extends BaseFragment {
    @BindView(R.id.iv_my_help_erweima)
    ImageView ivMyHelpErweima;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_my_earnings_help, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    public void onSupportVisible() {
        mTvTitle.setText("帮助与反馈");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_my_help_erweima)
    public void onViewClicked() {
    }
}
