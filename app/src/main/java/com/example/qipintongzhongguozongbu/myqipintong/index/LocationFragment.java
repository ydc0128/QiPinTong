package com.example.qipintongzhongguozongbu.myqipintong.index;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.event.LocationEvent;
import com.example.qipintongzhongguozongbu.myqipintong.utils.PrefUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;


/**
 * Description: 选择地址的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/24 下午2:27
 */
public class LocationFragment extends BaseFragment implements AMapLocationListener {
    @BindView(R.id.rv_select_location)
    RecyclerView rvSelectLocation;
    @BindView(R.id.indexBar)
    IndexBar indexBar;
    @BindView(R.id.tv_SideBar_Hint)
    TextView tvSideBarHint;
    Unbinder unbinder;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption = null;

    //设置给InexBar、ItemDecoration的完整数据集
    private List<BaseIndexPinyinBean> mSourceDatas;
    //头部数据源
    private List<LocationHeaderBean> mHeaderDatas;
    //主体部分数据源（城市数据）
    private List<LocationBean> mBodyDatas;

    private SuspensionDecoration mDecoration;

    private RvLocationAdapter mAdapter;

    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;
    private boolean isFristShow = true;
    private String city;
    private String address;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_location, null));
    }

    @Override
    public void initData() {

        initLocationDate();

        getLocationInfo();

        setRecyclerAdapter();

        super.initData();
    }

    private void initLocationDate() {

        mSourceDatas = new ArrayList<>();
        mHeaderDatas = new ArrayList<>();
        List<String> locationCity = new ArrayList<>();
        locationCity.add("定位中");
        mHeaderDatas.add(new LocationHeaderBean(locationCity, "   定位城市", "定"));
        List<String> recentCitys = new ArrayList<>();
        mHeaderDatas.add(new LocationHeaderBean(recentCitys, "   最近访问城市", "近"));
        List<String> hotCitys = new ArrayList<>();
        mHeaderDatas.add(new LocationHeaderBean(hotCitys, "   热门城市", "热"));
        mSourceDatas.addAll(mHeaderDatas);
    }

    private void setRecyclerAdapter() {

        mManager = new LinearLayoutManager(mActivity);

        rvSelectLocation.setLayoutManager(mManager);

        mAdapter = new RvLocationAdapter(mActivity, R.layout.item_select_city, mBodyDatas);

        //网格
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId) {
                    case R.layout.location_item_header:
                        final LocationHeaderBean meituanHeaderBean = (LocationHeaderBean) o;
                        //网格
                        RecyclerView recyclerView = holder.getView(R.id.rv_City);
                        recyclerView.setAdapter(
                                new CommonAdapter<String>(mActivity, R.layout.location_item_header_item, meituanHeaderBean.getCityList()) {
                                    @Override
                                    public void convert(ViewHolder holder, final String cityName) {
                                        holder.setText(R.id.tvName, cityName);
                                        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                if (cityName == "点击重试" || cityName == "定位中") {

                                                    ToastUtils.showToast(mActivity, "重新定位中");

                                                    getLocationInfo();

                                                    setRecyclerAdapter();

                                                } else {
                                                    PrefUtils.putString(mActivity, "location", cityName);
                                                    //保存定位的城市信息
                                                    EventBus.getDefault().post(new LocationEvent(cityName));
                                                    //定位成功后发送位置信息到主页面
                                                    pop();
                                                }
                                            }
                                        });
                                    }
                                });
                        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
                        break;
                    case R.layout.location_item_header_top:
                        LocationTopHeaderBean locationTopHeaderBean = (LocationTopHeaderBean) o;
                        holder.setText(R.id.tv_Current, locationTopHeaderBean.getTxt());
                        break;
                    default:
                        break;
                }
            }
        };
        mHeaderAdapter.setHeaderView(0, R.layout.location_item_header_top, new LocationTopHeaderBean("获取位置中..."));//设置当前位置信息
        mHeaderAdapter.setHeaderView(1, R.layout.location_item_header, mHeaderDatas.get(0));
        mHeaderAdapter.setHeaderView(2, R.layout.location_item_header, mHeaderDatas.get(1));
        mHeaderAdapter.setHeaderView(3, R.layout.location_item_header, mHeaderDatas.get(2));


        rvSelectLocation.setAdapter(mHeaderAdapter);

        if (isFristShow) {//这里避免条目间距重复增加
            rvSelectLocation.addItemDecoration(mDecoration = new SuspensionDecoration(mActivity, mSourceDatas)
                    .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
                    .setColorTitleBg(0xffefefef)
                    .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()))
                    .setColorTitleFont(mActivity.getResources().getColor(android.R.color.black))
                    .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));
            rvSelectLocation.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

            //使用indexBar
            indexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                    .setNeedRealIndex(true)//设置需要真实的索引
                    .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                    .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size());
            isFristShow = false;
        }

        initDatas(getResources().getStringArray(R.array.provinces));//城市信息
    }

    /**
     * function   : 获取地址数据并展示
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/24  下午3:14
     */
    private void initDatas(final String[] data) {

        //延迟两秒 模拟加载数据中....
        mActivity.getWindow().getDecorView().postDelayed(new Runnable() {


            @Override
            public void run() {
                mBodyDatas = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {
                    LocationBean cityBean = new LocationBean();

                    cityBean.setCity(data[i]);//设置城市名称
                    mBodyDatas.add(cityBean);
                }
                //先排序

                if (mBodyDatas != null && indexBar != null) {
                    indexBar.getDataHelper().sortSourceDatas(mBodyDatas);
                }

                mAdapter.setDatas(mBodyDatas);

                mAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(ViewGroup parent, View view, Object o, int position) {

                        PrefUtils.putString(mActivity, "City", mBodyDatas.get(position).getCity());
                        //存储选中城市为下一次最近访问城市做准备

                        EventBus.getDefault().post(new LocationEvent(mBodyDatas.get(position).getCity()));
                        //发送选择的城市数据去主页
                        pop();

                    }

                    @Override
                    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                        return false;
                    }
                });


                mHeaderAdapter.notifyDataSetChanged();
                mSourceDatas.addAll(mBodyDatas);

                if (mSourceDatas != null && indexBar != null) {
                    indexBar.setmSourceDatas(mSourceDatas)//设置数据
                            .invalidate();
                    mDecoration.setmDatas(mSourceDatas);
                }
            }
        }, 1000);


        //延迟两秒加载头部
        mActivity.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                LocationHeaderBean header1 = mHeaderDatas.get(0);
                header1.getCityList().clear();

                if (city != null) {
                    header1.getCityList().add(city);//设置定位城市的信息
                    //定位城市
                } else {
                    header1.getCityList().add("点击重试");//设置定位城市的信息
                    //定位城市
                }

                LocationHeaderBean header2 = mHeaderDatas.get(1);
                List<String> recentCitys = new ArrayList<>();


                String City = PrefUtils.getString(mActivity, "City", null);//获取最近去过的城市数据

                String location = PrefUtils.getString(mActivity, "location", null);//获取最近定位的城市数据


                if (location != null) {
                    recentCitys.add(location);
                }
                if (City != null) {
                    recentCitys.add(City);
                }

                //最近访问城市
                header2.setCityList(recentCitys);

                LocationHeaderBean header3 = mHeaderDatas.get(2);
                List<String> hotCitys = new ArrayList<>();
                hotCitys.add("上海");
                hotCitys.add("北京");
                hotCitys.add("杭州");
                hotCitys.add("广州");
                hotCitys.add("成都");
                hotCitys.add("武汉");
                hotCitys.add("天津");
                hotCitys.add("西安");
                hotCitys.add("苏州");
                //热门城市
                header3.setCityList(hotCitys);

                if (address != null) {
                    mHeaderAdapter.setHeaderView(0, R.layout.location_item_header_top, new LocationTopHeaderBean("您当前的位置为 : " + address));//设置当前位置信息
                } else {
                    mHeaderAdapter.setHeaderView(0, R.layout.location_item_header_top, new LocationTopHeaderBean("您当前的位置为 : " + "获取位置信息失败了"));//设置当前位置信息
                }
                mHeaderAdapter.notifyItemRangeChanged(0, 4);
            }
        }, 2000);

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

        if (mlocationClient != null) {
            mlocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
            mlocationClient = null;
        }

        unbinder.unbind();
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("选择城市");

        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();
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

                //城市信息
                //获取城市地址
                city = amapLocation.getCity();
                //地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                address = amapLocation.getAddress();

                if (city != null && address != null) {
                    mlocationClient.stopLocation();
                }

            }
        }
    }


}
