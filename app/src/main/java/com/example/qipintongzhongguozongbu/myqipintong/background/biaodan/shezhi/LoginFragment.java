package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager.MeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.login.w.qqapi.BaseUiListener;
import com.example.qipintongzhongguozongbu.myqipintong.login.w.qqapi.QQUser;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.google.gson.Gson;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/2/25.
 * 登陆界面
 */

public class LoginFragment extends BaseFragment {

    @BindView(R.id.et_login_id)
    EditText etLoginId;
    @BindView(R.id.et_login_password_id)
    EditText etLoginPasswordId;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;
    @BindView(R.id.tv_login_retrieve_password)
    TextView tvLoginRetrievePassword;
    @BindView(R.id.tv_login_user_register)
    TextView tvLoginUserRegister;
    @BindView(R.id.bt_QQ_login)
    Button btQQLogin;
    public static Tencent mTencent;
    Unbinder unbinder;
    private UserInfo mInfo;

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_menu_login, null));
    }


    @Override
    public void initData() {

        mTencent = Tencent.createInstance(GlobalConstants.QQAPPID, mActivity);

        super.initData();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("登陆");
        super.onSupportVisible();
    }

    @OnClick({R.id.et_login_id, R.id.et_login_password_id, R.id.bt_login_login, R.id.tv_login_retrieve_password, R.id.tv_login_user_register,
            R.id.bt_wx_login, R.id.bt_QQ_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_login_id:
//                手机号
                break;
            case R.id.et_login_password_id:
//                密码
                break;
            case R.id.bt_login_login:
                start(new MeFragment());
//                登陆
                break;
            case R.id.tv_login_retrieve_password:
                start(new AmendPasswordFragment());
//                找回密码
                break;
            case R.id.tv_login_user_register:
                start(new RegisterFragmnet());
//                注册
                break;
            case R.id.bt_wx_login:

                IWXAPI mApi = WXAPIFactory.createWXAPI(mActivity, GlobalConstants.WXADDID, true);
                mApi.registerApp(GlobalConstants.WXADDID);

                if (mApi != null && mApi.isWXAppInstalled()) {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test_neng";
                    mApi.sendReq(req);
                } else {
                    ToastUtils.showToast(mActivity, "您还没有安装微信");
                }

                break;
            case R.id.bt_QQ_login:

                onClickLogin();

                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /***
     * QQ平台返回返回数据个体 loginListener的values
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);


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
            mInfo = new UserInfo(mActivity, mTencent.getQQToken());
            mInfo.getUserInfo(listener);
        }
    }

}