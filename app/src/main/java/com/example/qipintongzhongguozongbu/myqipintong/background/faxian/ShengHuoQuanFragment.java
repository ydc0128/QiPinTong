package com.example.qipintongzhongguozongbu.myqipintong.background.faxian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNLifeAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.FoodDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by L on 2017/3/23.
 * 生活圈
 */

public class ShengHuoQuanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rv_n_life)
    RecyclerView rvNLife;
    @BindView(R.id.fl_n_shenghuoquan)
    FrameLayout flNShenghuoquan;
    @BindView(R.id.srl_life)
    SwipeRefreshLayout srlLife;
    private Unbinder unbinder;

    public static ShengHuoQuanFragment fragment = null;

    public static ShengHuoQuanFragment getInstance() {

        if (fragment == null) {
            fragment = new ShengHuoQuanFragment();
        }
        return fragment;
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_faxian_shenghuoquan, null));
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        setRvNLifeAdapter();
        super.onLazyInitView(savedInstanceState);
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

    private void setRvNLifeAdapter() {

        srlLife.setOnRefreshListener(this);//加载刷新控件

        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        srlLife.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        srlLife.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srlLife.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        rvNLife.setLayoutManager(new LinearLayoutManager(mActivity));

        RvNLifeAdapter rvNLifeAdapter = new RvNLifeAdapter(R.layout.item_shenghuo, ImageList.getImageList());

        rvNLife.setAdapter(rvNLifeAdapter);

        rvNLifeAdapter.setOnLoadMoreListener(this);

        rvNLife.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((SupportFragment) getParentFragment()).start(new FoodDetailsFragment());
            }
        });
    }

    @OnClick(R.id.tv_life_join)
    public void onClick() {//加入
        ((SupportFragment) getParentFragment()).start(new CommerceFragment());
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
