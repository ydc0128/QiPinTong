package com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFamousAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia.EnterpriseDataFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 名气在线页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/20 上午11:05
 */
public class FamousCompanyFragment extends BaseFragment {
    @BindView(R.id.bb_famous)
    Banner bbFamous;
    @BindView(R.id.bt_famous_join)
    Button btFamousJoin;
    @BindView(R.id.rv_famous)
    RecyclerView rvFamous;
    @BindView(R.id.rf_famous)
    PullToRefreshLayout rfFamous;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_famous_company, null));
    }


    @Override
    public void initData() {

        setBannerImage(bbFamous);

        setRefreshDate(rfFamous);

        setRvAdapter();

        super.initData();
    }

    private void setRvAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rvFamous.setLayoutManager(gridLayoutManager);
        rvFamous.addItemDecoration(new RvItemDecoration(10));
        //rvFamous.setAdapter(new RvFamousAdapter(R.layout.item_famous_company, ImageList.getImageList()));
        rvFamous.setAdapter(new RvFamousAdapter(R.layout.item_youming_company, ImageList.getImageList()));

        rvFamous.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                start(new CompanyDetailsFragment());
            }
        });

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

    @OnClick(R.id.bt_famous_join)
    public void onClick() {
        //我要加入
        start(new EnterpriseDataFragment());
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("名企在线");
        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();

    }
}
