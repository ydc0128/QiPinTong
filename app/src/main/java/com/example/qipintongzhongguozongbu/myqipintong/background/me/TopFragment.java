package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by L on 2017/3/14.
 * 排行榜
 */
public class TopFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.the_number_of_users)
    TextView theNumberOfUsers;
    @BindView(R.id.tv_shanghu)
    TextView tvShanghu;
    @BindView(R.id.enterprise)
    TextView enterprise;
    @BindView(R.id.personal)
    TextView personal;
    @BindView(R.id.ll_t_newly_increased)
    LinearLayout llTNewlyIncreased;
    @BindView(R.id.ll_t_user_details)
    LinearLayout llTUserDetails;
    @BindView(R.id.ll_t_financial_statistics)
    LinearLayout llTFinancialStatistics;
    @BindView(R.id.ll_t_team_management)
    LinearLayout llTTeamManagement;
    @BindView(R.id.ll_t_daily_charts)
    LinearLayout llTDailyCharts;
    @BindView(R.id.rf_my_earnings)
    BGARefreshLayout rfMyEarnings;

    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_top, null));
    }

//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }


    public void onSupportVisible() {
        mTvTitle.setText("超级管理");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
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

    @OnClick({R.id.ll_t_newly_increased, R.id.ll_t_user_details, R.id.ll_t_financial_statistics, R.id.ll_t_team_management, R.id.ll_t_daily_charts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_t_newly_increased:
                start(new NewRankingsTodayFragment());
                break;
            case R.id.ll_t_user_details:
                start(new UserDetailsFragment());
                break;
            case R.id.ll_t_financial_statistics:
                start(new FinancialStatisticsFragment());
                break;
            case R.id.ll_t_team_management:
//                start(new TeamManagementFragment());
                start(new TeamItemFragment());
                break;
            case R.id.ll_t_daily_charts:
                start(new TodayRankingTopfragment());
                break;
//            case R.id.ll_t_city:
//                start(new CityFragment());
////                切换城市
//                break;
        }
    }
}
