package com.example.qipintongzhongguozongbu.myqipintong.fragment.darenbang;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.VpShareAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 共享记录页面
 * Created by Administrator on 2017/6/3.
 */

public class ShareHistoryFragment extends BaseFragment {

    @BindView(R.id.ctl_share)
    CommonTabLayout ctlShare;
    @BindView(R.id.vp_share)
    ViewPager vpShare;
    Unbinder unbinder;

    private String[] mTitles = {"共享邀请", "已共享"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_share_history, null));
    }


    @Override
    public void initData() {

        initAdapter();

        initLible();

        super.initData();
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("共享记录");
        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }

    //初始化vp
    private void initAdapter() {

        ArrayList<BaseFragment> mFragments = new ArrayList<BaseFragment>();

        mFragments.add(ShareRequesPager.getInstance());

        mFragments.add(ShareConsentPager.getInstance());

        vpShare.setAdapter(new VpShareAdapter(getChildFragmentManager(), mFragments));

        vpShare.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                ctlShare.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    //初始化标题栏
    private void initLible() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }

        ctlShare.setTabData(mTabEntities);

        ctlShare.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpShare.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

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
