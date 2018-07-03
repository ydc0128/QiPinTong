package com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.AppApplication;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli.PartnerFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi.IdentitySettingFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.darenbang.MasterActivationFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.CollectionFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.EmptyFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.FansFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.me.SettingUpFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
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
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.widget.SelectableRoundedImageView;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.OptionPicker;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imlib.model.UserInfo;

/**
 * 注释：商户《我》界面
 * 作者：碧血染银枪 on 2017/5/9 09:25
 * 邮箱：ydc_0128@163.com
 */

public class CommercialTenantMyFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.commercial_tenant_p)
    CircleImageView commercialTenantP;
    @BindView(R.id.tv_my_commercial_tenant_Companies_nicheng)
    TextView tvMyCommercialTenantCompaniesNicheng;
    @BindView(R.id.commercial_tenant_ib_qiehuan)
    TextView commercialTenantIbQiehuan;
    @BindView(R.id.tv_my_commercial_tenant_Edit_your_resume)
    TextView tvMyCommercialTenantEditYourResume;
    @BindView(R.id.rl_commercial_tenant_title)
    RelativeLayout rlCommercialTenantTitle;
    @BindView(R.id.tv_commercial_tenant_my_The_delivery)
    LinearLayout tvCommercialTenantMyTheDelivery;
    @BindView(R.id.tv_commercial_tenant_my_nterview)
    LinearLayout tvCommercialTenantMyNterview;
    @BindView(R.id.tv_commercial_tenant_my_collection)
    LinearLayout tvCommercialTenantMyCollection;
    @BindView(R.id.tv_commercial_tenant_my_fans)
    LinearLayout tvCommercialTenantMyFans;
    @BindView(R.id.ll_commercial_tenant_my_data)
    LinearLayout llCommercialTenantMyData;
    @BindView(R.id.lv_my_commercial_tenant_account)
    LinearLayout lvMyCommercialTenantAccount;
    @BindView(R.id.lv_commercial_tenant_erweima)
    LinearLayout lvCommercialTenantErweima;
    @BindView(R.id.lv_commercial_tenant_kehuziyuan)
    LinearLayout lvCommercialTenantKehuziyuan;
    @BindView(R.id.lv_commercial_tenant_my_darenbang)
    LinearLayout lvCommercialTenantMyDarenbang;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.lv_commercial_tenant_my_jianliku)
    LinearLayout lvCommercialTenantMyJianliku;
    @BindView(R.id.lv_commercial_tenant_my_qiehuan)
    LinearLayout lvCommercialTenantMyQiehuan;
    @BindView(R.id.commercial_tenant_shezhi)
    LinearLayout commercialTenantShezhi;
    private SharedPreferences sp;
    private SelectableRoundedImageView imageView;
    private ImageView mNewVersionView;
    private boolean isHasNewVersion;
    private String url;
    private boolean isDebug;
    public static final String SHOW_RED = "SHOW_RED";
    private static final int COMPARE_VERSION = 54;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.background_commercial_tenant_my, null);
        initData();
        BroadcastManager.getInstance(getActivity()).addAction(SealConst.CHANGEINFO, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateUserInfo();
            }
        });
        compareVersion();
        return swipeBackView(view);
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
    @Override
    public void onSupportVisible() {
        mTvTitle.setText("我");
        mIvBack.setVisibility(View.GONE);
        super.onSupportVisible();
    }

    private void updateUserInfo() {
        String userId = sp.getString(SealConst.SEALTALK_LOGIN_ID, "");
        String username = sp.getString(SealConst.SEALTALK_LOGIN_NAME, "");
        String userPortrait = sp.getString(SealConst.SEALTALK_LOGING_PORTRAIT, "");
        if (tvMyCommercialTenantCompaniesNicheng != null)
            tvMyCommercialTenantCompaniesNicheng.setText(username);
        if (!TextUtils.isEmpty(userId)) {
            String portraitUri = SealUserInfoManager.getInstance().getPortraitUri
                    (new UserInfo(userId, username, Uri.parse(userPortrait)));
            ImageLoader.getInstance().displayImage(portraitUri, imageView, AppApplication.getOptions());
        }
    }

    public void initData() {
        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        updateUserInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Subscribe
    public void setBackground(FriendImageEvent event) {

        GlideUtils.loadImage(mActivity, event.getFile(), commercialTenantP);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @OnClick({R.id.commercial_tenant_p, R.id.tv_my_commercial_tenant_Companies_nicheng, R.id.commercial_tenant_ib_qiehuan, R.id.tv_my_commercial_tenant_Edit_your_resume, R.id.rl_commercial_tenant_title, R.id.tv_commercial_tenant_my_The_delivery, R.id.tv_commercial_tenant_my_nterview, R.id.tv_commercial_tenant_my_collection, R.id.tv_commercial_tenant_my_fans, R.id.ll_commercial_tenant_my_data, R.id.lv_my_commercial_tenant_account, R.id.lv_commercial_tenant_erweima, R.id.lv_commercial_tenant_kehuziyuan, R.id.lv_commercial_tenant_my_darenbang, R.id.lv_commercial_tenant_my_qiehuan, R.id.lv_commercial_tenant_my_jianliku, R.id.commercial_tenant_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.commercial_tenant_p:
                startActivity(new Intent(mActivity, SelectBackGroundActivity.class));
                break;
            case R.id.tv_my_commercial_tenant_Companies_nicheng:
                break;
            case R.id.commercial_tenant_ib_qiehuan:
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
                        commercialTenantIbQiehuan.setText(s);
                        if (index == 4) {
                            start(new PartnerFragment());
                        } else {

                        }
                    }
                });
                picker3.show();
                break;
            case R.id.tv_my_commercial_tenant_Edit_your_resume:

                break;
            case R.id.rl_commercial_tenant_title:
                break;
            case R.id.tv_commercial_tenant_my_The_delivery:
                break;
            case R.id.tv_commercial_tenant_my_nterview:
                break;
            case R.id.tv_commercial_tenant_my_collection:
                start(new CollectionFragment());
                break;
            case R.id.tv_commercial_tenant_my_fans:
                start(new FansFragment());
                break;
            case R.id.ll_commercial_tenant_my_data:
                break;
            case R.id.lv_my_commercial_tenant_account:
                start(new CommercialTenanyAccountFragment());
                break;
            case R.id.lv_commercial_tenant_erweima:
//                Dialog dialog = new Dialog(mActivity, R.style.MyDialog);
//                Window window = dialog.getWindow();
//                WindowManager.LayoutParams wlp = window.getAttributes();
//                wlp.gravity = Gravity.CENTER_HORIZONTAL;
//                window.setAttributes(wlp);
//                dialog.setContentView(R.layout.cloud_dialog_view);
//                dialog.show();
                start(new PaymentActivationFragment());
                break;
            case R.id.lv_commercial_tenant_kehuziyuan:
                start(new KuHuKUFragmeng());
             break;
            case R.id.lv_commercial_tenant_my_darenbang:
                start(new MasterActivationFragment());
                break;
            case R.id.lv_commercial_tenant_my_qiehuan:
                start(new IdentitySettingFragment());
//                身份切换
                break;
            case R.id.lv_commercial_tenant_my_jianliku:
                start(new EmptyFragment());
                mTvTitle.setText("简历库");
//                tvEmptyPrompt = (TextView) view.findViewById(R.id.tv_empty_prompt);
//                tvEmptyPrompt.setText("请完善资料，平台将为你提供精准服务");
                break;
            case R.id.commercial_tenant_shezhi:

                start(new SettingUpFragment());
                break;
        }
    }
}
