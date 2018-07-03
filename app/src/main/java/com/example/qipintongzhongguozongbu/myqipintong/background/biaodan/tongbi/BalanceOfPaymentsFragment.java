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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvBalanceMarketAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.youth.banner.listener.OnBannerClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * 支出页面
 * Created by L on 2017/2/11.
 */

public class BalanceOfPaymentsFragment extends BaseFragment implements OnBannerClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.tv_balance_time)
    TextView tvBalanceTime;
    @BindView(R.id.tv_balance_no)
    TextView tvBalanceNo;
    @BindView(R.id.balance_jilu)
    RelativeLayout balanceJilu;
    @BindView(R.id.rv_balance_market)
    RecyclerView rvBalanceMarket;
    @BindView(R.id.rf_zhichujilu)
    BGARefreshLayout rfZhichujilu;


    Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_balance_of_payments, null));
    }

    @Override
    public void OnBannerClick(int position) {

    }

    /**
     * 收支明细适配器
     */
    public void initData() {
        setRvBalanceMarketAdapter();
        setRefreshLayout();
        super.initData();
    }

    private void setRvBalanceMarketAdapter() {

        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        rvBalanceMarket.setLayoutManager(LayoutManager);
        rvBalanceMarket.setAdapter(new RvBalanceMarketAdapter(mActivity));
    }

    public void onSupportVisible() {

        mTvTitle.setText("消费记录");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }


    //下拉刷新
    private void setRefreshLayout() {

        rfZhichujilu.setDelegate(this);
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);
        rfZhichujilu.setRefreshViewHolder(refreshViewHolder);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rfZhichujilu.endRefreshing();
            }
        }, 1500);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                rfZhichujilu.endLoadingMore();
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

