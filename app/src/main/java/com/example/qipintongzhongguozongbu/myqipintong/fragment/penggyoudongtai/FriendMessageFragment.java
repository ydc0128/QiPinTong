package com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFriendMessageAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Description: 最新消息的展示页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/23 上午10:05
 */
public  class FriendMessageFragment extends BaseFragment {
    @BindView(R.id.rv_friend_message)
    RecyclerView rvFriendMessage;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_friend_message, null));
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setRvAdapter();

        super.onLazyInitView(savedInstanceState);
    }

    private void setRvAdapter() {

        rvFriendMessage.setLayoutManager(new LinearLayoutManager(mActivity));

        rvFriendMessage.setAdapter(new RvFriendMessageAdapter(R.layout.item_friend_message, ImageList.getImageList()));

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
