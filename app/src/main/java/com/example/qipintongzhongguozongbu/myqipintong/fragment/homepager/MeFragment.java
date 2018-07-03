package com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.AppApplication;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli.MyDataFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi.IdentitySettingFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.tongbi.MyEarningsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.darenbang.MasterActivationFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.CollectionFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.EmptyFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.FansFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.SettingUpFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.TopFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.tongbi.BiDouFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.CustomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.event.FriendImageEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai.SelectBackGroundActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealConst;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealUserInfoManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.SealAction;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.broadcast.BroadcastManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.network.async.AsyncTaskManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.network.async.OnDataListener;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.network.http.HttpException;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.response.VersionResponse;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imlib.model.UserInfo;


/**
 * Created by L on 2017/2/11.
 * 我
 */
public class MeFragment extends BaseFragment {


    @BindView(R.id.my_my_p)
    CircleImageView myMyP;
    @BindView(R.id.tv_my_Companies_nicheng)
    TextView tvMyCompaniesNicheng;
    @BindView(R.id.ib_qiehuan)
    TextView ibQiehuan;
    @BindView(R.id.tv_my_Edit_your_resume)
    TextView tvMyEditYourResume;
    @BindView(R.id.rl_my_my_title)
    RelativeLayout rlMyMyTitle;
    @BindView(R.id.tv_my_The_delivery)
    LinearLayout tvMyTheDelivery;
    @BindView(R.id.tv_my_nterview)
    LinearLayout tvMyNterview;
    @BindView(R.id.tv_my_collection)
    LinearLayout tvMyCollection;
    @BindView(R.id.tv_my_fans)
    LinearLayout tvMyFans;
    @BindView(R.id.ll_my_data)
    LinearLayout llMyData;
    @BindView(R.id.lv_my_earnings)
    LinearLayout lvMyEarnings;
    @BindView(R.id.lv_my_bidou)
    LinearLayout lvMyBidou;
    @BindView(R.id.lv_my_erweima)
    LinearLayout lvMyErweima;
    //    @BindView(R.id.lv_my_ycym)
//    LinearLayout lvMyYcym;
    @BindView(R.id.lv_my_darenbang)
    LinearLayout lvMyDarenbang;
    //    @BindView(R.id.lv_my_wycy)
//    LinearLayout lvMyWycy;
    @BindView(R.id.lv_my_shenfenqiehuan)
    LinearLayout lvMyShenfenqiehuan;
    @BindView(R.id.shezhi)
    LinearLayout shezhi;
    Unbinder unbinder;
    @BindView(R.id.lv_my_chaojiguanli)
    LinearLayout lvMyChaojiguanli;
    private SharedPreferences sp;
    private ImageView mNewVersionView;
    private boolean isHasNewVersion;
    private String url;
    private boolean isDebug;
    public static final String SHOW_RED = "SHOW_RED";
    private static final int COMPARE_VERSION = 54;
    private TextView tvEmptyPrompt;
    private ImageView mIvJianLi, mIvHeHhuoRen;
    private CustomDialog GiveMoneyDialog;
    private boolean isOpen;
    //    String token1 = "I3d9dUauFbfVCTvqvq3nW7Kl9MC0YtbMe81ao8MUBDhYNBWsQSqs8Mp9Qf8TNlayAi+WloH/GhkOx36wnCtJNg==";
    private Dialog dialog;
    private View inflate;
    private TextView tvShiYong, tvCDVName, tvCVDRenQi, tvCVDFans, tvCDVCity;
    private ImageView ivCDVPhoto, ivErWeiMa, ivDialogXingBie;
    private Button mDetermine;

    public static MeFragment getInstance() {
        return new MeFragment();
    }


