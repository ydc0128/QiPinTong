package com.example.qipintongzhongguozongbu.myqipintong.background.faxian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.VpNearAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/18.  附近页面
 */

public class NearFragment extends BaseFragment {


    @BindView(R.id.vp_my_near)
    ViewPager vpMyNear;
    @BindView(R.id.STL_near)
    SlidingTabLayout STLNear;
    private String[] mTitles = {"好工作", "高颜值", "名企", "合伙人", "生活圈"};
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private BaseFragment[] mFragmentArray = {HaoGongZuoFragment.getInstance(), GaoYanZhiFragment.getInstance(), MingQIFragment.getInstance(), HeHuoRenFragment.getInstance(), ShengHuoQuanFragment.getInstance()};
    Unbinder unbinder;


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_my_near, null));


    }

    public void initData() {
        super.initData();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setTitleDate();

        super.onLazyInitView(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setTitleDate() {

        for (int i = 0; i < mFragmentArray.length; i++) {
            mFragments.add(mFragmentArray[i]);
        }

        vpMyNear.setAdapter(new VpNearAdapter(getChildFragmentManager(), mFragments));

        STLNear.setViewPager(vpMyNear, mTitles);
    }

    public void onSupportVisible() {

        mTop.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;

    }


    @OnClick(R.id.iv_back_near)
    public void onClick() {
        pop();
    }
}
