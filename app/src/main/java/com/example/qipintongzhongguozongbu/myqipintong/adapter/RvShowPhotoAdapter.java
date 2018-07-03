package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.event.MessageEvent;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.io.File;
import java.util.ArrayList;

import de.greenrobot.event.EventBus;


/**
 * Description: 选择图片后展示的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/22 上午11:27
 */
public class RvShowPhotoAdapter extends RecyclerView.Adapter {


    private final Activity activity;
    private OnItemClickListener mOnItemClickListener;
    private ArrayList<File> mImageList;

    public RvShowPhotoAdapter(Activity Activity) {
        activity = Activity;
        mImageList = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_show_photo, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        if (position == mImageList.size()) {
            //最后一张 应该是加号图片
            Glide.with(activity).load(R.mipmap.addimage).into(((MyViewHolder) holder).ivShow);
            //这里是点击进入相册选择相片的按钮
            ((MyViewHolder) holder).ivDelete.setVisibility(View.GONE);
        } else {

            Glide.with(activity).load(mImageList.get(position)).into(((MyViewHolder) holder).ivShow);

            ((MyViewHolder) holder).ivDelete.setVisibility(View.VISIBLE);
        }


        if (mOnItemClickListener != null) {

            ((MyViewHolder) holder).ivShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (position == mImageList.size()) {//如果是最后一条数据 那个应该是加号添加图片的按钮 点击后去相册选择图片

                        mOnItemClickListener.onItemClick(position);

                    } else {//这里表示点击了选中的图片 应该去图片的详情页

                        ToastUtils.showToast(activity, "点击" + position);
                    }
                }
            });
        }
        //图片的删除按钮
        ((MyViewHolder) holder).ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position != mImageList.size()) {

                    mImageList.remove(mImageList.get(position));
                    notifyItemRemoved(position);
                    //这里点击后要刷新适配器
                    EventBus.getDefault().post(new MessageEvent());
                    //通知适配器进行刷新数据
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageList.size() + 1;
    }

    public void setmImageList(ArrayList images) {

        if (mImageList.size() + images.size() < 10) {

            mImageList.addAll(images);

        } else {

            ToastUtils.showToast(activity, "亲,最多选九张哦");

        }
    }


    public ArrayList<File> getImageList() {
        return mImageList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public SquareImageView ivShow;
        public ImageView ivDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivShow = (SquareImageView) itemView.findViewById(R.id.iv_show_photo);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete_photo);
        }

    }


}
