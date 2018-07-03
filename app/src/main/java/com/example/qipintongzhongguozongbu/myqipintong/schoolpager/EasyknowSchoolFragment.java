package com.example.qipintongzhongguozongbu.myqipintong.schoolpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.AppApplication;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.VpEasyKnowAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.event.SchoolTatleEvent;
import com.example.qipintongzhongguozongbu.myqipintong.labei.ChannelItem;
import com.example.qipintongzhongguozongbu.myqipintong.labei.ChannelManage;
import com.example.qipintongzhongguozongbu.myqipintong.labei.LabelSelectFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Description: 易通学院的主页
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/8 上午9:35
 */
public class EasyknowSchoolFragment extends BaseFragment {
    @BindView(R.id.STL_easy_school)
    SlidingTabLayout STLEasySchool;
    @BindView(R.id.iv_easy_school_add)
    ImageView ivEasySchoolAdd;
    @BindView(R.id.vp_easy_school)
    ViewPager vpEasySchool;
    Unbinder unbinder;

    public static EasyknowSchoolFragment getInstance() {
        return new EasyknowSchoolFragment();
    }

    private String[] itemArray = {"娱乐", " 咨询", "职场", "校园", "时尚", "丽质", "美食", "健康", "能量", "创投"};
    //                              0       1       2      3      4      5       6      7      8      9
    private BaseFragment[] fragmentArray = {PlayPagerFragment.getInstance(), NewsPagerFragment.getInstance(), WorkPagerFragment.getInstance(), SchoolPagerFragment.getInstance(), FashionPagerFragment.getInstance(), BeautifulPagerFragment.getInstance(), DeliciousPagerFragment.getInstance(), HealthPagerFragment.getInstance(), EnergyPagerFragment.getInstance(), CreatePagerFragment.getInstance()};
    //子页面fragment集合                                    0 娱乐                             1 咨询                            2  职场                            3  校园                              4  时尚                                5  丽质                                 6 美食                            7   健康                            8   能量                            9   创投

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_easyknowschool, null));
    }

    @Override
    public void initData() {

        EventBus.getDefault().register(this);

        setTitleDate();

        super.initData();

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


    @Override
    public void onSupportVisible() {

        mTop.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }

    /**
     * function   : 设置标题数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/2  下午7:45
     */
    private void setTitleDate() {

        ArrayList<ChannelItem> userChannel = (ArrayList<ChannelItem>) ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).getUserChannel();
        //获取用户定制的频道信息
        ArrayList<BaseFragment> mFragments = new ArrayList<BaseFragment>();

        String[] mTitles = new String[userChannel.size()];

        if (userChannel != null || userChannel.size() != 0) {

            for (int i = 0; i < userChannel.size(); i++) {

                mFragments.add(fragmentArray[userChannel.get(i).getId()]);

                mTitles[i] = itemArray[userChannel.get(i).getId()];
            }

            vpEasySchool.setAdapter(new VpEasyKnowAdapter(getChildFragmentManager(), mFragments));

            STLEasySchool.setViewPager(vpEasySchool, mTitles);
        }

    }

    @Override
    public void onSupportInvisible() {
        mTop.setVisibility(View.VISIBLE);
        super.onSupportInvisible();
    }

    @OnClick({R.id.iv_easy_school_back, R.id.iv_easy_school_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_easy_school_back:
                pop();
                break;
            case R.id.iv_easy_school_add:
                start(new LabelSelectFragment());
                break;
        }
    }

    /**
     * function   : 当频道管理页面数据改变时会调用此方法
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/4  上午11:08
     */
    @Subscribe
    public void TatleDate(SchoolTatleEvent event) {

        ArrayList<ChannelItem> list = event.getList();

        ArrayList<BaseFragment> mFragments = new ArrayList<BaseFragment>();
        //如果集合不为空才去设置数据
        String[] mTitles = new String[list.size()];

        if (!list.isEmpty() || list.size() != 0) {

            for (int i = 0; i < list.size(); i++) {

                mFragments.add(fragmentArray[list.get(i).getId()]);

                mTitles[i] = itemArray[list.get(i).getId()];
            }

            vpEasySchool.setAdapter(new VpEasyKnowAdapter(getChildFragmentManager(), mFragments));

            STLEasySchool.setViewPager(vpEasySchool, mTitles);
        }

    }

}




