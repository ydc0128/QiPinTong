package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFansAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.TabAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/14.
 * 粉丝
 */
public class FansFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.my_item_fans)
    CommonTabLayout myItemFans;
    @BindView(R.id.rv_f_jilu)
    RecyclerView rvFJilu;
    @BindView(R.id.rv_f_guanzhu)
    RecyclerView rvFGuanzhu;
    private String[] mTitles = {"我的粉丝", "我的关注"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    public void onSupportVisible() {
        mTvTitle.setText("粉丝");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    public void initData() {
        setLabelDate();
        setFansAdapter();
        setFansGuanzhuAdapter();
        super.initData();

    }

    private void setLabelDate() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabAdapter(mTitles[i]));
        }
        myItemFans.setTabData(mTabEntities);
        myItemFans.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        rvFJilu.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        rvFGuanzhu.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    private void setFansAdapter() {
        rvFJilu.setLayoutManager(new LinearLayoutManager(mActivity));
        rvFJilu.setAdapter(new RvFansAdapter(R.layout.item_fans, ImageList.getImageList()));

        rvFJilu.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                start(new FoodDetailsFragment());
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(mActivity, "点击了" + position);
            }
        });
    }

    private void setFansGuanzhuAdapter() {

        rvFGuanzhu.setLayoutManager(new LinearLayoutManager(mActivity));
        rvFGuanzhu.setAdapter(new RvFansAdapter(R.layout.item_fans, ImageList.getImageList()));

        rvFGuanzhu.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                start(new FoodDetailsFragment());
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(mActivity, "点击了" + position);
            }
        });
    }



    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_fans, null));
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
