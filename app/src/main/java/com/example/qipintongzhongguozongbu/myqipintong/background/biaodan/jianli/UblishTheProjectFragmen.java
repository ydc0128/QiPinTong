package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia.FinancingHistoryFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.BottomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.DialogListViewItem;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by L on 2017/3/8.
 * 创投天下--我的项目
 */

public class UblishTheProjectFragmen extends BaseFragment implements BottomDialog.OnBottomDialogItemOnClickListener{
    @BindView(R.id.et_utp_zhuti)
    EditText etUtpZhuti;
    @BindView(R.id.ll_utp_zhuti)
    LinearLayout llUtpZhuti;
    @BindView(R.id.tv_utp_lingyu)
    TextView tvUtpLingyu;
    @BindView(R.id.ll_utp_lingyu)
    LinearLayout llUtpLingyu;
    @BindView(R.id.tv_utp_quyu)
    TextView tvUtpQuyu;
    @BindView(R.id.ll_utp_quyu)
    LinearLayout llUtpQuyu;
    @BindView(R.id.tv_utp_lunci)
    TextView tvUtpLunci;
    @BindView(R.id.ll_utp_touzilunci)
    LinearLayout llUtpTouzilunci;
    @BindView(R.id.et_utp_edu)
    EditText etUtpEdu;
    @BindView(R.id.ll_utp_edu)
    LinearLayout llUtpEdu;
    @BindView(R.id.tv_utp_zhuangtai)
    TextView tvUtpZhuangtai;
    @BindView(R.id.ll_utp_zhuangtai)
    LinearLayout llUtpZhuangtai;
    @BindView(R.id.et_utp_biaoqian)
    EditText etUtpBiaoqian;
    @BindView(R.id.ll_utp_biaoqian)
    LinearLayout llUtpBiaoqian;
    @BindView(R.id.et_utp_xiangqing)
    EditText etUtpXiangqing;
    @BindView(R.id.ll_utp_xiangqing)
    LinearLayout llUtpXiangqing;
    @BindView(R.id.bt_utp_baocun)
    Button btUtpBaocun;
    @BindView(R.id.ll_utp_add_rongzi)
    LinearLayout llUtpAddRongzi;
    Unbinder unbinder;

    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_ublish_the_project, null));
    }
//    @Subscribe
//    public void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("发布项目");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick({R.id.ll_utp_zhuti, R.id.ll_utp_add_rongzi, R.id.ll_utp_lingyu, R.id.ll_utp_quyu, R.id.ll_utp_touzilunci, R.id.ll_utp_edu, R.id.ll_utp_zhuangtai, R.id.ll_utp_biaoqian, R.id.ll_utp_xiangqing, R.id.bt_utp_baocun})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_utp_zhuti:
                break;
            case R.id.ll_utp_lingyu:
                final OptionPicker picker = new OptionPicker(mActivity, new String[]{
                        "移动互联网", "金融", "企业服务", "教育", "医疗健康", "旅游"
                });
                picker.setCycleDisable(true);//不禁用循环
                picker.setLineVisible(false);//可见行
                picker.setTextSize(16);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker.getSelectedItem().toString();
                        tvUtpLingyu.setText(s);
                    }
                });
                picker.show();
                break;
            case R.id.ll_utp_quyu:
                final ArrayList<Province> site = new ArrayList<>();
                String json = null;
                try {
                    json = ConvertUtils.toString(getContext().getAssets().open("city.json"));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                site.addAll(JSON.parseArray(json, Province.class));
                final AddressPicker picker1 = new AddressPicker(mActivity, site);
                picker1.setSelectedItem("陕西", "西安", "雁塔");
                picker1.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        String y = picker1.getSelectedFirstItem().toString();
                        String m = picker1.getSelectedSecondItem().toString();
                        String d = picker1.getSelectedThirdItem().toString();

                        String s = y + "-" + m + "-" + d;
                        tvUtpQuyu.setText(s);
//                showToast(province + "省" + city + "市" + county + "区");
                    }
                });
                picker1.show();

                break;
            case R.id.ll_utp_touzilunci:
                final OptionPicker picker2 = new OptionPicker(mActivity, new String[]{
                        "未融资", "已融资", "天使轮", "A轮", "B轮", "C轮","D轮","已上市","不需要融资"
                });
                picker2.setCycleDisable(true);//不禁用循环
                picker2.setLineVisible(false);//可见行
                picker2.setTextSize(16);
                picker2.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker2.getSelectedItem().toString();
                        tvUtpZhuangtai.setText(s);
                    }
                });
                picker2.show();
                break;
            case R.id.ll_utp_edu:
                break;
            case R.id.ll_utp_zhuangtai:
                final OptionPicker picker4 = new OptionPicker(mActivity, new String[]{
                        "未运营", "已运营"
                });
                picker4.setCycleDisable(true);//不禁用循环
                picker4.setLineVisible(false);//可见行
                picker4.setTextSize(16);
                picker4.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker4.getSelectedItem().toString();
                        tvUtpZhuangtai.setText(s);
                    }
                });
                picker4.show();
                break;
            case R.id.ll_utp_biaoqian:
                break;
            case R.id.ll_utp_xiangqing:
                break;
            case R.id.ll_utp_add_rongzi:
                start(new FinancingHistoryFragment());
                break;
            case R.id.bt_utp_baocun:
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(DialogListViewItem item, int position) {

    }
}
