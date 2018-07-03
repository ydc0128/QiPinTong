package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

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
import com.example.qipintongzhongguozongbu.myqipintong.background.chuangtoutianxia.MyItemFragment;
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
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by L on 2017/3/4.
 * 我的界面——投资
 */

public class PartnerFragment extends BaseFragment {


    @BindView(R.id.ll_partner_data)
    LinearLayout llPartnerData;
    @BindView(R.id.tv_p_ziliao)
    RelativeLayout tvPZiliao;
    @BindView(R.id.tv_p_qiuzhi)
    TextView tvPQiuzhi;
    @BindView(R.id.rl_p_qiuzhi)
    RelativeLayout rlPQiuzhi;
    @BindView(R.id.rl_p_chuangye)
    RelativeLayout rlPChuangye;
    @BindView(R.id.tv_partner_territory)
    TextView tvPartnerTerritory;
    @BindView(R.id.tv_partner_city)
    TextView tvPartnerCity;
    @BindView(R.id.tv_partner_mode)
    TextView tvPartnerMode;
    @BindView(R.id.rl_partner_lines)
    RelativeLayout rlPartnerLines;
    @BindView(R.id.bt_partner_save)
    Button btPartnerSave;
Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_partner, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
       unbinder= ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("我要创业");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    @OnClick({R.id.ll_partner_data, R.id.tv_p_ziliao, R.id.rl_p_qiuzhi, R.id.rl_p_chuangye, R.id.tv_partner_territory, R.id.tv_partner_city, R.id.tv_partner_mode, R.id.bt_partner_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_partner_data:
                start(new InitialValueFragment());
                break;
            case R.id.tv_partner_territory:
                final OptionPicker territory = new OptionPicker(mActivity, new String[]{
                        "互联网", "金融", "企业服务", "教育", "医疗健康", "旅游"
                });
                territory.setCycleDisable(false);//不禁用循环
                territory.setLineVisible(false);//可见行
                territory.setTextSize(16);
                territory.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = territory.getSelectedItem().toString();
                        tvPartnerTerritory.setText(s);
                    }
                });
                territory.show();
                break;
            case R.id.tv_partner_city:
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
                        tvPartnerCity.setText(s);
//                showToast(province + "省" + city + "市" + county + "区");
                    }
                });
                picker.show();

                break;
            case R.id.tv_partner_mode:
                final OptionPicker mode = new OptionPicker(mActivity, new String[]{
                        "战略联盟", "企业外包"
                });
                mode.setCycleDisable(false);//不禁用循环
                mode.setLineVisible(false);//可见行
                mode.setTextSize(16);
                mode.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = mode.getSelectedItem().toString();
                        tvPartnerMode.setText(s);
                    }
                });
                mode.show();
                break;
            case R.id.bt_partner_save:
                break;
            case R.id.tv_p_ziliao:
                start(new InitialValueFragment());
                break;
            case R.id.rl_p_qiuzhi:
                final OptionPicker picker3 = new OptionPicker(mActivity, new String[]{
                        "在职-暂不考虑", "在职-考虑机会", "离职-月内到岗", "离职-随时到岗", "我要创业"
                });
                picker3.setCycleDisable(true);//不禁用循环
                picker3.setLineVisible(false);//可见行
                picker3.setTextSize(16);
                picker3.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker3.getSelectedItem().toString();
                        tvPQiuzhi.setText(s);
                    }
                });
                picker3.show();
                break;
            case R.id.rl_p_chuangye:
                start(new MyItemFragment());
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
