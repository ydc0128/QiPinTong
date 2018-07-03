package com.example.qipintongzhongguozongbu.myqipintong.login.w.wxapi.qqapi;

import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * Description: QQ登录的监听类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/25 下午3:30
 */
public class BaseUiListener implements IUiListener {
    @Override
    public void onComplete(Object response) {
        if (null == response) {
            LogUtils.e("登录失败");
            return;
        }
        JSONObject jsonResponse = (JSONObject) response;
        if (null != jsonResponse && jsonResponse.length() == 0) {
            LogUtils.e("登录失败");
            return;
        }
        LogUtils.e("登录成功");
        doComplete((JSONObject) response);
    }

    protected void doComplete(JSONObject values) {

    }

    @Override
    public void onError(UiError e) {
        //登录错误
    }

    @Override
    public void onCancel() {
        // 运行完成
    }
}
