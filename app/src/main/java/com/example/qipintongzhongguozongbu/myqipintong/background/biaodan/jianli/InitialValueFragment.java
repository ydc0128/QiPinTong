package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by L on 2017/2/23.
 * j基础资料编辑
 */
public class InitialValueFragment extends BaseFragment {

    @BindView(R.id.iv_div_open_close)
    ImageView ivDivOpenClose;
    @BindView(R.id.rl_my_data_initial_value_xingbi)
    RelativeLayout rlMyDataInitialValueXingbi;
    @BindView(R.id.tv_my_data_initial_value_xueli)
    TextView tvMyDataInitialValueXueli;
    @BindView(R.id.rl_my_data_initial_value_xueli)
    RelativeLayout rlMyDataInitialValueXueli;
    @BindView(R.id.tv_compnyetails_birthday)
    TextView tvCompnyetailsBirthday;
    @BindView(R.id.rl_compnyetails_birthday)
    RelativeLayout rlCompnyetailsBirthday;
    @BindView(R.id.ll_compnyetails_gongzuo)
    LinearLayout llCompnyetailsGongzuo;
    @BindView(R.id.tv_my_data_initial_value_city)
    TextView tvMyDataInitialValueCity;
    @BindView(R.id.rl_my_data_initial_value_city)
    RelativeLayout rlMyDataInitialValueCity;
//    @BindView(R.id.ll_compnyetails_Email)
//    LinearLayout llCompnyetailsEmail;
    @BindView(R.id.rl_my_data_initial_value_save)
    Button rlMyDataInitialValueSave;
    @BindView(R.id.rf_my_data_initial)
    BGARefreshLayout rfMyDataInitial;
    private boolean isOpen;

    Unbinder unbinder;
    private void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.dialog_ll_my_data_initial_value, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("基础资料");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

//    @OnClick({R.id.ed_ll_my_data_initial_value_touyin, R.id.ed_ll_my_data_initial_value_name, R.id.ll_my_data_initial_value_rb_man, R.id.ll_my_data_initial_value_rb_woman, R.id.ed_ll_my_data_initial_value_birthday, R.id.ed_ll_my_data_initial_value_mobile_phone, R.id.ed_ll_my_data_initial_value_email, R.id.ed_ll_my_data_initial_value_site, R.id.ed_ll_my_data_initial_value, R.id.ed_my_data_initial_value_signature, R.id.rl_my_data_initial_value_save})
//    public void onClick(View view) {
//        switch (view.getId()) {
//
//            case R.id.ed_ll_my_data_initial_value_touyin:
////                头像
//                break;
//            case R.id.ed_ll_my_data_initial_value_name:
////                名字
//                break;
//            case R.id.ll_my_data_initial_value_rb_man:
////                男
//                break;
//            case R.id.ll_my_data_initial_value_rb_woman:
////                nv
//                break;
//            case R.id.ed_ll_my_data_initial_value_birthday:
//                final DatePicker picker = new DatePicker(mActivity);
//                picker.setTopPadding(2);
//                picker.setRangeStart(1950, 8, 29);
//                picker.setRangeEnd(2050, 1, 11);
//                picker.setSelectedItem(2016, 10, 14);
//
//                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
//                    @Override
//                    public void onDatePicked(String year, String month, String day) {
//                        String y = picker.getSelectedYear();
//                        String m = picker.getSelectedMonth();
//                        String d = picker.getSelectedDay();
//                        String ccc = y + "-" + m + "-" + d;
//                        edLlMyDataInitialValueBirthday.setText(ccc);
//                        showToast(year + "-" + month + "-" + day);
//                    }
//                });
//                picker.setOnWheelListener(new DatePicker.OnWheelListener() {
//                    @Override
//                    public void onYearWheeled(int index, String year) {
//                        picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
//                    }
//
//                    @Override
//                    public void onMonthWheeled(int index, String month) {
//                        picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
//                    }
//
//                    @Override
//                    public void onDayWheeled(int index, String day) {
//                        picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
//                    }
//                });
//                picker.show();
////                生日
//                break;
//            case R.id.ed_ll_my_data_initial_value_mobile_phone:
////                手机号
//                break;
//            case R.id.ed_ll_my_data_initial_value_email:
////                邮箱
//                break;
//            case R.id.ed_ll_my_data_initial_value_site:
//                ArrayList<Province> data = new ArrayList<>();
//                String json = null;
//                try {
//                    json = ConvertUtils.toString(getContext().getAssets().open("city.json"));
//                } catch (IOException e2) {
//                    e2.printStackTrace();
//                }
//                data.addAll(JSON.parseArray(json, Province.class));
//                final AddressPicker site = new AddressPicker(mActivity, data);
//                site.setSelectedItem("陕西", "西安", "雁塔");
//                site.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
//                    @Override
//                    public void onAddressPicked(Province province, City city, County county) {
//                        String y = site.getSelectedFirstItem().toString();
//                        String m = site.getSelectedSecondItem().toString();
//                        String d = site.getSelectedThirdItem().toString();
//                        String s = y + "-" + m + "-" + d;
//                        edLlMyDataInitialValueSite.setText(s);
////
////                        showToast(province + "省" + city + "市" + county + "区");
//                    }
//                });
//                site.show();
////                地区
//                break;
//            case R.id.ed_ll_my_data_initial_value:
////                详细地址
//                break;
//            case R.id.ed_my_data_initial_value_signature:
////                签名
//                break;
//            case R.id.rl_my_data_initial_value_save:
////                保存
//                break;
//        }
//    }
//
//    public void onDestroyView() {
//        mTop.setBackgroundColor(Color.parseColor("#ffffff"));
//        super.onDestroyView();
//    }


