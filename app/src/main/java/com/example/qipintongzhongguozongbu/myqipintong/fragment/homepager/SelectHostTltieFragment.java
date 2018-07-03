package com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager;

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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.OtherLableAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.QptLableAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeIconBean;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.DividerGridItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.chuangfutianxia.CreateWorldFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.jianzhidaren.TimeJobFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian.FamousCompanyFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.quanzhisupin.FullTimeFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.xiaoyuanzhipin.SchoolPagerFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FaceAndTalentFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceFragment;
import com.example.qipintongzhongguozongbu.myqipintong.schoolpager.EasyknowSchoolFragment;
import com.example.qipintongzhongguozongbu.myqipintong.utils.InputUtil;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.OutputUtil;
import com.example.qipintongzhongguozongbu.myqipintong.utils.PrefUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Description: 首页选择标签的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/11 上午10:02
 */
public class SelectHostTltieFragment extends BaseFragment {
    @BindView(R.id.rv_qpt_label)
    RecyclerView rvQptLabel;
    @BindView(R.id.tv_host_select)
    TextView tvHostSelect;

    Unbinder unbinder;
    @BindView(R.id.rv_other_Edit)
    RecyclerView rvOtherEdit;
    @BindView(R.id.rv_other_show)
    RecyclerView rvOtherShow;


    private String[] IconName = {"全职速聘", "兼职达人", "校园直聘", "名企在线", "有才有貌", "创孵天下", "有商有客", "易通学院"};
    private int[] iconImage = {R.mipmap.full_time, R.mipmap.time_job, R.mipmap.school_find, R.mipmap.company, R.mipmap.face_good, R.mipmap.create_word, R.mipmap.youshangmain, R.mipmap.easy_school};

    private boolean isEdit = false;//是否是编辑模式

    private OtherLableAdapter otherLableAdapter;

    private ArrayList<HomeIconBean> hintList;
    private ArrayList<HomeIconBean> showList;


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_select_host, null));
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

        mIvBack.setVisibility(View.VISIBLE);
        mTvTitle.setText("频道定制");
        super.onSupportVisible();
    }

    @Override
    public void initData() {

        hintList = new ArrayList<>();

        if (PrefUtils.getBoolean(mActivity, "initSelect", true)) {//第一次进入此页面
            InitDate();
        }

        setQptAdapter();

        setEndAdapter();

        super.initData();
    }


    private void setQptAdapter() {

        rvQptLabel.setLayoutManager(new GridLayoutManager(mActivity, 4));

        rvQptLabel.setAdapter(new QptLableAdapter(R.layout.item_home_icon, getIconList()));

        rvQptLabel.addItemDecoration(new DividerGridItemDecoration());

        rvQptLabel.addOnItemTouchListener(new OnItemClickListener() {
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
     * function   : 可编辑的第三方服务适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/11  下午6:16
     */
    private void setEditAdapter() {

        final List<HomeIconBean> showList = new InputUtil<HomeIconBean>().readListFromSdCard("selectlabel");

        rvOtherEdit.setLayoutManager(new GridLayoutManager(mActivity, 4));

        otherLableAdapter = new OtherLableAdapter(R.layout.item_select_icon, showList);

        rvOtherEdit.setAdapter(otherLableAdapter);

        rvOtherEdit.addItemDecoration(new DividerGridItemDecoration());

        rvOtherEdit.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                showList.get(position).setShow(!showList.get(position).isShow());

                if (showList.get(position).isShow()) {
                    LogUtils.e("减号" + position);
                } else {
                    LogUtils.e("加号" + position);
                }

                otherLableAdapter.notifyItemChanged(position);

                new OutputUtil<HomeIconBean>().writeListIntoSDcard("selectlabel", showList);//第一次进入存储初始化的集合
            }

        });


    }

    /**
     * function   : 第一次进入页面是初始化数据走一次
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/18  下午4:16
     */
    private void InitDate() {

        showList = new ArrayList<>();

        for (int i = 0; i < IconName.length; i++) {

            HomeIconBean bean = new HomeIconBean();
            bean.setTitle(IconName[i]);
            bean.setIcon(iconImage[i]);
            bean.setPosition(i);
            bean.setShow(true);

            showList.add(bean);
        }

        new OutputUtil<HomeIconBean>().writeListIntoSDcard("selectlabel", showList);//第一次进入存储初始化的集合

    }


    //编辑完成的按钮
    @OnClick(R.id.tv_host_select)
    public void onClick() {

        isEdit = !isEdit;

        if (isEdit) {
            tvHostSelect.setText("完成");

            rvOtherEdit.setVisibility(View.VISIBLE);
            rvOtherShow.setVisibility(View.GONE);

            setEditAdapter();

        } else {
            tvHostSelect.setText("编辑");

            rvOtherEdit.setVisibility(View.GONE);
            rvOtherShow.setVisibility(View.VISIBLE);

            setEndAdapter();

        }


    }

    /**
     * function   : 编辑完成的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/11  下午6:16
     */
    private void setEndAdapter() {

        rvOtherShow.setLayoutManager(new GridLayoutManager(mActivity, 4));

        if (PrefUtils.getBoolean(mActivity, "initSelect", true)) {//第一次进入此页面

            QptLableAdapter adapter = new QptLableAdapter(R.layout.item_home_icon, showList);
            rvOtherShow.setAdapter(adapter);

            PrefUtils.putBoolean(mActivity, "initSelect", false);
        } else {

            hintList.clear();

            List<HomeIconBean> selectList = new InputUtil<HomeIconBean>().readListFromSdCard("selectlabel");

            if (selectList != null) {
                for (HomeIconBean bean : selectList) {
                    if (bean.isShow()) {
                        hintList.add(bean);
                    }

                    LogUtils.e(bean.getTitle());
                }
            }


            QptLableAdapter adapter = new QptLableAdapter(R.layout.item_home_icon, hintList);
            rvOtherShow.setAdapter(adapter);
        }

        rvOtherShow.addItemDecoration(new DividerGridItemDecoration());

        rvOtherShow.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(mActivity, hintList.get(position).getTitle());

                switch (hintList.get(position).getPosition()) {
                    case 0:
                        LogUtils.e("全职速聘");
                        break;
                    case 1:
                        LogUtils.e("兼职达人");
                        break;
                    case 2:
                        LogUtils.e("校园直聘");
                        break;
                    case 3:
                        LogUtils.e("名企在线");
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                }

            }
        });

    }

    public ArrayList<HomeIconBean> getIconList() {

        ArrayList<HomeIconBean> iconList = new ArrayList<>();

        for (int i = 0; i < IconName.length; i++) {

            HomeIconBean bean = new HomeIconBean();
            bean.setTitle(IconName[i]);
            bean.setIcon(iconImage[i]);
            bean.setPosition(i);
            bean.setShow(true);

            iconList.add(bean);

        }
        return iconList;
    }
}