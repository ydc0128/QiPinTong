package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description: 创投天下好项目的详情页面 融资历史的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/13 下午7:44
 */
public class RvProjectDetailsAdapter extends RecyclerView.Adapter {

    private final Activity mActivity;
    @BindView(R.id.tv_item_history_time)
    TextView tvItemHistoryTime;
    @BindView(R.id.tv_item_history_body)
    TextView tvItemHistoryBody;
    private MyViewHolder myViewHolder;

    public RvProjectDetailsAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_history, null);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_history_time)
        TextView tvItemHistoryTime;
        @BindView(R.id.tv_item_history_body)
        TextView tvItemHistoryBody;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AutoUtils.autoSize(itemView);
        }
    }

}
