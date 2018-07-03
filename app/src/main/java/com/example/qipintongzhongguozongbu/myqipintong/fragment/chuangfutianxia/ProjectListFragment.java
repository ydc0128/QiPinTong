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
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.ProjectListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.PullScrollViewListener;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullableScrollView;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description: 创孵天下好项目的列表页
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/12 下午6:59
 */
public class ProjectListFragment extends BaseFragment implements PullScrollViewListener {

    @BindView(R.id.bb_list_project)
    Banner bbListProject;
    @BindView(R.id.ll_list_project_location)
    LinearLayout llListProjectLocation;
    @BindView(R.id.ll_list_project_territory)
    LinearLayout llListProjectTerritory;
    @BindView(R.id.ll_list_project_round)
    LinearLayout llListProjectRound;
    @BindView(R.id.ll_list_project_limit)
    LinearLayout llListProjectLimit;
    @BindView(R.id.rv_list_project)
    RecyclerView rvListProject;
    @BindView(R.id.ll_list_project_select)
    LinearLayout llListProjectSelect;
    @BindView(R.id.OSV_project)
    PullableScrollView OSVProject;
    @BindView(R.id.rf_project)
    PullToRefreshLayout rfProject;

    private WindowManager.LayoutParams suspendLayoutParams;
    private WindowManager mWindowManager;
    private int llListProjectHeight;
    private int llListProjectTop;
    private int screenWidth;
    private View suspendView;
    private LinearLayout mLlselectLimit;
    private LinearLayout mLlselectLocation;
    private LinearLayout mLlselectRound;
    private LinearLayout mLlselectTerrutory;
    private Unbinder unbinder;


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_project_list, null));

    }

    @Override
    public void initData() {

        setRefreshDate(rfProject);

        setBannerImage(bbListProject);

        setProjectListAdapter();

        getControlDate();

        OSVProject.setScrollViewListener(this);
        //scrollview的滑动监听
        super.initData();

    }

    /**
     * function   : 项目列表适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/12  下午7:44
     */
    private void setProjectListAdapter() {

        rvListProject.setLayoutManager(new LinearLayoutManager(mActivity));
        rvListProject.setAdapter(new ProjectListAdapter(R.layout.item_good_project, ImageList.getImageList()));
        rvListProject.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new ProjectDetailsFragment());
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

    @OnClick({R.id.ll_list_project_location, R.id.ll_list_project_territory, R.id.ll_list_project_round, R.id.ll_list_project_limit})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ll_list_project_location:
                //区域
                break;
            case R.id.ll_list_project_territory:
                //领域
                break;
            case R.id.ll_list_project_round:
                //轮次
                break;
            case R.id.ll_list_project_limit:
                //额度
                break;


        }
    }

    /**
     * function   : 显示选项卡的悬浮窗
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  下午12:22
     */
    private void showSuspend() {

        if (suspendView == null) {

            suspendView = LayoutInflater.from(mActivity).inflate(R.layout.item_project_select, null);

            mLlselectLimit = (LinearLayout) suspendView.findViewById(R.id.ll_select_project_limit);
            mLlselectLocation = (LinearLayout) suspendView.findViewById(R.id.ll_select_project_location);
            mLlselectRound = (LinearLayout) suspendView.findViewById(R.id.ll_select_project_round);
            mLlselectTerrutory = (LinearLayout) suspendView.findViewById(R.id.ll_select_project_territory);

            if (suspendLayoutParams == null) {
                suspendLayoutParams = new WindowManager.LayoutParams();
                suspendLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION; //悬浮窗的类型，一般设为2002，表示在所有应用程序之上，但在状态栏之下
                suspendLayoutParams.format = PixelFormat.RGBA_8888;
                suspendLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;  //悬浮窗的行为，比如说不可聚焦，非模态对话框等等
                suspendLayoutParams.gravity = Gravity.TOP;  //悬浮窗的对齐方式
                suspendLayoutParams.width = screenWidth;
                suspendLayoutParams.height = llListProjectHeight;
                suspendLayoutParams.x = 0;  //悬浮窗X的位置
                suspendLayoutParams.y = mTop.getHeight();  ////悬浮窗Y的位置
            }
        }
        mLlselectLimit.setOnClickListener(new mLlselectLimit());
        mLlselectLocation.setOnClickListener(new mLlselectLocation());
        mLlselectRound.setOnClickListener(new mLlselectRound());
        mLlselectTerrutory.setOnClickListener(new mLlselectTerrutory());
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

    @Override
    public void onSupportInvisible() {

        removeSuspend();

        super.onSupportInvisible();

    }

    @Override
    public void onSupportVisible() {

        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("项目列表");

        super.onSupportVisible();

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
        llListProjectHeight = llListProjectSelect.getHeight();
        llListProjectTop = llListProjectSelect.getTop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onScrollChanged(PullableScrollView scrollView, int x, int y) {
        if (y >= llListProjectTop) {
            if (suspendView == null) {
                showSuspend();
            }
        } else if (y <= llListProjectTop) {
            if (suspendView != null) {
                removeSuspend();
                suspendView = null;
            }
        }
    }

    /**
     * function   : 额度的点击
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  下午12:19
     */
    class mLlselectLimit implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }

    /**
     * function   : 地址点击
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  下午12:20
     */
    class mLlselectLocation implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }

    /**
     * function   : 轮次点击
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  下午12:21
     */
    class mLlselectRound implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }

    /**
     * function   : 领域的点击
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  下午12:21
     */
    class mLlselectTerrutory implements View.OnClickListener {

        @Override
        public void onClick(View view) {

        }
    }
}
