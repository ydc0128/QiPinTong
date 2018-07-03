package com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvHomeIconAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.VpEasyKnowAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi.IdentitySettingFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;
import com.example.qipintongzhongguozongbu.myqipintong.edittext.SearchFragment;
import com.example.qipintongzhongguozongbu.myqipintong.event.LocationEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia.CreateWorldFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.jianzhidaren.TimeJobFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian.FamousCompanyFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.quanzhisupin.FullTimeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.xiaoyuanzhipin.SchoolPagerFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FaceAndTalentFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceFragment;
import com.example.qipintongzhongguozongbu.myqipintong.homelist.CompanyList;
import com.example.qipintongzhongguozongbu.myqipintong.homelist.EnterpriseList;
import com.example.qipintongzhongguozongbu.myqipintong.homelist.FaceList;
import com.example.qipintongzhongguozongbu.myqipintong.homelist.PartnerList;
import com.example.qipintongzhongguozongbu.myqipintong.homelist.PositionList;
import com.example.qipintongzhongguozongbu.myqipintong.homelist.ProjectList;
import com.example.qipintongzhongguozongbu.myqipintong.index.LocationFragment;
import com.example.qipintongzhongguozongbu.myqipintong.utils.PrefUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.gxz.library.StickyNavLayout;
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

/**
 * Description: 新的首页
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/9 下午3:34
 */
