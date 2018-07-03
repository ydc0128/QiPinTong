package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.tongbi.ExpenseCalendarFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：账户余额
 * 作者：碧血染银枪 on 2017/5/19 15:51
 * 邮箱：ydc_0128@163.com
 */

public class AccountBalanceFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.tv_account_earnings_shouyi)
    TextView tvAccountEarningsShouyi;
    @BindView(R.id.tv_my_account_balance)
    TextView tvMyAccountBalance;
    @BindView(R.id.tv_today_account)
    TextView tvTodayAccount;
    @BindView(R.id.tv_cumulative_account)
    TextView tvCumulativeAccount;
    @BindView(R.id.bt_account_earnings_top_up)
    Button btAccountEarningsTopUp;
    @BindView(R.id.ll_m_e_cunru)
    LinearLayout llMECunru;
    @BindView(R.id.rl_my_earnings_record)
    RelativeLayout rlMyEarningsRecord;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_account_balance, null));
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


    @OnClick({R.id.tv_account_earnings_shouyi, R.id.bt_account_earnings_top_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_account_earnings_shouyi:
                start(new ExpenseCalendarFragment());
                break;
            case R.id.bt_account_earnings_top_up:
                break;
        }
    }
}
