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
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNHezuoAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli.MyDataFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia.CreateWorldFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by L on 2017/3/23.
 * 合伙人
 */

public class HeHuoRenFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.rv_n_hezuo)
    RecyclerView rvNHezuo;
    @BindView(R.id.fl_n_hehuoren)
    FrameLayout flNHehuoren;
    @BindView(R.id.srl_hehuoren)
    SwipeRefreshLayout srlHehuoren;
    @BindView(R.id.tv_hehuoren_join)
    TextView tvHehuorenJoin;
    private Unbinder unbinder;

    public static HeHuoRenFragment fragment = null;


    public static HeHuoRenFragment getInstance() {

        if (fragment == null) {
            fragment = new HeHuoRenFragment();
        }
        return fragment;

    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_faxian_hehuoren, null));

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        initRefresh();

        setRvNHezuoAdapter();

        super.onLazyInitView(savedInstanceState);
    }

    private void initRefresh() {

        srlHehuoren.setOnRefreshListener(this);//加载刷新控件

        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        srlHehuoren.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        srlHehuoren.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srlHehuoren.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
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

    private void setRvNHezuoAdapter() {

        srlHehuoren.setOnRefreshListener(this);//加载刷新控件

        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        srlHehuoren.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        srlHehuoren.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srlHehuoren.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        rvNHezuo.setLayoutManager(new LinearLayoutManager(mActivity));
        rvNHezuo.setAdapter(new RvNHezuoAdapter(R.layout.item_hehuoren, ImageList.getImageList()));

        rvNHezuo.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((SupportFragment) getParentFragment()).start(new CreateWorldFragment());
            }
        });
    }

    @OnClick(R.id.tv_hehuoren_join)
    public void onClick() {
        ((SupportFragment) getParentFragment()).start(new MyDataFragment());
    }

    @Override
    public void onRefresh() {

    }
}
