package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by L on 2017/3/8.
 * 创投天下——发布项目--融资历史
 */

public class FinancingHistoryFragment extends BaseFragment {
    @BindView(R.id.tv_fh_time)
    TextView tvFhTime;
    @BindView(R.id.ll_fh_time)
    LinearLayout llFhTime;
    @BindView(R.id.tv_fh_turn)
    TextView tvFhTurn;
    @BindView(R.id.ll_fh_turn)
    LinearLayout llFhTurn;
    @BindView(R.id.tv_fh_imit)
    TextView tvFhImit;
    @BindView(R.id.ll_fh_imit)
    LinearLayout llFhImit;
    @BindView(R.id.tv_fh_organization)
    TextView tvFhOrganization;
    @BindView(R.id.ll_fh_organization)
    LinearLayout llFhOrganization;
    @BindView(R.id.bt_fh_add)
    Button btFhAdd;

    Unbinder unbinder;
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_financing_history, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("融资历史");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.ll_fh_time, R.id.ll_fh_turn, R.id.ll_fh_imit, R.id.ll_fh_organization, R.id.bt_fh_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_fh_time:
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
                                                      tvFhImit.setText(ccc);
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
            case R.id.ll_fh_turn:
//                start(new RoundsFragment());
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
                        tvFhTurn.setText(s);
                    }
                });
                picker2.show();
                break;
            case R.id.ll_fh_imit:
                break;
            case R.id.ll_fh_organization:
//                start(new FieldFragment());
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
                        tvFhOrganization.setText(s);
                    }
                });
                picker.show();
                break;
            case R.id.bt_fh_add:
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
