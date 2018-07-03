package com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.AppApplication;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.labei.ChannelAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.labei.ChannelItem;
import com.example.qipintongzhongguozongbu.myqipintong.labei.ItemDragHelperCallback;
import com.example.qipintongzhongguozongbu.myqipintong.labei.LifeManage;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: 生活圈的标签选择页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/5 下午5:29
 */
public class LifeSelectFragment extends BaseFragment {
    @BindView(R.id.rv_select_label)
    RecyclerView rvSelectLabel;
    Unbinder unbinder;

    private ChannelAdapter adapter;
    /**
     * 其它栏目列表
     */
    ArrayList<ChannelItem> otherChannelList = new ArrayList();
    /**
     * 用户栏目列表
     */
    ArrayList<ChannelItem> userChannelList = new ArrayList();

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_select_label, null));
    }

    @Override
    public void initData() {
        initLabel();
        super.initData();
    }

    /**
     * function   : 初始化标题数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/9  下午2:44
     */
    private void initLabel() {

        userChannelList = ((ArrayList<ChannelItem>) LifeManage.getLifeManage(AppApplication.getApp().getLifeHelper()).getUserChannel());
        otherChannelList = ((ArrayList<ChannelItem>) LifeManage.getLifeManage(AppApplication.getApp().getLifeHelper()).getOtherChannel());

        final GridLayoutManager manager = new GridLayoutManager(mActivity, 4);
        rvSelectLabel.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvSelectLabel);

        adapter = new ChannelAdapter(mActivity, helper, userChannelList, otherChannelList);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        rvSelectLabel.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ToastUtils.showToast(mActivity, userChannelList.get(position).getName());
            }
        });


    }

    /**
     * function   : 退出时候保存选择后数据的设置
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/9  下午3:22
     */
    private void saveChannel() {
        LifeManage.getLifeManage(AppApplication.getApp().getLifeHelper()).deleteAllChannel();
        LifeManage.getLifeManage(AppApplication.getApp().getLifeHelper()).saveUserChannel(adapter.getChannnelLst());
        LifeManage.getLifeManage(AppApplication.getApp().getLifeHelper()).saveOtherChannel(adapter.getOthorChannnelLst());
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
    public void onSupportInvisible() {
        saveChannel();
        super.onSupportInvisible();
    }

    @Override
    public void onSupportVisible() {
        mTop.setVisibility(View.VISIBLE);
        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("频道选择");
        super.onSupportVisible();
    }
}
