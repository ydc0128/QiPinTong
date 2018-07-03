package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.liushibuju.WelfareFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.index.EventAll;

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
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.util.ConvertUtils;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by L on 2017/2/24.
 * 发布职位
 */

public class PostAJobFragment extends BaseFragment {


    @BindView(R.id.ll_post_a_job_namae)
    LinearLayout llPostAJobNamae;
    @BindView(R.id.tv_paj_post)
    TextView tvPajPost;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.ll_post_a_job_job_nature)
    RelativeLayout llPostAJobJobNature;
    @BindView(R.id.tv_paj_zhiweileibie)
    TextView tvPajZhiweileibie;
    @BindView(R.id.ll_post_a_job_job_category)
    RelativeLayout llPostAJobJobCategory;
    @BindView(R.id.tv_post_a_job_work_experience)
    TextView tvPostAJobWorkExperience;
    @BindView(R.id.ll_post_a_job_work_experience)
    RelativeLayout llPostAJobWorkExperience;
    @BindView(R.id.tv_post_a_job_education)
    TextView tvPostAJobEducation;
    @BindView(R.id.ll_post_a_job_education)
    RelativeLayout llPostAJobEducation;
    @BindView(R.id.ll_post_a_job_number_of_people)
    LinearLayout llPostAJobNumberOfPeople;
    @BindView(R.id.tv_post_a_job_salary)
    TextView tvPostAJobSalary;
    @BindView(R.id.ll_post_a_job_salary)
    RelativeLayout llPostAJobSalary;
    @BindView(R.id.tv_post_a_job_welfare)
    TextView tvPostAJobWelfare;
    @BindView(R.id.ll_post_a_job_welfare)
    RelativeLayout llPostAJobWelfare;
    @BindView(R.id.tv_post_a_job_city)
    TextView tvPostAJobCity;
    @BindView(R.id.ll_post_a_job_city)
    RelativeLayout llPostAJobCity;
    @BindView(R.id.ll_post_a_job_site)
    LinearLayout llPostAJobSite;
    @BindView(R.id.ll_post_a_job_describe)
    LinearLayout llPostAJobDescribe;
    @BindView(R.id.ll_post_a_job_status)
    LinearLayout llPostAJobStatus;
    @BindView(R.id.rl_post_a_job_add)
    Button rlPostAJobAdd;
    @BindView(R.id.rf_post_a_job)
    BGARefreshLayout rfPostAJob;
Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_post_a_job, null));

    }

    public void initData() {
        super.initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
       unbinder= ButterKnife.bind(this, rootView);
        return rootView;

    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(EventAll message) {
        tvPostAJobWelfare.setText(message.s);
    }

    public void onSupportVisible() {
        mTvTitle.setText("发布职位");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    @OnClick({R.id.ll_post_a_job_namae, R.id.ll_post_a_job_job_nature, R.id.ll_post_a_job_job_category, R.id.ll_post_a_job_work_experience, R.id.ll_post_a_job_education, R.id.ll_post_a_job_number_of_people, R.id.ll_post_a_job_salary, R.id.ll_post_a_job_welfare, R.id.ll_post_a_job_city, R.id.ll_post_a_job_site, R.id.ll_post_a_job_describe, R.id.ll_post_a_job_status, R.id.rl_post_a_job_add})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ll_post_a_job_namae:
                start(new ExpectTheIndustryFragment());
//                职位名称
                break;
            case R.id.ll_post_a_job_job_nature:
                final OptionPicker picker4 = new OptionPicker(mActivity, new String[]{
                        "全职", "兼职", "实习"
                });
                picker4.setCycleDisable(false);//不禁用循环
                picker4.setLineVisible(false);//可见行
                picker4.setTextSize(16);
                picker4.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker4.getSelectedItem().toString();
                        tvPajPost.setText(s);
                    }
                });
                picker4.show();
