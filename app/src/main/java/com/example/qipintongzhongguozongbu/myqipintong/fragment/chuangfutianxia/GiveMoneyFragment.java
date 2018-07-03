package com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.GiveMoneyCompanyAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.GiveMoneyPersonAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.TabAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.GiveMoneyCompanyDate;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.PullScrollViewListener;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullableScrollView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 投资机构页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/10 下午3:22
 */
public class GiveMoneyFragment extends BaseFragment implements View.OnClickListener, PullScrollViewListener {

    @BindView(R.id.bb_give_money)
    Banner bbGiveMoney;
    Unbinder unbinder;
    @BindView(R.id.CTL_give_money)
    CommonTabLayout CTLGiveMoney;
    @BindView(R.id.ll_select_lingyu)
    LinearLayout llSelectLingyu;
    @BindView(R.id.ll_select_jieduan)
    LinearLayout llSelectJieduan;
    @BindView(R.id.ll_select_diqu)
    LinearLayout llSelectDiqu;
    @BindView(R.id.rl_give_money_select)
    RelativeLayout rlGiveMoneySelect;
    @BindView(R.id.rv_give_person)
    RecyclerView rvGivePerson;
    @BindView(R.id.rv_give_company)
    RecyclerView rvGiveCompany;
    @BindView(R.id.rf_give_money)
    PullToRefreshLayout rfGiveMoney;
    @BindView(R.id.psv_give_money)
    PullableScrollView psvGiveMoney;


    private WindowManager.LayoutParams suspendLayoutParams;
    private WindowManager mWindowManager;
    private View suspendView;
    private int screenWidth;
    private int mSelectTop;

