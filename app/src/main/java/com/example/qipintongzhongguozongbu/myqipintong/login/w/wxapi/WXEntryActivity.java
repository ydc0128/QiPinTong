package com.example.qipintongzhongguozongbu.myqipintong.login.w.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Description: 微信登录的处理类 用于获取微信用户的信息
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/14 下午4:21
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI mWeixinAPI;
    private static String uuid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeixinAPI = WXAPIFactory.createWXAPI(this, GlobalConstants.WXADDID, true);
        mWeixinAPI.handleIntent(this.getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWeixinAPI.handleIntent(intent, this);//必须调用此句话
    }

    //微信发送的请求将回调到onReq方法
    @Override
    public void onReq(BaseReq req) {
        LogUtils.e("onReq");
    }

    //发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp resp) {
        LogUtils.e("onResp");
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                LogUtils.e("ERR_OK");
                //发送成功
                SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                if (sendResp != null) {
                    String code = sendResp.code;
                    getAccess_token(code);
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                LogUtils.e("ERR_USER_CANCEL");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                LogUtils.e("ERR_AUTH_DENIED");
                //发送被拒绝
                break;
            default:
                //发送返回
                break;
        }

    }

    /**
     * 获取openid accessToken值用于后期操作
     *
     * @param code 请求码
     */
    private void getAccess_token(final String code) {//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + GlobalConstants.WXADDID
                + "&secret="
                + GlobalConstants.APP_SECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";
        LogUtils.e("getAccess_token：" + path);
        LogUtils.e(code);

        Map<String, String> map = new HashMap<String, String>();
        map.put("getAccess_token", "getAccess_token");

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(path, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                LogUtils.e("getAccess_token_result:" + response);
                JSONObject jsonObject = null;

                try {
                    jsonObject = new JSONObject(response);
                    String openid = jsonObject.getString("openid").toString().trim();
                    String access_token = jsonObject.getString("access_token").toString().trim();
                    getUserMesg(access_token, openid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("失败" + error);
            }
        });

        requestQueue.add(request);


    }

    /**
     * 获取微信的个人信息
     *
     * @param access_token
     * @param openid
     */

    private void getUserMesg(final String access_token, final String openid) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
        LogUtils.e("getUserMesg：" + path);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(path, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                LogUtils.e("getUserMesg_result:" + response);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    String nickname = jsonObject.getString("nickname");
                    int sex = Integer.parseInt(jsonObject.get("sex").toString());
                    String headimgurl = jsonObject.getString("headimgurl");

                    String userName = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
                    //名字需要转码

                    LogUtils.e("用户基本信息:");
                    LogUtils.e("nickname:" + userName);
                    LogUtils.e("sex:" + sex);
                    LogUtils.e("headimgurl:" + headimgurl);

                    if (sex == 1) {
                        LogUtils.e("男");
                        ToastUtils.showToast(WXEntryActivity.this, "用户名字" + userName + "              性别" + "          男");
                    } else if (sex == 2) {
                        ToastUtils.showToast(WXEntryActivity.this, "用户名字" + userName + "               性别" + "          女");
                        LogUtils.e("女");
                    } else if (sex == 0) {
                        ToastUtils.showToast(WXEntryActivity.this, "用户名字" + userName + "               性别" + "         不明");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                finish();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.e("失败" + error);
            }
        });

        requestQueue.add(request);


    }

}