    @OnClick({R.id.iv_div_open_close, R.id.rl_my_data_initial_value_xingbi, R.id.rl_my_data_initial_value_xueli, R.id.rl_compnyetails_birthday, R.id.ll_compnyetails_gongzuo, R.id.rl_my_data_initial_value_city,  R.id.rl_my_data_initial_value_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_my_data_initial_value_xingbi:
                break;
            case R.id.rl_my_data_initial_value_xueli:
                final OptionPicker picker4 = new OptionPicker(mActivity, new String[]{
                        "初中", "高中", "大专", "本科", "硕士", "博士", "其他"
                });
                picker4.setCycleDisable(true);//不禁用循环
                picker4.setLineVisible(false);//可见行
                picker4.setTextSize(16);
                picker4.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker4.getSelectedItem().toString();
                        tvMyDataInitialValueXueli.setText(s);
                    }
                });
                picker4.show();

                break;
            case R.id.rl_compnyetails_birthday:
                final DatePicker picker = new DatePicker(mActivity);
                picker.setTopPadding(2);
                picker.setRangeStart(1950, 8, 29);
                picker.setRangeEnd(2050, 1, 11);
                picker.setSelectedItem(2016, 10, 14);

                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        String y = picker.getSelectedYear();
                        String m = picker.getSelectedMonth();
                        String d = picker.getSelectedDay();
                        String ccc = y + "-" + m + "-" + d;
                        tvCompnyetailsBirthday.setText(ccc);
                        showToast(year + "-" + month + "-" + day);
                    }
                });
                picker.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {
                        picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
                    }
                });
                picker.show();
//                生日
                break;
            case R.id.ll_compnyetails_gongzuo:
                break;
            case R.id.rl_my_data_initial_value_city:
                ArrayList<Province> data = new ArrayList<>();
                String json = null;
                try {
                    json = ConvertUtils.toString(getContext().getAssets().open("city.json"));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                data.addAll(JSON.parseArray(json, Province.class));
                final AddressPicker site = new AddressPicker(mActivity, data);
                site.setSelectedItem("陕西", "西安", "雁塔");
                site.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        String y = site.getSelectedFirstItem().toString();
                        String m = site.getSelectedSecondItem().toString();
                        String d = site.getSelectedThirdItem().toString();
                        String s = y + "-" + m + "-" + d;
                        tvMyDataInitialValueCity.setText(s);
//
//                        showToast(province + "省" + city + "市" + county + "区");
                    }
                });
                site.show();
//                地区
                break;
//            case R.id.ll_compnyetails_Email:
//                break;
            case R.id.rl_my_data_initial_value_save:
                break;
            case R.id.iv_div_open_close:
                if (isOpen) {
                    ivDivOpenClose.setImageResource(R.mipmap.nan_open);
                } else {
                    ivDivOpenClose.setImageResource(R.mipmap.close);
                }
                isOpen = !isOpen;
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