    private String[] mTitles = {"投资机构", "投资人"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int mSelectHeight;
    private LinearLayout mLingYU;
    private LinearLayout mDiQu;
    private LinearLayout mJieDuan;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_give_money, null));
    }


    @Override
    public void initData() {

        setBannerImage(bbGiveMoney);

        setRefreshDate(rfGiveMoney);

        setTitleDate();

        setCompanyAdapter();

        serPersonAdapter();

        getControlDate();

        super.initData();
    }


    /**
     * function   : 投资人
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/13  上午10:44
     */
    private void serPersonAdapter() {

        rlGiveMoneySelect.setVisibility(View.GONE);

        rvGivePerson.setLayoutManager(new LinearLayoutManager(mActivity));

        rvGivePerson.setAdapter(new GiveMoneyPersonAdapter(R.layout.item_give_person_money, getPersonDate()));

        rvGivePerson.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new GiveMoneyPersonFragment());
            }
        });

    }

    /**
     * function   : 投资机构
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/13  上午10:41
     */
    private void setCompanyAdapter() {

        rvGiveCompany.setLayoutManager(new LinearLayoutManager(mActivity));

        rvGiveCompany.setAdapter(new GiveMoneyCompanyAdapter(R.layout.item_give_company_money, getDate()));

        rvGiveCompany.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new GiveMoneyCompanyFragment());
            }
        });

    }


    /**
     * function   : 设置 "投资人", "投资机构" 选项栏
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/10  下午3:53
     */
    private void setTitleDate() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabAdapter(mTitles[i]));
        }

        CTLGiveMoney.setTabData(mTabEntities);

        CTLGiveMoney.setOnTabSelectListener(new OnTabSelectListener() {


            @Override
            public void onTabSelect(int position) {

                switch (position) {
                    case 0:
                        //投资机构
                        mTvTitle.setText("投资机构");

                        rvGiveCompany.setVisibility(View.VISIBLE);
                        rvGivePerson.setVisibility(View.GONE);
                        rlGiveMoneySelect.setVisibility(View.VISIBLE);

                        psvGiveMoney.setScrollViewListener(GiveMoneyFragment.this);

                        break;
                    case 1:
                        //投资人
                        mTvTitle.setText("投资人");

                        rvGiveCompany.setVisibility(View.GONE);
                        rvGivePerson.setVisibility(View.VISIBLE);
                        rlGiveMoneySelect.setVisibility(View.GONE);

                        removeSuspend();

                        psvGiveMoney.setScrollViewListener(null);

                        break;
                }
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

    @Override
    public void onSupportVisible() {
        mTvTitle.setText("投资机构");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


    @Override
    public void onSupportInvisible() {
        removeSuspend();
        super.onSupportInvisible();
    }

    /**
     * function   : 投资机构条目数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/10  下午5:16
     */
    public List getDate() {

        ArrayList<GiveMoneyCompanyDate> list = new ArrayList<>();

        for (int i = 0; i < GlobalConstants.mPhotoDate.length; i++) {

            GiveMoneyCompanyDate giveMoneyDate = new GiveMoneyCompanyDate();
            giveMoneyDate.setLocation("天涯海角" + i);
            giveMoneyDate.setNumber(423 + i);
            giveMoneyDate.setSeenumber("3288");
            giveMoneyDate.setTitle("中国银江投资集团");
            giveMoneyDate.setImage(GlobalConstants.mPhotoDate[i]);

            list.add(giveMoneyDate);
        }

        return list;
    }


    public List getPersonDate() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < GlobalConstants.mIvDate.length; i++) {

            list.add(GlobalConstants.mIvDate[i]);

        }

        return list;
    }

    /**
     * function   : 显示选项卡的悬浮窗
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  下午12:22
     */
    private void showSuspend() {

        if (suspendView == null) {

            suspendView = LayoutInflater.from(mActivity).inflate(R.layout.item_select_give_money, null);
            mLingYU = (LinearLayout) suspendView.findViewById(R.id.ll_select_lingyu);
            mDiQu = (LinearLayout) suspendView.findViewById(R.id.ll_select_diqu);
            mJieDuan = (LinearLayout) suspendView.findViewById(R.id.ll_select_jieduan);

            if (suspendLayoutParams == null) {
                suspendLayoutParams = new WindowManager.LayoutParams();
                suspendLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION; //悬浮窗的类型，一般设为2002，表示在所有应用程序之上，但在状态栏之下
                suspendLayoutParams.format = PixelFormat.RGBA_8888;
                suspendLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;  //悬浮窗的行为，比如说不可聚焦，非模态对话框等等
                suspendLayoutParams.gravity = Gravity.TOP;  //悬浮窗的对齐方式
                suspendLayoutParams.width = screenWidth;
                suspendLayoutParams.height = mSelectHeight;//悬浮窗高度
                suspendLayoutParams.x = 0;  //悬浮窗X的位置
                suspendLayoutParams.y = mTop.getHeight(); ////悬浮窗Y的位置
            }
        }
        mLingYU.setOnClickListener(new LingYuOnClick());
        mDiQu.setOnClickListener(new DiQuOnClick());
        mJieDuan.setOnClickListener(new JieDuanOnClick());
        mWindowManager.addView(suspendView, suspendLayoutParams);

    }

    /**
     * 移除选项卡悬浮框
     */
    public void removeSuspend() {
        if (suspendView != null) {
            mWindowManager.removeView(suspendView);
            suspendView = null;
        }
    }

    /**
     * function   : 获取控件宽高等信息
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  上午10:59
     */
    public void getControlDate() {

        mWindowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = mWindowManager.getDefaultDisplay().getWidth();


        rlGiveMoneySelect.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (rlGiveMoneySelect != null) {
                    mSelectHeight = rlGiveMoneySelect.getMeasuredHeight();
                    mSelectTop = rlGiveMoneySelect.getTop();
                }
            }
        });
    }


    @OnClick({R.id.ll_select_lingyu, R.id.ll_select_jieduan, R.id.ll_select_diqu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_select_lingyu:
                //领域
                ToastUtils.showToast(mActivity, "领域");
                break;
            case R.id.ll_select_jieduan:
                //阶段
                break;
            case R.id.ll_select_diqu:
                //地区
                break;
        }
    }

    @Override
    public void onScrollChanged(PullableScrollView scrollView, int x, int y) {

        if (y >= mSelectTop) {
            if (suspendView == null) {
                showSuspend();
            }
        } else if (y <= mSelectTop) {
            if (suspendView != null) {
                removeSuspend();
                suspendView = null;
            }
        }

    }

    //领域
    private class LingYuOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }

    //地区
    private class DiQuOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }

    //阶段
    private class JieDuanOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }


}
