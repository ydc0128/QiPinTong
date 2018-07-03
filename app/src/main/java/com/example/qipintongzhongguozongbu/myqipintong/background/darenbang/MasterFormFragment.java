package com.example.qipintongzhongguozongbu.myqipintong.background.darenbang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.index.LocationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * 注释：达人榜激活表单
 * 作者：碧血染银枪 on 2017/6/2 14:10
 * 邮箱：ydc_0128@163.com
 */

public class MasterFormFragment extends BaseFragment {
    @BindView(R.id.rl_master_foem_quyu)
    RelativeLayout rlMasterFoemQuyu;
    @BindView(R.id.rl_master_foem_shuliang)
    RelativeLayout rlMasterFoemShuliang;
    @BindView(R.id.rl_master_foem_danjia)
    RelativeLayout rlMasterFoemDanjia;
    @BindView(R.id.rl_master_foem_xingbei)
    RelativeLayout rlMasterFoemXingbei;
    @BindView(R.id.rl_master_foem_nianling)
    RelativeLayout rlMasterFoemNianling;
    @BindView(R.id.bt_master_form)
    Button btMasterForm;
    @BindView(R.id.tv_master_foem_quyu)
    TextView tvMasterFoemQuyu;
    @BindView(R.id.tv_master_foem_shuliang)
    TextView tvMasterFoemShuliang;
    @BindView(R.id.tv_master_foem_danjia)
    TextView tvMasterFoemDanjia;
    @BindView(R.id.tv_master_foem_xingbei)
    TextView tvMasterFoemXingbei;
    @BindView(R.id.tv_master_foem_nianling)
    TextView tvMasterFoemNianling;
    Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_master_form, null));
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

    @OnClick({R.id.rl_master_foem_quyu, R.id.rl_master_foem_shuliang, R.id.rl_master_foem_danjia, R.id.rl_master_foem_xingbei, R.id.rl_master_foem_nianling, R.id.bt_master_form})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_master_foem_quyu:
               start(new LocationFragment());
                break;
            case R.id.rl_master_foem_shuliang:
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle("请输入数量").setIcon(android.R.drawable.ic_dialog_info)
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        tvMasterFoemShuliang.getText().toString();
                    }
                });
                builder.show();
                break;
            case R.id.rl_master_foem_danjia:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(mActivity);
                builder1.setTitle("请输入单价").setIcon(android.R.drawable.ic_dialog_info)
                        .setNegativeButton("取消", null);
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        tvMasterFoemDanjia.getText().toString();
                    }
                });
                builder1.show();
//                请输入单价
                break;
            case R.id.rl_master_foem_xingbei:
                final OptionPicker picker3 = new OptionPicker(mActivity, new String[]{
                        "男", "女"
                });
                picker3.setCycleDisable(true);//不禁用循环
                picker3.setLineVisible(false);//可见行
                picker3.setTextSize(16);
                picker3.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker3.getSelectedItem().toString();
                        tvMasterFoemXingbei.setText(s);
                    }
                });
                picker3.show();
//                性别
                break;
            case R.id.rl_master_foem_nianling:
                final OptionPicker picker = new OptionPicker(mActivity, new String[]{
                        "10-18", "18-25","25-35","35-45"
                });
                picker.setCycleDisable(true);//不禁用循环
                picker.setLineVisible(false);//可见行
                picker.setTextSize(16);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker.getSelectedItem().toString();
                        tvMasterFoemNianling.setText(s);
                    }
                });
                picker.show();
//                选择年龄
                break;
            case R.id.bt_master_form:

//                确定
                break;
        }
    }
}
