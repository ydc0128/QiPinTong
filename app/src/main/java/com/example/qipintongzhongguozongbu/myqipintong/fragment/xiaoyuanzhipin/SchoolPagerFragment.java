package com.example.qipintongzhongguozongbu.myqipintong.fragment.xiaoyuanzhipin;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.SchoolJobTimeAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.SchoolPositionAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.TabAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.VpSchoolAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FacePositionDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.schoolpager.EasyknowSchoolFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 校园直聘的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/16 上午9:56
 */
public class SchoolPagerFragment extends BaseFragment {
    @BindView(R.id.bb_school)
    Banner bbSchool;
    @BindView(R.id.bt_school_take)
    Button btSchoolTake;
    @BindView(R.id.rl_school_time_job)
    RelativeLayout rlSchoolTimeJob;
    @BindView(R.id.rl_school_study)
    RelativeLayout rlSchoolStudy;
    @BindView(R.id.rl_school_work)
    RelativeLayout rlSchoolWork;
    @BindView(R.id.tv_school_recruit)
    TextView tvSchoolRecruit;
    @BindView(R.id.vp_school)
    ViewPager vpSchool;
    @BindView(R.id.fiv_school_indicator)
    FixedIndicatorView fivSchoolIndicator;
    @BindView(R.id.CTL_school)
    CommonTabLayout CTLSchool;
    @BindView(R.id.rf_school_home)
    PullToRefreshLayout rfSchoolHome;
    @BindView(R.id.rv_school_time_job)
    RecyclerView rvSchoolTimeJob;
    @BindView(R.id.rv_school_full_job)
    RecyclerView rvSchoolFullJob;

    private String[] mTitles = {"热门兼职", "最新职位"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_school_home, null));

    }

    @Override
    public void initData() {

        setHandBanner();

        setLabelDate();

        setBannerImage(bbSchool);

        setRefreshDate(rfSchoolHome);

        setTimeJobAdapter();

        setFullJobAdapter();

        super.initData();

    }

    /**
     * function   : 最新职位
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/22  下午5:07
     */
    private void setFullJobAdapter() {

        rvSchoolFullJob.setLayoutManager(new LinearLayoutManager(mActivity));
        rvSchoolFullJob.setAdapter(new SchoolPositionAdapter(R.layout.item_time_position, ImageList.getImageList()));
        rvSchoolFullJob.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new FacePositionDetailsFragment());
            }
        });

    }

    /**
     * function   : 热门兼职
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/22  下午5:07
     */
    private void setTimeJobAdapter() {

        rvSchoolTimeJob.setLayoutManager(new LinearLayoutManager(mActivity));
        rvSchoolTimeJob.setAdapter(new SchoolJobTimeAdapter(R.layout.item_time_job, ImageList.getImageList()));
        rvSchoolTimeJob.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new FacePositionDetailsFragment());
            }
        });
    }


    /**
     * function   : 设置手动轮播图
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/9  下午3:05
     */
    private void setHandBanner() {

        IndicatorViewPager indicatorViewPager = new IndicatorViewPager(fivSchoolIndicator, vpSchool);
        indicatorViewPager.setAdapter(new VpSchoolAdapter(mActivity, ImageList.getImageList()));

    }

    /**
     * function   : 热门兼职 最新职位 点击事件
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/6  下午5:07
     */
    private void setLabelDate() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabAdapter(mTitles[i]));
        }
        CTLSchool.setTabData(mTabEntities);
        CTLSchool.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        //热门兼职
                        rvSchoolTimeJob.setVisibility(View.VISIBLE);
                        rvSchoolFullJob.setVisibility(View.GONE);
                        break;
                    case 1:
                        //最新职位
                        rvSchoolTimeJob.setVisibility(View.GONE);
                        rvSchoolFullJob.setVisibility(View.VISIBLE);
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

        mTvTitle.setText("校园直聘");
        mIvBack.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.bt_school_take, R.id.rl_school_time_job, R.id.rl_school_study, R.id.rl_school_work, R.id.tv_school_recruit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_school_take:
                //立即获取
                break;
            case R.id.rl_school_time_job:
                //学生兼职
                start(new SchoolStudyFragment());
                break;
            case R.id.rl_school_study:
                //我要实习
                start(new SchoolTestFragment());
                break;
            case R.id.rl_school_work:
                //就业指南
                start(new EasyknowSchoolFragment());
                break;
            case R.id.tv_school_recruit:
                //校招专场
                start(new RecruitListFragment());
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
