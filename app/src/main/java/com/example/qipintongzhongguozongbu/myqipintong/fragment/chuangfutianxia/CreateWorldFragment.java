package com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCreateLocalAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.CityRoadFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvTenDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description: 创孵天下首页
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/12 上午11:29
 */
public class CreateWorldFragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.bb_create)
    Banner bbCreate;
    @BindView(R.id.rl_create_project)
    RelativeLayout rlCreateProject;
    @BindView(R.id.rl_create_partnership)
    RelativeLayout rlCreatePartnership;
    @BindView(R.id.rl_create_give_money)
    RelativeLayout rlCreateGiveMoney;
    @BindView(R.id.tv_create_project)
    TextView tvCreateProject;
    @BindView(R.id.rv_create_horizontal)
    RecyclerView rvCreateHorizontal;
    @BindView(R.id.tv_create_local)
    TextView tvCreateLocal;
    @BindView(R.id.rv_create_vertical)
    RecyclerView rvCreateVertical;
    @BindView(R.id.rf_create)
    PullToRefreshLayout rfCreate;
    private Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_createworld, null));
    }

    @Override
    public void initData() {


        setBannerImage(bbCreate);

        setRefreshDate(rfCreate);

        setRecyclerViewHorizontal();

        setRecyclerViewVertical();

        super.initData();

    }

    /**
     * function   : 项目推荐的条目
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/12  下午2:23
     */
    private void setRecyclerViewHorizontal() {

        LinearLayoutManager linearManager = new LinearLayoutManager(mActivity);
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCreateHorizontal.setLayoutManager(linearManager);
        rvCreateHorizontal.addItemDecoration(new RvTenDecoration());
        // rvCreateHorizontal.setAdapter(new RvCreateProjectAdapter(getDate()));

        rvCreateHorizontal.addOnItemTouchListener(new  com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new ProjectDetailsFragment());
            }
        });

    }

//    public List getDate() {
//        ArrayList<ItemTypeDate> list = new ArrayList<ItemTypeDate>();
//        for (int i = 0; i < ItemTypeDate.getList().size(); i++) {
//            list.add(new ItemTypeDate(ItemTypeDate.IMAGE_TYPE, faceList));
//        }
//        list.add(new ItemTypeDate(ItemTypeDate.MORE_TYPE, faceList));
//        return list;
//    }

    /**
     * function   : 同城路演的条目
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/12  下午2:26
     */
    private void setRecyclerViewVertical() {

        rvCreateVertical.setLayoutManager(new LinearLayoutManager(mActivity));
        rvCreateVertical.setAdapter(new RvCreateLocalAdapter(R.layout.item_create_local, ImageList.getImageList()));

        rvCreateVertical.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new CityRoadFragment());
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


    @OnClick({R.id.rl_create_project, R.id.rl_create_partnership, R.id.rl_create_give_money, R.id.tv_create_project, R.id.tv_create_local, R.id.tv_create_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_create_project:
                //好项目
                start(new ProjectListFragment());
                break;
            case R.id.rl_create_partnership:
                //合伙人
                start(new ProjectTogetherFragment());
                break;
            case R.id.rl_create_give_money:
                //投资机构
                start(new GiveMoneyFragment());
                break;
            case R.id.tv_create_project:
                //项目推荐
                break;
            case R.id.tv_create_local:
                //同城路演
                break;
            case R.id.rv_create_vertical:
                start(new CityRoadFragment());
                break;
            case R.id.tv_create_more://查看更多
                start(new ProjectListFragment());
                break;
        }
    }


    @Override
    public void onSupportVisible() {

        mTop.setVisibility(View.VISIBLE);
        mTvTitle.setText("创孵天下");
        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
