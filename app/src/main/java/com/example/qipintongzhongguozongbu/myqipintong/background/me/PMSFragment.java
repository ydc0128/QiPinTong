package com.example.qipintongzhongguozongbu.myqipintong.background.me;

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
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/30.
 * PMS
 */

public class PMSFragment extends BaseFragment {




    @BindView(R.id.vp_pms)
    ViewPager vpPms;
    @BindView(R.id.STL_Pms)
    SlidingTabLayout STLPms;
    private String[] mTitles = {"找工作", "找公司", "找合伙人"};
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    private BaseFragment[] mFragmentArray = {ZhaoGongZuoFragment.getInstance(), ZhaoGongSiFragment.getInstance(), ZhaoHeHuoRenFragment.getInstance()};
    Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_pms, null));
    }

    public void initData() {
        super.initData();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setTitleDate();

        super.onLazyInitView(savedInstanceState);
    }

    private void setTitleDate() {

        for (int i = 0; i < mFragmentArray.length; i++) {
            mFragments.add(mFragmentArray[i]);
        }

        vpPms.setAdapter(new VpNearAdapter(getChildFragmentManager(), mFragments));

        STLPms.setViewPager(vpPms, mTitles);
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
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

