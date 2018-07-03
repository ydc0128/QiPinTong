package com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.android.volley.Request;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.Volley.VolleyHandler;
import com.example.qipintongzhongguozongbu.myqipintong.Volley.VolleyHttpRequest;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvHomeFaceAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvHomeIconAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNewHomeCompanyAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.VpBannerAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.rvNewFullTimeAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ComTypeData;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeBean;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ItemTypeDate;
import com.example.qipintongzhongguozongbu.myqipintong.dateserver.HomeDatafromServer;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvRightFifteenDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.CustomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.event.LocationEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia.CreateWorldFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.jianzhidaren.TimeJobFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian.CompanyDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian.FamousCompanyFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.quanzhisupin.FullTimeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.webview.WebDetalisFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.xiaoyuanzhipin.SchoolPagerFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FaceAndTalentDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FaceAndTalentFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceFragment;
import com.example.qipintongzhongguozongbu.myqipintong.index.LocationFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.schoolpager.EasyknowSchoolFragment;
import com.example.qipintongzhongguozongbu.myqipintong.utils.CacheUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.PrefUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.StringUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.AutoVerticalScrollTextView;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullableScrollView;
import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * Description: 主页
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/3 下午9:32
 */
public class HomeFragment extends BaseFragment implements AMapLocationListener {


    Unbinder unbinder;
    @BindView(R.id.pull_icon)
    ImageView pullIcon;
    @BindView(R.id.refreshing_icon)
    CircleImageView refreshingIcon;
    @BindView(R.id.state_tv)
    TextView stateTv;
    @BindView(R.id.state_iv)
    ImageView stateIv;
    @BindView(R.id.head_view)
    RelativeLayout headView;
    @BindView(R.id.bb_home)
    Banner bbHome;
    @BindView(R.id.rv_home_icon)
    RecyclerView rvHomeIcon;
    @BindView(R.id.iv_home_money)
    ImageView ivHomeMoney;
    @BindView(R.id.bt_home_take)
    Button btHomeTake;
    @BindView(R.id.iv_home_easy_school)
    ImageView ivHomeEasySchool;
    @BindView(R.id.avst_home_consult)
    AutoVerticalScrollTextView avstHomeConsult;
    @BindView(R.id.iv_arrows)
    ImageView ivArrows;
    @BindView(R.id.vp_home)
    ViewPager vpHome;
    @BindView(R.id.fiv_indicator)
    FixedIndicatorView fivIndicator;
    @BindView(R.id.rv_home_face)
    RecyclerView rvHomeFace;
    @BindView(R.id.tv_home_face_show_more)
    TextView tvHomeFaceShowMore;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.rv_home_company)
    RecyclerView rvHomeCompany;
    @BindView(R.id.tv_home_company_show_more)
    TextView tvHomeCompanyShowMore;
    @BindView(R.id.rv_home_job_work)
    RecyclerView rvHomeJobWork;
    @BindView(R.id.rl_home_Full_Time)
    RelativeLayout rlHomeFullTime;
    @BindView(R.id.sv_home)
    PullableScrollView svHome;
    @BindView(R.id.pullup_icon)
    ImageView pullupIcon;
    @BindView(R.id.loading_icon)
    CircleImageView loadingIcon;
    @BindView(R.id.loadstate_tv)
    TextView loadstateTv;
    @BindView(R.id.loadstate_iv)
    ImageView loadstateIv;
    @BindView(R.id.loadmore_view)
    RelativeLayout loadmoreView;
    @BindView(R.id.rf_home)
    PullToRefreshLayout rfHome;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption = null;


    private IndicatorViewPager indicatorViewPager;
    private ArrayList list;
    private String[] mTitles = {"全职速聘", "高薪兼职"};
    private BannerComponent bannerComponent;
    private int number = 0;
    ArrayList iconList = new ArrayList<>();
    private String[] IconName = {"全职速聘", "兼职达人", "校园直聘", "名企在线", "有才有貌", "创孵天下", "有商有客", "易通学院"};
    private int[] iconImage = {R.mipmap.full_time, R.mipmap.time_job, R.mipmap.school_find, R.mipmap.company, R.mipmap.face_good, R.mipmap.create_word, R.mipmap.youshangmain, R.mipmap.easy_school};
    private Handler handler;
    private RvHomeIconAdapter rvHomeIconAdapter;
    private int isLocation = 0;

    private static final int REQUEST_CODE = 111;//启动扫码页面的请求码
    private static final int PERMISSION_CODE = 100;//相机,定位的权限请求码
    private static int PERMISSION_TYPE; //权限的类型 用于区别是定位还是相机权限的请求
    private static final int LOCATION_REQUST = 0;//定位权限的标识码
    private static final int CAMERA_REQUST = 1;//相机权限的标识码
    private RvNewHomeCompanyAdapter rvNewHomeCompanyAdapter;
    private CustomDialog mDialod;
    private View mVvSearch;
    private ImageView mIvScan;
    private TextView mTvLocation;


    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
