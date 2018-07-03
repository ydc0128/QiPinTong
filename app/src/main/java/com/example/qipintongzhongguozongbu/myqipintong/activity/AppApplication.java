package com.example.qipintongzhongguozongbu.myqipintong.activity;


import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.labei.LifeHelper;
import com.example.qipintongzhongguozongbu.myqipintong.labei.SQLHelper;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.RongDatabaseDriver;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.RongDatabaseFilesProvider;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealAppContext;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealUserInfoManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.db.Friend;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.message.TestMessage;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.message.provider.ContactNotificationMessageProvider;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.message.provider.TestMessageProvider;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.pinyin.CharacterParser;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.NLog;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.RongGenerate;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.stetho.RongDbFilesDumperPlugin;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.UserDetailActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.utils.SharedPreferencesContext;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.inspector.database.DefaultDatabaseConnectionProvider;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.QbSdk;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.rongcloud.contactcard.ContactCardExtensionModule;
import cn.rongcloud.contactcard.IContactCardClickListener;
import cn.rongcloud.contactcard.IContactCardInfoProvider;
import cn.rongcloud.contactcard.message.ContactMessage;
import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.display.FadeInBitmapDisplayer;
import io.rong.imkit.RongExtensionManager;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.LocationMessageItemProvider;
import io.rong.imkit.widget.provider.RealTimeLocationMessageProvider;
import io.rong.imlib.ipc.RongExceptionHandler;
import io.rong.imlib.model.UserInfo;
import io.rong.recognizer.RecognizeExtensionModule;


/**
 * Description: 全局初始化类
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/9 下午2:48
 */
public class AppApplication extends MultiDexApplication {//继承的MultiDexApplication为了dex分包使用
    private static AppApplication mAppApplication;
    private SQLHelper sqlHelper;
    private LifeHelper lifeHelper;
    private static DisplayImageOptions options;

    private IWXAPI wxapi;
    private static RequestQueue queue;
//    String token1 = "RpZDZPAuIjhSkt3+byFXv7Kl9MC0YtbMe81ao8MUBDhYNBWsQSqs8GbcnERF9d1emzl893WDMk9MEBvHzToUgw==";


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        regToWx();

        initTBS();

