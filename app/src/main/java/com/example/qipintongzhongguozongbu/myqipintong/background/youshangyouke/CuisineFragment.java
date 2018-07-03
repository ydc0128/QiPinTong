package com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RyFragmentCuisineBigAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RyFragmentCuisineSmallAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RecyclerItemClickListener;
import com.youth.banner.listener.OnBannerClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by L on 2017/3/2.
 * 菜系
 */

public class CuisineFragment extends BaseFragment implements OnBannerClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.ry_fragment_cuisine_big)
    RecyclerView ryFragmentCuisineBig;
    @BindView(R.id.ry_fragment_cuisine_small)
    RecyclerView ryFragmentCuisineSmall;
    @BindView(R.id.rf_fragment_cuisine)
    BGARefreshLayout rfFragmentCuisine;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_cuisine, null));
    }

    public void initData() {
        setRyFragmentCuisineBigAdapter();
        setRefreshLayout();
        super.initData();
    }

    private void setRyFragmentCuisineBigAdapter() {
        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        ryFragmentCuisineBig.setLayoutManager(LayoutManager);
        ryFragmentCuisineBig.setAdapter(new RyFragmentCuisineBigAdapter(mActivity));
        ryFragmentCuisineBig.setVisibility(View.VISIBLE);
        ryFragmentCuisineSmall.setVisibility(View.GONE);
        ryFragmentCuisineBig.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), ryFragmentCuisineBig, new RecyclerItemClickListener.OnItemClickListener() {

            public void onItemClick(View view, int position) {
                setRyFragmentCuisineSmallAdapter();
                // ...
            }


            public void onItemLongClick(View view, int position) {
                setRyFragmentCuisineSmallAdapter();
            }
        }));

    }

    public void onSupportVisible() {
        mTvTitle.setText("菜系");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    private void setRyFragmentCuisineSmallAdapter() {
        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        ryFragmentCuisineSmall.setLayoutManager(LayoutManager);
        ryFragmentCuisineSmall.setAdapter(new RyFragmentCuisineSmallAdapter(mActivity));
        ryFragmentCuisineSmall.setVisibility(View.VISIBLE);
        ryFragmentCuisineBig.setVisibility(View.VISIBLE);
        ryFragmentCuisineSmall.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), ryFragmentCuisineSmall, new RecyclerItemClickListener.OnItemClickListener() {

            public void onItemClick(View view, int position) {
                getFragmentManager().popBackStack();
                // ...
            }


            public void onItemLongClick(View view, int position) {
                getFragmentManager().popBackStack();
            }
        }));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void OnBannerClick(int position) {

    }

    //下拉刷新
    private void setRefreshLayout() {
        rfFragmentCuisine.setDelegate((BGARefreshLayout.BGARefreshLayoutDelegate) this);
        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);

        rfFragmentCuisine.setRefreshViewHolder(refreshViewHolder);
    }

    //下拉刷新
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rfFragmentCuisine.endRefreshing();
            }
        }, 1500);
    }

    //上滑
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                rfFragmentCuisine.endLoadingMore();
            }
        }, 150);
        return true;
    }
}