    @Override
    public void onSupportVisible() {
        mTvTitle.setText("我");
        mIvBack.setVisibility(View.GONE);
        super.onSupportVisible();
    }

    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_my, null);
        isDebug = getContext().getSharedPreferences("config", getContext().MODE_PRIVATE).getBoolean("isDebug", false);
        initData();
        BroadcastManager.getInstance(getActivity()).addAction(SealConst.CHANGEINFO, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateUserInfo();
            }
        });
        compareVersion();
        return view;
    }

    @Subscribe
    public void setBackground(FriendImageEvent event) {

        GlideUtils.loadImage(mActivity, event.getFile(), myMyP);

    }

    @OnClick({R.id.my_my_p, R.id.tv_my_Companies_nicheng, R.id.ib_qiehuan, R.id.tv_my_Edit_your_resume, R.id.rl_my_my_title, R.id.tv_my_The_delivery, R.id.tv_my_nterview, R.id.tv_my_collection, R.id.tv_my_fans, R.id.ll_my_data, R.id.lv_my_earnings, R.id.lv_my_bidou, R.id.lv_my_darenbang, R.id.lv_my_chaojiguanli, R.id.lv_my_erweima, R.id.lv_my_shenfenqiehuan, R.id.shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_my_p:
                startActivity(new Intent(mActivity, SelectBackGroundActivity.class));
                //头像
                break;
            case R.id.tv_my_Companies_nicheng:
                break;
            case R.id.ib_qiehuan:
//                final OptionPicker picker3 = new OptionPicker(mActivity, new String[]{
//                        "在职-暂不考虑", "在职-考虑机会", "离职-月内到岗", "离职-随时到岗", "我要创业"
//                });
//                picker3.setCycleDisable(true);//不禁用循环
//                picker3.setLineVisible(false);//可见行
//                picker3.setTextSize(16);
//                picker3.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
//                    @Override
//                    public void onOptionPicked(int index, String item) {
//                        String s = picker3.getSelectedItem().toString();
//                        ibQiehuan.setText(s);
//                        if (index == 4) {
//                            start(new PartnerFragment());
//                        } else {
//
//                        }
//                    }
//                });
//                picker3.show();
                GiveMoneyDialog = new CustomDialog(mActivity, R.style.MyDialog) {
                    @Override
                    public View getView() {
                        View dialogView = View.inflate(mActivity, R.layout.zhuangtaimoshi, null);
                        mIvJianLi = (ImageView) dialogView.findViewById(R.id.iv_jianli_open_close);
                        mIvHeHhuoRen = (ImageView) dialogView.findViewById(R.id.iv_hehuoren_open_close);
                        mIvJianLi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isOpen) {
                                    mIvJianLi.setImageResource(R.drawable.sild_bg_on);
                                } else {
                                    mIvJianLi.setImageResource(R.drawable.sild_bg_off);
                                }
                                isOpen = !isOpen;
                            }
                        });
                        mIvHeHhuoRen.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isOpen) {
                                    mIvHeHhuoRen.setImageResource(R.drawable.sild_bg_on);
                                } else {
                                    mIvHeHhuoRen.setImageResource(R.drawable.sild_bg_off);
                                }
                                isOpen = !isOpen;
                            }
                        });
                        return dialogView;
                    }
                };
                GiveMoneyDialog.show();
                break;
            case R.id.tv_my_Edit_your_resume:
                start(new MyDataFragment());
//                编辑简历
                break;
            case R.id.rl_my_my_title:
                break;
            case R.id.tv_my_The_delivery:
//                投递记录
                start(new EmptyFragment());
                break;
            case R.id.tv_my_nterview:

                start(new EmptyFragment());
                break;
            case R.id.tv_my_collection:
                start(new CollectionFragment());
//                收藏
                break;
            case R.id.tv_my_fans:
                start(new FansFragment());
//                粉丝
                break;
            case R.id.ll_my_data:
                break;
            case R.id.lv_my_earnings:
                start(new MyEarningsFragment());
//                钱包
                break;
            case R.id.lv_my_bidou:
                start(new BiDouFragment());
//币斗
                break;
//            case R.id.lv_my_ycym:
//                start(new DataDaseFragment());
////                有才有貌
//                break;
            case R.id.lv_my_darenbang:
                start(new MasterActivationFragment());
//                达人帮
                break;
//            case R.id.lv_my_wycy:
//                start(new MyItemFragment());
////                我要创业
//                break;
            case R.id.lv_my_chaojiguanli:
                start(new TopFragment());
//                超级管理
                break;
            case R.id.lv_my_erweima:
