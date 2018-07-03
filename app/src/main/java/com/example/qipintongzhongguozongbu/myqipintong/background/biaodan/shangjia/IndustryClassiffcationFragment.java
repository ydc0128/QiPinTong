package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RyIndustryClassiffcationBigAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RyIndustryClassiffcationMiddleAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RyIndustryClassiffcationSmallAdapter;
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
 * 行业分类
 */

public class IndustryClassiffcationFragment extends BaseFragment implements OnBannerClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {


    @BindView(R.id.ry_industry_classiffcation_big)
    RecyclerView ryIndustryClassiffcationBig;
    @BindView(R.id.ry_industry_classiffcation_middle)
    RecyclerView ryIndustryClassiffcationMiddle;
    @BindView(R.id.ry_industry_classiffcation_small)
    RecyclerView ryIndustryClassiffcationSmall;
    @BindView(R.id.rf_industry_classifition)
    BGARefreshLayout rfIndustryClassifition;

    Unbinder unbinder;
    public void initData() {
        setRyIndustryClassiffcationBigAdapter();
        setRefreshLayout();
        super.initData();
    }

    //    大类适配器
    private void setRyIndustryClassiffcationBigAdapter() {

        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        ryIndustryClassiffcationBig.setLayoutManager(LayoutManager);
        ryIndustryClassiffcationBig.setAdapter(new RyIndustryClassiffcationBigAdapter(mActivity));
        ryIndustryClassiffcationBig.setVisibility(View.VISIBLE);
        ryIndustryClassiffcationMiddle.setVisibility(View.GONE);
        ryIndustryClassiffcationSmall.setVisibility(View.GONE);
        ryIndustryClassiffcationBig.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), ryIndustryClassiffcationBig, new RecyclerItemClickListener.OnItemClickListener() {

            public void onItemClick(View view, int position) {
                setRyIndustryClassiffcationMiddleAdapter();
                // ...
            }


            public void onItemLongClick(View view, int position) {
                setRyIndustryClassiffcationMiddleAdapter();
            }
        }));

    }


    //    中类适配器
    private void setRyIndustryClassiffcationMiddleAdapter() {

        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        ryIndustryClassiffcationMiddle.setLayoutManager(LayoutManager);
        ryIndustryClassiffcationMiddle.setAdapter(new RyIndustryClassiffcationMiddleAdapter(mActivity));
        ryIndustryClassiffcationMiddle.setVisibility(View.VISIBLE);
        ryIndustryClassiffcationBig.setVisibility(View.VISIBLE);
        ryIndustryClassiffcationSmall.setVisibility(View.GONE);
        ryIndustryClassiffcationMiddle.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), ryIndustryClassiffcationMiddle, new RecyclerItemClickListener.OnItemClickListener() {

            public void onItemClick(View view, int position) {
                setRyIndustryClassiffcationSmallAdapter();
                // ...
            }


            public void onItemLongClick(View view, int position) {
                setRyIndustryClassiffcationSmallAdapter();
            }
        }));

    }

    private void setRyIndustryClassiffcationSmallAdapter() {

        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        ryIndustryClassiffcationSmall.setLayoutManager(LayoutManager);
        ryIndustryClassiffcationSmall.setAdapter(new RyIndustryClassiffcationSmallAdapter(mActivity));
        ryIndustryClassiffcationSmall.setVisibility(View.VISIBLE);
        ryIndustryClassiffcationBig.setVisibility(View.VISIBLE);
        ryIndustryClassiffcationMiddle.setVisibility(View.VISIBLE);
        ryIndustryClassiffcationSmall.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), ryIndustryClassiffcationSmall, new RecyclerItemClickListener.OnItemClickListener() {

            public void onItemClick(View view, int position) {
                getFragmentManager().popBackStack();
                // ...
            }


            public void onItemLongClick(View view, int position) {
                getFragmentManager().popBackStack();
            }
        }));
    }

    public static IndustryClassiffcationFragment getInstance() {
        return new IndustryClassiffcationFragment();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_industry_classification, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("行业分类");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }


    //下拉刷新
    private void setRefreshLayout() {
        rfIndustryClassifition.setDelegate((BGARefreshLayout.BGARefreshLayoutDelegate) this);
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);

        rfIndustryClassifition.setRefreshViewHolder(refreshViewHolder);
    }

    //下拉刷新
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rfIndustryClassifition.endRefreshing();
            }
        }, 1500);
    }

    //上滑
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                rfIndustryClassifition.endLoadingMore();
            }
        }, 150);
        return true;
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @OnClick({R.id.ry_industry_classiffcation_big, R.id.ry_industry_classiffcation_middle, R.id.ry_industry_classiffcation_small})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ry_industry_classiffcation_big:
                break;
            case R.id.ry_industry_classiffcation_middle:
                break;
            case R.id.ry_industry_classiffcation_small:
                break;
        }
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
