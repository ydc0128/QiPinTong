package com.example.qipintongzhongguozongbu.myqipintong.background.tongbi;

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
 * Created by L on 2017/3/27.
 * 币斗
 */
public class BiDouFragment extends BaseFragment {
    @BindView(R.id.bt_bidou_gain)
    Button btBidouGain;

    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_bi_dou, null));
    }
    public void onSupportVisible() {
        mTvTitle.setText("币斗");
        mIvBack.setVisibility(View.VISIBLE);
//      mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.bt_bidou_gain)
    public void onClick() {
        start(new EarningsOfBiDouFragment() );
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
