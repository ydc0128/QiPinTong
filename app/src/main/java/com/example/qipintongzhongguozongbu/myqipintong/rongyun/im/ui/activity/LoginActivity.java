package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.HomeActivity;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.login.w.qqapi.BaseUiListener;
import com.example.qipintongzhongguozongbu.myqipintong.login.w.qqapi.QQUser;
import com.example.qipintongzhongguozongbu.myqipintong.login.w.wxapi.WXEntryActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealConst;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealUserInfoManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.network.http.HttpException;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.response.GetTokenResponse;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.response.GetUserInfoByIdResponse;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.response.LoginResponse;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.AMUtils;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.CommonUtils;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.NLog;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.NToast;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.RongGenerate;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.widget.ClearWriteEditText;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.widget.LoadDialog;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.google.gson.Gson;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.AuthActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class LoginActivity extends BaseAActivity implements View.OnClickListener {

    private final static String TAG = "LoginActivity";
    private static final int LOGIN = 5;
    private static final int GET_TOKEN = 6;
    private static final int SYNC_USER_INFO = 9;
    private com.tencent.connect.UserInfo mInfo;
    private ImageView mImg_Background;
    private ClearWriteEditText mPhoneEdit, mPasswordEdit;
    private String phoneString;
    private String passwordString;
    private String connectResultId;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String loginToken;
    private TextView mYouke, Other;
    private Dialog dialog;
    private View inflate;
    private LinearLayout mWeixin, mQQ, mWeibo;
    private Context context;
    public static Tencent mTencent;
//    String token1 = "I3d9dUauFbfVCTvqvq3nW7Kl9MC0YtbMe81ao8MUBDhYNBWsQSqs8Mp9Qf8TNlayAi+WloH/GhkOx36wnCtJNg==";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setHeadVisibility(View.GONE);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();
        mTencent = Tencent.createInstance(GlobalConstants.QQAPPID,this);
        initView();
    }
    private void initView() {
        mPhoneEdit = (ClearWriteEditText) findViewById(R.id.de_login_phone);
        mPasswordEdit = (ClearWriteEditText) findViewById(R.id.de_login_password);
        Button mConfirm = (Button) findViewById(R.id.de_login_sign);
        TextView mRegister = (TextView) findViewById(R.id.de_login_register);
        TextView forgetPassword = (TextView) findViewById(R.id.de_login_forgot);
        mYouke = (TextView) findViewById(R.id.de_login_zhijie);
        Other = (TextView) findViewById(R.id.tv_other_login);
        forgetPassword.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        dialog = new Dialog(this, R.style.BottomDialog);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.third_party_landing, null);
        //初始化控件
        mWeixin = (LinearLayout) inflate.findViewById(R.id.ll_tpl_weixin);
        mQQ = (LinearLayout) inflate.findViewById(R.id.ll_tpl_QQ);
        mWeibo = (LinearLayout) inflate.findViewById(R.id.ll_tpl_weibo);
        mWeixin.setOnClickListener(this);
        mQQ.setOnClickListener(this);
        mWeixin.setOnClickListener(this);
        //将布局设置给Dialog
        mYouke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomeActivity
                        .class));
            }
        });


        Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mydialog();
            }
        });
        mImg_Background = (ImageView) findViewById(R.id.de_img_backgroud);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translate_anim);
                mImg_Background.startAnimation(animation);
            }
        }, 200);
        mPhoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    AMUtils.onInactive(mContext, mPhoneEdit);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        String oldPhone = sp.getString(SealConst.SEALTALK_LOGING_PHONE, "");
        String oldPassword = sp.getString(SealConst.SEALTALK_LOGING_PASSWORD, "");

        if (!TextUtils.isEmpty(oldPhone) && !TextUtils.isEmpty(oldPassword)) {
            mPhoneEdit.setText(oldPhone);
            mPasswordEdit.setText(oldPassword);
        }

        if (getIntent().getBooleanExtra("kickedByOtherClient", false)) {
            final AlertDialog dlg = new AlertDialog.Builder(LoginActivity.this).create();
            dlg.show();
            Window window = dlg.getWindow();
            window.setContentView(R.layout.other_devices);
            TextView text = (TextView) window.findViewById(R.id.ok);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dlg.cancel();
                }
            });
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.de_login_sign:
                phoneString = mPhoneEdit.getText().toString().trim();
                passwordString = mPasswordEdit.getText().toString().trim();

                if (TextUtils.isEmpty(phoneString)) {
                    NToast.shortToast(mContext, R.string.phone_number_is_null);
                    mPhoneEdit.setShakeAnimation();
                    return;
                }

                if (!AMUtils.isMobile(phoneString)) {
                    NToast.shortToast(mContext, R.string.Illegal_phone_number);
                    mPhoneEdit.setShakeAnimation();
                    return;
                }

                if (TextUtils.isEmpty(passwordString)) {
                    NToast.shortToast(mContext, R.string.password_is_null);
                    mPasswordEdit.setShakeAnimation();
                    return;
                }
                if (passwordString.contains(" ")) {
                    NToast.shortToast(mContext, R.string.password_cannot_contain_spaces);
                    mPasswordEdit.setShakeAnimation();
                    return;
                }
                LoadDialog.show(mContext);
                editor.putBoolean("exit", false);
                editor.apply();
                String oldPhone = sp.getString(SealConst.SEALTALK_LOGING_PHONE, "");
                request(LOGIN, true);
                break;
            case R.id.de_login_register:
                startActivityForResult(new Intent(this, RegisterActivity.class), 1);
                break;
            case R.id.de_login_forgot:
                startActivityForResult(new Intent(this, ForgetPasswordActivity.class), 2);
                break;
            case R.id.ll_tpl_weixin:
                startActivity(new Intent(LoginActivity.this, WXEntryActivity.class));
                break;
            case R.id.ll_tpl_QQ:
                startActivity(new Intent(LoginActivity.this,AuthActivity.class));

                break;
            case R.id.ll_tpl_weibo:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && data != null) {
            String phone = data.getStringExtra("phone");
            String password = data.getStringExtra("password");
            mPhoneEdit.setText(phone);
            mPasswordEdit.setText(password);
        } else if (data != null && requestCode == 1) {
            String phone = data.getStringExtra("phone");
            String password = data.getStringExtra("password");
            String id = data.getStringExtra("id");
            String nickname = data.getStringExtra("nickname");
            if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(id) && !TextUtils.isEmpty(nickname)) {
                mPhoneEdit.setText(phone);
                mPasswordEdit.setText(password);
                editor.putString(SealConst.SEALTALK_LOGING_PHONE, phone);
                editor.putString(SealConst.SEALTALK_LOGING_PASSWORD, password);
                editor.putString(SealConst.SEALTALK_LOGIN_ID, id);
                editor.putString(SealConst.SEALTALK_LOGIN_NAME, nickname);
                editor.apply();
            }
            if (requestCode == Constants.REQUEST_LOGIN ||
                    requestCode == Constants.REQUEST_APPBAR) {
                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        switch (requestCode) {
            case LOGIN:
                return action.login("86", phoneString, passwordString);
            case GET_TOKEN:
                return action.getToken();
            case SYNC_USER_INFO:
                return action.getUserInfoById(connectResultId);
        }
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        if (result != null) {
            switch (requestCode) {
                case LOGIN:
                    LoginResponse loginResponse = (LoginResponse) result;
                    if (loginResponse.getCode() == 200) {
                        loginToken = loginResponse.getResult().getToken();
                        if (!TextUtils.isEmpty(loginToken)) {
                            RongIM.connect(loginToken, new RongIMClient.ConnectCallback() {
                                @Override
                                public void onTokenIncorrect() {
                                    NLog.e("connect", "onTokenIncorrect");
                                    reGetToken();
                                }

                                @Override
                                public void onSuccess(String s) {
                                    connectResultId = s;
                                    NLog.e("connect", "onSuccess userid:" + s);
                                    editor.putString(SealConst.SEALTALK_LOGIN_ID, s);
                                    editor.apply();
                                    SealUserInfoManager.getInstance().openDB();
                                    request(SYNC_USER_INFO, true);
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {
                                    NLog.e("connect", "onError errorcode:" + errorCode.getValue());
                                }
                            });
                        }
                    } else if (loginResponse.getCode() == 100) {
                        LoadDialog.dismiss(mContext);
                        NToast.shortToast(mContext, R.string.phone_or_psw_error);
                    } else if (loginResponse.getCode() == 1000) {
                        LoadDialog.dismiss(mContext);
                        NToast.shortToast(mContext, R.string.phone_or_psw_error);
                    }
                    break;
                case SYNC_USER_INFO:
                    GetUserInfoByIdResponse userInfoByIdResponse = (GetUserInfoByIdResponse) result;
                    if (userInfoByIdResponse.getCode() == 200) {
                        if (TextUtils.isEmpty(userInfoByIdResponse.getResult().getPortraitUri())) {
                            userInfoByIdResponse.getResult().setPortraitUri(RongGenerate.generateDefaultAvatar(userInfoByIdResponse.getResult().getNickname(), userInfoByIdResponse.getResult().getId()));
                        }
                        String nickName = userInfoByIdResponse.getResult().getNickname();
                        String portraitUri = userInfoByIdResponse.getResult().getPortraitUri();
                        editor.putString(SealConst.SEALTALK_LOGIN_NAME, nickName);
                        editor.putString(SealConst.SEALTALK_LOGING_PORTRAIT, portraitUri);
                        editor.apply();
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(connectResultId, nickName, Uri.parse(portraitUri)));
                    }
                    //不继续在login界面同步好友,群组,群组成员信息
                    SealUserInfoManager.getInstance().getAllUserInfo();
                    goToHome();
                    break;
                case GET_TOKEN:
                    GetTokenResponse tokenResponse = (GetTokenResponse) result;
                    if (tokenResponse.getCode() == 200) {
                        String token = tokenResponse.getResult().getToken();
                        if (!TextUtils.isEmpty(token)) {
                            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                                @Override
                                public void onTokenIncorrect() {
                                    Log.e(TAG, "reToken Incorrect");
                                }

                                @Override
                                public void onSuccess(String s) {
                                    connectResultId = s;
                                    NLog.e("connect", "onSuccess userid:" + s);
                                    editor.putString(SealConst.SEALTALK_LOGIN_ID, s);
                                    editor.apply();
                                    SealUserInfoManager.getInstance().openDB();
                                    request(SYNC_USER_INFO, true);
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode e) {

                                }
                            });
                        }
                    }
                    break;
            }
        }
    }

    private void reGetToken() {
        request(GET_TOKEN);
    }

    @Override
    public void onFailure(int requestCode, int state, Object result) {
        if (!CommonUtils.isNetworkConnected(mContext)) {
            LoadDialog.dismiss(mContext);
            NToast.shortToast(mContext, getString(R.string.network_not_available));
            return;
        }
        switch (requestCode) {
            case LOGIN:
                LoadDialog.dismiss(mContext);
                NToast.shortToast(mContext, R.string.login_api_fail);
                break;
            case SYNC_USER_INFO:
                LoadDialog.dismiss(mContext);
                NToast.shortToast(mContext, R.string.sync_userinfo_api_fail);
                break;
            case GET_TOKEN:
                LoadDialog.dismiss(mContext);
                NToast.shortToast(mContext, R.string.get_token_api_fail);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void goToHome() {
        editor.putString("loginToken", loginToken);
        editor.putString(SealConst.SEALTALK_LOGING_PHONE, phoneString);
        editor.putString(SealConst.SEALTALK_LOGING_PASSWORD, passwordString);
        editor.apply();
        LoadDialog.dismiss(mContext);
        NToast.shortToast(mContext, R.string.login_success);
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
    public void Mydialog() {
        dialog = new Dialog(this, R.style.bottom_menu_style);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.third_party_landing, null);
        //初始化控件
        mWeixin = (LinearLayout) inflate.findViewById(R.id.ll_tpl_weixin);
        mQQ = (LinearLayout) inflate.findViewById(R.id.ll_tpl_QQ);
        mWeibo = (LinearLayout) inflate.findViewById(R.id.ll_tpl_weibo);
        mWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IWXAPI mApi = WXAPIFactory.createWXAPI(LoginActivity.this, GlobalConstants.WXADDID, true);
                mApi.registerApp(GlobalConstants.WXADDID);

                if (mApi != null && mApi.isWXAppInstalled()) {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test_neng";
                    mApi.sendReq(req);
                } else {
                    ToastUtils.showToast(LoginActivity.this, "您还没有安装微信");
                }


            }
        });
        mQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();

            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//    将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        dialog.show();//显示对话框
    }


    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
        }
    }

    /**
     * 继承的到BaseUiListener得到doComplete()的方法信息
     */
    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {//得到用户的ID  和签名等信息  用来得到用户信息
            initOpenidAndToken(values);
            updateUserInfo();
        }
    };

    /**
     * 获取登录QQ腾讯平台的权限信息(用于访问QQ用户信息)
     *
     * @param jsonObject
     */
    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (Exception e) {
        }
    }


    /**
     * function   : 获取用户信息
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/25  下午3:21
     */
    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {
                @Override
                public void onError(UiError e) {
                }

                @Override
                public void onComplete(final Object response) {

                    Gson gson = new Gson();
                    QQUser user = gson.fromJson(response.toString(), QQUser.class);
                    LogUtils.e("昵称：" + user.getNickname() + "  性别:" + user.getGender() + "  地址：" + user.getProvince() + user.getCity() + "头像路径：" + user.getFigureurl_qq_2());

                }

                @Override
                public void onCancel() {
                }
            };
            mInfo = new com.tencent.connect.UserInfo(LoginActivity.this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);
        }
    }

}
