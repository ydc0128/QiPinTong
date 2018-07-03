package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 期望职位
 * Created by L on 2017/2/23.
 */

public class DesiredOccupationFragment extends BaseFragment {


    @BindView(R.id.bt_desired_occupation_add)
    Button btDesiredOccupationAdd;
    Unbinder unbinder;

    public static DesiredOccupationFragment getInstance() {
        return new DesiredOccupationFragment();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_desired_occupation, null));
    }


    public void onSupportVisible() {
        mTvTitle.setText("期望职位");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick({R.id.bt_desired_occupation_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_desired_occupation_add:
                start(new DesiredOccupationAddFragment());
//                添加
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
