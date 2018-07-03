package com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCompanyAynamicAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCompanyEvaluateAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCompanyLocationAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCompanyPositionAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNewFriendAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.TabAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDynamicBean;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.IconDelalisFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description: 有才有貌公司详情页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/18 下午3:12
 */
public class CompanyDetailsFragment extends BaseFragment {

    @BindView(R.id.tv_company_details_name)
    TextView tvCompanyDetailsName;
    @BindView(R.id.tv_company_details_attention_number)
    TextView tvCompanyDetailsAttentionNumber;
    @BindView(R.id.tv_company_details_fans_number)
    TextView tvCompanyDetailsFansNumber;
    @BindView(R.id.tv_company_details_industry)
    TextView tvCompanyDetailsIndustry;
    @BindView(R.id.tv_company_details_state)
    TextView tvCompanyDetailsState;
    @BindView(R.id.tv_company_details_number)
    TextView tvCompanyDetailsNumber;
    @BindView(R.id.iv_company_details_icon)
    CircleImageView ivCompanyDetailsIcon;
    @BindView(R.id.CTL_company)
    CommonTabLayout CTLCompany;
    @BindView(R.id.rv_company_details_location)
    RecyclerView rvCompanyDetailsLocation;
    @BindView(R.id.tv_company_details_evaluate)
    TextView tvCompanyDetailsEvaluate;
    @BindView(R.id.tv_company_details_grade)
    TextView tvCompanyDetailsGrade;
    @BindView(R.id.rv_company_details_evaluate)
    RecyclerView rvCompanyDetailsEvaluate;
    @BindView(R.id.rv_company_details_position)
    RecyclerView rvCompanyDetailsPosition;
    @BindView(R.id.rv_person_details_aynamic)
    RecyclerView rvPersonDetailsAynamic;
    Unbinder unbinder;
    @BindView(R.id.iv_company_details_shoucang)
    ImageView ivCompanyDetailsShoucang;
    @BindView(R.id.iv_company_details_vip)
    ImageView ivCompanyDetailsVip;
    private boolean isShouCang;

