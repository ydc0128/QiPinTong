package com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.faxian.NearFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.tongbi.RobTongbiFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai.FriendDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan.LifeCircleFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 发现的页面布局
 */
public class DetectFragment extends BaseFragment {


    Unbinder unbinder;

    public static DetectFragment getInstance() {

        return new DetectFragment();
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_detect, null);
        return view;
    }

    @Override
    public void onSupportVisible() {
        mTvTitle.setText("发现");
        mIvBack.setVisibility(View.GONE);
        mTop.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


    @OnClick({R.id.ll_friend, R.id.ll_gold, R.id.ll_parent, R.id.ll_live, R.id.ll_tb,R.id.ll_drb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_friend:
                start(new FriendDetailsFragment());
                break;
            case R.id.ll_gold:
                start(new NearFragment());
                break;
            case R.id.ll_parent:

                break;
            case R.id.ll_live:
                // start(new VitasPhereFragment());
                start(new LifeCircleFragment());
                //生活圈
                break;
            case R.id.ll_tb:
                start(new RobTongbiFragment());
                break;
            case R.id.ll_drb:
//                start(new ExpertListFragment());
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


    @OnClick(R.id.ll_drb)
    public void onViewClicked() {
    }
}
