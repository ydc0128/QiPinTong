package com.example.qipintongzhongguozongbu.myqipintong.background.tongbi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by L on 2017/3/27.
 * 币斗--存入
 */
public class TongbiFragment extends BaseFragment {

    @BindView(R.id.tv_tongbib_cunru)
    TextView tvTongbibCunru;
    @BindView(R.id.tv_tongbi_riqi)
    TextView tvTongbiRiqi;
    @BindView(R.id.cb_tongbi)
    CheckBox cbTongbi;
    @BindView(R.id.bt_tongbi_queding)
    Button btTongbiQueding;


    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_gain_tongbi, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }
    public void onSupportVisible() {
        mTvTitle.setText("通币收益");
        mIvBack.setVisibility(View.VISIBLE);
//      mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }
    @OnClick({R.id.tv_tongbi_riqi, R.id.cb_tongbi, R.id.bt_tongbi_queding})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_tongbi_riqi:
                break;
            case R.id.cb_tongbi:
                break;
            case R.id.bt_tongbi_queding:
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
