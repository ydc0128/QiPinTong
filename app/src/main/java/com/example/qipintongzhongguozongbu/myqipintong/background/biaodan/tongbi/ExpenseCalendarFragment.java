package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.tongbi;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvExpenseMarketAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.youth.banner.listener.OnBannerClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 消费记录页面
 * Created by L on 2017/2/11.
 */


public class ExpenseCalendarFragment extends BaseFragment implements OnBannerClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.tv_expense_time)
    TextView tvExpenseTime;
    @BindView(R.id.tv_expense_no)
    TextView tvExpenseNo;
    @BindView(R.id.balance_jilu)
    RelativeLayout balanceJilu;
    @BindView(R.id.rv_expense_market)
    RecyclerView rvExpenseMarket;
    @BindView(R.id.rf_xiaofeijjilu)
    BGARefreshLayout rfXiaofeijjilu;

    Unbinder unbinder;
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_expense_galendar, null));
    }

    @Override
    public void OnBannerClick(int position) {

    }

    /**
     * 收支明细适配器
     */
    public void initData() {
        setRvExpenseMarketAdapter();
        setRefreshLayout();
        super.initData();
    }

    //下拉刷新
    private void setRefreshLayout() {

        rfXiaofeijjilu.setDelegate(this);
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);
        rfXiaofeijjilu.setRefreshViewHolder(refreshViewHolder);
    }

    private void setRvExpenseMarketAdapter() {

        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        rvExpenseMarket.setLayoutManager(LayoutManager);
        rvExpenseMarket.setAdapter(new RvExpenseMarketAdapter(mActivity));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("收益记录");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


    @OnClick({R.id.tv_expense_time, R.id.tv_expense_no, R.id.balance_jilu, R.id.rv_expense_market})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_expense_time:
                break;
            case R.id.tv_expense_no:
                break;
            case R.id.balance_jilu:
                break;
            case R.id.rv_expense_market:
                break;
        }
    }

    //下拉刷新
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rfXiaofeijjilu.endRefreshing();
            }
        }, 1500);
    }

    //上滑
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                rfXiaofeijjilu.endLoadingMore();
            }
        }, 150);
        return true;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}