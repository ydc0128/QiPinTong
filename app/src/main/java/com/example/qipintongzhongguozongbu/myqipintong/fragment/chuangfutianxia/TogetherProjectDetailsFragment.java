package com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.IconDelalisFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description: 创投天下合伙人的详情页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/1/14 下午2:59
 */
public class TogetherProjectDetailsFragment extends BaseFragment {
    @BindView(R.id.civ_together_project_details_icon)
    CircleImageView civTogetherProjectDetailsIcon;
    @BindView(R.id.civ_together_project_details_name)
    TextView civTogetherProjectDetailsName;
    @BindView(R.id.tv_together_project_details_industry)
    TextView tvTogetherProjectDetailsIndustry;
    @BindView(R.id.tv_together_project_details_money)
    TextView tvTogetherProjectDetailsMoney;
    @BindView(R.id.tv_together_project_details_age)
    TextView tvTogetherProjectDetailsAge;
    @BindView(R.id.tv_together_project_details_location)
    TextView tvTogetherProjectDetailsLocation;
    @BindView(R.id.tv_together_project_details_introduce)
    TextView tvTogetherProjectDetailsIntroduce;
    @BindView(R.id.tv_together_project_details_cooperation)
    TextView tvTogetherProjectDetailsCooperation;
    @BindView(R.id.rl_attention)
    RelativeLayout rlAttention;
    @BindView(R.id.rl_friend)
    RelativeLayout rlFriend;
    @BindView(R.id.rl_consult)
    RelativeLayout rlConsult;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_together_project_details, null));

    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onSupportInvisible() {

        mButton.setVisibility(View.VISIBLE);

        super.onSupportInvisible();
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("合伙人");
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }

    @OnClick({R.id.rl_attention, R.id.rl_friend, R.id.rl_consult, R.id.civ_together_project_details_icon})
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
            case R.id.civ_together_project_details_icon:
                //头像
                IconDelalisFragment iconDelalisFragment = new IconDelalisFragment();
                iconDelalisFragment.setPhotoDate((String) ImageList.getImageList().get(0));
                start(iconDelalisFragment);
                break;
        }
    }


}
