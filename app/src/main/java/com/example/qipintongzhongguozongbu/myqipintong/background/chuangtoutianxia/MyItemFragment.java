package com.example.qipintongzhongguozongbu.myqipintong.background.chuangtoutianxia;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.TabAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli.UblishTheProjectFragmen;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia.LocalRoadFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/11.
 * 创投天下
 */

public class MyItemFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.my_item)
    CommonTabLayout myItem;
    @BindView(R.id.vp_my_item)
    RecyclerView vpMyItem;
    @BindView(R.id.fl_my_item_luyan)
    FrameLayout flMyItemLuyan;
    @BindView(R.id.bt_my_item_luyan)
    Button btMyItemLuyan;
    @BindView(R.id.bt_my_item_xiangmu)
    Button btMyItemXiangmu;
    @BindView(R.id.fl_my_item_xiangmu)
    FrameLayout flMyItemXiangmu;
    private String[] mTitles = {"我的项目", "同城路演"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_my_item, null));
    }

    @Override
    public void initData() {
        setLabelDate();
        super.initData();

    }

    private void setLabelDate() {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabAdapter(mTitles[i]));
        }
        myItem.setTabData(mTabEntities);
        myItem.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        flMyItemXiangmu.setVisibility(View.VISIBLE);
                        flMyItemLuyan.setVisibility(View.GONE);
                        btMyItemXiangmu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                start(new UblishTheProjectFragmen());
                            }
                        });
                        break;
//                    case 1:
//                        btMyItem.setText("添加投资人");
//                        btMyItem.setOnClickListener(new MyTouzirenListener());
//                        break;
//                    case 2:
//                        btMyItem.setText("添加投资案例");
//                        btMyItem.setOnClickListener(new MyAnliListener());
//                        break;
                    case 1:
                        flMyItemXiangmu.setVisibility(View.GONE);
                        flMyItemLuyan.setVisibility(View.VISIBLE);
                        btMyItemLuyan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                start(new LocalRoadFragment());
                            }
                        });
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

    }

    public void onSupportVisible() {
        mTvTitle.setText("创投天下");
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

//    class MyAnliListener implements View.OnClickListener {
//        public void onClick(View v) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//            builder.setMessage("请先申请成为投资机构");
//            builder.setPositiveButton("立即申请", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    start(new InvestmentInstitutionsDataFragment());
//                }
//            });
//            builder.setNegativeButton("知道了", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            builder.create().show();
//        }
//
//    }
//
//    class MyTouzirenListener implements View.OnClickListener {
//        public void onClick(View v) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//            builder.setMessage("请先申请成为投资机构");
//            builder.setPositiveButton("立即申请", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    start(new InvestmentInstitutionsDataFragment());
//                }
//            });
//            builder.setNegativeButton("知道了", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            builder.create().show();
//        }
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}

