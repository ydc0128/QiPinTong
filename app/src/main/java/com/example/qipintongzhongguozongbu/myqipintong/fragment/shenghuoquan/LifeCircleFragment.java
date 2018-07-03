package com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.QptLableAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.DividerGridItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.webview.WebDetalisFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description: 可定制的生活圈页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/5 下午3:30
 */
public class LifeCircleFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.rv_life_often)
    RecyclerView rvLifeOften;
    @BindView(R.id.tv_life_select)
    TextView tvLifeSelect;
    @BindView(R.id.rv_life_other)
    RecyclerView rvLifeOther;


    private String[] itemArray = {"有商有客", " 京东商城", "美丽说", "美团外卖", "大众点评", "滴滴出行", "同城旅游", "途牛旅游"};
    private int[] iconImage = {R.mipmap.full_time, R.mipmap.time_job, R.mipmap.school_find, R.mipmap.company, R.mipmap.face_good, R.mipmap.create_word, R.mipmap.youshangmain, R.mipmap.easy_school};
    private QptLableAdapter oftenAdapter;
    private ArrayList<HomeIconBean> mIconList;
    private WebDetalisFragment webFragment;

    //子页面fragment集合


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_life_circle, null));
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

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("生活圈");
        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }

    @Override
    public void initData() {

        mIconList = new ArrayList<>();

        setOftenAdapter();

        webFragment = new WebDetalisFragment();

        super.initData();
    }


    /**
     * function   : 访问记录的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/20  下午4:31
     *
     * @param mIconList
     */
    private void setLifeAdapter(ArrayList<HomeIconBean> mIconList) {

        rvLifeOften.setLayoutManager(new GridLayoutManager(mActivity, 4));

        oftenAdapter = new QptLableAdapter(R.layout.item_home_icon, mIconList);

        rvLifeOften.setAdapter(new QptLableAdapter(R.layout.item_home_icon, mIconList));


        rvLifeOften.addItemDecoration(new DividerGridItemDecoration());

        rvLifeOften.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (getIconDate().get(position).getPosition()) {
                    case 0:
                        start(new CommerceFragment());
                        break;
                    case 1:
                        webFragment.setUrlDate("https://m.jd.com/");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        // start(new JDComFragment());
                        break;
                    case 2:
                        webFragment.setUrlDate("http://m.meilishuo.com/?tabnum=0");
                        // start(new BeautifulSaidFragment());
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        break;
                    case 3:
                        webFragment.setUrlDate("http://i.meituan.com/");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        // start(new MeiTuanFragment());
                        break;
                    case 4:
                        webFragment.setUrlDate("https://m.dianping.com/citylist");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        // start(new DaZhongFeagment());
                        break;
                    case 5:
                        webFragment.setUrlDate("http://www.xiaojukeji.com/index/index");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        break;
                    case 6:
                        webFragment.setUrlDate("http://m.ly.com/?refid=18024458");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        break;
                    case 7:
                        webFragment.setUrlDate("https://m.tuniu.com/");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        break;

                }
                start(webFragment);

            }
        });


    }


    /**
     * function   : 底部的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/20  下午4:31
     */
    private void setOftenAdapter() {

        rvLifeOther.setLayoutManager(new GridLayoutManager(mActivity, 4));

        rvLifeOther.setAdapter(new QptLableAdapter(R.layout.item_home_icon, getIconDate()));

        rvLifeOther.addItemDecoration(new DividerGridItemDecoration());

        rvLifeOther.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        start(new CommerceFragment());
                        break;
                    case 1:
                        webFragment.setUrlDate("https://m.jd.com/");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        // start(new JDComFragment());
                        break;
                    case 2:
                        webFragment.setUrlDate("http://m.meilishuo.com/?tabnum=0");
                        // start(new BeautifulSaidFragment());
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        break;
                    case 3:
                        webFragment.setUrlDate("http://i.meituan.com/");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        // start(new MeiTuanFragment());
                        break;
                    case 4:
                        webFragment.setUrlDate("https://m.dianping.com/citylist");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        // start(new DaZhongFeagment());
                        break;
                    case 5:
                        webFragment.setUrlDate("http://www.xiaojukeji.com/index/index");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        break;
                    case 6:
                        webFragment.setUrlDate("http://m.ly.com/?refid=18024458");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        break;
                    case 7:
                        webFragment.setUrlDate("https://m.tuniu.com/");
                        webFragment.setTitle(getIconDate().get(position).getTitle());
                        break;

                }
                start(webFragment);

                mIconList.add(getIconDate().get(position));

                Collections.reverse(mIconList);//翻转排序

                setLifeAdapter(mIconList);
            }
        });


    }


    public List<HomeIconBean> getIconDate() {

        ArrayList<HomeIconBean> list = new ArrayList<>();

        for (int i = 0; i < iconImage.length; i++) {

            HomeIconBean bean = new HomeIconBean();

            bean.setTitle(itemArray[i]);
            bean.setIcon(iconImage[i]);
            bean.setPosition(i);
            list.add(bean);
        }

        return list;
    }

}
