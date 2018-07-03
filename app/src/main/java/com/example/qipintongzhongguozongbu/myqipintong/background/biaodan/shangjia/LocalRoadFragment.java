package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.PhotoActivity;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.BottomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.DialogListViewItem;

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
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * Created by L on 2017/3/8.
 * 投资天下--同城路演——添加
 */

public class LocalRoadFragment extends BaseFragment implements BottomDialog.OnBottomDialogItemOnClickListener {
    @BindView(R.id.et_utp_zhuti)
    EditText etUtpZhuti;
    @BindView(R.id.ll_lr_zhuti)
    LinearLayout llLrZhuti;
    @BindView(R.id.tv_utp_quyu)
    TextView tvUtpQuyu;
    @BindView(R.id.ll_lr_quyu)
    LinearLayout llLrQuyu;
    @BindView(R.id.tv_lr_site)
    TextView tvLrSite;
    @BindView(R.id.ll_lr_site)
    LinearLayout llLrSite;
    @BindView(R.id.ll_lr_open)
    LinearLayout llLrOpen;
    @BindView(R.id.ll_lr_close)
    LinearLayout llLrClose;
    @BindView(R.id.et_lr_sponsor)
    EditText etLrSponsor;
    @BindView(R.id.ll_lr_sponsor)
    LinearLayout llLrSponsor;
    @BindView(R.id.et_lr_xiangqing)
    EditText etLrXiangqing;
    @BindView(R.id.ll_lr_xiangqing)
    LinearLayout llLrXiangqing;
    @BindView(R.id.tv_lr_uploading)
    TextView tvLrUploading;
    @BindView(R.id.bt_lr_baocun)
    Button btLrBaocun;
    @BindView(R.id.tv_lr_open)
    TextView tvLrOpen;
    @BindView(R.id.tv_lr_close)
    TextView tvLrClose;
    @BindView(R.id.ll_lr_shangchuan)
    LinearLayout llLrShangchuan;

    Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_local_road, null));
}

    public void onSupportVisible() {
        mTvTitle.setText("同城路演");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.ll_lr_zhuti, R.id.ll_lr_shangchuan, R.id.ll_lr_quyu, R.id.ll_lr_site, R.id.ll_lr_open, R.id.ll_lr_close, R.id.ll_lr_sponsor, R.id.ll_lr_xiangqing, R.id.bt_lr_baocun})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_lr_zhuti:
                break;
            case R.id.ll_lr_quyu:
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
                        tvUtpQuyu.setText(s);
//                showToast(province + "省" + city + "市" + county + "区");
                    }
                });
                picker.show();

                break;
            case R.id.ll_lr_site:
                break;
            case R.id.ll_lr_open:
                final DatePicker open = new DatePicker(mActivity);
                open.setTopPadding(2);
                open.setRangeStart(2016, 8, 29);
                open.setRangeEnd(2111, 1, 11);
                open.setSelectedItem(2017, 2, 14);

                open.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener()

                                           {
                                               @Override
                                               public void onDatePicked(String year, String month, String day) {
                                                   String y = open.getSelectedYear();
                                                   String m = open.getSelectedMonth();
                                                   String d = open.getSelectedDay();
                                                   String ccc = y + "-" + m + "-" + d;
                                                   tvLrOpen.setText(ccc);
                                               }
                                           }

                );
                open.setOnWheelListener(new DatePicker.OnWheelListener()

                                        {
                                            @Override
                                            public void onYearWheeled(int index, String year) {
                                                open.setTitleText(year + "-" + open.getSelectedMonth() + "-" + open.getSelectedDay());
                                            }

                                            @Override
                                            public void onMonthWheeled(int index, String month) {
                                                open.setTitleText(open.getSelectedYear() + "-" + month + "-" + open.getSelectedDay());
                                            }

                                            @Override
                                            public void onDayWheeled(int index, String day) {
                                                open.setTitleText(open.getSelectedYear() + "-" + open.getSelectedMonth() + "-" + day);
                                            }
                                        }
                );
                open.show();

                break;
            case R.id.ll_lr_close:
                final DatePicker picker1 = new DatePicker(mActivity);
                picker1.setTopPadding(2);
                picker1.setRangeStart(2016, 8, 29);
                picker1.setRangeEnd(2111, 1, 11);
                picker1.setSelectedItem(2017, 2, 14);

                picker1.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener()

                                              {
                                                  @Override
                                                  public void onDatePicked(String year, String month, String day) {
                                                      String y = picker1.getSelectedYear();
                                                      String m = picker1.getSelectedMonth();
                                                      String d = picker1.getSelectedDay();
                                                      String ccc = y + "-" + m + "-" + d;
                                                      tvLrClose.setText(ccc);
                                                  }
                                              }

                );
                picker1.setOnWheelListener(new DatePicker.OnWheelListener()

                                           {
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
                                           }
                );
                picker1.show();

                break;
            case R.id.ll_lr_sponsor:
                break;
            case R.id.ll_lr_xiangqing:
                break;
            case R.id.bt_lr_baocun:
                break;
            case R.id.ll_lr_shangchuan:
                Intent intent = new Intent(mActivity, PhotoActivity.class);
                startActivity(intent);
        }
    }

//    private void showBottomDialog() {
//        BottomDialog dialog = new BottomDialog(mActivity,
//                R.style.transparentFrameWindowStyle);
//        dialog.setOnBottomDialogItemOnClickListener(this);
//        dialog.addItem(new DialogListViewItem("相册"));
//        dialog.addItem(new DialogListViewItem("拍照"));
//        dialog.show();
//    }

    @Override
    public void onItemClick(DialogListViewItem item, int position) {
//        Intent intent = new Intent(mActivity, GetPhotoActivity.class);
//        intent.putExtra("TAG", position);
//        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
