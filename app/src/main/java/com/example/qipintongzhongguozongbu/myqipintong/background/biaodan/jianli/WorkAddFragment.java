package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.twofold.ValuePickerMockActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by L on 2017/2/27.
 * 工作经验
 */

public class WorkAddFragment extends BaseFragment {


    @BindView(R.id.tv_wa_hangye)
    TextView tvWaHangye;
    @BindView(R.id.rl_my_data_work_add_industry)
    RelativeLayout rlMyDataWorkAddIndustry;
    @BindView(R.id.tv_wa_qiye)
    EditText tvWaQiye;
    @BindView(R.id.rl_my_data_work_add_firm)
    LinearLayout rlMyDataWorkAddFirm;
    @BindView(R.id.tv_wa_zhiwei)
    TextView tvWaZhiwei;
    @BindView(R.id.rl_my_data_work_add_job_category)
    RelativeLayout rlMyDataWorkAddJobCategory;
    @BindView(R.id.tv_wa_zhiweimingcheng)
    EditText tvWaZhiweimingcheng;
    @BindView(R.id.rl_my_data_work_add_job_name)
    LinearLayout rlMyDataWorkAddJobName;
    @BindView(R.id.et_my_data_work_add_work_open)
    TextView etMyDataWorkAddWorkOpen;
    @BindView(R.id.et_my_data_work_add_work_closs)
    TextView etMyDataWorkAddWorkCloss;
    @BindView(R.id.tv_wa_yuexin)
    TextView tvWaYuexin;
    @BindView(R.id.rl_my_data_work_add_money)
    RelativeLayout rlMyDataWorkAddMoney;
    @BindView(R.id.tv_wa_haiwaijingyan)
    EditText tvWaHaiwaijingyan;
    @BindView(R.id.rl_my_data_work_add_overseas)
    LinearLayout rlMyDataWorkAddOverseas;
    @BindView(R.id.tv_my_data_work_add_enterprise_property)
    TextView tvMyDataWorkAddEnterpriseProperty;
    @BindView(R.id.rl_my_data_work_add_enterprise_property)
    RelativeLayout rlMyDataWorkAddEnterpriseProperty;
    @BindView(R.id.tv_my_data_work_add_staff_size)
    TextView tvMyDataWorkAddStaffSize;
    @BindView(R.id.rl_my_data_work_add_staff_size)
    RelativeLayout rlMyDataWorkAddStaffSize;
    @BindView(R.id.ed_wa_gongzuomiaoshu)
    EditText edWaGongzuomiaoshu;
    @BindView(R.id.rl_my_data_work_add_job_description)
    LinearLayout rlMyDataWorkAddJobDescription;
    @BindView(R.id.rl_my_data_work_add_add)
    Button rlMyDataWorkAddAdd;
    @BindView(R.id.rf_my_data_work)
    BGARefreshLayout rfMyDataWork;
    Unbinder unbinder;
    private String leftValue;
    private String rightValue;

    private void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    //    public static String getTime(Date date){
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  //如果里面需要小时和分钟就带上HH：mm不需要就不用写
//        return format.format(date);
//    }
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.dailog_rl_my_data_work_add, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("工作经历");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


//    private RyfExpectTheIndustrySmallAdapter.OnItemClickListener mListener = new RyfExpectTheIndustryBigAdapter.OnItemClickListener() {
//        @Override
//        public void onItemClick(View view, int position) {
//            tvWaHangye.setText(s);
//        }