//                职位性质
                break;
            case R.id.ll_post_a_job_job_category:
                start(new ExpectTheIndustryFragment());
//                职位类别
                break;
            case R.id.ll_post_a_job_work_experience:
                final OptionPicker bod = new OptionPicker(mActivity, new String[]{
                        "1年", "2年", "3-5年", "5-8年", "8-10年", "10年以上"
                });
                bod.setCycleDisable(true);//不禁用循环
                bod.setLineVisible(false);//可见行
                bod.setTextSize(16);
                bod.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = bod.getSelectedItem().toString();
                        tvPostAJobWorkExperience.setText(s);

                    }
                });
                bod.show();
//                工作经验
                break;
            case R.id.ll_post_a_job_education:
                final OptionPicker education = new OptionPicker(mActivity, new String[]{
                        "初中", "高中", "大专", "本科", "硕士", "博士"
                });
                education.setCycleDisable(true);//不禁用循环
                education.setLineVisible(false);//可见行
                education.setTextSize(16);
                education.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = education.getSelectedItem().toString();
                        tvPostAJobEducation.setText(s);
                    }
                });
                education.show();
//                学历要求
                break;
            case R.id.ll_post_a_job_number_of_people:
//                ;
//                final EditText e2 = new EditText(view.getContext());
//                new AlertDialog.Builder(mActivity).setTitle("招聘人数")
//                        .setView(e2)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int i) {
//                                String str = e2.getText().toString();
//
//                            }
//                        }).
//                        setNegativeButton("取消", null).
//                        show();
//                招聘人数
                break;
            case R.id.ll_post_a_job_salary:
                final OptionPicker salary = new OptionPicker(mActivity, new String[]{
                        "1-2k", "2-3k", "3-5k", "5-8k", "8-10k", "10-12k", "12-15k", "15k以上"
                });
                salary.setCycleDisable(true);//不禁用循环
                salary.setLineVisible(false);//可见行
                salary.setTextSize(16);
                salary.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = salary.getSelectedItem().toString();
                        tvPostAJobSalary.setText(s);

                    }
                });
                salary.show();
//                月薪范围
                break;
            case R.id.ll_post_a_job_welfare:
                start(new WelfareFragment());
//                福利
                break;
            case R.id.ll_post_a_job_city:
                final ArrayList<Province> site = new ArrayList<>();
                String json = null;
                try {
                    json = ConvertUtils.toString(getContext().getAssets().open("city.json"));
                } catch (IOException e6) {
                    e6.printStackTrace();
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
                        tvPostAJobCity.setText(s);
//                showToast(province + "省" + city + "市" + county + "区");
                    }
                });
                picker.show();
//                工作城市
                break;
            case R.id.ll_post_a_job_site:
//                ;
//                final EditText e3 = new EditText(view.getContext());
//                new AlertDialog.Builder(mActivity).setTitle("工作地点")
//                        .setView(e3)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int i) {
//                                String str = e3.getText().toString();
//                            }
//                        }).
//                        setNegativeButton("取消", null).
//                        show();
////                工作地点
                break;
            case R.id.ll_post_a_job_describe:
//                ;
//                final EditText e = new EditText(view.getContext());
//                new AlertDialog.Builder(mActivity).setTitle("职位描述")
//                        .setView(e)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int i) {
//                                Integer.parseInt(e.getText().toString());
//                            }
//                        }).
//                        setNegativeButton("取消", null).
//                        show();
//                职位描述
                break;
            case R.id.ll_post_a_job_status:
//                ;
//                final EditText e1 = new EditText(view.getContext());
//                new AlertDialog.Builder(mActivity).setTitle("任职资格")
//                        .setView(e1)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int i) {
//                                String str = e1.getText().toString();
//                            }
//                        }).
//                        setNegativeButton("取消", null).
//
//                        show();
//                任职资格
                break;
            case R.id.rl_post_a_job_add:
//                保存
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
