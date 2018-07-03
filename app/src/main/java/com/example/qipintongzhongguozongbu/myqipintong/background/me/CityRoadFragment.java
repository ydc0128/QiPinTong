package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCityRoadEvaluateAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by L on 2017/2/17.
 * 同城路演item详情页面
 */

public class CityRoadFragment extends BaseFragment {


    @BindView(R.id.tv_city_road_title)
    TextView tvCityRoadTitle;
    @BindView(R.id.tv_city_road_release_time)
    TextView tvCityRoadReleaseTime;
    @BindView(R.id.tv_city_road_browse)
    TextView tvCityRoadBrowse;
    @BindView(R.id.ib_city_road_like)
    ImageView ibCityRoadLike;
    @BindView(R.id.ib_city_road_share)
    ImageView ibCityRoadShare;
    @BindView(R.id.iv_city_road_picture)
    ImageView ivCityRoadPicture;
    @BindView(R.id.tv_city_road_time)
    TextView tvCityRoadTime;
    @BindView(R.id.tv_city_road_site)
    TextView tvCityRoadSite;
    @BindView(R.id.tv_city_road_sponsor)
    TextView tvCityRoadSponsor;
    @BindView(R.id.tv_city_road_essay)
    TextView tvCityRoadEssay;
    @BindView(R.id.rv_city_road_message)
    RecyclerView rvCityRoadMessage;
    @BindView(R.id.tv_city_road_enrollment)
    TextView tvCityRoadEnrollment;
    @BindView(R.id.tv_city_road_i_want_to_sign_up)
    TextView tvCityRoadIWantToSignUp;
    private boolean isChecked;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_city_road, null));


    }

    public void onSupportVisible() {
        mTvTitle.setText("同城路演");
        mButton.setVisibility(View.GONE);
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    /**
     * 同城路演条目适配器
     */
    public void initData() {
        setRvCityRoadEvaluateAdapter();
        super.initData();
    }


    private void setRvCityRoadEvaluateAdapter() {

        rvCityRoadMessage.setLayoutManager(new LinearLayoutManager(mActivity));
        rvCityRoadMessage.setAdapter(new RvCityRoadEvaluateAdapter(R.layout.item_city_road, ImageList.getImageList()));

    }


    @OnClick({R.id.ib_city_road_like, R.id.ib_city_road_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_city_road_like://收藏


                if (isChecked) {
                    ibCityRoadLike.setImageResource(R.mipmap.shoucangheigh);
                } else {
                    ibCityRoadLike.setImageResource(R.mipmap.shoucangnomal);
                }
                isChecked = !isChecked;

                break;
            case R.id.ib_city_road_share://分享
                break;
        }
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

}
