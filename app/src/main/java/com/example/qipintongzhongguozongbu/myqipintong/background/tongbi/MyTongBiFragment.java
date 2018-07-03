package com.example.qipintongzhongguozongbu.myqipintong.background.tongbi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.tongbi.ExpenseCalendarFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.youth.banner.listener.OnBannerClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的通币界面
 * Created by L on 2017/2/10.
 */

public class MyTongBiFragment extends BaseFragment implements OnBannerClickListener {


    Unbinder unbinder;
    @BindView(R.id.tv_my_tongbi_yuee)
    TextView tvMyTongbiYuee;
    @BindView(R.id.iv_my_tongbi_jinbi)
    ImageView ivMyTongbiJinbi;
    @BindView(R.id.ll_my_tongbi_top_up1)
    LinearLayout llMyTongbiTopUp1;
    @BindView(R.id.ll_my_tongbi_top_up2)
    LinearLayout llMyTongbiTopUp2;
    @BindView(R.id.ll_my_tongbi_top_up3)
    LinearLayout llMyTongbiTopUp3;
    @BindView(R.id.ll_my_tongbi_top_up4)
    LinearLayout llMyTongbiTopUp4;
    @BindView(R.id.bt_my_tongbi_top_up)
    Button btMyTongbiTopUp;
    @BindView(R.id.tv_my_tongbi_record)
    TextView tvMyTongbiRecord;
    @BindView(R.id.rl_my_tongbi_record)
    RelativeLayout rlMyTongbiRecord;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragmenet_my_tongbi, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("充值");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void OnBannerClick(int position) {

    }


    @OnClick({R.id.ll_my_tongbi_top_up1, R.id.ll_my_tongbi_top_up2, R.id.ll_my_tongbi_top_up3, R.id.ll_my_tongbi_top_up4, R.id.bt_my_tongbi_top_up, R.id.tv_my_tongbi_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_my_tongbi_top_up1:
                break;
            case R.id.ll_my_tongbi_top_up2:
                break;
            case R.id.ll_my_tongbi_top_up3:
                break;
            case R.id.ll_my_tongbi_top_up4:
                break;
            case R.id.bt_my_tongbi_top_up:
                break;
            case R.id.tv_my_tongbi_record:
                // 消费记录
                start(new ExpenseCalendarFragment());
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