    private View pager_home;
    private View pager_position;
    private View pager_dynmic;
    private String[] mTitles = {"主页", "职位", "动态"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private RvNewFriendAdapter rvNewFriendAdapter;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_company_details, null);
        pager_position = view.findViewById(R.id.include_compay_pager_position);
        pager_home = view.findViewById(R.id.include_compay_pager_home);
        pager_dynmic = view.findViewById(R.id.include_compay_dynamic);
        return swipeBackView(view);
    }

    @Override
    public void initData() {

        setTitlesDate();

        setLocationAdapter();

        setEvaluateAdapter();

        setPositionAdapter();

        setAynamicAdapter();


        setInitState();

        super.initData();
    }

    /**
     * function   : 设置初次进入页面显示的状态
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/18  上午11:56
     */
    private void setInitState() {
        pager_home.setVisibility(View.VISIBLE);
        pager_position.setVisibility(View.GONE);
        pager_dynmic.setVisibility(View.GONE);
    }

    /**
     * function   : 动态的数据适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/19  下午12:08
     */
    private void setAynamicAdapter() {

//        rvPersonDetailsAynamic.setLayoutManager(new LinearLayoutManager(mActivity));
//        rvPersonDetailsAynamic.setAdapter(new RvCompanyAynamicAdapter(mActivity, this, getDate()));




        rvPersonDetailsAynamic.setLayoutManager(new LinearLayoutManager(mActivity));
        rvNewFriendAdapter = new RvNewFriendAdapter(this, mActivity);
        rvNewFriendAdapter.setList(ImageList.getListData());
        rvPersonDetailsAynamic.setAdapter(rvNewFriendAdapter);





    }


    /**
     * function   : 职位的列表适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/18  下午9:48
     */
    private void setPositionAdapter() {

        rvCompanyDetailsPosition.setLayoutManager(new LinearLayoutManager(mActivity));
        rvCompanyDetailsPosition.setAdapter(new RvCompanyPositionAdapter(R.layout.item_company_position, getTextDate()));

    }

    /**
     * function   : 公司地址的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/18  下午7:27
     */
    private void setLocationAdapter() {

        rvCompanyDetailsLocation.setLayoutManager(new LinearLayoutManager(mActivity));
        rvCompanyDetailsLocation.setAdapter(new RvCompanyLocationAdapter(R.layout.item_text, getTextDate()));
    }

    /**
     * function   : 面试评价的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/18  下午7:27
     */
    private void setEvaluateAdapter() {

        rvCompanyDetailsEvaluate.setLayoutManager(new LinearLayoutManager(mActivity));
        rvCompanyDetailsEvaluate.setAdapter(new RvCompanyEvaluateAdapter(R.layout.item_company_evaluate, ImageList.getImageList()));
    }

    /**
     * function   : 设置选项卡的数据 点击事件
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/18  下午5:47
     */
    private void setTitlesDate() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabAdapter(mTitles[i]));
        }
        CTLCompany.setTabData(mTabEntities);
        CTLCompany.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        //主页
                        pager_home.setVisibility(View.VISIBLE);
                        pager_position.setVisibility(View.GONE);
                        pager_dynmic.setVisibility(View.GONE);
                        break;
                    case 1:
                        //职位
                        pager_home.setVisibility(View.GONE);
                        pager_position.setVisibility(View.VISIBLE);
                        pager_dynmic.setVisibility(View.GONE);
                        break;
                    case 2:
                        //动态
                        pager_home.setVisibility(View.GONE);
                        pager_position.setVisibility(View.GONE);
                        pager_dynmic.setVisibility(View.VISIBLE);
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

    @Override
    public void onSupportVisible() {

        mTop.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);
        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("公司详情");

        super.onSupportVisible();
    }


    @OnClick({R.id.iv_company_details_icon, R.id.rl_fans, R.id.rl_send, R.id.iv_company_details_shoucang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_fans://关注
                ToastUtils.showToast(mActivity, "关注");
                break;
            case R.id.rl_send://投简历
                ToastUtils.showToast(mActivity, "简历");
                break;
            case R.id.iv_company_details_shoucang:

                isShouCang = !isShouCang;
                if (isShouCang) {
                    ivCompanyDetailsShoucang.setImageResource(R.mipmap.shoucangheigh);
                } else {
                    ivCompanyDetailsShoucang.setImageResource(R.mipmap.shoucangnomal);
                }
                break;
            case R.id.iv_company_details_icon:
                //头像
                IconDelalisFragment iconDelalisFragment = new IconDelalisFragment();
                iconDelalisFragment.setPhotoDate((String) ImageList.getImageList().get(0));
                start(iconDelalisFragment);
                break;
        }
    }

    public List<FriendDynamicBean> getDate() {

        ArrayList<FriendDynamicBean> list = new ArrayList<>();

        for (int i = 0; i < GlobalConstants.mIvDate.length; i++) {
            FriendDynamicBean bean = new FriendDynamicBean();
            bean.setIcon(GlobalConstants.mPhotoDate[i]);
            bean.setInputText("RecyclerView是support.v7包中的控件，可以说是ListView和GridView的增强升级版。官方对RecyclerView的描述是（不翻译不是因为我英语差啊，真的）： A flexible view for providing a limited window into a large data set." + i);
            bean.setImageList(ImageList.getImageList());
            bean.setName("葫芦" + i + "娃");
            list.add(bean);
        }

        return list;
    }


    public ArrayList getTextDate() {

        ArrayList<String> mDates = new ArrayList<>();

        mDates.add("未央店 : 未央区未央路盛龙广场1号楼9层");
        mDates.add("未央店 : 未央区未央路盛龙广场2号楼9层");
        mDates.add("未央店 : 未央区未央路盛龙广场3号楼9层");
        mDates.add("未央店 : 未央区未央路盛龙广场4号楼9层");

        return mDates;
    }

}
