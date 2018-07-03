package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：客户库
 * 作者：碧血染银枪 on 2017/5/19 11:49
 * 邮箱：ydc_0128@163.com
 */

public class CustomerWarehouseFragment extends BaseFragment {
    @BindView(R.id.the_number_of_customer)
    TextView theNumberOfCustomer;
    @BindView(R.id.tv_customer_today_number)
    TextView tvCustomerTodayNumber;
    @BindView(R.id.ll_customer_today_number)
    LinearLayout llCustomerTodayNumber;
    @BindView(R.id.ll_customer_today_mingxi)
    LinearLayout llCustomerTodayMingxi;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_customer, null));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_customer_today_number, R.id.ll_customer_today_mingxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_customer_today_number:
                break;
            case R.id.ll_customer_today_mingxi:
                break;
        }
    }
}
