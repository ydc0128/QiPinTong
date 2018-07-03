package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.tongbi;

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
import com.example.qipintongzhongguozongbu.myqipintong.background.tongbi.BiDouFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.tongbi.MyTongBiFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的收益界面
 * Created by L on 2017/2/9.
 */

public class MyEarningsFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_my_earnings_shouyi)
    TextView tvMyEarningsShouyi;
    @BindView(R.id.bt_my_earnings_top_up)
    Button btMyEarningsTopUp;
    @BindView(R.id.bt_my_earnings_extract)
    Button btMyEarningsExtract;
    @BindView(R.id.ll_m_e_cunru)
    LinearLayout llMECunru;
    @BindView(R.id.ll_my_earnings_help)
    LinearLayout llMyEarningsHelp;
    @BindView(R.id.tv_my_earnings_guize)
    TextView tvMyEarningsGuize;
    private Dialog dialog;
    private View inflate;
    private Button mDetermine;
    public void onSupportVisible() {
        mTvTitle.setText("钱包");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_my_earnings, null));
    }


    @OnClick({R.id.ll_m_e_cunru, R.id.bt_my_earnings_top_up, R.id.tv_my_earnings_guize,R.id.bt_my_earnings_extract, R.id.tv_my_earnings_shouyi, R.id.ll_my_earnings_help})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_my_earnings_guize:
               Mydialog();
                //充值规则
                break;
            case R.id.bt_my_earnings_top_up:
//                Intent intent = new Intent(mActivity, RechargeActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
                start(new MyTongBiFragment());
                //充值
                break;
            case R.id.bt_my_earnings_extract:
//                Intent intent1 = new Intent(mActivity, GetDepositActivity.class);
//                startActivityForResult(intent1, REQUEST_CODE);
                start(new VerifyTheMobilePhonefragment());
                //提现
                break;
//            case R.id.tv_my_earnings_record:
//                //券包
////                start(new BalanceOfPaymentsFragment());
//                break;
            case R.id.tv_my_earnings_shouyi:
                //收益记录
                start(new ExpenseCalendarFragment());
                break;
            case R.id.ll_m_e_cunru:
                start(new BiDouFragment());
                break;
            case R.id.ll_my_earnings_help:
                start(new HelpFragment());
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    public void Mydialog() {
        dialog = new Dialog(mActivity, R.style.bottom_menu_style);
        //填充对话框的布局
        inflate = LayoutInflater.from(mActivity).inflate(R.layout.tixianguize, null);
        //初始化控件
        mDetermine = (Button) inflate.findViewById(R.id.bt_tixian_queding);
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
