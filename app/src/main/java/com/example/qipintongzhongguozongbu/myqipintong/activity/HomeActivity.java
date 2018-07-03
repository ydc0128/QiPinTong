package com.example.qipintongzhongguozongbu.myqipintong.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.TabEntity;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager.DetectFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager.HostFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager.MeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.webview.WebFragment;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.HomeWatcherReceiver;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.NToast;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.widget.LoadDialog;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.LoginActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.NewFriendListActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.SealSearchActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.adapter.ConversationListAdapterEx;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.widget.MorePopWindow;
import com.example.qipintongzhongguozongbu.myqipintong.schoolpager.EasyknowSchoolFragment;
import com.example.qipintongzhongguozongbu.myqipintong.utils.PrefUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.message.ContactNotificationMessage;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static com.example.qipintongzhongguozongbu.myqipintong.R.id.fl_hometop;

/**
 * Description: 主页
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/3 上午11:40
 */
public class HomeActivity extends SupportActivity implements View.OnClickListener, IUnReadMessageObserver {

    public FragmentManager supportFragmentManager;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.iv_home_camera)
    ImageView ivHomeCamera;
    @BindView(R.id.rl_hometop)
    RelativeLayout rlHometop;
    @BindView(fl_hometop)
    FrameLayout flHometop;
    @BindView(R.id.ll_button)
    CommonTabLayout llButton;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.tv_home_city)
    TextView tvHomeCity;
    @BindView(R.id.vv_search)
    View vvSearch;
    @BindView(R.id.iv_home_scan)
    ImageView ivHomeScan;
    @BindView(R.id.rv_home_home_top)
    RelativeLayout rvHomeHomeTop;
    @BindView(R.id.rl_home_activity)
    RelativeLayout rlHomeActivity;
    @BindView(R.id.tv_top_txl)
    TextView tvTopTxl;
    @BindView(R.id.ac_iv_search)
    ImageView acIvSearch;
    @BindView(R.id.seal_more)
    ImageView sealMore;
    @BindView(R.id.rl_xiaoxi_top)
    RelativeLayout rlXiaoxiTop;


    private String[] mTitles = {"首页", "消息", "发现", "我"};
    private int[] mIconUnselectIds = {
            R.mipmap.home_false, R.mipmap.message_false,
            R.mipmap.discover_false, R.mipmap.me_false};
    private int[] mIconSelectIds = {
            R.mipmap.home_true, R.mipmap.message_true,
            R.mipmap.discover_true, R.mipmap.me_true};


