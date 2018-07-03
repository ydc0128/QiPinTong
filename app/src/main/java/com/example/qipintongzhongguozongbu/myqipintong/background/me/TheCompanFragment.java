package com.example.qipintongzhongguozongbu.myqipintong.background.me;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.AppApplication;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli.AccountBalanceFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli.PostAJobFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia.EnterpriseMaterialEditorFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi.IdentitySettingFragment;
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
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imlib.model.UserInfo;

/**
 * Created by L on 2017/3/4.
 * 我的界面————-企业
 */

public class TheCompanFragment extends BaseFragment {


    @BindView(R.id.my_tc)
    CircleImageView myTc;
    @BindView(R.id.tv_tc_Companies_hire_tong)
    TextView tvTcCompaniesHireTong;
    @BindView(R.id.ib_xaila)
    LinearLayout ibXaila;
    @BindView(R.id.tv_tc_Edit_your_resume)
    TextView tvTcEditYourResume;
    @BindView(R.id.rl_tc_title)
    RelativeLayout rlTcTitle;
    @BindView(R.id.lltc_release)
    LinearLayout lltcRelease;
    @BindView(R.id.ll_tc_received)
    LinearLayout llTcReceived;
    @BindView(R.id.ll_tc_waiting_for_the)
    LinearLayout llTcWaitingForThe;
    @BindView(R.id.ll_tc_collection)
    LinearLayout llTcCollection;
    @BindView(R.id.ll_tc_earnings)
    RelativeLayout llTcEarnings;
//    @BindView(R.id.rl_btc_client)
//    RelativeLayout rlBtcClient;
//    @BindView(R.id.rl_btc_share_talent_show)
//    RelativeLayout rlBtcShareTalentShow;
    @BindView(R.id.rl_btc_resume_entrepot)
    RelativeLayout rlBtcResumeEntrepot;
//    @BindView(R.id.rl_btc_zhushou)
//    RelativeLayout rlBtcZhushou;
    @BindView(R.id.ll_tc_erweima)
    RelativeLayout llTcErweima;
    @BindView(R.id.ll_tc_super_admin)
    RelativeLayout llTcSuperAdmin;
    @BindView(R.id.ll_tc_qiehuan)
    RelativeLayout llTcQieHuan;
    @BindView(R.id.ll_tc_shezhi)
    RelativeLayout llTcShezhi;
    Unbinder unbinder;
    private SharedPreferences sp;
    private SelectableRoundedImageView imageView;
    private ImageView mNewVersionView;
    private boolean isHasNewVersion;
    private String url;
    private boolean isDebug;
    public static final String SHOW_RED = "SHOW_RED";
    private static final int COMPARE_VERSION = 54;
    private TextView tvEmptyPrompt;

    public void onSupportVisible() {
        mTvTitle.setText("我");
        mIvBack.setVisibility(View.VISIBLE);
//      mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.background_the_company, null);
        initData();
//        tvEmptyPrompt = (TextView) view.findViewById(R.id.tv_empty_prompt);
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

        GlideUtils.loadImage(mActivity, event.getFile(), myTc);
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

    @OnClick({R.id.my_tc, R.id.tv_tc_Companies_hire_tong, R.id.tv_tc_Edit_your_resume, R.id.rl_tc_title, R.id.lltc_release, R.id.ll_tc_received, R.id.ll_tc_waiting_for_the, R.id.ll_tc_collection, R.id.ll_tc_earnings, R.id.rl_btc_resume_entrepot, R.id.ll_tc_erweima, R.id.ll_tc_super_admin, R.id.ll_tc_qiehuan, R.id.ll_tc_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_tc:
                startActivity(new Intent(mActivity, SelectBackGroundActivity.class));
                break;
            case R.id.tv_tc_Companies_hire_tong:
                break;
//            case R.id.tv_tc_Do_not_disturb:
//                break;
//            case R.id.ib_xaila:
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
//                        tvTcDoNotDisturb.setText(s);
//                        if (index == 4) {
//                            start(new PartnerFragment());
//                        } else {
//
//                        }
//                    }
//                });
//                picker3.show();
//                break;
            case R.id.tv_tc_Edit_your_resume:
                start(new EnterpriseMaterialEditorFragment());
//                编辑
                break;
            case R.id.rl_tc_title:
                break;
            case R.id.lltc_release:
                start(new PostAJobFragment());
//                发布简历
                break;
            case R.id.ll_tc_received:
                start(new ReceivedYourResumeFragment());
//                收到简历
                break;
            case R.id.ll_tc_waiting_for_the:
                start(new CollectionFragment());
//                收藏
                break;
            case R.id.ll_tc_collection:
                start(new FansFragment());
//                粉丝
                break;
            case R.id.ll_tc_earnings:
                start(new AccountBalanceFragment());
//               钱包
                break;
//            case R.id.rl_btc_client:
//                start(new CustomerWarehouseFragment());
//                break;
//            case R.id.rl_btc_share_talent_show:
//                start(new BindingMobilePhoneFragment());
//                break;
            case R.id.rl_btc_resume_entrepot:
                start(new EmptyFragment());
                mTvTitle.setText("简历库");
//                tvEmptyPrompt = (TextView) view.findViewById(R.id.tv_empty_prompt);
//                tvEmptyPrompt.setText("请完善资料，平台将为你提供精准服务");
                break;
//            case R.id.rl_btc_zhushou:
//                start(new AssistantFragment());
//                break;
            case R.id.ll_tc_erweima:
                Dialog dialog = new Dialog(mActivity, R.style.MyDialog);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER_HORIZONTAL;
                window.setAttributes(wlp);
                dialog.setContentView(R.layout.cloud_dialog_view);
                dialog.show();
                break;
            case R.id.ll_tc_super_admin:
                start(new TopFragment());
                break;
            case R.id.ll_tc_qiehuan:
                start(new IdentitySettingFragment());
                break;
            case R.id.ll_tc_shezhi:
                start(new SettingUpFragment());
//               设置
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

    private void updateUserInfo() {
        String userId = sp.getString(SealConst.SEALTALK_LOGIN_ID, "");
        String username = sp.getString(SealConst.SEALTALK_LOGIN_NAME, "");
        String userPortrait = sp.getString(SealConst.SEALTALK_LOGING_PORTRAIT, "");
        if (tvTcCompaniesHireTong != null)
            tvTcCompaniesHireTong.setText(username);
        if (!TextUtils.isEmpty(userId)) {
            String portraitUri = SealUserInfoManager.getInstance().getPortraitUri
                    (new UserInfo(userId, username, Uri.parse(userPortrait)));
            ImageLoader.getInstance().displayImage(portraitUri, imageView, AppApplication.getOptions());
        }
    }

    public void initData() {
        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
//        EventBus.getDefault().register(this);
        updateUserInfo();
    }
}
