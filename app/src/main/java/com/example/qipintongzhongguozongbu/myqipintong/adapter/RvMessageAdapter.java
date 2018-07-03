package com.example.qipintongzhongguozongbu.myqipintong.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 消息列表的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/14 上午11:54
 */
public class RvMessageAdapter extends SwipeMenuAdapter {


    private final Activity mActivity;
    private final List mIvDate;
    private OnItemClickListener mOnItemClickListener;

    public RvMessageAdapter(Activity mActivity, List mIvDate) {
        this.mActivity = mActivity;
        this.mIvDate = mIvDate;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(mActivity).inflate(R.layout.item_message, parent, false);
    }

    @Override
    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {

        return new MyViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Glide.with(mActivity).load(mIvDate.get(position)).placeholder(R.mipmap.loding).thumbnail(0.1f).error(R.mipmap.lodingerror).into(((MyViewHolder) holder).CIVMessageIcon);

        ((MyViewHolder) holder).setOnItemClickListener(mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mIvDate.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.CIV_message_icon)
        CircleImageView CIVMessageIcon;
        @BindView(R.id.tv_message_name)
        TextView tvMessageName;
        @BindView(R.id.tv_message_time)
        TextView tvMessageTime;
        @BindView(R.id.tv_message_body)
        TextView tvMessageBody;

        OnItemClickListener mOnItemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }


        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
