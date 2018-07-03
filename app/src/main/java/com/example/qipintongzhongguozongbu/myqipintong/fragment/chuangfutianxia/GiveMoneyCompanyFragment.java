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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvGiveMoneyCaseAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvGiveMoneyCompanyAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvGiveMoneyPeopleAdapter;
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
 * Description: 创投天下投资机构 机构详情
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/16 下午2:24
 */
public class GiveMoneyCompanyFragment extends BaseFragment {

    @BindView(R.id.tv_give_money_company_ditalis_name)
    TextView tvGiveMoneyCompanyDitalisName;
    @BindView(R.id.tv_give_money_company_detalis_number)
    TextView tvGiveMoneyCompanyDetalisNumber;
    @BindView(R.id.tv_give_money_company_details_location)
    TextView tvGiveMoneyCompanyDetailsLocation;
    @BindView(R.id.tv_give_money_company_details_intro)
    TextView tvGiveMoneyCompanyDetailsIntro;
    @BindView(R.id.tv_give_money_company_details_jieduan)
    TextView tvGiveMoneyCompanyDetailsJieduan;
    @BindView(R.id.tv_give_money_company_details_field)
    TextView tvGiveMoneyCompanyDetailsField;
    @BindView(R.id.rl_give_money_company_details_person)
    RelativeLayout rlGiveMoneyCompanyDetailsPerson;
    @BindView(R.id.rl_give_money_company_details_case)
    RelativeLayout rlGiveMoneyCompanyDetailsCase;
    @BindView(R.id.iv_give_money_company_background)
    ImageView ivGiveMoneyCompanyBackground;
    @BindView(R.id.civ_give_money_company_icon)
    CircleImageView civGiveMoneyCompanyIcon;
    @BindView(R.id.rl_attention)
    RelativeLayout rlAttention;
    @BindView(R.id.rl_friend)
    RelativeLayout rlFriend;
    @BindView(R.id.rl_consult)
    RelativeLayout rlConsult;
    Unbinder unbinder;
    @BindView(R.id.rv_give_money_company_person)
    RecyclerView rvGiveMoneyCompanyPerson;
    @BindView(R.id.rv_give_money_company_company)
    RecyclerView rvGiveMoneyCompanyCompany;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_give_money_company, null));

    }

    @Override
    public void initData() {

        setPersonAdapter();

        setCaseAdapter();

        super.initData();
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

    @OnClick({R.id.rl_attention, R.id.rl_friend, R.id.rl_consult, R.id.civ_give_money_company_icon, R.id.rl_give_money_company_details_person, R.id.rl_give_money_company_details_case})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_attention:
                //关注
                break;
            case R.id.rl_friend:
                //好友
                break;
            case R.id.rl_consult:
                //咨询
                break;
            case R.id.civ_give_money_company_icon:
                //头像
                IconDelalisFragment iconDelalisFragment = new IconDelalisFragment();
                iconDelalisFragment.setPhotoDate((String) ImageList.getImageList().get(0));
                start(iconDelalisFragment);
                break;
            case R.id.rl_give_money_company_details_person:
                //投资人
                rvGiveMoneyCompanyPerson.setVisibility(View.VISIBLE);
                rvGiveMoneyCompanyCompany.setVisibility(View.GONE);
                break;
            case R.id.rl_give_money_company_details_case:
                //投资案例
                rvGiveMoneyCompanyPerson.setVisibility(View.GONE);
                rvGiveMoneyCompanyCompany.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * function   : 投资案例条目
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/16  下午3:22
     */
    private void setCaseAdapter() {

        rvGiveMoneyCompanyCompany.setLayoutManager(new LinearLayoutManager(mActivity));

        rvGiveMoneyCompanyCompany.setAdapter(new RvGiveMoneyCaseAdapter(R.layout.item_give_money_case, getPeopleDate()));

    }

    /**
     * function   : 投资人条目
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/16  下午3:21
     */
    private void setPersonAdapter() {

        rvGiveMoneyCompanyPerson.setLayoutManager(new LinearLayoutManager(mActivity));

        rvGiveMoneyCompanyPerson.setAdapter(new RvGiveMoneyPeopleAdapter(R.layout.item_give_money_people, getPeopleDate()));

    }

    public List<String> getPeopleDate() {

        ArrayList<String> mNames = new ArrayList<>();
        mNames.add("任星星");
        mNames.add("任行星");
        mNames.add("任月亮");
        mNames.add("任太阳");
        return mNames;
    }

    @Override
    public void onSupportVisible() {

        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }
}
