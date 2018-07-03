package com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvComercephotoAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvDaiJinAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.PhotoPopup;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.photoDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.map.Location;
import com.example.qipintongzhongguozongbu.myqipintong.map.NativeDialog;
import com.example.qipintongzhongguozongbu.myqipintong.utils.StringUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 店铺的详情页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/9 上午9:36
 */
public class CommerceDetailsFragment extends BaseFragment {
    @BindView(R.id.iv_commerce_food_icon)
    SquareImageView ivCommerceFoodIcon;
    @BindView(R.id.tv_commerce_food_name)
    TextView tvCommerceFoodName;
    @BindView(R.id.tv_commerce_food_type)
    TextView tvCommerceFoodType;
    @BindView(R.id.tv_commerce_food_location)
    TextView tvCommerceFoodLocation;
    @BindView(R.id.tv_commerce_food_distance)
    TextView tvCommerceFoodDistance;
    @BindView(R.id.tv_commerce_food_number)
    TextView tvCommerceFoodNumber;
    @BindView(R.id.rv_commerce_daijin)
    RecyclerView rvCommerceDaijin;
    @BindView(R.id.tv_commerce_pay)
    TextView tvCommercePay;
    @BindView(R.id.tv_commerce_dizhi)
    TextView tvCommerceDizhi;
    @BindView(R.id.iv_commerce_phone)
    ImageView ivCommercePhone;
    @BindView(R.id.iv_commerce_daohang)
    ImageView ivCommerceDaohang;
    @BindView(R.id.rv_commerce_photo)
    RecyclerView rvCommercePhoto;
    Unbinder unbinder;
    @BindView(R.id.ll_commerce_daijin)
    LinearLayout llCommerceDaijin;

    private Location loc_end = new Location(30.862644, 103.663077, "e");
    //终点信息 商家的位置

    private static final String PHONE_NUMBER = "1111111111";
    //商家电话号码


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_commerce_details, null));
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

    @OnClick({R.id.tv_commerce_pay, R.id.iv_commerce_phone, R.id.iv_commerce_daohang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commerce_pay://支付
                break;
            case R.id.iv_commerce_phone://电话

                if (StringUtils.isEmpty(PHONE_NUMBER)) {
                    ToastUtils.showToast(mActivity, "抱歉,该商户尚未上传电话号码");
                } else {
                    callPhone(PHONE_NUMBER);
                }


                break;
            case R.id.iv_commerce_daohang://导航

                NativeDialog msgDialog = new NativeDialog(mActivity, loc_end);
                msgDialog.show();

                break;
        }
    }


    @Override
    public void initData() {

        setDaiJinAdapter();

        setphotoAdapter();

        super.initData();

    }


    @Override
    public void onSupportVisible() {

        mTvTitle.setText("店铺详情");
        mTop.setVisibility(View.VISIBLE);
        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();
    }

    /**
     * function   : 代金券的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/9  上午10:31
     */
    private void setDaiJinAdapter() {

        final ArrayList<String> list = new ArrayList<>();
        list.add("5元代金券,满一千万可用");
        list.add("5毛代金券,满十万可用");
        list.add("5分代金券,满十万可用");

        if (list == null || list.size() == 0) {
            llCommerceDaijin.setVisibility(View.GONE);
        }

        rvCommerceDaijin.setLayoutManager(new LinearLayoutManager(mActivity));
        RvDaiJinAdapter adapter = new RvDaiJinAdapter(R.layout.item_daijin, list);
        rvCommerceDaijin.setAdapter(adapter);

        rvCommerceDaijin.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    /**
     * function   : 店铺照片的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/9  上午10:31
     */
    private void setphotoAdapter() {

        rvCommercePhoto.setLayoutManager(new GridLayoutManager(mActivity, 3));

        rvCommercePhoto.addItemDecoration(new RvItemDecoration(10));

        rvCommercePhoto.setAdapter(new RvComercephotoAdapter(R.layout.item_person_image, ImageList.getImageList()));

        rvCommercePhoto.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                new PhotoPopup(mActivity, CommerceDetailsFragment.this, ImageList.getImageList(), position).showPopupWindow();
            }
        });
    }

    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    private void callPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
