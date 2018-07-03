package com.example.qipintongzhongguozongbu.myqipintong.fragment.darenbang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvRequseAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 共享邀请
 * Created by Administrator on 2017/6/3.
 */

public class ShareRequesPager extends BaseFragment {
    @BindView(R.id.rv_RecyclerView)
    RecyclerView rvRecyclerView;
    Unbinder unbinder;

    @Override
    public View initView() {
        return View.inflate(mActivity, R.layout.recyclerview, null);
    }

    public static BaseFragment getInstance() {
        return new ShareRequesPager();
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setAdapter();

        super.onLazyInitView(savedInstanceState);
    }

    private void setAdapter() {

        rvRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        rvRecyclerView.setAdapter(new RvRequseAdapter(R.layout.item_requse_share, ImageList.getImageList()));

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
