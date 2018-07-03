package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.youcaiyoumao;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.PhotoActivity;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.BottomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.DialogListViewItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by L on 2017/2/21.
 * 有才有貌我要加入
 */

public class CompanmerceResumeFragment extends BaseFragment implements BottomDialog.OnBottomDialogItemOnClickListener {


    @BindView(R.id.ll_compnyetails_namae)
    LinearLayout llCompnyetailsNamae;
    @BindView(R.id.iv_c_open_close)
    ImageView ivCOpenClose;
    @BindView(R.id.ll_compnyetails_height)
    LinearLayout llCompnyetailsHeight;
    @BindView(R.id.ll_compnyetails_bodily_form)
    TextView llCompnyetailsBodilyForm;
    @BindView(R.id.tv_compnyetails_birthday)
    TextView tvCompnyetailsBirthday;
    @BindView(R.id.ll_compnyetails_marital_status)
    TextView llCompnyetailsMaritalStatus;
    @BindView(R.id.iv_menu_photo)
    ImageView ivMenuPhoto;
    @BindView(R.id.bt_companydetails_save)
    Button btCompanydetailsSave;
    private boolean isOpen;

    Unbinder unbinder;

    private void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_menu_companydetails, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("有才有貌");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.ll_compnyetails_namae, R.id.iv_menu_photo, R.id.iv_c_open_close, R.id.tv_compnyetails_birthday, R.id.ll_compnyetails_height, R.id.ll_compnyetails_bodily_form, R.id.ll_compnyetails_marital_status, R.id.bt_companydetails_save})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ll_compnyetails_namae:
//                final EditText e1 = new EditText(view.getContext());
//                new AlertDialog.Builder(mActivity).setTitle("姓名")
//                        .setView(e1)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int i) {
//                                String str = e1.getText().toString().trim();
//                            }
//
//                        }).
//                        setNegativeButton("取消", null).
//
//                        show();
                //姓名
                break;
            case R.id.tv_compnyetails_birthday:
                //生日
                final DatePicker picker = new DatePicker(mActivity);
                picker.setTopPadding(2);
                picker.setRangeStart(2016, 8, 29);
                picker.setRangeEnd(2111, 1, 11);
                picker.setSelectedItem(2017, 2, 14);

                picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        String y = picker.getSelectedYear();
                        String m = picker.getSelectedMonth();
                        String d = picker.getSelectedDay();
                        String ccc = y + "-" + m + "-" + d;
                        tvCompnyetailsBirthday.setText(ccc);
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
                break;
            case R.id.ll_compnyetails_height:
                break;
            case R.id.ll_compnyetails_bodily_form:

                final OptionPicker bod = new OptionPicker(mActivity, new String[]{
                        "微瘦", "匀称", "微胖"
                });
                bod.setCycleDisable(true);//不禁用循环
                bod.setLineVisible(false);//可见行
                bod.setTextSize(16);
                bod.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = bod.getSelectedItem().toString();
                        llCompnyetailsBodilyForm.setText(s);
                        showToast("item" + item);
                    }
                });
                bod.show();
                //体型
                break;
            case R.id.ll_compnyetails_marital_status:
                final OptionPicker picker3 = new OptionPicker(mActivity, new String[]{
                        "未婚", "已婚", "离异"
                });
                picker3.setCycleDisable(true);
                picker3.setLineVisible(false);
                picker3.setTextSize(16);
                picker3.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker3.getSelectedItem().toString();
                        llCompnyetailsMaritalStatus.setText(s);
                        showToast("item" + item);
                    }
                });
                picker3.show();
                //婚姻状况
                break;
            case R.id.iv_menu_photo:
                Intent intent = new Intent(mActivity, PhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_companydetails_save:
                //保存
                break;
            case R.id.iv_c_open_close:
                if (isOpen) {
                    ivCOpenClose.setImageResource(R.mipmap.nan_open);
                } else {
                    ivCOpenClose.setImageResource(R.mipmap.close);
                }
                isOpen = !isOpen;
        }
    }


    public void onItemClick(DialogListViewItem item, int position) {
        Intent intent = new Intent(mActivity, PhotoActivity.class);
        intent.putExtra("TAG", position);
        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}


