package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：财务管理
 * 作者：碧血染银枪 on 2017/5/25 15:02
 * 邮箱：ydc_0128@163.com
 */

public class FinancialStatisticsFragment extends BaseFragment {
    @BindView(R.id.tv_Opening_today)
    TextView tvOpeningToday;
    @BindView(R.id.rl_fs_Opening_today)
    RelativeLayout rlFsOpeningToday;
    @BindView(R.id.tv_Withdrawals_today)
    TextView tvWithdrawalsToday;
    @BindView(R.id.rl_fs_Withdrawals_today)
    RelativeLayout rlFsWithdrawalsToday;
    @BindView(R.id.tv_Accumulative_cash_withdrawal)
    TextView tvAccumulativeCashWithdrawal;
    @BindView(R.id.rl_fs_Accumulative_cash_withdrawal)
    RelativeLayout rlFsAccumulativeCashWithdrawal;
    @BindView(R.id.tv_Accumulated_amount)
    TextView tvAccumulatedAmount;
    @BindView(R.id.rl_fs_Accumulated_amount)
    RelativeLayout rlFsAccumulatedAmount;
    @BindView(R.id.tv_Recharge_today)
    TextView tvRechargeToday;
    @BindView(R.id.rl_fs_Recharge_today)
    RelativeLayout rlFsRechargeToday;
    @BindView(R.id.tv_Today_amount)
    TextView tvTodayAmount;
    @BindView(R.id.rl_fs_Today_amount)
    RelativeLayout rlFsTodayAmount;
    @BindView(R.id.tv_Cumulative_recharge)
    TextView tvCumulativeRecharge;
    @BindView(R.id.rl_fs_Cumulative_recharge)
    RelativeLayout rlFsCumulativeRecharge;
    @BindView(R.id.tv_Accumulated_recharge_amount)
    TextView tvAccumulatedRechargeAmount;
    @BindView(R.id.rl_fs_Accumulated_recharge_amount)
    RelativeLayout rlFsAccumulatedRechargeAmount;
    Unbinder unbinder;

    public void onSupportVisible() {
        mTvTitle.setText("财务管理");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_financial_statistics, null));
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

    @OnClick({R.id.rl_fs_Opening_today, R.id.rl_fs_Withdrawals_today, R.id.rl_fs_Accumulative_cash_withdrawal, R.id.rl_fs_Accumulated_amount, R.id.rl_fs_Recharge_today, R.id.rl_fs_Today_amount, R.id.rl_fs_Cumulative_recharge, R.id.rl_fs_Accumulated_recharge_amount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_fs_Opening_today:
                start(new PersonalDetailsFragment());
                break;
            case R.id.rl_fs_Withdrawals_today:
               start(new  CumulativeRechargeFragment());
                break;
            case R.id.rl_fs_Accumulative_cash_withdrawal:
                start(new PersonalDetailsFragment());
                break;
            case R.id.rl_fs_Accumulated_amount:
                start(new  CumulativeRechargeFragment());
                break;
            case R.id.rl_fs_Recharge_today:
                start(new PersonalDetailsFragment());
                break;
            case R.id.rl_fs_Today_amount:
                start(new  CumulativeRechargeFragment());
                break;
            case R.id.rl_fs_Cumulative_recharge:
                start(new PersonalDetailsFragment());
                break;
            case R.id.rl_fs_Accumulated_recharge_amount:
                start(new  CumulativeRechargeFragment());
                break;
        }
    }
}
