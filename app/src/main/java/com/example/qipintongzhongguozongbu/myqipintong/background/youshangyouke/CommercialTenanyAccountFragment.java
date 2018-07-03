package com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.tongbi.ExpenseCalendarFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：商户账户
 * 作者：碧血染银枪 on 2017/5/9 11:10
 * 邮箱：ydc_0128@163.com
 */

public class CommercialTenanyAccountFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.ll_commercial_tenant_account_yingye)
    LinearLayout llCommercialTenantAccountYingye;
    @BindView(R.id.ll_commercial_tenant_account_chongzhi)
    LinearLayout llCommercialTenantAccountChongzhi;
    @BindView(R.id.bt_commercial_tenant_account_top_up)
    Button btCommercialTenantAccountTopUp;
    @BindView(R.id.my_shouzhi)
    TextView myShouzhi;


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_commercial_tenant_account, null));
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
        mTvTitle.setText("账户");
        mIvBack.setVisibility(View.GONE);
        super.onSupportVisible();
    }

    @OnClick({R.id.bt_commercial_tenant_account_top_up, R.id.my_shouzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_commercial_tenant_account_top_up:
//                Intent intent = new Intent(getActivity(), WXPayEntryActivity.class);
//                startActivity(intent);
                break;
//
            case R.id.my_shouzhi:
                start(new ExpenseCalendarFragment());
                break;
        }
    }

    @OnClick(R.id.my_shouzhi)
    public void onViewClicked() {
    }
}
