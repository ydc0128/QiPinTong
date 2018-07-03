package com.example.qipintongzhongguozongbu.myqipintong.fragment.darenbang;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCityListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvExpertAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFansListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.youth.banner.Banner;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 达人帮列表页面
 * Created by Administrator on 2017/6/2.
 */

public class ExpertListFragment extends BaseFragment implements View.OnClickListener {

    int mSelectY = 0;
    @BindView(R.id.rv_expert)
    RecyclerView rvExpert;
    @BindView(R.id.dropDownMenu)
    DropDownMenu selectExpert;
    @BindView(R.id.tv_select)
    TextView tvSelectExpert;

    private String headers[] = {"区域 ： 西安市高新区", "粉丝量 ： 由多到少"};
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String fans[] = {"1", "2", "200", "33", "000", "99", "100"};
    private Unbinder unbinder;
    private HeaderShareLayout headerlayout;
    private RvExpertAdapter adapter;
    private LinearLayoutManager manager;


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_expert_list, null));
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("达人帮");
        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }

    @Override
    public void initData() {

        initDropDown();

        setAdapter();

        super.initData();
    }

    //初始化选项卡
    private void initDropDown() {

        List<View> popupViews = new ArrayList<>();

        final RecyclerView cityView = new RecyclerView(mActivity);
        cityView.setLayoutManager(new LinearLayoutManager(mActivity));
        cityView.setAdapter(new RvCityListAdapter(R.layout.item_text, Arrays.asList(citys)));
        cityView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                selectExpert.setTabText(position == 0 ? headers[0] : citys[position]);
                selectExpert.closeMenu();
            }
        });


        final RecyclerView fansView = new RecyclerView(mActivity);
        fansView.setLayoutManager(new LinearLayoutManager(mActivity));
        fansView.setAdapter(new RvFansListAdapter(R.layout.item_text, Arrays.asList(fans)));
        fansView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapteddmExpertr, View view, int position) {
                selectExpert.setTabText(position == 0 ? headers[1] : fans[position]);
                selectExpert.closeMenu();
            }
        });


        popupViews.add(cityView);
        popupViews.add(fansView);

        ViewGroup parent = (ViewGroup) tvSelectExpert.getParent();
        parent.removeView(tvSelectExpert);

        selectExpert.setDropDownMenu(Arrays.asList(headers), popupViews, tvSelectExpert);
    }

    private void setAdapter() {

        manager = new LinearLayoutManager(mActivity);

        rvExpert.setLayoutManager(manager);

        adapter = new RvExpertAdapter(R.layout.item_expert_list, ImageList.getImageList());

        headerlayout = new HeaderShareLayout(mActivity);//头布局

        setBannerImage((Banner) headerlayout.findViewById(R.id.bb_expert));

        TextView mTvjoin = (TextView) headerlayout.findViewById(R.id.bt_expert_join);

        mTvjoin.setOnClickListener(this);

        adapter.addHeaderView(headerlayout);

        rvExpert.setAdapter(adapter);

        rvExpert.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new ShareListFragment());

            }
        });


        rvExpert.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int[] location = new int[2];
                selectExpert.getLocationOnScreen(location);
                int y = location[1];
                mSelectY = y;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (headerlayout == null) return;
                int getTop = headerlayout.getDistanceY();
                if (getTop <= mSelectY) {
                    selectExpert.setVisibility(View.VISIBLE);
                } else {
                    selectExpert.setY(0);
                    selectExpert.setVisibility(View.GONE);
                }
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
    public void onClick(View v) {//加入按钮
        MoveToPosition(manager, rvExpert, 1);

    }


    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }


}