    @OnClick({R.id.rl_my_data_work_add_industry, R.id.rl_my_data_work_add_enterprise_property, R.id.rl_my_data_work_add_staff_size, R.id.rl_my_data_work_add_firm, R.id.rl_my_data_work_add_job_category, R.id.rl_my_data_work_add_job_name, R.id.et_my_data_work_add_work_open, R.id.et_my_data_work_add_work_closs, R.id.tv_wa_yuexin, R.id.rl_my_data_work_add_money, R.id.rl_my_data_work_add_overseas, R.id.rl_my_data_work_add_job_description, R.id.rl_my_data_work_add_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_my_data_work_add_industry:
//                start(new IndustryClassiffcationFragment());
                Intent intent = new Intent(mActivity, ValuePickerMockActivity.class);
                intent.putExtra(ValuePickerMockActivity.SELECTED_LEFT, leftValue);
                intent.putExtra(ValuePickerMockActivity.SELECTED_RIGHT, rightValue);
                startActivityForResult(intent, 1);
//                行业类别
                break;
            case R.id.rl_my_data_work_add_firm:
                break;
            case R.id.rl_my_data_work_add_job_category:
//                start(new ExpectTheIndustryFragment());
                Intent intent1 = new Intent(mActivity, ValuePickerMockActivity.class);
                intent1.putExtra(ValuePickerMockActivity.SELECTED_LEFT, leftValue);
                intent1.putExtra(ValuePickerMockActivity.SELECTED_RIGHT, rightValue);
                startActivityForResult(intent1, 1);
//                职位类别
                break;
            case R.id.rl_my_data_work_add_job_name:
                break;
            case R.id.et_my_data_work_add_work_open:
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
                        etMyDataWorkAddWorkOpen.setText(ccc);
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
//                开始时间
                break;
            case R.id.et_my_data_work_add_work_closs:
                final DatePicker picker1 = new DatePicker(mActivity);
                picker1.setTopPadding(2);
                picker1.setRangeStart(1950, 8, 29);
                picker1.setRangeEnd(2050, 1, 11);
                picker1.setSelectedItem(2017, 2, 14);

                picker1.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
                    @Override
                    public void onDatePicked(String year, String month, String day) {
                        String y = picker1.getSelectedYear();
                        String m = picker1.getSelectedMonth();
                        String d = picker1.getSelectedDay();
                        String ccc = y + "-" + m + "-" + d;
                        etMyDataWorkAddWorkCloss.setText(ccc);
//                        showToast(year + "-" + month + "-" + day);
                    }
                });
                picker1.setOnWheelListener(new DatePicker.OnWheelListener() {
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
                });
                picker1.show();
//                离职时间
                break;
            case R.id.tv_wa_yuexin:

                break;
            case R.id.rl_my_data_work_add_money:
                final OptionPicker picker3 = new OptionPicker(mActivity, new String[]{
                        "1-2k", "2-4k", "4-6k", "6-8k", "8-10k", "10-12k", "12-15k"
                });
                picker3.setCycleDisable(true);//不禁用循环
                picker3.setLineVisible(false);//可见行
                picker3.setTextSize(16);
                picker3.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker3.getSelectedItem().toString();
                        tvWaYuexin.setText(s);
                    }
                });
                picker3.show();
//                月薪
                break;
            case R.id.rl_my_data_work_add_overseas:
//                 海外经验
                break;
            case R.id.rl_my_data_work_add_enterprise_property:
                final OptionPicker picker4 = new OptionPicker(mActivity, new String[]{
                        "事业单位", "国企", "外资", "合资", "代表处", "民营", "股份制", "上市公司", "国家机关", "其他"
                });
                picker4.setCycleDisable(true);//不禁用循环
                picker4.setLineVisible(false);//可见行
                picker4.setTextSize(16);
                picker4.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker4.getSelectedItem().toString();
                        tvMyDataWorkAddEnterpriseProperty.setText(s);
                    }
                });
                picker4.show();
//              公司性质
                break;
            case R.id.rl_my_data_work_add_staff_size:
                final OptionPicker picker5 = new OptionPicker(mActivity, new String[]{
                        "20人一下", "20-99人", "99-499人", "500-999人", "1000人以上"
                });
                picker5.setCycleDisable(true);//不禁用循环
                picker5.setLineVisible(false);//可见行
                picker5.setTextSize(16);
                picker5.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker5.getSelectedItem().toString();
                        tvMyDataWorkAddStaffSize.setText(s);
                    }
                });
                picker5.show();
//                人员规模
                break;
            case R.id.rl_my_data_work_add_job_description:
                break;
            case R.id.rl_my_data_work_add_add:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == requestCode) {
            if (RESULT_OK == resultCode) {
                //选择车牌
                leftValue = data.getStringExtra(ValuePickerMockActivity.SELECTED_LEFT);
                rightValue = data.getStringExtra(ValuePickerMockActivity.SELECTED_RIGHT);
                tvWaHangye.setText(leftValue + " - " + rightValue);
                tvWaZhiwei.setText(leftValue + " - " + rightValue);
            }
        }
    }
}

