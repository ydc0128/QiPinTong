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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNWorkAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli.DesiredOccupationAddFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FacePositionDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by L on 2017/3/23.
 * 好工作
 */

public class HaoGongZuoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    @BindView(R.id.rv_n_supin)
    RecyclerView rvNSupin;
    @BindView(R.id.srl_good_joob)
    SwipeRefreshLayout srlGoodJoob;
    @BindView(R.id.tv_good_work_join)
    TextView tvGoodWorkJoin;
    @BindView(R.id.fl_n_haogongzuo)
    FrameLayout flNHaogongzuo;
    private Unbinder unbinder;
    private static final int PAGE_SIZE = 20;//每页数据数量

    public static HaoGongZuoFragment fragment = null;

    public static HaoGongZuoFragment getInstance() {

        if (fragment == null) {
            fragment = new HaoGongZuoFragment();
        }
        return fragment;
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_faxian_haogongzuo, null));

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        initRefresh();

        setRvSuPinAdapter();


        super.onLazyInitView(savedInstanceState);
    }


    private void initRefresh() {

        srlGoodJoob.setOnRefreshListener(this);//加载刷新控件

        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        srlGoodJoob.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        srlGoodJoob.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srlGoodJoob.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void setRvSuPinAdapter() {

        rvNSupin.setLayoutManager(new LinearLayoutManager(mActivity));
        // rvNWork.setAdapter(new RvNWorkAdapter(R.layout.item_good_work, ImageList.getImageList()));
        RvNWorkAdapter rvNWorkAdapter = new RvNWorkAdapter(R.layout.item_full_time_job, ImageList.getImageList());

        rvNSupin.setAdapter(rvNWorkAdapter);

        rvNWorkAdapter.setOnLoadMoreListener(this);

        rvNSupin.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                ((SupportFragment) getParentFragment()).start(new FacePositionDetailsFragment());

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


    //下来刷新
    @Override
    public void onRefresh() {

    }

    //加载更多
    @Override
    public void onLoadMoreRequested() {

        srlGoodJoob.setEnabled(false);

    }

    @OnClick(R.id.tv_good_work_join)
    public void onClick() {
        ((SupportFragment) getParentFragment()).start(new DesiredOccupationAddFragment());
    }
}