        mAppApplication = this;
        ZXingLibrary.initDisplayOpinion(this);//初始化ZXing
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
        // 初始化 JPush
        queue = Volley.newRequestQueue(this);
        //初始化volley
        initImageLoader();
        inRongyun();
    }


    /**
     * function   : 初始化webViewX5内核
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/18  下午5:09
     */
    private void initTBS() {

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    /**
     * function   : 注册微信
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/12  下午5:53
     */
    private void regToWx() {

        wxapi = WXAPIFactory.createWXAPI(this, GlobalConstants.WXADDID, true);

        wxapi.registerApp(GlobalConstants.WXADDID);

    }


    /**
     * 获取Application
     */
    public static AppApplication getApp() {
        return mAppApplication;
    }

    /**
     * 获取数据库Helper
     */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mAppApplication);
        return sqlHelper;
    }

    public LifeHelper getLifeHelper() {

        if (lifeHelper == null) {
            lifeHelper = new LifeHelper(mAppApplication);
        }
        return lifeHelper;
    }

    /**
     * 摧毁应用进程时候调用
     */
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
    }


    //volley消息队列入口
    public static RequestQueue getQueue() {
        return queue;
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }

    private void inRongyun() {
        Stetho.initialize(new Stetho.Initializer(this) {
            @Override
            protected Iterable<DumperPlugin> getDumperPlugins() {
                return new Stetho.DefaultDumperPluginsBuilder(AppApplication.this)
                        .provide(new RongDbFilesDumperPlugin(AppApplication.this, new RongDatabaseFilesProvider(AppApplication.this)))
                        .finish();
            }

            @Override
            protected Iterable<ChromeDevtoolsDomain> getInspectorModules() {
                Stetho.DefaultInspectorModulesBuilder defaultInspectorModulesBuilder = new Stetho.DefaultInspectorModulesBuilder(AppApplication.this);
                defaultInspectorModulesBuilder.provideDatabaseDriver(new RongDatabaseDriver(AppApplication.this, new RongDatabaseFilesProvider(AppApplication.this), new DefaultDatabaseConnectionProvider()));
                return defaultInspectorModulesBuilder.finish();
            }
        });

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

//            LeakCanary.install(this);//内存泄露检测
//            RongPushClient.registerHWPush(this);
//            RongPushClient.registerMiPush(this, "2882303761517473625", "5451747338625");
//            try {
//                RongPushClient.registerGCM(this);
//            } catch (RongException e) {
//                e.printStackTrace();
//            }

            /**
             * 注意：
             *
             * IMKit SDK调用第一步 初始化
             *
             * context上下文
             *
             * 只有两个进程需要初始化，主进程和 push 进程
             */
            //RongIM.setServerInfo("nav.cn.ronghub.com", "img.cn.ronghub.com");
            RongIM.init(this);
            NLog.setDebug(true);//Seal Module Log 开关
            SealAppContext.init(this);
            SharedPreferencesContext.init(this);
//            RongIM.setLocationProvider((RongIM.LocationProvider) this);
            Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(this));

            try {
                RongIM.registerMessageTemplate(new ContactNotificationMessageProvider());
                RongIM.registerMessageTemplate(new RealTimeLocationMessageProvider());
                RongIM.registerMessageType(TestMessage.class);
                RongIM.registerMessageTemplate(new TestMessageProvider());
                RongIM.registerMessageTemplate(new LocationMessageItemProvider());

            } catch (Exception e) {
                e.printStackTrace();
            }

            openSealDBIfHasCachedToken();

            options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.de_default_portrait)
                    .showImageOnFail(R.drawable.de_default_portrait)
                    .showImageOnLoading(R.drawable.de_default_portrait)
                    .displayer(new FadeInBitmapDisplayer(300))
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();

            //RongExtensionManager.getInstance().registerExtensionModule(new PTTExtensionModule(this, true, 1000 * 60));
            RongExtensionManager.getInstance().registerExtensionModule(new ContactCardExtensionModule(new IContactCardInfoProvider() {
                @Override
                public void getContactCardInfoProvider(final IContactCardInfoCallback contactInfoCallback) {
                    SealUserInfoManager.getInstance().getFriends(new SealUserInfoManager.ResultCallback<List<Friend>>() {
                        @Override
                        public void onSuccess(List<Friend> friendList) {
                            contactInfoCallback.getContactCardInfoCallback(friendList);
                        }

                        @Override
                        public void onError(String errString) {
                            contactInfoCallback.getContactCardInfoCallback(null);
                        }
                    });
                }
            }, new IContactCardClickListener() {
                @Override
                public void onContactCardClick(View view, ContactMessage content) {
                    Intent intent = new Intent(view.getContext(), UserDetailActivity.class);
                    Friend friend = SealUserInfoManager.getInstance().getFriendByID(content.getId());
                    if (friend == null) {
                        UserInfo userInfo = new UserInfo(content.getId(), content.getName(),
                                Uri.parse(TextUtils.isEmpty(content.getImgUrl()) ? RongGenerate.generateDefaultAvatar(content.getName(), content.getId()) : content.getImgUrl()));
                        friend = CharacterParser.getInstance().generateFriendFromUserInfo(userInfo);
                    }
                    intent.putExtra("friend", friend);
                    view.getContext().startActivity(intent);
                }
            }));
            RongExtensionManager.getInstance().registerExtensionModule(new RecognizeExtensionModule());
        }
    }

    public static DisplayImageOptions getOptions() {
        return options;
    }

    private void openSealDBIfHasCachedToken() {
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        String cachedToken = sp.getString("loginToken", "");
        if (!TextUtils.isEmpty(cachedToken)) {
            String current = getCurProcessName(this);
            String mainProcessName = getPackageName();
            if (mainProcessName.equals(current)) {
                SealUserInfoManager.getInstance().openDB();
            }
        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
