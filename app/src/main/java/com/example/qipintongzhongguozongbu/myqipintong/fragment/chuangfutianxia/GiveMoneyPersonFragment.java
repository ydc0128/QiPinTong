package com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia;

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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvGiveMoneyCompanyAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvGiveMoneyPersonAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 创投天下 投资机构 投资人详情页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/16 上午9:43
 */
public class GiveMoneyPersonFragment extends BaseFragment {
    @BindView(R.id.iv_give_money_person_background)
    ImageView ivGiveMoneyPersonBackground;
    @BindView(R.id.civ_give_money_person_icon)
    CircleImageView civGiveMoneyPersonIcon;
    @BindView(R.id.tv_give_money_person_name)
    TextView tvGiveMoneyPersonName;
    @BindView(R.id.tv_give_money_person_company)
    TextView tvGiveMoneyPersonCompany;
    @BindView(R.id.tv_give_money_person_position)
    TextView tvGiveMoneyPersonPosition;
    @BindView(R.id.tv_give_money_person_location)
    TextView tvGiveMoneyPersonLocation;
    @BindView(R.id.tv_give_money_person_intro)
    TextView tvGiveMoneyPersonIntro;
    @BindView(R.id.tv_give_money_person_good)
    TextView tvGiveMoneyPersonGood;
    @BindView(R.id.rv_give_money_person)
    RecyclerView rvGiveMoneyPerson;
    @BindView(R.id.rv_give_money_company)
    RecyclerView rvGiveMoneyCompany;
    @BindView(R.id.rl_attention)
    RelativeLayout rlAttention;
    @BindView(R.id.rl_friend)
    RelativeLayout rlFriend;
    @BindView(R.id.rl_consult)
    RelativeLayout rlConsult;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_give_money_person, null));
    }

    @Override
    public void initData() {

        setPersonAdapter();

        setCompanyAdapter();

        super.initData();
    }

    /**
     * function   : 投资偏好
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/16  上午10:52
     */
    private void setPersonAdapter() {

        rvGiveMoneyPerson.setLayoutManager(new LinearLayoutManager(mActivity));

        rvGiveMoneyPerson.setAdapter(new RvGiveMoneyPersonAdapter(R.layout.item_give_money_person, getDate()));
    }

    /**
     * function   : 投资机构
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/16  上午10:53
     */
    private void setCompanyAdapter() {

        rvGiveMoneyCompany.setLayoutManager(new LinearLayoutManager(mActivity));

        rvGiveMoneyCompany.setAdapter(new RvGiveMoneyCompanyAdapter(R.layout.item_give_money_company, ImageList.getImageList()));

    }

    @Override
    public void onSupportInvisible() {

        mButton.setVisibility(View.VISIBLE);

        super.onSupportInvisible();
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("投资人");

        mIvBack.setVisibility(View.VISIBLE);

        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
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

    @OnClick({R.id.civ_give_money_person_icon, R.id.rl_attention, R.id.rl_friend, R.id.rl_consult})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ_give_money_person_icon:
                //头像
                break;
            case R.id.rl_attention:
                //关注
                break;
            case R.id.rl_friend:
                //好友
                break;
            case R.id.rl_consult:
                //咨询
                break;
        }
    }

    public List getDate() {
        ArrayList<String> mTitles = new ArrayList<>();
        mTitles.add("关注领域");
        mTitles.add("投资阶段");
        mTitles.add("单笔投资");
        mTitles.add("关注区域");
        return mTitles;
    }



}
