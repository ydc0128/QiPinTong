package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.index.LocationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.OptionPicker;

/**
 * Created by L on 2017/3/4.
 * 投资机构资料
 */

public class InvestmentInstitutionsDataFragment extends BaseFragment {


    @BindView(R.id.tv_ii_field)
    TextView tvIiField;
    @BindView(R.id.ll_ii_field)
    LinearLayout llIiField;
    @BindView(R.id.tv_ii_rounds)
    TextView tvIiRounds;
    @BindView(R.id.ll_ii_rounds)
    LinearLayout llIiRounds;
    @BindView(R.id.tv_ii_city)
    TextView tvIiCity;
    @BindView(R.id.ll_ii_city)
    LinearLayout llIiCity;
    @BindView(R.id.rl_ii_amount)
    LinearLayout rlIiAmount;
    @BindView(R.id.tv_ii_synopsis)
    EditText tvIiSynopsis;
    @BindView(R.id.rl_ii_synopsis)
    LinearLayout rlIiSynopsis;
    @BindView(R.id.rl_ii_apply_for)
    Button rlIiApplyFor;

    Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.bacground_investment_institutions, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("投资机构");
        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();
    }

    @OnClick({R.id.ll_ii_field, R.id.ll_ii_rounds, R.id.ll_ii_city, R.id.rl_ii_amount, R.id.rl_ii_synopsis, R.id.rl_ii_apply_for})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_ii_field:
//                start(new FieldFragment());
                final OptionPicker picker3 = new OptionPicker(mActivity, new String[]{
                        "移动互联网", "金融", "企业服务", "教育", "医疗健康", "旅游"
                });
                picker3.setCycleDisable(true);//不禁用循环
                picker3.setLineVisible(false);//可见行
                picker3.setTextSize(16);
                picker3.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker3.getSelectedItem().toString();
                        tvIiField.setText(s);
                    }
                });
                picker3.show();


//                投资领域
                break;
            case R.id.ll_ii_rounds:
//                start(new RoundsFragment());
                final OptionPicker picker = new OptionPicker(mActivity, new String[]{
                        "未融资", "已融资", "天使轮", "A轮", "B轮", "C轮", "D轮","已上市","不需要融资"
                });
                picker.setCycleDisable(true);//不禁用循环
                picker.setLineVisible(false);//可见行
                picker.setTextSize(16);
                picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker.getSelectedItem().toString();
                        tvIiRounds.setText(s);
                    }
                });
                picker.show();
//                投资轮次
                break;
            case R.id.ll_ii_city:
//                start(new CityFragment());
                start(new LocationFragment());
//                投资区域
                break;
            case R.id.rl_ii_amount:
//                成功数量
                break;
            case R.id.rl_ii_synopsis:
//                机构简介
                break;
            case R.id.rl_ii_apply_for:
//                申请
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
