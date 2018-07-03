package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.youcaiyoumao;

import android.content.Intent;
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

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.PhotoActivity;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by L on 2017/2/23.
 * 基础资料__有才有貌
 */
public class DataDaseFragment extends BaseFragment {


    @BindView(R.id.ll_my_data_dase_name)
    LinearLayout llMyDataDaseName;
    @BindView(R.id.iv_mdd_open_close)
    ImageView ivMddOpenClose;
    @BindView(R.id.ll_my_data_dase_gender)
    RelativeLayout llMyDataDaseGender;
    @BindView(R.id.ll_my_data_dase_birthday)
    LinearLayout llMyDataDaseBirthday;
    @BindView(R.id.tv_mdd_tixing)
    TextView tvMddTixing;
    @BindView(R.id.ll_my_data_dase_mobile)
    LinearLayout llMyDataDaseMobile;
    @BindView(R.id.tv_mdd_shengri)
    TextView tvMddShengri;
    @BindView(R.id.ll_my_data_dase_email)
    LinearLayout llMyDataDaseEmail;
    @BindView(R.id.ll_my_data_dase_area)
    LinearLayout llMyDataDaseArea;
    @BindView(R.id.rv_mdd_photo)
    ImageView rvMddPhoto;
    @BindView(R.id.bt_my_data_dase_save)
    Button btMyDataDaseSave;
    @BindView(R.id.rf_my)
    LinearLayout rfMy;
    @BindView(R.id.tv_mdd_hunyin)
    TextView tvMddHunyin;
    private boolean isOpen;
    Unbinder unbinder;
    public static DataDaseFragment getInstance() {
        return new DataDaseFragment();
    }
    private void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.rl_my_data_dase, null));
    }


    public void onSupportVisible() {
        mTvTitle.setText("基础资料");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


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

    @OnClick({R.id.ll_my_data_dase_name, R.id.ll_my_data_dase_gender, R.id.ll_my_data_dase_birthday, R.id.ll_my_data_dase_mobile, R.id.ll_my_data_dase_email, R.id.ll_my_data_dase_area, R.id.rv_mdd_photo, R.id.bt_my_data_dase_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_data_dase_name:
//                名字
                break;
            case R.id.ll_my_data_dase_gender:
                if (isOpen) {
                    ivMddOpenClose.setImageResource(R.mipmap.nan_open);
                } else {
                    ivMddOpenClose.setImageResource(R.mipmap.close);
                }
                isOpen = !isOpen;
                break;
            case R.id.ll_my_data_dase_birthday:
//                身高
                break;
            case R.id.ll_my_data_dase_mobile:
                final OptionPicker picker4 = new OptionPicker(mActivity, new String[]{
                        "偏胖", "正常", "偏瘦"
                });
                picker4.setCycleDisable(true);//不禁用循环
                picker4.setLineVisible(false);//可见行
                picker4.setTextSize(16);
                picker4.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker4.getSelectedItem().toString();
                        tvMddTixing.setText(s);
                    }
                });
                picker4.show();


//体型
                break;
            case R.id.ll_my_data_dase_email:
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
                        tvMddShengri.setText(ccc);
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
//出生年月
                break;
            case R.id.ll_my_data_dase_area:
                final OptionPicker picker1 = new OptionPicker(mActivity, new String[]{
                        "离异", "未婚"
                });
                picker1.setCycleDisable(true);//不禁用循环
                picker1.setLineVisible(false);//可见行
                picker1.setTextSize(16);
                picker1.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker1.getSelectedItem().toString();
                        tvMddHunyin.setText(s);
                    }
                });
                picker1.show();
//                婚姻状况
                break;
            case R.id.rv_mdd_photo:
                startActivity(new Intent(getActivity(), PhotoActivity.class));
//                添加照片
                break;
            case R.id.bt_my_data_dase_save:
//               保存
                break;
        }
    }
}
