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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNewFriendAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvPersonAynmicAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvPersonStudyAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvPersonTrainAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvPersonWorkAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.TabAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDynamicBean;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeBean;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.IconDelalisFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
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
 * Description: 个人详情有才有貌的详情页
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/16 下午9:51
 */
public class FaceAndTalentDetailsFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.iv_person_details_icon)
    CircleImageView ivPersonDetailsIcon;
    @BindView(R.id.tv_person_details_name)
    TextView tvPersonDetailsName;
    @BindView(R.id.iv_person_details_gender)
    ImageView ivPersonDetailsGender;
    @BindView(R.id.tv_person_details_attention_number)
    TextView tvPersonDetailsAttentionNumber;
    @BindView(R.id.tv_person_details_fans_number)
    TextView tvPersonDetailsFansNumber;
    @BindView(R.id.tv_person_details_body_height)
    TextView tvPersonDetailsBodyHeight;
    @BindView(R.id.tv_person_details_marriage)
    TextView tvPersonDetailsMarriage;
    @BindView(R.id.tv_person_details_location)
    TextView tvPersonDetailsLocation;
    @BindView(R.id.tv_person_details_position)
    TextView tvPersonDetailsPosition;
    @BindView(R.id.tv_person_details_salary)
    TextView tvPersonDetailsSalary;
    @BindView(R.id.tv_person_details_phone)
    TextView tvPersonDetailsPhone;
    @BindView(R.id.tv_person_details_locations)
    TextView tvPersonDetailsLocations;
    @BindView(R.id.tv_person_details_year)
    TextView tvPersonDetailsYear;
    @BindView(R.id.tv_person_details_education)
    TextView tvPersonDetailsEducation;
    @BindView(R.id.tv_person_details_industry)
    TextView tvPersonDetailsIndustry;
    @BindView(R.id.tv_person_details_work_state)
    TextView tvPersonDetailsWorkState;
    @BindView(R.id.tv_person_details_superiority)
    TextView tvPersonDetailsSuperiority;
    @BindView(R.id.tv_person_details_evaluate)
    TextView tvPersonDetailsEvaluate;
    @BindView(R.id.tv_person_award)
    TextView tvPersonAward;
    @BindView(R.id.rv_person_details_aynamic)
    RecyclerView rvPersonDetailsAynamic;
    @BindView(R.id.rv_person_details_study)
    RecyclerView rvPersonDetailsStudy;
    @BindView(R.id.rv_person_details_train)
    RecyclerView rvPersonDetailsTrain;
    @BindView(R.id.rv_person_details_work)
    RecyclerView rvPersonDetailsWork;
    @BindView(R.id.CTL_person)
    CommonTabLayout CTLPerson;
    @BindView(R.id.rl_face_include)
    RelativeLayout rlFaceInclude;
    private View pager_home;
    private View pager_dynamic;


    private String[] mTitles = {"主页", "动态"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_face_details, null);
        pager_home = view.findViewById(R.id.include_pager_home);
        pager_dynamic = view.findViewById(R.id.include_pager_dynamic);
        return swipeBackView(view);
    }

    @Override
    public void initData() {

        setStudyAdapter();

        setTrainAdapter();

        setWorkAdapter();

        setAynamicAdapter();

        setTitlesDate();

        ivPersonDetailsIcon.setImageResource(R.drawable.iv_1);

        super.initData();

    }

    /**
     * function   : 选项卡的按钮点击
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/18  下午6:22
     */
    private void setTitlesDate() {


        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabAdapter(mTitles[i]));
        }
        CTLPerson.setTabData(mTabEntities);
        CTLPerson.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        //主页

                        pager_dynamic.setVisibility(View.GONE);
                        pager_home.setVisibility(View.VISIBLE);

                        break;

                    case 1:
                        //动态

                        pager_dynamic.setVisibility(View.VISIBLE);
                        pager_home.setVisibility(View.GONE);

                        break;

                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }


    /**
     * function   : 动态的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/17  下午4:31
     */
    private void setAynamicAdapter() {

        rvPersonDetailsAynamic.setLayoutManager(new LinearLayoutManager(mActivity));
        RvNewFriendAdapter rvNewFriendAdapter = new RvNewFriendAdapter(this, mActivity);
        rvNewFriendAdapter.setList(ImageList.getListData());
        rvPersonDetailsAynamic.setAdapter(rvNewFriendAdapter);
    }

    /**
     * function   : 工作经历的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/17  下午4:29
     */
    private void setWorkAdapter() {

        rvPersonDetailsWork.setLayoutManager(new LinearLayoutManager(mActivity));
        rvPersonDetailsWork.setAdapter(new RvPersonWorkAdapter(R.layout.item_person_work, getTextDate()));
    }

    /**
     * function   : 培训经历的适配
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/17  下午4:29
     */
    private void setTrainAdapter() {

        rvPersonDetailsTrain.setLayoutManager(new LinearLayoutManager(mActivity));
        rvPersonDetailsTrain.setAdapter(new RvPersonTrainAdapter(R.layout.item_person_study, getTextDate()));
    }

    /**
     * function   : 教育经历的适配
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/17  下午4:29
     */
    private void setStudyAdapter() {

        rvPersonDetailsStudy.setLayoutManager(new LinearLayoutManager(mActivity));
        rvPersonDetailsStudy.setAdapter(new RvPersonStudyAdapter(R.layout.item_person_study, getTextDate()));

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

        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);
        mTvTitle.setText("个人详情");
        mTop.setVisibility(View.VISIBLE);

        super.onSupportVisible();
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

        mDates.add("未央路盛龙广场1号楼9层");
        mDates.add("未央路盛龙广场1号楼9层");
        mDates.add("未央路盛龙广场1号楼9层");
        mDates.add("未央路盛龙广场1号楼9层");

        return mDates;
    }

    @OnClick({R.id.rl_fans, R.id.rl_send, R.id.iv_person_details_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_fans:
                //加关注
                break;
            case R.id.rl_send:
                //发邀请
                break;
            case R.id.iv_person_details_icon:
                //头像点击
                IconDelalisFragment iconDelalisFragment = new IconDelalisFragment();
                iconDelalisFragment.setPhotoDate((String) ImageList.getImageList().get(0));
                start(iconDelalisFragment);
                break;
        }
    }


}
