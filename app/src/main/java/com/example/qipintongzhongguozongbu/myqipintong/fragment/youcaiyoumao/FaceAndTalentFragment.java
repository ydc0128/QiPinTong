package com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFaceAndTalentAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvGoodMoneyAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.TabAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.youcaiyoumao.CompanmerceResumeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 有才有貌的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/11 上午10:37
 */
public class FaceAndTalentFragment extends BaseFragment {


    @BindView(R.id.bb_face)
    Banner bbFace;
    @BindView(R.id.bt_join)
    Button btJoin;
    @BindView(R.id.CTL_face)
    CommonTabLayout CTLFace;
    @BindView(R.id.rv_face_face)
    RecyclerView rvFaceFace;
    @BindView(R.id.rv_face_money)
    RecyclerView rvFaceMoney;
    Unbinder unbinder;
    @BindView(R.id.rf_face)
    PullToRefreshLayout rfFace;
    private String[] mTitles = {"有才有貌", "高薪职位"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_face, null));
    }

    @Override
    public void initData() {

        setFaceAdapter();

        setMoneyAdapter();

        setRefreshDate(rfFace);

        setBannerImage(bbFace);

        setLabelDate();

        rvFaceFace.setVisibility(View.VISIBLE);
        rvFaceMoney.setVisibility(View.GONE);

        super.initData();


    }

    /**
     * function   : 有才有貌 , 高薪职位点击事件
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/6  下午4:52
     */
    private void setLabelDate() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabAdapter(mTitles[i]));
        }
        CTLFace.setTabData(mTabEntities);
        CTLFace.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        //有才有貌
                        rvFaceFace.setVisibility(View.VISIBLE);
                        rvFaceMoney.setVisibility(View.GONE);
                        break;
                    case 1:
                        //高薪职位
                        rvFaceFace.setVisibility(View.GONE);
                        rvFaceMoney.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("有才有貌");
        mButton.setVisibility(View.GONE);
        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();

    }


    @OnClick({R.id.bt_join})
    public void onClick(View view) {

        start(new CompanmerceResumeFragment());

    }

    /**
     * function   : 设置高薪职位的条目适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/11  下午5:55
     */
    private void setMoneyAdapter() {

        rvFaceMoney.setLayoutManager(new LinearLayoutManager(mActivity));
        rvFaceMoney.setAdapter(new RvGoodMoneyAdapter(R.layout.item_face_good_money, ImageList.getImageList()));

        rvFaceMoney.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new FacePositionDetailsFragment());
            }
        });
    }

    /**
     * function   : 有才有貌条目的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/11  下午12:28
     */
    private void setFaceAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFaceFace.setLayoutManager(gridLayoutManager);
        rvFaceFace.addItemDecoration(new RvItemDecoration(10));
        rvFaceFace.setAdapter(new RvFaceAndTalentAdapter(R.layout.item_face_and_talent, ImageList.getImageList()));

        rvFaceFace.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new FaceAndTalentDetailsFragment());
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


}
