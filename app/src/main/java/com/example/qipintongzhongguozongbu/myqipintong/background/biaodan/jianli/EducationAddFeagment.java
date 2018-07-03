package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import butterknife.Unbinder;
/**
 * Created by L on 2017/2/23.
 * 学习经历
 */

public class EducationAddFeagment extends BaseFragment {


    @BindView(R.id.rl_my_data_education_add_school)
    LinearLayout rlMyDataEducationAddSchool;
    @BindView(R.id.et_education_add_major)
    EditText etEducationAddMajor;
    @BindView(R.id.rl_my_data_education_add_major)
    LinearLayout rlMyDataEducationAddMajor;
    @BindView(R.id.bt_education_rb_yes)
    RadioButton btEducationRbYes;
    @BindView(R.id.bt_education_rb_no)
    RadioButton btEducationRbNo;
    @BindView(R.id.et_my_data_education_open)
    TextView etMyDataEducationOpen;
    @BindView(R.id.et_my_data_education_close)
    TextView etMyDataEducationClose;
    @BindView(R.id.et_education_add_degree)
    EditText etEducationAddDegree;
    @BindView(R.id.rl_my_data_education_add_degree)
    LinearLayout rlMyDataEducationAddDegree;
    @BindView(R.id.et_education_add_experience)
    EditText etEducationAddExperience;
    @BindView(R.id.rl_my_data_education_add_experience)
    LinearLayout rlMyDataEducationAddExperience;
    @BindView(R.id.rl_my_data_education_add_add)
    Button rlMyDataEducationAddAdd;
    Unbinder unbinder;
    private void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    public static EducationAddFeagment getInstance() {
        return new EducationAddFeagment();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.dialog_rl_my_data_education_add, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder= ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("学习经历");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    @OnClick({R.id.bt_education_rb_yes, R.id.bt_education_rb_no, R.id.rl_my_data_education_add_school,
            R.id.rl_my_data_education_add_major, R.id.rl_my_data_education_add_degree, R.id.et_my_data_education_close, R.id.et_my_data_education_open,
            R.id.rl_my_data_education_add_experience, R.id.rl_my_data_education_add_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_my_data_education_add_school:
                final EditText school = new EditText(view.getContext());
                new AlertDialog.Builder(mActivity).setTitle("学校")
                        .setView(school)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                school.getText().toString();
                            }
                        }).
                        setNegativeButton("取消", null).

                        show();
//                学校
                break;
            case R.id.rl_my_data_education_add_major:
                final EditText major = new EditText(view.getContext());
                new AlertDialog.Builder(mActivity).setTitle("专业")
                        .setView(major)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                major.getText().toString();
                            }
                        }).
                        setNegativeButton("取消", null).

                        show();
//                专业
                break;
            case R.id.bt_education_rb_yes:
//                是统招
                break;
            case R.id.bt_education_rb_no:
//                不是统招
                break;
            case R.id.et_my_data_education_open:
                final DatePicker open = new DatePicker(mActivity);
                open.setTopPadding(2);
                open.setRangeStart(1950, 8, 29);
                open.setRangeEnd(2050, 1, 11);
                open.setSelectedItem(2017, 2, 14);
                open.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        String y = open.getSelectedYear();
                        String m = open.getSelectedMonth();
                        String d = open.getSelectedDay();
                        String ccc = y + "-" + m + "-" + d;
                        etMyDataEducationOpen.setText(ccc);
                        showToast(year + "-" + month + "-" + day);
                    }
                });
                open.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        open.setTitleText(year + "-" + open.getSelectedMonth() + "-" + open.getSelectedDay());
                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        open.setTitleText(open.getSelectedYear() + "-" + month + "-" + open.getSelectedDay());
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {
                        open.setTitleText(open.getSelectedYear() + "-" + open.getSelectedMonth() + "-" + day);
                    }
                });
                open.show();
                break;
            case R.id.et_my_data_education_close:
                final DatePicker closs = new DatePicker(mActivity);
                closs.setTopPadding(2);
                closs.setRangeStart(1950, 8, 29);
                closs.setRangeEnd(2050, 1, 11);
                closs.setSelectedItem(2016, 10, 14);

                closs.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        String y = closs.getSelectedYear();
                        String m = closs.getSelectedMonth();
                        String d = closs.getSelectedDay();
                        String ccc = y + "-" + m + "-" + d;
                        etMyDataEducationClose.setText(ccc);
                        showToast(year + "-" + month + "-" + day);
                    }
                });
                closs.setOnWheelListener(new DatePicker.OnWheelListener() {
                    @Override
                    public void onYearWheeled(int index, String year) {
                        closs.setTitleText(year + "-" + closs.getSelectedMonth() + "-" + closs.getSelectedDay());
                    }

                    @Override
                    public void onMonthWheeled(int index, String month) {
                        closs.setTitleText(closs.getSelectedYear() + "-" + month + "-" + closs.getSelectedDay());
                    }

                    @Override
                    public void onDayWheeled(int index, String day) {
                        closs.setTitleText(closs.getSelectedYear() + "-" + closs.getSelectedMonth() + "-" + day);
                    }
                });
                closs.show();
                break;
            case R.id.rl_my_data_education_add_degree:
//学位
                final EditText degree = new EditText(view.getContext());
                new AlertDialog.Builder(mActivity).setTitle("学位")
                        .setView(degree)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                String str = degree.getText().toString();
                            }
                        }).
                        setNegativeButton("取消", null).

                        show();
                break;
            case R.id.rl_my_data_education_add_experience:
                final EditText e2 = new EditText(view.getContext());
                new AlertDialog.Builder(mActivity).setTitle("证书")
                        .setView(e2)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int i) {
                                String str = e2.getText().toString();
                            }
                        }).
                        setNegativeButton("取消", null).

                        show();
//                证书
                break;
            case R.id.rl_my_data_education_add_add:
//                添加
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
