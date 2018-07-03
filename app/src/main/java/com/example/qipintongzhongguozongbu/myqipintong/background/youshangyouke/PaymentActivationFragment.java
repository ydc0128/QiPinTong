package com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.tongbi.MyTongBiFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.QPTPayFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：支付激活
 * 作者：碧血染银枪 on 2017/6/2 10:53
 * 邮箱：ydc_0128@163.com
 */

public class PaymentActivationFragment extends BaseFragment {
    @BindView(R.id.bt_pay_actovation)
    Button btPayActovation;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_pay_activation, null));
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

    @OnClick(R.id.bt_pay_actovation)
    public void onViewClicked() {
        new AlertDialog.Builder(mActivity).setTitle("提示")//设置对话框标题
                .setMessage("您当前账户余额为O，请先充值")//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮

                    @Override

                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        start(new MyTongBiFragment()
                        );

                    }
                }).setNegativeButton("返回", new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件

                // TODO Auto-generated method stub
                start(new QPTPayFragment());
                Log.i("alertdialog", " 请保存数据！");

            }
        }).show();//在按键响应事件中显示此对话框
    }
}

