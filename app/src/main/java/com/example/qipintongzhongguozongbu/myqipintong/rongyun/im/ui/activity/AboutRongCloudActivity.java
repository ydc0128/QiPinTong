package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealConst;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.UpdateService;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.NToast;


public class AboutRongCloudActivity extends BaseAActivity {

    private boolean isHasNewVersion;
    private ImageView mNewVersionView;
    private String url;
    long[] mHits = new long[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle(R.string.set_rongcloud);

        RelativeLayout mUpdateLog = (RelativeLayout) findViewById(R.id.rl_update_log);
        RelativeLayout mFunctionIntroduce = (RelativeLayout) findViewById(R.id.rl_function_introduce);
        RelativeLayout mRongCloudWeb = (RelativeLayout) findViewById(R.id.rl_rongcloud_web);
        mNewVersionView = (ImageView) findViewById(R.id.about_sealtalk_version);
        RelativeLayout mVersionItem = (RelativeLayout) findViewById(R.id.rl_version);
        TextView version = (TextView) findViewById(R.id.sealtalk_version);
        RelativeLayout mCloseDebug = (RelativeLayout) findViewById(R.id.close_debug);
        version.setText(SealConst.SEALTALKVERSION);
        mUpdateLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutRongCloudActivity.this, UpdateLogActivity.class));
            }
        });
        mFunctionIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutRongCloudActivity.this, FunctionIntroducedActivity.class));
            }
        });

        mRongCloudWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutRongCloudActivity.this, QiPinTongWebActivity.class));
            }
        });
        url = getIntent().getStringExtra("url");
        isHasNewVersion = getIntent().getBooleanExtra("isHasNewVersion", false);
        if (isHasNewVersion) {
            mNewVersionView.setVisibility(View.VISIBLE);
            mVersionItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mNewVersionView.setVisibility(View.GONE);
                    final AlertDialog dlg = new AlertDialog.Builder(AboutRongCloudActivity.this).create();
                    dlg.show();
                    Window window = dlg.getWindow();
                    window.setContentView(R.layout.dialog_download);
                    TextView text = (TextView) window.findViewById(R.id.friendship_content1);
                    TextView photo = (TextView) window.findViewById(R.id.friendship_content2);
                    text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse(url);
                            intent.setData(content_url);
                            startActivity(intent);
                            dlg.cancel();
                        }
                    });
                    photo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NToast.shortToast(mContext, getString(R.string.downloading_apk));
                            UpdateService.Builder.create(url)
                                    .setStoreDir("update/flag")
                                    .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                                    .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                                    .build(mContext);
                            dlg.cancel();
                        }
                    });
                    isHasNewVersion = false;
                }
            });
            getVersionInfo();
        }


    }
    private String[] getVersionInfo() {
        String[] version = new String[2];

        PackageManager packageManager = getPackageManager();

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            version[0] = String.valueOf(packageInfo.versionCode);
            version[1] = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }
}
