package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 注释：空页面提示
 * 作者：碧血染银枪 on 2017/5/19 10:40
 * 邮箱：ydc_0128@163.com
 */

public class EmptyFragment extends BaseFragment {
    @BindView(R.id.tv_empty_prompt)
    TextView tvEmptyPrompt;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_empty, null);
        return swipeBackView(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("投递简历");
        mIvBack.setVisibility(View.GONE);
        super.onSupportVisible();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
