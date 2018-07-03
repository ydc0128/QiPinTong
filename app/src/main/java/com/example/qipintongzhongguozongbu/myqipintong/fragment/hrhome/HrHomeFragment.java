package com.example.qipintongzhongguozongbu.myqipintong.fragment.hrhome;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvHrHomeFocusAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvHrHomeHrAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.TabAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDynamicBean;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * \
 * Description: HR Home 页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/17 下午2:19
 */
public class HrHomeFragment extends BaseFragment {
    @BindView(R.id.bb_hr_home)
    Banner bbHrHome;
    @BindView(R.id.CTL_hr_home)
    CommonTabLayout CTLHrHome;
    @BindView(R.id.rv_hr_home)
    RecyclerView rvHrHome;
    Unbinder unbinder;

    private String[] mTitles = {"看点", "HR"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int mPosition;
    private List<String> date;
    //根据位置判断是哪一个适配器生效 决定跳转去哪一个页面


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragmenet_hr_home, null));
    }

    @Override
    public void initData() {

        setBannerImage(bbHrHome);

        setTitleDate();

        setFocusAdapert();

        super.initData();
    }

    /**
     * function   : HR
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/17  下午2:56
     */
    private void setHrAdapter() {

        rvHrHome.setLayoutManager(new LinearLayoutManager(mActivity));

        rvHrHome.setAdapter(new RvHrHomeHrAdapter(R.layout.item_hr_home_hr, getDate()));


    }

    /**
     * function   : 看点
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/17  下午2:56
     */
    private void setFocusAdapert() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvHrHome.setLayoutManager(linearLayoutManager);

        rvHrHome.setAdapter(new RvHrHomeFocusAdapter(mActivity, this, getFriendDate()));
    }


    private void setTitleDate() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabAdapter(mTitles[i]));
        }

        CTLHrHome.setTabData(mTabEntities);

        CTLHrHome.setOnTabSelectListener(new OnTabSelectListener() {


            @Override
            public void onTabSelect(int position) {

                mPosition = position;

                switch (position) {
                    case 0:
                        //看点
                        setFocusAdapert();
                        break;
                    case 1:
                        //HR
                        setHrAdapter();
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

    public List<FriendDynamicBean> getFriendDate() {
        ArrayList<FriendDynamicBean> list = new ArrayList<>();

        FriendDynamicBean bean = new FriendDynamicBean();

        for (int i = 0; i < GlobalConstants.mPhotoDate.length; i++) {
            ArrayList<Integer> images = new ArrayList<>();

            bean.setName("海绵宝宝");
            images.add(GlobalConstants.mPhotoDate[i]);
            // bean.setImageList(images);
            bean.setInputText("看成败 人生豪迈 大不了 加班再改 嘿嘿哈哈红红火火恍恍惚惚");
            list.add(bean);

        }


        return list;
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("HR HOME");

        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();
    }


    public List<String> getDate() {
        ArrayList<String> list = new ArrayList<>();

        list.add("派大星");
        list.add("派大星");
        list.add("派大星");
        list.add("派大星");
        list.add("派大星");

        return list;
    }
}