//                Dialog dialog = new Dialog(mActivity, R.style.MyDialog);
//                Window window = dialog.getWindow();
//                WindowManager.LayoutParams wlp = window.getAttributes();
//                wlp.gravity = Gravity.CENTER_HORIZONTAL;
//                window.setAttributes(wlp);
//                dialog.setContentView(R.layout.cloud_dialog_view);
//                dialog.show();
//                二维码
                Mydialog();
                break;
            case R.id.lv_my_shenfenqiehuan:
                start(new IdentitySettingFragment());
//                身份切换
                break;
            case R.id.shezhi:
                start(new SettingUpFragment());
//                设置
                break;
        }
    }

    private void compareVersion() {
        AsyncTaskManager.getInstance(getActivity()).request(COMPARE_VERSION, new OnDataListener() {
            @Override
            public Object doInBackground(int requestCode, String parameter) throws HttpException {
                return new SealAction(getActivity()).getSealTalkVersion();
            }

            @Override
            public void onSuccess(int requestCode, Object result) {
                if (result != null) {
                    VersionResponse response = (VersionResponse) result;
                    String[] s = response.getAndroid().getVersion().split("\\.");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < s.length; i++) {
                        sb.append(s[i]);
                    }

                    String[] s2 = SealConst.SEALTALKVERSION.split("\\.");
                    StringBuilder sb2 = new StringBuilder();
                    for (int i = 0; i < s2.length; i++) {
                        sb2.append(s2[i]);
                    }
                    if (Integer.parseInt(sb.toString()) > Integer.parseInt(sb2.toString())) {
                        mNewVersionView.setVisibility(View.VISIBLE);
                        url = response.getAndroid().getUrl();
                        isHasNewVersion = true;
                        BroadcastManager.getInstance(getActivity()).sendBroadcast(SHOW_RED);
                    }
                }
            }

            @Override
            public void onFailure(int requestCode, int state, Object result) {

            }
        });
    }

    public void initData() {
        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
//        EventBus.getDefault().register(this);
        updateUserInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void updateUserInfo() {
        String userId = sp.getString(SealConst.SEALTALK_LOGIN_ID, "");
        String username = sp.getString(SealConst.SEALTALK_LOGIN_NAME, "");
        String userPortrait = sp.getString(SealConst.SEALTALK_LOGING_PORTRAIT, "");
        if (tvMyCompaniesNicheng != null)
            tvMyCompaniesNicheng.setText(username);
        if (!TextUtils.isEmpty(userId)) {
            String portraitUri = SealUserInfoManager.getInstance().getPortraitUri
                    (new UserInfo(userId, username, Uri.parse(userPortrait)));
            ImageLoader.getInstance().displayImage(portraitUri, myMyP, AppApplication.getOptions());
        }
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
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    public void Mydialog() {
        dialog = new Dialog(mActivity, R.style.MyDialog);
        //填充对话框的布局
        inflate = LayoutInflater.from(mActivity).inflate(R.layout.cloud_dialog_view, null);
        //初始化控件
        ivCDVPhoto = (ImageView) inflate.findViewById(R.id.iv_cdv_photo);
        tvCDVName = (TextView) inflate.findViewById(R.id.tv_cdv_name);
        tvCDVCity = (TextView) inflate.findViewById(R.id.tv_cdv_city);
        ivErWeiMa = (ImageView) inflate.findViewById(R.id.iv_cdv_QR_code);
        ivDialogXingBie = (ImageView) inflate.findViewById(R.id.iv_dialog_photo_xingbie);
        tvCVDRenQi = (TextView) inflate.findViewById(R.id.tv_cdv_moods);
        tvCVDFans = (TextView) inflate.findViewById(R.id.tv_cdv_fans);
        tvShiYong = (TextView) inflate.findViewById(R.id.tv_dialog_sshiyongguize);
        tvShiYong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogshiyong();

            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//    将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();//显示对话框
    }

    public void dialogshiyong() {
        dialog = new Dialog(mActivity, R.style.bottom_menu_style);
        //填充对话框的布局
        inflate = LayoutInflater.from(mActivity).inflate(R.layout.erweimaguize, null);
        //初始化控件
        mDetermine = (Button) inflate.findViewById(R.id.bt_ewm_queding);
        mDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//    将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();//显示对话框
    }

}


