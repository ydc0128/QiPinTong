package com.example.qipintongzhongguozongbu.myqipintong.background.chuangtoutianxia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia.LocalRoadFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by L on 2017/3/11.
 * 创投
 */

public class IWantToVentureFragment extends BaseFragment {
    @BindView(R.id.fragment_tongchengluyan)
    FrameLayout fragmentTongchengluyan;
    @BindView(R.id.my_fl_touzi)
    FrameLayout myFlTouzi;


    @Override
    public View initView() {
        return swipeBackView( View.inflate(mActivity, R.layout.background_i_want_to_venture, null));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("创投天下");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick(R.id.my_fl_touzi)
    public void onClick() {
//        投资
        start(new LocalRoadFragment());
    }
}
