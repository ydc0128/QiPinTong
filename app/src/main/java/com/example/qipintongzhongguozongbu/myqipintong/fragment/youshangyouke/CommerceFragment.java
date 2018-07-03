package com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCityListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvComerceAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFansListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFoodAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvSortListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.youth.banner.Banner;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: 有商有客的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/7 下午4:42
 */
public class CommerceFragment extends BaseFragment {


    @BindView(R.id.rv_commerce)
    RecyclerView rvCommerce;
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    @BindView(R.id.tv_select)
    TextView tvSelect;


    private HeaderCommerceLayout headerLayout;
    int mSelectY = 0;
    private Unbinder unbinder;
    private String headers[] = {"全部商家", "附近", "智能排序"};
    private String fans[] = {"1KM", "2KM", "3KM", "4KM", "5KM", "10KM"};
    private String sort[] = {"综合", "离我最近", "人气最高"};

    private String catalogues1[] = new String[]{"全部频道", "美食", "休闲娱乐", "购物", "酒店", "丽人", "运动健身", "结婚", "亲子", "爱车", "生活服务"};
    private String catalogues[][] = new String[][]{
            new String[]{"全部美食", "本帮江浙菜", "川菜", "粤菜", "湘菜", "东北菜", "台湾菜", "新疆/清真", "素菜", "火锅", "自助餐", "小吃快餐", "日本", "韩国料理",
                    "东南亚菜", "西餐", "面包甜点", "其他"},
            new String[]{"全部休闲娱乐", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全部购物", "综合商场", "服饰鞋包", "运动户外", "珠宝饰品", "化妆品", "数码家电", "亲子购物", "家居建材"
                    , "书店", "书店", "眼镜店", "特色集市", "更多购物场所", "食品茶酒", "超市/便利店", "药店"},
            new String[]{"全部休闲娱乐", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全", "咖啡厅", "酒吧", "茶馆", "KTV", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全部", "咖啡厅", "酒吧", "茶馆", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全部休", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全部休闲", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全部休闲娱", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏"},
            new String[]{"全部休闲娱乐", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏", "更多休闲娱乐"},
            new String[]{"全部休闲aaa", "咖啡厅", "酒吧", "茶馆", "KTV", "电影院", "游乐游艺", "公园", "景点/郊游", "洗浴", "足浴按摩", "文化艺术",
                    "DIY手工坊", "桌球馆", "桌面游戏"},
    };


    private List<View> popupViews = new ArrayList<>();
    private RecyclerView rv2;
    private int mPosition;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_commerce, null));
    }

    public static BaseFragment getInstance() {
        return new CommerceFragment();
    }


    @Override
    public void initData() {

        initDropDown();

        setCommerceAdapter();

        refreshAdapter();//初始化商品分类右侧的列表

        super.initData();

    }

    private void initDropDown() {
        //地址的布局
        View cityView = View.inflate(mActivity, R.layout.recyclerview_two, null);
        RecyclerView rv1 = (RecyclerView) cityView.findViewById(R.id.rv_1);
        rv2 = (RecyclerView) cityView.findViewById(R.id.rv_2);

        rv1.setLayoutManager(new LinearLayoutManager(mActivity));
        rv1.setAdapter(new RvComerceAdapter(R.layout.item_select_text, Arrays.asList(catalogues1)));

        rv1.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPosition = position;
                refreshAdapter();
            }

        });


        RecyclerView fansView = new RecyclerView(mActivity);
        fansView.setLayoutManager(new LinearLayoutManager(mActivity));
        fansView.setAdapter(new RvFansListAdapter(R.layout.item_text, Arrays.asList(fans)));
        fansView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDropDownMenu.setTabText(position == 0 ? headers[1] : fans[position]);
                mDropDownMenu.closeMenu();
            }
        });

        RecyclerView sortView = new RecyclerView(mActivity);
        sortView.setLayoutManager(new LinearLayoutManager(mActivity));
        sortView.setAdapter(new RvSortListAdapter(R.layout.item_text, Arrays.asList(sort)));
        sortView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sort[position]);
                mDropDownMenu.closeMenu();
            }
        });


        popupViews.add(cityView);
        popupViews.add(fansView);
        popupViews.add(sortView);

        ViewGroup parent = (ViewGroup) tvSelect.getParent();
        parent.removeView(tvSelect);

        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, tvSelect);
    }


    //刷新地址选择器右侧的列表
    public void refreshAdapter() {

        rv2.setLayoutManager(new LinearLayoutManager(mActivity));
        rv2.setAdapter(new RvCityListAdapter(R.layout.item_text, Arrays.asList(catalogues[mPosition])));
        rv2.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDropDownMenu.setTabText(position == 0 ? headers[0] : catalogues[mPosition][position]);
                mDropDownMenu.closeMenu();
            }
        });


    }

    /**
     * function   : 菜品的条目
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/10  下午7:52
     */

    private void setCommerceAdapter() {
        rvCommerce.setLayoutManager(new LinearLayoutManager(mActivity));
        // rvMarket.setAdapter(new RvCommerceAdapter(R.layout.item_commerce_greens, ImageList.getImageList()));
        RvFoodAdapter adapter = new RvFoodAdapter(R.layout.item_commerce_food, ImageList.getImageList());

        headerLayout = new HeaderCommerceLayout(mActivity);//头布局

        setBannerImage((Banner) headerLayout.findViewById(R.id.bb_commerce));

        adapter.addHeaderView(headerLayout);

        rvCommerce.setAdapter(adapter);

        rvCommerce.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //start(new FoodDetailsFragment());
                start(new CommerceDetailsFragment());
            }
        });
        headerLayout.findViewById(R.id.rl_delicious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //美食
                start(new FoodListFragment());
            }
        });
        headerLayout.findViewById(R.id.rl_leisure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //休闲娱乐
                start(new HappyPlayFragment());
            }
        });
        headerLayout.findViewById(R.id.rl_beautiful).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //丽人
                start(new BeautifulPeopleFragment());
            }
        });
        headerLayout.findViewById(R.id.rl_grogshop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //酒店
                start(new GrogshopFragment());
            }
        });
        headerLayout.findViewById(R.id.rl_commerce_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择标签页
                start(new CommerceSelectFragment());
            }
        });
        headerLayout.findViewById(R.id.tv_commerce_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //立即加入

            }
        });

        rvCommerce.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int[] location = new int[2];
                mDropDownMenu.getLocationOnScreen(location);
                int y = location[1];
                mSelectY = y;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (headerLayout == null) return;
                int getTop = headerLayout.getDistanceY();
                if (getTop <= mSelectY) {
                    mDropDownMenu.setVisibility(View.VISIBLE);
                } else {
                    mDropDownMenu.setY(0);
                    mDropDownMenu.setVisibility(View.GONE);
                }
            }
        });

    }


    @Override
    public void onSupportVisible() {
        mTvTitle.setText("有商有客");
        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);
        super.onSupportVisible();
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

}
