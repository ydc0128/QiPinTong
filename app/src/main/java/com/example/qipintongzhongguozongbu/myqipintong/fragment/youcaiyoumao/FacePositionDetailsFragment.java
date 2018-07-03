package com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvPositionDescribeAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvPositionStatusAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.IconDelalisFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 有才有貌个人职位详情的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/18 上午9:58
 */
public class FacePositionDetailsFragment extends BaseFragment {

    @BindView(R.id.iv_position_details_icon)
    CircleImageView ivPositionDetailsIcon;
    @BindView(R.id.tv_position_details_name)
    TextView tvPositionDetailsName;
    @BindView(R.id.tv_position_details_attention_number)
    TextView tvPositionDetailsAttentionNumber;
    @BindView(R.id.tv_position_details_fans)
    TextView tvPositionDetailsFans;
    @BindView(R.id.tv_position_details_fans_number)
    TextView tvPositionDetailsFansNumber;
    @BindView(R.id.tv_position_details_industry)
    TextView tvPositionDetailsIndustry;
    @BindView(R.id.tv_position_details_state)
    TextView tvPositionDetailsState;
    @BindView(R.id.tv_position_details_number)
    TextView tvPositionDetailsNumber;
    @BindView(R.id.tv_position_details_profession)
    TextView tvPositionDetailsProfession;
    @BindView(R.id.tv_position_details_salary)
    TextView tvPositionDetailsSalary;
    @BindView(R.id.tv_position_details_locations)
    TextView tvPositionDetailsLocations;
    @BindView(R.id.tv_position_details_year)
    TextView tvPositionDetailsYear;
    @BindView(R.id.tv_position_details_education)
    TextView tvPositionDetailsEducation;
    @BindView(R.id.rv_position_details_describe)
    RecyclerView rvPositionDetailsDescribe;
    @BindView(R.id.rv_position_details_status)
    RecyclerView rvPositionDetailsStatus;
    @BindView(R.id.tv_position_details_url)
    TextView tvPositionDetailsUrl;
    @BindView(R.id.tv_position_details_company)
    TextView tvPositionDetailsCompany;
    @BindView(R.id.tv_position_details_location)
    TextView tvPositionDetailsLocation;
    @BindView(R.id.civ_position_person_icon)
    CircleImageView civPositionPersonIcon;
    @BindView(R.id.tv_position_person_details_name)
    TextView tvPositionPersonDetailsName;
    @BindView(R.id.tv_position_details_position)
    TextView tvPositionDetailsPosition;
    @BindView(R.id.tv_position_details_phone)
    TextView tvPositionDetailsPhone;
    Unbinder unbinder;
    @BindView(R.id.tv_project_ziuxn)
    TextView tvProjectZiuxn;
    @BindView(R.id.rl_position_deatils)
    RelativeLayout rlPositionDeatils;
    @BindView(R.id.iv_position_details_shoucang)
    ImageView ivPositionDetailsShoucang;

    private boolean isShouCang;


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_position_details, null));
    }

    @Override
    public void initData() {

        setDescribeAdapter();

        setStatusAdapter();

        civPositionPersonIcon.setImageResource(R.drawable.icon);

        super.initData();
    }

    /**
     * function   : 任职资格的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/18  上午11:03
     */
    private void setStatusAdapter() {

        rvPositionDetailsStatus.setLayoutManager(new LinearLayoutManager(mActivity));
        rvPositionDetailsStatus.setAdapter(new RvPositionStatusAdapter(R.layout.item_text, getDate()));

    }

    /**
     * function   : 职位描述适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/18  上午11:03
     */
    private void setDescribeAdapter() {

        rvPositionDetailsDescribe.setLayoutManager(new LinearLayoutManager(mActivity));
        rvPositionDetailsDescribe.setAdapter(new RvPositionDescribeAdapter(R.layout.item_text, getTextDate()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_position_details_url, R.id.tv_position_details_company, R.id.tv_position_details_location, R.id.iv_position_details_icon, R.id.ll_add_friend, R.id.iv_position_details_shoucang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_position_details_url:
                //网址
                break;
            case R.id.tv_position_details_company:
                //公司名称
                break;
            case R.id.tv_position_details_location:
                //地址
                break;


            case R.id.ll_add_friend:
                //加好友

                break;
            case R.id.iv_position_details_icon:
                //头像
                IconDelalisFragment iconDelalisFragment = new IconDelalisFragment();
                iconDelalisFragment.setPhotoDate((String) ImageList.getImageList().get(0));
                start(iconDelalisFragment);

                break;

            case R.id.iv_position_details_shoucang:
                isShouCang = !isShouCang;
                if (isShouCang) {
                    ivPositionDetailsShoucang.setImageResource(R.mipmap.shoucangheigh);
                } else {
                    ivPositionDetailsShoucang.setImageResource(R.mipmap.shoucangnomal);
                }

                break;
        }
    }

    @Override
    public void onSupportVisible() {

        mTop.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);
        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("职位详情");

        super.onSupportVisible();
    }


    public List getDate() {

        ArrayList<String> mDate = new ArrayList<>();
        mDate.add("熟练使用PS等设计软件");
        mDate.add("不会使用PS等设计软件");
        mDate.add("谁会使用PS等设计软件");
        mDate.add("我会使用PS等设计软件");
        return mDate;
    }


    public List getTextDate() {

        ArrayList<String> mDate = new ArrayList<>();

        mDate.add("谁要负责完成客户网站首页及内页效果设计;");
        mDate.add("我要负责完成客户网站首页及内页效果设计;");
        mDate.add("就不负责完成客户网站首页及内页效果设计;");
        mDate.add("我会负责完成客户网站首页及内页效果设计;");

        return mDate;
    }

    @OnClick(R.id.iv_position_details_shoucang)
    public void onClick() {
    }
}
