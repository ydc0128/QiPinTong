package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;


import android.content.Intent;
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
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.event.FriendImageEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai.SelectBackGroundActivity;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by L on 2017/2/22.
 * 编辑简历
 */

public class MyDataFragment extends BaseFragment {


    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.iv_my_xingbie)
    ImageView ivMyXingbie;
    @BindView(R.id.ll_my_data_edit_your_resume)
    LinearLayout llMyDataEditYourResume;
    @BindView(R.id.rl_my_data_jichuziliao)
    RelativeLayout rlMyDataJichuziliao;
    @BindView(R.id.rl_my_data_bianjijinli)
    RelativeLayout rlMyDataBianjijinli;
    @BindView(R.id.bt_jianli_liulan)
    Button btJianliLiulan;
    @BindView(R.id.rf_my_data)
    LinearLayout rfMyData;
    Unbinder unbinder;
    @BindView(R.id.iv_mm_photo)
    CircleImageView ivMmPhoto;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.bakcground_menu_my_data, null);
        return swipeBackView(view);
    }


    public void onSupportVisible() {
        mTvTitle.setText("编辑简历");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @Subscribe
    public void setBackground(FriendImageEvent event) {

        GlideUtils.loadImage(mActivity, event.getFile(),ivMmPhoto );

    }

    @OnClick({R.id.tv_my_name, R.id.iv_my_xingbie, R.id.ll_my_data_edit_your_resume, R.id.rl_my_data_jichuziliao, R.id.rl_my_data_bianjijinli, R.id.bt_jianli_liulan, R.id.rf_my_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_my_name:
                break;
            case R.id.iv_my_xingbie:
                break;
            case R.id.ll_my_data_edit_your_resume:
                startActivity(new Intent(mActivity, SelectBackGroundActivity.class));
                break;
            case R.id.rl_my_data_jichuziliao:
                start(new InitialValueFragment());
                break;
            case R.id.rl_my_data_bianjijinli:
                start(new BianjiFragment());
                break;
            case R.id.bt_jianli_liulan:
                start(new BrowseFragment());
                break;
            case R.id.rf_my_data:
                break;
        }
    }
}
