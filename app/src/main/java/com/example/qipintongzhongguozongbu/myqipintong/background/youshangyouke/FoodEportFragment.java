package com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.index.LocationFragment;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by L on 2017/2/25.
 * 美食天下分类
 */

public class FoodEportFragment extends BaseFragment {
    @BindView(R.id.ll_food_eport_area)
    LinearLayout llFoodEportArea;
    @BindView(R.id.ll_food_eport_cuisine)
    LinearLayout llFoodEportCuisine;
    @BindView(R.id.ll_food_eport_screen)
    LinearLayout llFoodEportScreen;
    @BindView(R.id.ll_food_eport_sort)
    LinearLayout llFoodEportSort;

    public static FoodEportFragment getInstance() {
        return new FoodEportFragment();
    }

    @Override
    public View initView() {
        return swipeBackView( View.inflate(mActivity, R.layout.baackground_menu_food_eport, null));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("美食天下");
        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();
    }

    @OnClick({R.id.ll_food_eport_area, R.id.ll_food_eport_cuisine, R.id.ll_food_eport_screen, R.id.ll_food_eport_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_food_eport_area:
                start(new LocationFragment());
//                地区
                break;
            case R.id.ll_food_eport_cuisine:
                start(new CuisineFragment());
//                菜系
                break;
            case R.id.ll_food_eport_screen:
//                筛选
                break;
            case R.id.ll_food_eport_sort:
                new AlertDialog.Builder(mActivity).setTitle("离我最近")
                        .setMultiChoiceItems(new String[]{"离我最近", "人均最高", "人均最低"}, null, null)
                        .setPositiveButton("确定", null)

                        .setNegativeButton("取消", null).
                        show();
//                离我最近
                break;
        }
    }
}
