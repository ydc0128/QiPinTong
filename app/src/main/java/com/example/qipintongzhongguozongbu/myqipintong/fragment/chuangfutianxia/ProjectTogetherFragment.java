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
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvTogetherProjectAdapter;
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
 * Description: 创投天下的合伙人页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/14 上午10:40
 */
public class ProjectTogetherFragment extends BaseFragment implements PullScrollViewListener {
    @BindView(R.id.bb_together_project)
    Banner bbTogetherProject;

    @BindView(R.id.ll_together_project_location)
    LinearLayout llTogetherProjectLocation;
    @BindView(R.id.ll_together_project_territory)
    LinearLayout llTogetherProjectTerritory;
    @BindView(R.id.ll_together_project_mode)
    LinearLayout llTogetherProjectMode;
    @BindView(R.id.ll_together_project_limit)
    LinearLayout llTogetherProjectLimit;
    @BindView(R.id.ll_together_project_select)
    LinearLayout llTogetherProjectSelect;
    @BindView(R.id.rv_together_project)
    RecyclerView rvTogetherProject;
    @BindView(R.id.OSV_together_project)
    PullableScrollView OSVTogetherProject;
    @BindView(R.id.rf_together)
    PullToRefreshLayout rfTogether;

    private WindowManager.LayoutParams suspendLayoutParams;
    private WindowManager mWindowManager;
    private RelativeLayout mHomeTop;
    private int screenWidth;
    private View suspendView;
    private int selectTop;
    private int selectHeight;
    private LinearLayout mLocation;
    private LinearLayout mTerritory;
    private LinearLayout mMode;
    private LinearLayout mLimit;
    private Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_project_together, null));

    }

    @Override
    public void initData() {

        getControlDate();

        setRefreshDate(rfTogether);

        setBannerImage(bbTogetherProject);

        setRecyclerViewAdapter();

        OSVTogetherProject.setScrollViewListener(this);

        super.initData();

    }

    private void setRecyclerViewAdapter() {

        rvTogetherProject.setLayoutManager(new LinearLayoutManager(mActivity));
        rvTogetherProject.setAdapter(new RvTogetherProjectAdapter(R.layout.item_together_project, ImageList.getImageList()));
        rvTogetherProject.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new TogetherProjectDetailsFragment());
            }
        });

    }

    @Override
    public void onSupportVisible() {
        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("合伙人");
        super.onSupportVisible();

    }

    @Override
    public void onSupportInvisible() {
        removeSuspend();
        super.onSupportInvisible();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.ll_together_project_location, R.id.ll_together_project_territory, R.id.ll_together_project_mode, R.id.ll_together_project_limit})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ll_together_project_location:
                //区域
                break;
            case R.id.ll_together_project_territory:
                //领域
                break;
            case R.id.ll_together_project_mode:
                //方式
                break;
            case R.id.ll_together_project_limit:
                //额度
                break;
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


        selectHeight = llTogetherProjectSelect.getHeight();
        selectTop = llTogetherProjectSelect.getTop();

    }

    /**
     * function   : 显示选项卡的悬浮窗
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  下午12:22
     */
    private void showSuspend() {

        if (suspendView == null) {

            suspendView = LayoutInflater.from(mActivity).inflate(R.layout.item_project_together_select, null);

            mLocation = (LinearLayout) suspendView.findViewById(R.id.ll_select_together_project_location);
            mTerritory = (LinearLayout) suspendView.findViewById(R.id.ll_select_together_project_territory);
            mMode = (LinearLayout) suspendView.findViewById(R.id.ll_select_together_project_mode);
            mLimit = (LinearLayout) suspendView.findViewById(R.id.ll_select_together_project_limit);


            if (suspendLayoutParams == null) {
                suspendLayoutParams = new WindowManager.LayoutParams();
                suspendLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION; //悬浮窗的类型，一般设为2002，表示在所有应用程序之上，但在状态栏之下
                suspendLayoutParams.format = PixelFormat.RGBA_8888;
                suspendLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;  //悬浮窗的行为，比如说不可聚焦，非模态对话框等等
                suspendLayoutParams.gravity = Gravity.TOP;  //悬浮窗的对齐方式
                suspendLayoutParams.width = screenWidth;
                suspendLayoutParams.height = selectHeight;
                suspendLayoutParams.x = 0;  //悬浮窗X的位置
                suspendLayoutParams.y = mTop.getHeight();  ////悬浮窗Y的位置
            }
        }


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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onScrollChanged(PullableScrollView scrollView, int x, int y) {
        if (y >= selectTop) {
            if (suspendView == null) {
                showSuspend();
            }
        } else if (y <= selectTop) {
            if (suspendView != null) {
                removeSuspend();
                suspendView = null;
            }
        }
    }
}
