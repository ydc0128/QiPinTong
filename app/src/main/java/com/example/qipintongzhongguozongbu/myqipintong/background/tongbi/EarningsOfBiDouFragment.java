package com.example.qipintongzhongguozongbu.myqipintong.background.tongbi;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi.VerifyTheMobilePhonefragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/4/18.
 * 币斗收益
 */

public class EarningsOfBiDouFragment extends BaseFragment {
    @BindView(R.id.earnings_yesterday)
    TextView earningsYesterday;
    @BindView(R.id.tv_deposit)
    TextView tvDeposit;
    @BindView(R.id.tv_returns_a_week)
    TextView tvReturnsAWeek;
    @BindView(R.id.ll_m_e_cunru)
    LinearLayout llMECunru;
    @BindView(R.id.ll_m_e_cunru_2)
    LinearLayout llMECunru2;
    @BindView(R.id.tv_my_bidou_shouyi_cunru)
    TextView tvMyBidouShouyiCunru;
    @BindView(R.id.tv_my_bidou_shouyi_zhuanchu)
    TextView tvMyBidouShouyiZhuanchu;
    @BindView(R.id.rl_my_earnings_record)
    LinearLayout rlMyEarningsRecord;
    @BindView(R.id.rf_my_earnings)
    LinearLayout rfMyEarnings;
    Unbinder unbinder;
    @BindView(R.id.ll_m_e_cunru_bidouguize)
    LinearLayout llMECunruBidouguize;
    private Dialog dialog;
    private View inflate;
    private TextView mExplainone, mExplainTwo, mExplainThree;
    private Button mDetermine;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_bidou_shouyi, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("币斗收益");
        mIvBack.setVisibility(View.VISIBLE);
//      mTop.setBackgroundColor(Color.parseColor("#0093dd"));
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_m_e_cunru, R.id.ll_m_e_cunru_2, R.id.tv_my_bidou_shouyi_cunru, R.id.tv_my_bidou_shouyi_zhuanchu, R.id.ll_m_e_cunru_bidouguize})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_m_e_cunru:
                start(new VerifyTheMobilePhonefragment());
                break;
            case R.id.ll_m_e_cunru_2:
                start(new MyTongBiFragment());
                break;
            case R.id.tv_my_bidou_shouyi_cunru:
                start(new TongbiFragment());
                break;
            case R.id.ll_m_e_cunru_bidouguize:
                Mydialog();
                break;
            case R.id.tv_my_bidou_shouyi_zhuanchu:


                break;
        }
    }

    public void Mydialog() {
        dialog = new Dialog(mActivity, R.style.bottom_menu_style);
        //填充对话框的布局
        inflate = LayoutInflater.from(mActivity).inflate(R.layout.bidoushuoming, null);
        //初始化控件
        mDetermine = (Button) inflate.findViewById(R.id.bt_queding);
        mDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//    将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();//显示对话框
    }
}
