package com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCityAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseActivity;
import com.example.qipintongzhongguozongbu.myqipintong.event.TextEvent;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * Description: 朋友圈选择地址的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/3 上午9:47
 */
public class SelectLocationActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener, AMapLocationListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_location_select)
    RecyclerView rvLocationSelect;
    @BindView(R.id.srl_location)
    SwipeRefreshLayout srlLocation;
    @BindView(R.id.iv_location_back)
    ImageView ivLocationBack;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption = null;
    private static final int PERMISSION_CODE = 100;//定位的权限请求码

    private static final int PAGE_SIZE = 20;//每页数据数量

    private static int PAGE_NUM = 1;//当前页数

    private int delayMillis = 1000;

    private boolean isErr;
    private boolean mLoadMoreEndGone = false;


    private boolean isLoadMore = true;//是否在加载更多
    private RvCityAdapter rvCityAdapter;
    private List<PoiItem> date;
    private PoiSearch.Query query;
    private String cityCode;
    private LatLonPoint latLonPoint;
    private int poisSize;//请求数据时单次返回的条目数量
    private ArrayList<PoiItem> pois;
    private ArrayList<PoiItem> poiItems;
    private String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_select_location);
        ButterKnife.bind(this);

        poiItems = new ArrayList<>();

        setLocationPermission();

        setRvAdapyer();

        super.onCreate(savedInstanceState);
    }


    public void setRvAdapyer() {


        srlLocation.setOnRefreshListener(this);//加载刷新控件

        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        srlLocation.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        srlLocation.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srlLocation.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        rvLocationSelect.setLayoutManager(new LinearLayoutManager(this));

        rvCityAdapter = new RvCityAdapter(R.layout.item_select_place, pois);

        rvCityAdapter.openLoadAnimation();

        rvCityAdapter.setOnLoadMoreListener(this);//加载更多监听

        rvCityAdapter.setEnableLoadMore(isLoadMore);//判断当前是否是下拉刷新状态才决定要不要记载更多

        rvLocationSelect.setAdapter(rvCityAdapter);


        rvLocationSelect.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (position == 0) {
                    EventBus.getDefault().post(new TextEvent("不显示位置"));
                } else if (position == 1) {
                    EventBus.getDefault().post(new TextEvent(city + ""));
                } else {
                    EventBus.getDefault().post(new TextEvent(poiItems.get(position).getTitle()));
                }


                finish();

            }
        });

    }


    /**
     * function   : 这里获取到定位周边的数据才设置适配器 刷新等
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/4  上午10:08
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {

        if (i == 1000) {//1000表示正确拿到数据

            pois = poiResult.getPois();

            poiItems.addAll(pois);

            poisSize = pois.size();

            rvCityAdapter.addData(pois);

        }

    }


    @Override
    public void onRefresh() {

        isLoadMore = false;

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                poiItems.clear();

                setPoiSearch(1);

                isErr = false;

                srlLocation.setRefreshing(false);
                rvCityAdapter.setEnableLoadMore(true);

            }
        }, delayMillis);

        isLoadMore = true;
    }


    @Override
    public void onLoadMoreRequested() {
        srlLocation.setEnabled(false);
        rvLocationSelect.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (rvCityAdapter.getData().size() < PAGE_SIZE) {
                    rvCityAdapter.loadMoreEnd(true);
                } else {
                    if (poisSize != PAGE_SIZE) {//如果请求的数据没有20条 认为没有更多数据
//                    pullToRefreshAdapter.loadMoreEnd();//default visible
                        rvCityAdapter.loadMoreEnd(true);//true is gone,false is visible

                    } else {
                        if (isErr) {

                            setPoiSearch(PAGE_NUM++);

                            // rvCityAdapter.addData(pois);

                            rvCityAdapter.loadMoreComplete();
                        } else {
                            isErr = true;
                            rvCityAdapter.loadMoreFail();
                        }
                    }
                    if (srlLocation != null) {
                        srlLocation.setEnabled(true);
                    }
                }
            }

        }, delayMillis);
    }


    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        LogUtils.e("地址onPoiItemSearched ");
    }


    /**
     * function   : 定位权限相关
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  上午9:45
     */
    private void setLocationPermission() {

        if (AndPermission.hasPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)) {//判断是否有定位权限

            getLocationInfo();

        } else {//申请定位权限

            AndPermission.with(this)
                    .requestCode(PERMISSION_CODE)
                    .permission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .rationale(new MyLocationListener())
                    // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                    .send();
        }

    }

    @OnClick({R.id.iv_location_back, R.id.iv_location_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_location_back:
                finish();
                break;
            case R.id.iv_location_find:

                Intent intent = new Intent(this, SearchLocationActivity.class);
                intent.putExtra("cityCode", cityCode);
                startActivity(intent);

                break;
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
            new AlertDialog.Builder(SelectLocationActivity.this)

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，第一个参数是当前Acitivity/Fragment，回调方法写在当前Activity/Framgent。
        AndPermission.onRequestPermissionsResult(this, PERMISSION_CODE, permissions, grantResults);
    }

    @PermissionYes(PERMISSION_CODE)//获取权限成功
    private void getMultiYes(List<String> grantedPermissions) {

        getLocationInfo();

    }

    @PermissionNo(PERMISSION_CODE)//获取权限失败
    private void getMultiNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {

            AndPermission.defaultSettingDialog(this, PERMISSION_CODE).show();

        }
    }

    /**
     * function   : 定位相关功能
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/30  上午10:08
     */
    private void getLocationInfo() {

        mlocationClient = new AMapLocationClient(this);
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
                LogUtils.e(amapLocation.getAoiName());

                double latitude = amapLocation.getLatitude();
                //获取当前位置经纬度
                double longitude = amapLocation.getLongitude();

                cityCode = amapLocation.getCityCode();

                city = amapLocation.getCity();

                latLonPoint = new LatLonPoint(latitude, longitude);

                setPoiSearch(PAGE_NUM);

                mlocationClient.stopLocation();

            }
        }
    }

    private void setPoiSearch(int pageNum) {

        query = new PoiSearch.Query("", "", cityCode);

        query.setPageSize(PAGE_SIZE);// 设置每页最多返回多少条poiitem

        query.setPageNum(pageNum);//设置查询页码

        PoiSearch poiSearch = new PoiSearch(this, query);

        poiSearch.setOnPoiSearchListener(this);

        poiSearch.setBound(new PoiSearch.SearchBound(latLonPoint, 1000));//设置周边搜索的中心点以及半径

        poiSearch.searchPOIAsyn();
    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent();
        intent.putExtra("city", "所在位置");
        setResult(0, intent);
        super.onDestroy();
    }
}
