package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

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
 * Created by L on 2017/3/17.
 * 入住-优惠信息
 */

public class PreferentialFragment extends BaseFragment {
    @BindView(R.id.bt_preferntial_baocun)
    Button btPreferntialBaocun;

    Unbinder unbinder;
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_preferntial, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("优惠信息");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick(R.id.bt_preferntial_baocun)
    public void onClick() {
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
