package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by L on 2017/3/1.
 * 商家入住
 */

public class MerchantsInFragment extends BaseFragment {


    @BindView(R.id.ll_merchants_in_facade_name)
    LinearLayout llMerchantsInFacadeName;
    @BindView(R.id.jingyingpinlei)
    TextView jingyingpinlei;
    @BindView(R.id.ll_merchants_in_business_species)
    RelativeLayout llMerchantsInBusinessSpecies;
    @BindView(R.id.ll_merchants_in_consume)
    LinearLayout llMerchantsInConsume;
    @BindView(R.id.ll_merchants_in_business_area)
    LinearLayout llMerchantsInBusinessArea;
    @BindView(R.id.biaoqian)
    TextView biaoqian;
    @BindView(R.id.ll_merchants_in_tag)
    RelativeLayout llMerchantsInTag;
    @BindView(R.id.ll_merchants_in_log)
    RelativeLayout llMerchantsInLog;
    @BindView(R.id.ll_merchants_in_certification)
    RelativeLayout llMerchantsInCertification;
    @BindView(R.id.ll_merchants_in_contcat)
    RelativeLayout llMerchantsInContcat;
    @BindView(R.id.ll_merchants_in_youhui)
    RelativeLayout llMerchantsInYouhui;
    @BindView(R.id.merchants_in_save)
    Button merchantsInSave;
    @BindView(R.id.rf_industry_classifition)
    LinearLayout rfIndustryClassifition;

    Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_menu_merchants_in, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("商家入住");
        mIvBack.setVisibility(View.GONE);
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


    @OnClick({R.id.ll_merchants_in_facade_name, R.id.ll_merchants_in_business_species, R.id.ll_merchants_in_consume, R.id.ll_merchants_in_business_area, R.id.ll_merchants_in_tag, R.id.ll_merchants_in_log, R.id.ll_merchants_in_certification, R.id.ll_merchants_in_contcat, R.id.ll_merchants_in_youhui, R.id.merchants_in_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_merchants_in_facade_name:
//                门店名称
                break;
            case R.id.ll_merchants_in_business_species:
                final OptionPicker picker3 = new OptionPicker(mActivity, new String[]{
                        "美食天下", "丽人圈", "休闲娱乐", "酒店预定"
                });
                picker3.setCycleDisable(true);//不禁用循环
                picker3.setLineVisible(false);//可见行
                picker3.setTextSize(16);
                picker3.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker3.getSelectedItem().toString();
                        jingyingpinlei.setText(s);
                    }
                });
                picker3.show();
//                经营品类
                break;
            case R.id.ll_merchants_in_consume:
//                人均消费
                break;
            case R.id.ll_merchants_in_business_area:

//                商圈
                break;
            case R.id.ll_merchants_in_tag:
                final OptionPicker picker = new OptionPicker(mActivity, new String[]{
                        "免费wifi", "停车"
                });
                picker.setCycleDisable(true);//不禁用循环
                picker.setLineVisible(false);//可见行
                picker.setTextSize(16);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker.getSelectedItem().toString();
                        biaoqian.setText(s);
                    }
                });
                picker.show();
//                标签
                break;
            case R.id.ll_merchants_in_log:
                start(new LogFragment());
//                图片
                break;
            case R.id.ll_merchants_in_certification:
                start(new CertificationFragment());
//                认证
                break;
            case R.id.ll_merchants_in_contcat:
                start(new ContactFragment());
//                联系方式
                break;
            case R.id.ll_merchants_in_youhui:
                start(new PreferentialFragment());
//                优惠
                break;
            case R.id.merchants_in_save:
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
