package com.example.qipintongzhongguozongbu.myqipintong.fragment.xiaoyuanzhipin;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RecruitDetilsAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 招聘会详情页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/10 上午9:56
 */
public class RecruitDetailsFragment extends BaseFragment {
    @BindView(R.id.tv_recruit_details_title)
    TextView tvRecruitDetailsTitle;
    @BindView(R.id.iv_recruit_details_share)
    ImageView ivRecruitDetailsShare;
    @BindView(R.id.iv_recruit_details_collect)
    ImageView ivRecruitDetailsCollect;
    @BindView(R.id.tv_recruit_details_site)
    TextView tvRecruitDetailsSite;
    @BindView(R.id.tv_recruit_details_time)
    TextView tvRecruitDetailsTime;
    @BindView(R.id.bb_recruit_details)
    Banner bbRecruitDetails;
    @BindView(R.id.tv_recruit_details_deadline)
    TextView tvRecruitDetailsDeadline;
    @BindView(R.id.tv_recruit_details_money)
    TextView tvRecruitDetailsMoney;
    @BindView(R.id.tv_recruit_details_type)
    TextView tvRecruitDetailsType;
    @BindView(R.id.tv_recruit_details_serve)
    TextView tvRecruitDetailsServe;
    @BindView(R.id.rv_recruit_details)
    RecyclerView rvRecruitDetails;
    @BindView(R.id.tv_recruit_details_phone_number)
    TextView tvRecruitDetailsPhoneNumber;
    @BindView(R.id.tv_recruit_details_location)
    TextView tvRecruitDetailsLocation;
    @BindView(R.id.tv_recruit_details_gps)
    TextView tvRecruitDetailsGps;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_recruit_details, null));
    }

    @Override
    public void initData() {

        setBannerImage(bbRecruitDetails);

        setRecyclerViewAdapter();

        super.initData();
    }

    private void setRecyclerViewAdapter() {

        rvRecruitDetails.setLayoutManager(new LinearLayoutManager(mActivity));
        //设置条目数据
        rvRecruitDetails.setAdapter(new RecruitDetilsAdapter(R.layout.item_text, getData()));

    }

    /**
     * function   :
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/10  上午11:13
     */
    private ArrayList getData() {

        ArrayList<String> list = new ArrayList<>();
        list.add("1. 叶凡：本书男主角，与众老同学在泰山聚会时一同被九龙拉棺带离地球，进入北斗星域，得知自己是荒古圣体。");
        list.add("2. 姬紫月：本书女主角，出场年龄十七岁。被叶凡劫持一同经历青铜古殿历险，依靠碎裂的神光遁符解除禁制，反过来挟持叶凡。");
        list.add("3. 庞博：叶凡大学时最好的朋友，壮硕魁伟，直率义气。到达北斗星域后因服用了圣果被灵墟洞天作为仙苗，在青帝坟墓处为青帝十九代孙附体离去，肉身被锤炼至四极境界。");
        list.add("4. 安妙依：第二女主角，妙欲庵当代传人，外表却极其圣洁淡雅，更有一种勾魂魅力。");
        list.add("5. 姬皓月：姬家七公子，姬紫月亲兄，为稀世神体，修有海上升明月的轮海异象。");
        list.add("6. 李小曼：叶凡大学时期的女友，婀娜挺秀，心性自私绝情。");
        list.add("7. 秦瑶：妖族女子，颜如玉部属，美丽惑人。多次挑逗叶凡，在一次宴会后与叶凡酒后乱性。");
        list.add("8. 姚曦：摇光圣地圣女，容貌美丽勾魂，声音甜腻惑人，性格谨慎，有一定心机。试图控制叶凡反被轻薄。");
        return list;
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

    @OnClick({R.id.iv_recruit_details_share, R.id.iv_recruit_details_collect, R.id.tv_recruit_details_gps})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_recruit_details_share:
                //分享按钮
                break;
            case R.id.iv_recruit_details_collect:
                //收藏按钮
                break;
            case R.id.tv_recruit_details_gps:
                //导航

                break;
        }
    }

    @Override
    public void onSupportInvisible() {

        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportInvisible();

    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("招聘会");
        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();

    }
}