public class HostFragment extends BaseFragment implements AMapLocationListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.bb_home_new)
    Banner bbHomeNew;
    @BindView(R.id.iv_home_easy_school)
    ImageView ivHomeEasySchool;
    @BindView(R.id.tv_home_join)
    TextView tvHomeJoin;
    @BindView(R.id.id_stickynavlayout_topview)
    LinearLayout idStickynavlayoutTopview;
    @BindView(R.id.id_stickynavlayout_indicator)
    SlidingTabLayout idStickynavlayoutIndicator;
    @BindView(R.id.id_stickynavlayout_viewpager)
    ViewPager idStickynavlayoutViewpager;
    @BindView(R.id.id_stick)
    StickyNavLayout idStick;
    Unbinder unbinder;
    @BindView(R.id.srl_host)
    SwipeRefreshLayout srlHost;
    @BindView(R.id.rv_home_icon_new)
    RecyclerView rvHomeIconNew;

    private View mVvSearch;
    private ImageView mIvScan;
    private TextView mTvCity;

    private String[] IconName = {"全职速聘", "兼职达人", "校园直聘", "名企在线", "有才有貌", "创孵天下", "有商有客", "全部频道"};
    private int[] iconImage = {R.mipmap.full_time, R.mipmap.time_job, R.mipmap.school_find, R.mipmap.company, R.mipmap.face_good, R.mipmap.create_word, R.mipmap.youshangmain, R.mipmap.all};


    private String[] mTitles = {"职位", "高颜值", "项目", "企业", "商家", "合伙人"};

    private int isLocation = 0;

    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption = null;

    private static final int REQUEST_CODE = 111;//启动扫码页面的请求码
    private static final int PERMISSION_CODE = 100;//相机,定位的权限请求码
    private static int PERMISSION_TYPE; //权限的类型 用于区别是定位还是相机权限的请求
    private static final int LOCATION_REQUST = 0;//定位权限的标识码
    private static final int CAMERA_REQUST = 1;//相机权限的标识码
    private RelativeLayout mRvTop;


    @Override
    public View initView() {
        mVvSearch = mActivity.findViewById(R.id.vv_search);
        mIvScan = (ImageView) mActivity.findViewById(R.id.iv_home_scan);
        mTvCity = (TextView) mActivity.findViewById(R.id.tv_home_city);
        mRvTop = (RelativeLayout) mActivity.findViewById(R.id.rv_home_home_top);
        return View.inflate(mActivity, R.layout.frgamnet_host, null);
    }

    public static BaseFragment getInstance() {
        return new HostFragment();
    }


    @Override
    public void initData() {

        mTvCity.setText(PrefUtils.getString(mActivity, "City", "北京"));//选择的地主存储在sp中

        EventBus.getDefault().register(this);

        setBannerImage(bbHomeNew);

        setTopButtonClick();

        setLocationPermission();

        super.initData();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setIconAdapter();

        initRefresh();

        initSlidingTab();

        super.onLazyInitView(savedInstanceState);
    }


    private void initRefresh() {

        srlHost.setEnabled(true);

        srlHost.setOnRefreshListener(this);//加载刷新控件

        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        srlHost.setProgressViewOffset(true, 30, 150);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        srlHost.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srlHost.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        idStick.setOnStickStateChangeListener(new StickyNavLayout.onStickStateChangeListener() {
            @Override
            public void isStick(boolean isStick) {

            }

            @Override
            public void scrollPercent(float percent) {
                if (percent == 0) {
                    srlHost.setEnabled(true);
                    srlHost.setOnRefreshListener(HostFragment.this);//加载刷新控件
                } else {
                    srlHost.setEnabled(false);
                    srlHost.setOnRefreshListener(null);
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                srlHost.setRefreshing(false);
            }
        }, 2000);

    }


    @Override
    public void onSupportVisible() {

        mTop.setVisibility(View.GONE);
        mRvTop.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.VISIBLE);

        super.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {

        mTop.setVisibility(View.VISIBLE);

        mRvTop.setVisibility(View.GONE);

        super.onSupportInvisible();
    }


    /**
     * function   : 顶部按钮的点击事件
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/11  上午9:52
     */
    private void setTopButtonClick() {

        mIvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PERMISSION_TYPE = CAMERA_REQUST;

                setCameraPermission();
            }
        });

        mTvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLocation = 1;

                setLocationPermission();

                PERMISSION_TYPE = LOCATION_REQUST;
            }
        });

        mVvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(new SearchFragment());
            }
        });
    }

    /**
     * function   : 首页的标签对应条目
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/16  上午11:49
     */
    private void initSlidingTab() {

        ArrayList<BaseFragment> Fragments = new ArrayList<>();

        Fragments.add(PositionList.getInstance());
        Fragments.add(FaceList.getInstance());
        Fragments.add(ProjectList.getInstance());
        Fragments.add(EnterpriseList.getInstance());
        Fragments.add(CompanyList.getInstance());
        Fragments.add(PartnerList.getInstance());

        idStickynavlayoutViewpager.setAdapter(new VpEasyKnowAdapter(getChildFragmentManager(), Fragments));

        idStickynavlayoutIndicator.setViewPager(idStickynavlayoutViewpager, mTitles);

    }

    private ArrayList<HomeIconBean> getIconDate() {

        ArrayList<HomeIconBean> iconList = new ArrayList<>();

        for (int i = 0; i < IconName.length; i++) {
            HomeIconBean bean = new HomeIconBean();
            bean.setTitle(IconName[i]);
            bean.setIcon(iconImage[i]);
            iconList.add(bean);
        }
        return iconList;
    }


    private void setIconAdapter() {

        rvHomeIconNew.setLayoutManager(new GridLayoutManager(mActivity, 4));

        rvHomeIconNew.setAdapter(new RvHomeIconAdapter(R.layout.item_icon_type, getIconDate()));

        rvHomeIconNew.addOnItemTouchListener(new OnItemClickListener() {
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
                    case 7://全部
                        start(new SelectHostTltieFragment());
                        break;
                }
            }
        });


    }


    /**
     * function   : 当用户选地址完成后执行
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/28  下午5:39
     */
    @Subscribe
    public void setLocation(LocationEvent event) {
        mTvCity.setText(event.getLocation());
        PrefUtils.putString(mActivity, "City", event.getLocation());
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


    //立即加入
    @OnClick(R.id.tv_home_join)
    public void onClick() {
        start(new IdentitySettingFragment());

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
                    mTvCity.setText(city);

                    PrefUtils.putString(mActivity, "City", city);
                    mlocationClient.stopLocation();
                }

            }
        }
    }

}
