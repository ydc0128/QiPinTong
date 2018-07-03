package com.example.qipintongzhongguozongbu.myqipintong.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealAppContext;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.LoginActivity;

import io.rong.imkit.RongIM;


/**
 * ??????????
 * ???????? on 2017/5/4 15:21
 * ???ydc_0128@163.com
 */
public class SplashActivity extends Activity {

    private Context context;
    private android.os.Handler handler = new android.os.Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        String cacheToken = sp.getString("loginToken", "");
        if (!TextUtils.isEmpty(cacheToken)) {
            RongIM.connect(cacheToken, SealAppContext.getInstance().getConnectCallback());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToMain();
                }
            }, 800);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToLogin();
                }
            }, 800);
        }
    }


    private void goToMain() {
        startActivity(new Intent(context, HomeActivity.class));
        finish();
    }

    private void goToLogin() {
        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

}
