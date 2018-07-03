package com.example.qipintongzhongguozongbu.myqipintong.background.me;

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
 * 注释：用户明细
 * 作者：碧血染银枪 on 2017/5/25 16:06
 * 邮箱：ydc_0128@163.com
 */

public class UserDetailsFragment extends BaseFragment {
    @BindView(R.id.ll_udf_shanghu)
    LinearLayout llUdfShanghu;
    @BindView(R.id.ll_udf_qiye)
    LinearLayout llUdfQiye;
    @BindView(R.id.ll_udf_geren)
    LinearLayout llUdfGeren;
    Unbinder unbinder;

    public void onSupportVisible() {
        mTvTitle.setText("用户明细");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_user_details, null));
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

    @OnClick({R.id.ll_udf_shanghu, R.id.ll_udf_qiye, R.id.ll_udf_geren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_udf_shanghu:
                start(new PersonalDetailsFragment());
                break;
            case R.id.ll_udf_qiye:
                start(new PersonalDetailsFragment());
                break;
            case R.id.ll_udf_geren:
                start(new PersonalDetailsFragment());
                break;
        }
    }
}