//        mVvSearch = mActivity.findViewById(R.id.vv_search);
//        mIvScan = (ImageView) mActivity.findViewById(R.id.iv_home_scan);
//        mTvLocation = (TextView) mActivity.findViewById(R.id.tv_home_location);
        return view;
    }


    @Override
    public void onSupportVisible() {

        mButton.setVisibility(View.VISIBLE);

        mTop.setVisibility(View.VISIBLE);

        super.onSupportVisible();
    }


    @Override
    public void initData() {

        EventBus.getDefault().register(this);
        //注册监听
        //setRvHomeIconAdapter();

        super.initData();

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        // getDataServer();

        setRefresh();

        //setTopButtonClick();


        boolean isShowDialog = PrefUtils.getBoolean(mActivity, "isShowDialog", true);
        if (isShowDialog) {
            showDialogLocation();
        }

        super.onLazyInitView(savedInstanceState);
    }

    /**
     * function   : 顶部按钮的点击事件
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/11  上午9:52
     */
    private void setTopButtonClick() {
//
//        mIvScan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                PERMISSION_TYPE = CAMERA_REQUST;
//
//                setCameraPermission();
//            }
//        });
//
//        mTvLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isLocation = 1;
//
//                setLocationPermission();
//
//                PERMISSION_TYPE = LOCATION_REQUST;
//            }
//        });
//
//        mVvSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                start(new SearchFragment());
//            }
//        });
    }


    /**
     * function   : 弹窗询问用户是否允许获取位置信息
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/10  下午3:02
     */
    private void showDialogLocation() {
        mDialod = new CustomDialog(mActivity, R.style.MyDialog) {

            @Override
            public View getView() {
                View view = View.inflate(mActivity, R.layout.dialog_location, null);
                TextView mTvYes = (TextView) view.findViewById(R.id.tv_dialog_yes);
                TextView mTvNo = (TextView) view.findViewById(R.id.tv_dialog_no);


                mTvYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PrefUtils.putBoolean(mActivity, "isShowDialog", false);
                        setLocationPermission();
                        mDialod.dismiss();
                    }
                });
                mTvNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        PrefUtils.putBoolean(mActivity, "isShowDialog", true);
                        mDialod.dismiss();
                    }
                });
                return view;
            }
        };

        mDialod.show();
    }

    /**
     * function   : 图标的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/17  下午3:37
     */
    private void setRvHomeIconAdapter() {

        rvHomeIcon.setLayoutManager(new GridLayoutManager(mActivity, 4));

        rvHomeIconAdapter = new RvHomeIconAdapter(R.layout.item_home_icon, getIconDate());

        rvHomeIcon.setAdapter(rvHomeIconAdapter);

        rvHomeIcon.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0://全职速聘
                        start(new FullTimeFragment());
                        break;
                    case 1://兼职达人
                        start(new TimeJobFragment());
                        break;
                    case 2://校园直聘
                        start(new SchoolPagerFragment());
                        break;
                    case 3://名企在线
                        start(new FamousCompanyFragment());
                        break;
                    case 4://有才有貌
                        start(new FaceAndTalentFragment());
                        break;
                    case 5://创孵天下
                        start(new CreateWorldFragment());
                        break;
                    case 6://有商有客
                        start(new CommerceFragment());
                        break;
                    case 7://易通学院
                        start(new EasyknowSchoolFragment());
                        break;

                }
            }
        });

    }


    /**
     * function   : 设置手动轮播图
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/1  下午3:15
     *
     * @param midBanners
     */
    private void setHandBanner(List<HomeBean.MidBannersBean> midBanners) {
        //手动轮播图适配器
        indicatorViewPager = new IndicatorViewPager(fivIndicator, vpHome);
        indicatorViewPager.setAdapter(new VpBannerAdapter(this, mActivity, midBanners));
        bannerComponent = new BannerComponent(fivIndicator, vpHome, false);
        bannerComponent.setAdapter(new VpBannerAdapter(this, mActivity, midBanners));
    }


    /**
     * function   : 名企在线条目
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/1  上午10:22
     */
    private void setRvHomeCompanyAdapter(final List<HomeBean.ComListBean> comList) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(HORIZONTAL);
        rvHomeCompany.setLayoutManager(linearLayoutManager);
        rvHomeCompany.addItemDecoration(new RvRightFifteenDecoration());

        rvNewHomeCompanyAdapter = new RvNewHomeCompanyAdapter(getComDate(comList));
        rvHomeCompany.setAdapter(rvNewHomeCompanyAdapter);

        rvHomeCompany.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (getComDate(comList).size() - 1 == position) {
                    ToastUtils.showToast(mActivity, "查看更多");
                } else {
                    start(new CompanyDetailsFragment());
                }
            }
        });


    }

    /**
     * function   : 名企在线 这里根据数据集合来决定加载展示数据的布局个数
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/21  下午5:52
     */
    public List getComDate(List<HomeBean.ComListBean> ComList) {
        ArrayList<ComTypeData> list = new ArrayList<ComTypeData>();

        for (int i = 0; i < ComList.size(); i++) {
            list.add(new ComTypeData(ComTypeData.IMAGE_TYPE, ComList));
        }
        list.add(new ComTypeData(ComTypeData.MORE_TYPE, ComList));
        return list;
    }

    /**
     * function   : 有才有貌条目适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/1  上午10:19
     *
     * @param faceList
     */
    private void setRvHomeFaceAdapter(final List<HomeBean.MemListBean> faceList) {

        LinearLayoutManager linearManager = new LinearLayoutManager(mActivity);

        linearManager.setOrientation(HORIZONTAL);

        rvHomeFace.setLayoutManager(linearManager);

        rvHomeFace.addItemDecoration(new RvRightFifteenDecoration());

        rvHomeFace.setAdapter(new RvHomeFaceAdapter(getFaceDate(faceList)));

        rvHomeFace.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (getFaceDate(faceList).size() - 1 == position) {//查看更多

                    start(new FaceAndTalentFragment());

                } else {
                    start(new FaceAndTalentDetailsFragment());
                }
            }
        });

    }

    /**
     * function   : 这里根据数据集合来决定加载展示数据的布局个数
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/21  下午5:52
     */
    public List getFaceDate(List<HomeBean.MemListBean> faceList) {
        ArrayList<ItemTypeDate> list = new ArrayList<ItemTypeDate>();

        for (int i = 0; i < faceList.size(); i++) {
            list.add(new ItemTypeDate(ItemTypeDate.IMAGE_TYPE, faceList));
        }
        list.add(new ItemTypeDate(ItemTypeDate.MORE_TYPE, faceList));
        return list;
    }

    /**
     * function   : 全职速聘的数据适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/1  上午10:22
     *
     * @param jobList
     */
    private void setRvFullTimeAdapter(List<HomeBean.FjobListBean> jobList) {

        rvHomeJobWork.setLayoutManager(new LinearLayoutManager(mActivity));

        rvHomeJobWork.setAdapter(new rvNewFullTimeAdapter(R.layout.item_full_time_job, jobList));

        rvHomeJobWork.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                start(new CompanyDetailsFragment());

            }
        });


    }


    /**
     * function   : 翻滚文字的点击事件
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/27  下午4:17
     *
     * @param rollUrl
     */
    private void setScrollTextOnClick(final ArrayList<String> rollUrl) {

        avstHomeConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WebDetalisFragment webDetalisFragment = new WebDetalisFragment();
                webDetalisFragment.setUrlDate(rollUrl.get(number % rollUrl.size()));
                start(webDetalisFragment);

            }
        });

    }

    /**
     * function   : 设置文字垂直翻滚效果
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/7  下午5:26
     *
     * @param rollTexts
     */
    private void setScrollTextView(final ArrayList<String> rollTexts) {

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if (msg.what == 199) {
                    if (avstHomeConsult != null) {
                        avstHomeConsult.next();
                        number++;
                        avstHomeConsult.setText(rollTexts.get(number % rollTexts.size()));
                    }
                }
            }
        };

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    handler.sendEmptyMessage(199);
                    SystemClock.sleep(3000);
                }
            }
        }.start();
    }


    @OnClick({R.id.rl_home_Full_Time, R.id.tv_home_face_show_more, R.id.tv_home_company_show_more, R.id.bt_home_take})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_home_Full_Time:
                //全职速聘底部查看更多
                start(new FullTimeFragment());
                break;
            case R.id.tv_home_face_show_more:
                //有才有貌更多
                start(new FaceAndTalentFragment());
                break;
            case R.id.tv_home_company_show_more:
                //名企在线更多
                start(new FamousCompanyFragment());
                break;
            case R.id.bt_home_take:
                //抢取

                break;
        }
    }

    /**
     * function   : 当用户选地址完成后执行
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/28  下午5:39
     */
    @Subscribe
    public void setLocation(LocationEvent event) {
        mTvLocation.setText(event.getLocation());
    }


    /**
     * function   : 图标的8个数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/17  下午5:18
     */
    public ArrayList<HomeIconBean> getIconDate() {

        for (int i = 0; i < IconName.length; i++) {
            HomeIconBean bean = new HomeIconBean();
            bean.setTitle(IconName[i]);
            bean.setIcon(iconImage[i]);
            iconList.add(bean);
        }
        return iconList;
    }


    /**
     * function   : 动态获取拍照权限
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  下午3:46
     */
    private void setCameraPermission() {

        if (AndPermission.hasPermission(mActivity, Manifest.permission.CAMERA)) {//判断是否有相机权限

            Intent intent = new Intent(mActivity, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);

        } else {//申请相机权限

            AndPermission.with(this)
                    .requestCode(PERMISSION_CODE)
                    .permission(Manifest.permission.CAMERA)
                    .rationale(new MyRationaleListener())
                    // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                    .send();
        }
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
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * function   : 用户拒绝过相机授权后再次请求授权会执行
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  上午11:18
     */
    class MyRationaleListener implements RationaleListener {

        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            // 自定义对话框。
            new AlertDialog.Builder(mActivity)

                    .setTitle("友情提示")

                    .setMessage(R.string.message_permission_camera)

                    .setPositiveButton(R.string.btn_dialog_yes_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();
                        }
                    })

                    .setNegativeButton(R.string.btn_dialog_no_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel();
                        }
                    }).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，第一个参数是当前Acitivity/Fragment，回调方法写在当前Activity/Framgent。
        AndPermission.onRequestPermissionsResult(this, PERMISSION_CODE, permissions, grantResults);
    }

    @PermissionYes(PERMISSION_CODE)//获取权限成功
    private void getMultiYes(List<String> grantedPermissions) {

        switch (PERMISSION_TYPE) {//这里判断获取的是相机还是定位的权限

            case LOCATION_REQUST:
                switch (isLocation) {
                    case 0:
                        getLocationInfo();
                        break;
                    case 1:
                        start(new LocationFragment());//有权限直接去开启定位页面
                        break;
                }

                break;
            case CAMERA_REQUST:
                Intent intent = new Intent(mActivity, CaptureActivity.class);
                startActivityForResult(intent, PERMISSION_CODE);
                break;
        }


    }

    @PermissionNo(PERMISSION_CODE)//获取权限失败
    private void getMultiNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {

            AndPermission.defaultSettingDialog(this, PERMISSION_CODE).show();

        }
    }


    /**
     * function   : 定位权限相关
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  上午9:45
     */
    private void setLocationPermission() {

        if (AndPermission.hasPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {//判断是否有定位权限 有权限是判断是首页进入就要获取定位权限还是去定位页面要获取权限

            switch (isLocation) {
                case 0:
                    getLocationInfo();
                    break;
                case 1:
                    start(new LocationFragment());//有权限直接去开启定位页面
                    break;
            }

        } else {//申请定位权限

            AndPermission.with(this)
                    .requestCode(PERMISSION_CODE)
                    .permission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .rationale(new MyLocationListener())
                    // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                    .send();
        }

    }

    /**
     * function   : 用户拒绝过定位授权后再次请求授权会执行
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  上午11:18
     */
    class MyLocationListener implements RationaleListener {

        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            // 自定义对话框。
            new AlertDialog.Builder(mActivity)

                    .setTitle("友情提示")

                    .setMessage(R.string.message_permission_location)

                    .setPositiveButton(R.string.btn_dialog_yes_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();
                        }
                    })

                    .setNegativeButton(R.string.btn_dialog_no_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel();
                        }
                    }).show();
        }
    }


    /**
     * function   : 二维码扫描返回结果
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/1  上午10:03
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(mActivity, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mActivity, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    /**
     * function   : 定位相关功能
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/30  上午10:08
     */
    private void getLocationInfo() {

        mlocationClient = new AMapLocationClient(mActivity);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息

                String city = amapLocation.getCity();//获取城市地址

                if (city != null) {
                    mTvLocation.setText(city);
                    mlocationClient.stopLocation();
                }

            }
        }
    }

    /**
     * function   : 刷新数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/21  上午10:46
     */
    private void setRefresh() {

        if (rfHome != null) {

            rfHome.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                    new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            // 千万别忘了告诉控件刷新完毕了哦！
                            getDataFromServer();
                            rfHome.refreshFinish(PullToRefreshLayout.SUCCEED);
                            ToastUtils.showToast(mActivity, "我刷新完啦");
                        }
                    }.sendEmptyMessageDelayed(0, 1000);
                }

                @Override
                public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                    new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            // 千万别忘了告诉控件刷新完毕了哦！
                            getDataFromServer();
                            rfHome.refreshFinish(PullToRefreshLayout.SUCCEED);
                            ToastUtils.showToast(mActivity, "我刷新完啦");
                        }
                    }.sendEmptyMessageDelayed(0, 1000);
                }
            });
        }

    }

    /**
     * function   : 这里去获取数据 如果有缓存 先从缓存取 在访问网络 保证数据最新
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/21  上午11:28
     */
    public void getDataServer() {

        String cache = CacheUtils.getCache(mActivity, GlobalConstants.HOME_URl);
        if (!StringUtils.isEmpty(cache)) {
            processData(cache);
        }

        getDataFromServer();
    }

    /**
     * function   : 获取网络数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/20  上午9:40
     */
    public void getDataFromServer() {

        VolleyHttpRequest.Volley_StringRequest(Request.Method.POST, GlobalConstants.HOME_URl, null, new VolleyHandler<String>() {

                    @Override
                    public void reqSuccess(String response) {

                        CacheUtils.setCache(mActivity, GlobalConstants.HOME_URl, response);

                        processData(response);

                    }

                    @Override
                    public void reqError(String error) {
                        ToastUtils.showToast(mActivity, "您的网络离家出走了");
                    }
                }
        );
    }


    /**
     * function   : 解析数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/21  上午11:28
     */
    private void processData(String response) {

        HomeDatafromServer mServer = new HomeDatafromServer(response);

        setBannerImage(bbHome, mServer.getTopBanners());

        setScrollTextView(mServer.getRollTexts());

        setScrollTextOnClick(mServer.getRollUrl());

        setHandBanner(mServer.getMidBanners());

        setRvHomeFaceAdapter(mServer.getFaceList());

        setRvFullTimeAdapter(mServer.getJobList());

        setRvHomeCompanyAdapter(mServer.getComList());

    }
}