//    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


    //    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ConversationListFragment mConversationListFragment = null;
    private boolean isDebug;
    private Context mContext;
    private Conversation.ConversationType[] mConversationsTypes = null;
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    String token1 = "RpZDZPAuIjhSkt3+byFXv7Kl9MC0YtbMe81ao8MUBDhYNBWsQSqs8GbcnERF9d1emzl893WDMk9MEBvHzToUgw==";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = this;
        isDebug = getSharedPreferences("config", MODE_PRIVATE).getBoolean("isDebug", false);
        ivBack.setVisibility(View.GONE);
        supportFragmentManager = getSupportFragmentManager();
        PrefUtils.putBoolean(getBaseContext(), "isFirstin", false);
        //标记 从引导页进入主页面
        StatusBarUtil.setColor(HomeActivity.this, getResources().getColor(R.color.colorbluebg));
        //设置状态栏颜色
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_content, HostFragment.getInstance());
        }
        onDragOut();
        selectFragment();
        setTitleDate();
        hintKbTwo();
        initData();
    }

    private void xiaoxi() {

        rlXiaoxiTop.setVisibility(View.VISIBLE);
        acIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SealSearchActivity.class));
            }
        });
        sealMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MorePopWindow morePopWindow = new MorePopWindow(HomeActivity.this);
                morePopWindow.showPopupWindow(sealMore);
            }
        });
    }

    /**
     * function   : 根据极光推送的消息内容判断 决定显示哪一个fragment
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/17  上午11:43
     */
    private void selectFragment() {

        int type = getIntent().getIntExtra("TYPE", 0);

        switch (type) {
            case 0://未识别到推送主题 默认打开主页
                replaceLoadRootFragment(R.id.fl_content, HostFragment.getInstance(), false);
                break;
            case 1://新闻
                replaceLoadRootFragment(R.id.fl_content, EasyknowSchoolFragment.getInstance(), false);
                break;
            case 2://消息
                replaceLoadRootFragment(R.id.fl_content, HostFragment.getInstance(), false);
                break;
            case 3://文章
                replaceLoadRootFragment(R.id.fl_content, WebFragment.getInstance(), false);
                break;

        }

    }

    /**
     * function   : 开启fragment的动画效果
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/3  下午4:11
     */
    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
        // 设置无动画
        // return new DefaultNoAnimator();
        // 设置自定义动画
        // return new FragmentAnimator(enter,exit,popEnter,popExit);

        // 默认竖向(和安卓5.0以上的动画相同)
        //return super.onCreateFragmentAnimator();
    }

    /**
     * function   : 绝对布局
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/18  上午10:39
     */
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

    /**
     * function   : 设置底部导航栏数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/13  下午6:04
     */

    private void setTitleDate() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        llButton.setTabData(mTabEntities);
        llButton.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        rlHometop.setVisibility(View.VISIBLE);
                        rlXiaoxiTop.setVisibility(View.GONE);
                        replaceLoadRootFragment(R.id.fl_content, HostFragment.getInstance(), false);
                        break;
                    case 1:
                        xiaoxi();
                        Fragment conversationList = initConversationList();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fl_content, conversationList).commit();
                        RongIM.connect(token1, new RongIMClient.ConnectCallback() {
                            @Override
                            public void onTokenIncorrect() {
                                Log.e("this", "---onTokenIncorrect--");
                            }

                            @Override
                            public void onSuccess(String s) {
                                Log.i("this", "---onSuccess--" + s);
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode e) {
                                Log.e("this", "---onError--" + e);
                            }

                        });
                        break;
                    case 2:
                        rlHometop.setVisibility(View.VISIBLE);
                        rlXiaoxiTop.setVisibility(View.GONE);
                        replaceLoadRootFragment(R.id.fl_content, DetectFragment.getInstance(), false);
                        break;
                    case 3:
                        rlHometop.setVisibility(View.VISIBLE);
                        rlXiaoxiTop.setVisibility(View.GONE);
                        replaceLoadRootFragment(R.id.fl_content, MeFragment.getInstance(), false);
                        break;

                }

            }
            @Override
            public void onTabReselect(int position) {
                switch (position) {
                    case 0:
                        rlXiaoxiTop.setVisibility(View.GONE);
                        rlHometop.setVisibility(View.VISIBLE);
                        replaceLoadRootFragment(R.id.fl_content, HostFragment.getInstance(), false);
                        break;
                    case 1:
                        xiaoxi();
                        Fragment conversationList = initConversationList();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fl_content, conversationList).commit();
                        RongIM.connect(token1, new RongIMClient.ConnectCallback() {
                            @Override
                            public void onTokenIncorrect() {
                                Log.e("this", "---onTokenIncorrect--");
                            }

                            @Override
                            public void onSuccess(String s) {
                                Log.i("this", "---onSuccess--" + s);
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode e) {
                                Log.e("this", "---onError--" + e);
                            }

                        });
                        break;
                    case 2:
                        rlHometop.setVisibility(View.VISIBLE);
                        rlXiaoxiTop.setVisibility(View.GONE);
                        replaceLoadRootFragment(R.id.fl_content, DetectFragment.getInstance(), false);
                        break;
                    case 3:
                        rlHometop.setVisibility(View.VISIBLE);
                        rlXiaoxiTop.setVisibility(View.GONE);
                        replaceLoadRootFragment(R.id.fl_content, MeFragment.getInstance(), false);
                        break;
                }

            }
        });
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
                break;
        }
    }

    private Fragment initConversationList() {

        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            if (isDebug) {
                uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //璁剧疆绉佽亰浼氳瘽鏄惁鑱氬悎鏄剧ず
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//缇ょ粍
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//鍏叡鏈嶅姟鍙?                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//璁㈤槄鍙?                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//绯荤粺
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.DISCUSSION
                };

            } else {
                uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //璁剧疆绉佽亰浼氳瘽鏄惁鑱氬悎鏄剧ず
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//缇ょ粍
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//鍏叡鏈嶅姟鍙?                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//璁㈤槄鍙?                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//绯荤粺
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM
                };
            }
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }




    protected void initData() {

        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };

        RongIM.getInstance().addUnReadMessageCountChangedObserver((IUnReadMessageObserver) this, conversationTypes);
        getConversationPush();// 获取 push 的 id 和 target
        getPushMessage();
    }

    private void getConversationPush() {
        if (getIntent() != null && getIntent().hasExtra("PUSH_CONVERSATIONTYPE") && getIntent().hasExtra("PUSH_TARGETID")) {

            final String conversationType = getIntent().getStringExtra("PUSH_CONVERSATIONTYPE");
            final String targetId = getIntent().getStringExtra("PUSH_TARGETID");


            RongIM.getInstance().getConversation(Conversation.ConversationType.valueOf(conversationType), targetId, new RongIMClient.ResultCallback<Conversation>() {
                @Override
                public void onSuccess(Conversation conversation) {

                    if (conversation != null) {

                        if (conversation.getLatestMessage() instanceof ContactNotificationMessage) { //好友消息的push
                            startActivity(new Intent(HomeActivity.this, NewFriendListActivity.class));
                        } else {
                            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon().appendPath("conversation")
                                    .appendPath(conversationType).appendQueryParameter("targetId", targetId).build();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {

                }
            });
        }
    }

    /**
     * 得到不落地 push 消息
     */
    private void getPushMessage() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {
            String path = intent.getData().getPath();
            if (path.contains("push_message")) {
                SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
                String cacheToken = sharedPreferences.getString("loginToken", "");
                if (TextUtils.isEmpty(cacheToken)) {
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                } else {
                    if (!RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED)) {
                        LoadDialog.show(mContext);
                        RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
                            @Override
                            public void onTokenIncorrect() {
                                LoadDialog.dismiss(mContext);
                            }

                            @Override
                            public void onSuccess(String s) {
                                LoadDialog.dismiss(mContext);
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode e) {
                                LoadDialog.dismiss(mContext);
                            }
                        });
                    }
                }
            }
        }
    }

    public void onCountChanged(int count) {
        llButton.setVisibility(View.VISIBLE);
        llButton.showMsg(1, 9);//设置消息列表未读数

        llButton.showDot(1);//设置红点位置


    }


    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        RongIM.getInstance().removeUnReadMessageCountChangedObserver((IUnReadMessageObserver) this);
        if (mHomeKeyReceiver != null)
            this.unregisterReceiver(mHomeKeyReceiver);
        super.onDestroy();
    }

    public void onDragOut() {
////        mUnreadNumView.setVisibility(View.GONE);
        NToast.shortToast(mContext, getString(R.string.clear_success));
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                if (conversations != null && conversations.size() > 0) {
                    for (Conversation c : conversations) {
                        RongIM.getInstance().clearMessagesUnreadStatus(c.getConversationType(), c.getTargetId(), null);
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        }, mConversationsTypes);

    }

    private HomeWatcherReceiver mHomeKeyReceiver = null;

    //如果遇见 Android 7.0 系统切换到后台回来无效的情况 把下面注册广播相关代码注释或者删除即可解决。下面广播重写 home 键是为了解决三星 note3 按 home 键花屏的一个问题
    private void registerHomeKeyReceiver(Context context) {
        if (mHomeKeyReceiver == null) {
            mHomeKeyReceiver = new HomeWatcherReceiver();
            final IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            try {
                context.registerReceiver(mHomeKeyReceiver, homeFilter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}