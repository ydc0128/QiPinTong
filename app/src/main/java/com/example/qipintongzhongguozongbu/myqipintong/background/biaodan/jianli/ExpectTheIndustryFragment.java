package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RyfExpectTheIndustryBigAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RyfExpectTheIndustrySmallAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RecyclerItemClickListener;
import com.youth.banner.listener.OnBannerClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by L on 2017/2/23.
 * 职位分类
 */

public class ExpectTheIndustryFragment extends BaseFragment implements OnBannerClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.bt_expect_the_industry_big)
    Button btExpectTheIndustryBig;
    @BindView(R.id.bt_expect_the_industry_small)
    Button btExpectTheIndustrySmall;
    @BindView(R.id.ry_expect_the_industry_big)
    RecyclerView ryExpectTheIndustryBig;
    @BindView(R.id.ry_expect_the_industry_small)
    RecyclerView ryExpectTheIndustrySmall;
    @BindView(R.id.rf_expect_the_industry)
    BGARefreshLayout rfExpectTheIndustry;
Unbinder unbinder;
    public static ExpectTheIndustryFragment getInstance() {
        return new ExpectTheIndustryFragment();
    }

    public void initData() {
        setRyfExpectTheIndustryBigAdapter();
        setRefreshLayout();
        super.initData();
    }


    //大类适配
    private void setRyfExpectTheIndustryBigAdapter() {
        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        ryExpectTheIndustryBig.setLayoutManager(LayoutManager);
        ryExpectTheIndustryBig.setAdapter(new RyfExpectTheIndustryBigAdapter(mActivity));
        ryExpectTheIndustryBig.setVisibility(View.VISIBLE);
        ryExpectTheIndustrySmall.setVisibility(View.GONE);
        ryExpectTheIndustryBig.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), ryExpectTheIndustryBig, new RecyclerItemClickListener.OnItemClickListener() {

            public void onItemClick(View view, int position) {
                setRyfExpectTheIndustrySmaallAdapter();
                // ...
            }


            public void onItemLongClick(View view, int position) {
                setRyfExpectTheIndustrySmaallAdapter();
            }
        }));

    }

    //小类适配
    private void setRyfExpectTheIndustrySmaallAdapter() {
        final LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        ryExpectTheIndustrySmall.setLayoutManager(LayoutManager);
        ryExpectTheIndustrySmall.setAdapter(new RyfExpectTheIndustrySmallAdapter(mActivity));
        ryExpectTheIndustrySmall.setVisibility(View.VISIBLE);
        ryExpectTheIndustryBig.setVisibility(View.VISIBLE);
        ryExpectTheIndustrySmall.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), ryExpectTheIndustrySmall, new RecyclerItemClickListener.OnItemClickListener() {
            public void onItemClick(View view, int position) {
//                String s=ryExpectTheIndustrySmall.toString();
//                EventBus.getDefault().post(new EventAll(s));
                getFragmentManager().popBackStack();
                // ...
            }


            public void onItemLongClick(View view, int position) {
                getFragmentManager().popBackStack();
            }
        }));
    }


    //下拉刷新
    private void setRefreshLayout() {
        rfExpectTheIndustry.setDelegate((BGARefreshLayout.BGARefreshLayoutDelegate) this);
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);

        rfExpectTheIndustry.setRefreshViewHolder(refreshViewHolder);
    }

    //下拉刷新
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rfExpectTheIndustry.endRefreshing();
            }
        }, 1500);
    }

    //上滑
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                rfExpectTheIndustry.endLoadingMore();
            }
        }, 150);
        return true;
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
       unbinder= ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public View initView() {
        return swipeBackView( View.inflate(mActivity, R.layout.expect_the_industry_fragment, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("职位");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick({R.id.bt_expect_the_industry_big, R.id.bt_expect_the_industry_small, R.id.ry_expect_the_industry_big, R.id.ry_expect_the_industry_small, R.id.rf_expect_the_industry})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_expect_the_industry_big:
                setRyfExpectTheIndustryBigAdapter();
                break;
            case R.id.bt_expect_the_industry_small:
                setRyfExpectTheIndustrySmaallAdapter();
                break;
            case R.id.rf_expect_the_industry:
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
