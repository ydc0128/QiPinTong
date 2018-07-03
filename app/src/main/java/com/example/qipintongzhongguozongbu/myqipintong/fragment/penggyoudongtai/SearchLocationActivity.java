package com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
import com.example.qipintongzhongguozongbu.myqipintong.utils.StringUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Description: 朋友圈 位置信息 搜索地址的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/4 下午3:26
 */
public class SearchLocationActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, PoiSearch.OnPoiSearchListener {


    @BindView(R.id.iv_location_search_back)
    ImageView ivLocationSearchBack;
    @BindView(R.id.et_search_location)
    EditText etSearchLocation;
    @BindView(R.id.rv_location_search)
    RecyclerView rvLocationSearch;
    @BindView(R.id.srl_search)
    SwipeRefreshLayout srlSearch;

    private static final int PAGE_SIZE = 20;//每页数据数量

    private static int PAGE_NUM = 1;//当前页数

    private int delayMillis = 1000;
    private RvCityAdapter rvCityAdapter;
    private boolean isErr;
    private boolean isLoadMore = true;//是否在加载更多
    private int poisSize;//请求数据时单次返回的条目数量
    private PoiSearch.Query query;
    private ArrayList<PoiItem> poiItems;
    private ArrayList<PoiItem> pois;
    private String input;
    private String cityCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_location);
        ButterKnife.bind(this);

        cityCode = getIntent().getStringExtra("cityCode");
        //获取上个页面定位到的用户所在城市信息
        LogUtils.e(cityCode);

        poiItems = new ArrayList<>();

        setRvAdapyer();

        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.iv_location_search_back, R.id.bt_location_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_location_search_back:
                finish();
                break;
            case R.id.bt_location_find:
                //搜索
                input = etSearchLocation.getText().toString().trim();
                if (!StringUtils.isEmpty(input)) {
                    setPoiSearch(input, 1);
                } else {
                    ToastUtils.showToast(this, "请先输入内容");
                }

                break;
        }
    }


    public void setRvAdapyer() {

        srlSearch.setRefreshing(false);

        rvLocationSearch.setLayoutManager(new LinearLayoutManager(this));

        rvCityAdapter = new RvCityAdapter(R.layout.item_select_place, pois);

        rvCityAdapter.openLoadAnimation();

        rvCityAdapter.setOnLoadMoreListener(this);//加载更多监听

        rvCityAdapter.setEnableLoadMore(isLoadMore);//判断当前是否是下拉刷新状态才决定要不要记载更多

        rvLocationSearch.setAdapter(rvCityAdapter);

        rvLocationSearch.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                EventBus.getDefault().post(new TextEvent(poiItems.get(position).getTitle()));

                finish();

            }
        });

    }

    private void setPoiSearch(String str, int pageNum) {

        query = new PoiSearch.Query(str, "", cityCode);

        query.setPageSize(PAGE_SIZE);// 设置每页最多返回多少条poiitem

        query.setPageNum(pageNum);//设置查询页码

        PoiSearch poiSearch = new PoiSearch(this, query);

        poiSearch.setOnPoiSearchListener(this);

        poiSearch.searchPOIAsyn();
    }


    @Override
    public void onLoadMoreRequested() {
        // srlLocation.setEnabled(false);
        rvLocationSearch.postDelayed(new Runnable() {
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

                            setPoiSearch(input, PAGE_NUM++);

                            rvCityAdapter.loadMoreComplete();
                        } else {
                            isErr = true;
                            rvCityAdapter.loadMoreFail();
                        }
                    }

                }
            }

        }, delayMillis);
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == 1000) {//1000表示正确拿到数据

            pois = poiResult.getPois();

            poiItems.addAll(pois);

            poisSize = pois.size();

            rvCityAdapter.addData(pois);

            for (PoiItem p : pois
                    ) {
                LogUtils.e(p.getTitle() + "");
            }

        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }
}
