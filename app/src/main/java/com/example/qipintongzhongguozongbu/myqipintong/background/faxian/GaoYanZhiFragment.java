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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNLevelOfAppearanceAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.youcaiyoumao.CompanmerceResumeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FaceAndTalentDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by L on 2017/3/23.
 * 高颜值
 */

public class GaoYanZhiFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_n_level_of_appearance)
    RecyclerView rvNLevelOfAppearance;
    @BindView(R.id.fl_n_gaoyanzh)
    FrameLayout flNGaoyanzh;
    @BindView(R.id.srl_good_face)
    SwipeRefreshLayout srlGoodFace;
    private Unbinder unbinder;

    public static GaoYanZhiFragment fragment = null;

    public static GaoYanZhiFragment getInstance() {

        if (fragment == null) {
            fragment = new GaoYanZhiFragment();
        }
        return fragment;
    }


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_faxian_gaoyanzhi, null));
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        setRvNLevelOfAppearanceAdapter();
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


    private void setRvNLevelOfAppearanceAdapter() {


        srlGoodFace.setOnRefreshListener(this);//加载刷新控件

        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        srlGoodFace.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        srlGoodFace.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srlGoodFace.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        rvNLevelOfAppearance.setLayoutManager(new LinearLayoutManager(mActivity));

        RvNLevelOfAppearanceAdapter rvNLevelOfAppearanceAdapter = new RvNLevelOfAppearanceAdapter(R.layout.item_gaoyanzhi, ImageList.getImageList());

        rvNLevelOfAppearance.setAdapter(rvNLevelOfAppearanceAdapter);


        rvNLevelOfAppearanceAdapter.setOnLoadMoreListener(this);

        rvNLevelOfAppearance.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((SupportFragment) getParentFragment()).start(new FaceAndTalentDetailsFragment());
            }
        });
    }

    @OnClick(R.id.tv_good_face_join)
    public void onClick() {
        ((SupportFragment) getParentFragment()).start(new CompanmerceResumeFragment());
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
