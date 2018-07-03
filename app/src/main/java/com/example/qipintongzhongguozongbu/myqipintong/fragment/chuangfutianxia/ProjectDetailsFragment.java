package com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvProjectDetailsAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.IconDelalisFragment;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 创投天下好项目的详情页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/13 下午2:33
 */
public class ProjectDetailsFragment extends BaseFragment {

    @BindView(R.id.iv_project_details_icon)
    CircleImageView ivProjectDetailsIcon;
    @BindView(R.id.tv_project_details_name)
    TextView tvProjectDetailsName;
    @BindView(R.id.tv_project_details_title)
    TextView tvProjectDetailsTitle;
    @BindView(R.id.tv_project_details_state)
    TextView tvProjectDetailsState;
    @BindView(R.id.tv_project_details_location)
    TextView tvProjectDetailsLocation;
    @BindView(R.id.tv_project_details_number)
    TextView tvProjectDetailsNumber;
    @BindView(R.id.tv_project_details_money)
    TextView tvProjectDetailsMoney;
    @BindView(R.id.tv_project_details_body)
    TextView tvProjectDetailsBody;
    @BindView(R.id.rv_project_details)
    RecyclerView rvProjectDetails;
    @BindView(R.id.TFL_project_details)
    TagFlowLayout TFLProjectDetails;
    @BindView(R.id.rl_attention)
    RelativeLayout rlAttention;
    @BindView(R.id.rl_friend)
    RelativeLayout rlFriend;
    @BindView(R.id.rl_consult)
    RelativeLayout rlConsult;

    private String[] mVals = new String[]//融资历史的标签内容
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView"};

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_project_details, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void initData() {

        setRecyclerViewAdapter();

        setTagAdapter();

        super.initData();

    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("项目详情");
        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();

    }


    /**
     * function   : 标签的布局适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  下午9:39
     */
    private void setTagAdapter() {

        TFLProjectDetails.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView mTv = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.tag_textview, TFLProjectDetails, false);
                mTv.setText(s);
                return mTv;
            }
        });

    }

    /**
     * function   : 融资历史的条目适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/13  下午9:40
     */
    private void setRecyclerViewAdapter() {

        rvProjectDetails.setLayoutManager(new LinearLayoutManager(mActivity));
        rvProjectDetails.setAdapter(new RvProjectDetailsAdapter(mActivity));

    }

    @OnClick({R.id.rl_attention, R.id.rl_friend, R.id.rl_consult, R.id.iv_project_details_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_attention:
                //关注
                ToastUtils.showToast(mActivity, "关注");
                break;
            case R.id.rl_friend:
                //朋友
                break;
            case R.id.rl_consult:
                //简历
                break;
            case R.id.iv_project_details_icon:
                //头像
                IconDelalisFragment iconDelalisFragment = new IconDelalisFragment();
                iconDelalisFragment.setPhotoDate((String) ImageList.getImageList().get(0));
                start(iconDelalisFragment);
                break;

        }
    }


}
