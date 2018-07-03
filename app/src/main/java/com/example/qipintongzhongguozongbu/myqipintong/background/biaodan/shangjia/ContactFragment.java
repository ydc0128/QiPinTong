package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

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
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.event.LocationEvent;

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
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by L on 2017/3/17.
 * ysyk-商家入住-联系方式
 */

public class ContactFragment extends BaseFragment {


    @BindView(R.id.ll_merchants_in_contact_shangquan)
    LinearLayout llMerchantsInContactShangquan;
    @BindView(R.id.ll_merchants_in_contact_phone_number)
    LinearLayout llMerchantsInContactPhoneNumber;
    @BindView(R.id.merchants_in_city)
    TextView merchantsInCity;
    @BindView(R.id.ll_merchants_in_store_address)
    RelativeLayout llMerchantsInStoreAddress;
    @BindView(R.id.ll_merchants_in_license_time)
    LinearLayout llMerchantsInLicenseTime;
    @BindView(R.id.ll_merchants_in_map_markers)
    RelativeLayout llMerchantsInMapMarkers;
    @BindView(R.id.tv_cont_baocun)
    Button tvContBaocun;
    Unbinder unbinder;

    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_contact, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("联系方式");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setLocation(LocationEvent event) {
        merchantsInCity.setText(event.getLocation());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_merchants_in_contact_shangquan, R.id.ll_merchants_in_contact_phone_number, R.id.merchants_in_city, R.id.ll_merchants_in_store_address, R.id.ll_merchants_in_license_time, R.id.ll_merchants_in_map_markers, R.id.tv_cont_baocun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_merchants_in_contact_shangquan:
                break;
            case R.id.ll_merchants_in_contact_phone_number:
                break;
            case R.id.merchants_in_city:
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
                        merchantsInCity.setText(s);
//                showToast(province + "省" + city + "市" + county + "区");
                    }
                });
                picker.show();
                break;
            case R.id.ll_merchants_in_store_address:
                break;
            case R.id.ll_merchants_in_license_time:
                break;
            case R.id.ll_merchants_in_map_markers:
                break;
            case R.id.tv_cont_baocun:
                break;
        }
    }
}
