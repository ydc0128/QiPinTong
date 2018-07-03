package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by L on 2017/2/23.
 * 培训经历
 */

public class TrainAddFragment extends BaseFragment {


    @BindView(R.id.et_train_add_school)
    EditText etTrainAddSchool;
    @BindView(R.id.rl_my_data_train_add_school)
    LinearLayout rlMyDataTrainAddSchool;
    @BindView(R.id.et_train_add_major)
    EditText etTrainAddMajor;
    @BindView(R.id.rl_my_data_train_add_major)
    LinearLayout rlMyDataTrainAddMajor;
    @BindView(R.id.et_train_add_site)
    TextView etTrainAddSite;
    @BindView(R.id.rl_my_data_train_add_site)
    LinearLayout rlMyDataTrainAddSite;
    @BindView(R.id.et_my_data_train_add_open)
    TextView etMyDataTrainAddOpen;
    @BindView(R.id.et_my_data_train_add_close)
    TextView etMyDataTrainAddClose;
    @BindView(R.id.rl_my_data_train_add_time)
    LinearLayout rlMyDataTrainAddTime;
    @BindView(R.id.rl_my_data_add_degree)
    LinearLayout rlMyDataAddDegree;
    @BindView(R.id.rl_my_data_train_add_add)
    Button rlMyDataTrainAddAdd;
    @BindView(R.id.rl_my_data_train_add_experience)
    LinearLayout rlMyDataTrainAddExperience;
Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.dialog_ll_my_data_train_add, null));
    }

    private void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
       unbinder= ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("培训经历");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }


    @OnClick({R.id.rl_my_data_train_add_school, R.id.rl_my_data_train_add_major, R.id.et_train_add_site, R.id.et_my_data_train_add_open, R.id.et_my_data_train_add_close, R.id.rl_my_data_train_add_time, R.id.rl_my_data_add_degree, R.id.rl_my_data_train_add_add})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.rl_my_data_train_add_school:
                break;
            case R.id.rl_my_data_train_add_major:
                break;
            case R.id.et_train_add_site:
                final ArrayList<Province> site = new ArrayList<>();
                String json = null;
                try {
                    json = ConvertUtils.toString(getContext().getAssets().open("city.json"));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                site.addAll(JSON.parseArray(json, Province.class));
                final AddressPicker picker = new AddressPicker(mActivity, site);
                picker.setSelectedItem("陕西", "西安", "雁塔");
                picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        String y = picker.getSelectedFirstItem().toString();
                        String m = picker.getSelectedSecondItem().toString();
                        String d = picker.getSelectedThirdItem().toString();

                        String s = y + "-" + m + "-" + d;
                        etTrainAddSite.setText(s);
//                showToast(province + "省" + city + "市" + county + "区");
                    }
                });
                picker.show();

                break;
            case R.id.et_my_data_train_add_open:
                final DatePicker picker1 = new DatePicker(mActivity);
                picker1.setTopPadding(2);
                picker1.setRangeStart(2016, 8, 29);
                picker1.setRangeEnd(2111, 1, 11);
                picker1.setSelectedItem(2017, 2, 14);

                picker1.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener()

                                              {
                                                  @Override
                                                  public void onDatePicked(String year, String month, String day) {
                                                      String y = picker1.getSelectedYear();
                                                      String m = picker1.getSelectedMonth();
                                                      String d = picker1.getSelectedDay();
                                                      String ccc = y + "-" + m + "-" + d;
                                                      etMyDataTrainAddOpen.setText(ccc);
                                                  }
                                              }

                );
                picker1.setOnWheelListener(new DatePicker.OnWheelListener()

                                           {
                                               @Override
                                               public void onYearWheeled(int index, String year) {
                                                   picker1.setTitleText(year + "-" + picker1.getSelectedMonth() + "-" + picker1.getSelectedDay());
                                               }

                                               @Override
                                               public void onMonthWheeled(int index, String month) {
                                                   picker1.setTitleText(picker1.getSelectedYear() + "-" + month + "-" + picker1.getSelectedDay());
                                               }

                                               @Override
                                               public void onDayWheeled(int index, String day) {
                                                   picker1.setTitleText(picker1.getSelectedYear() + "-" + picker1.getSelectedMonth() + "-" + day);
                                               }
                                           }
                );
                picker1.show();

                break;
            case R.id.et_my_data_train_add_close:
                final DatePicker picker2 = new DatePicker(mActivity);
                picker2.setTopPadding(2);
                picker2.setRangeStart(2016, 8, 29);
                picker2.setRangeEnd(2111, 1, 11);
                picker2.setSelectedItem(2017, 2, 14);
                picker2.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener()

                                              {
                                                  @Override
                                                  public void onDatePicked(String year, String month, String day) {
//
                                                      String y = picker2.getSelectedYear();
                                                      String m = picker2.getSelectedMonth();
                                                      String d = picker2.getSelectedDay();
                                                      String ccc = y + "-" + m + "-" + d;
                                                      etMyDataTrainAddClose.setText(ccc);
                                                  }
                                              }

                );
                picker2.setOnWheelListener(new DatePicker.OnWheelListener()

                                           {
                                               @Override
                                               public void onYearWheeled(int index, String year) {
                                                   picker2.setTitleText(year + "-" + picker2.getSelectedMonth() + "-" + picker2.getSelectedDay());
                                               }

                                               @Override
                                               public void onMonthWheeled(int index, String month) {
                                                   picker2.setTitleText(picker2.getSelectedYear() + "-" + month + "-" + picker2.getSelectedDay());
                                               }

                                               @Override
                                               public void onDayWheeled(int index, String day) {
                                                   picker2.setTitleText(picker2.getSelectedYear() + "-" + picker2.getSelectedMonth() + "-" + day);
                                               }
                                           }
                );
                picker2.show();
                break;
            case R.id.rl_my_data_add_degree:
                break;
            case R.id.rl_my_data_train_add_add:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}


