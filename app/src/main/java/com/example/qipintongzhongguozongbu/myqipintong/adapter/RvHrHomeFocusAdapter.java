package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDynamicBean;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: HR Home Hr 条目列表
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/2/17 下午3:42
 */
public class RvHrHomeFocusAdapter extends RecyclerView.Adapter {


    private final Activity mActivity;
    private final BaseFragment baseFragment;
    private final List<FriendDynamicBean> date;
    private MyViewHolder myViewHolder;


    public RvHrHomeFocusAdapter(Activity mActivity, BaseFragment baseFragment, List<FriendDynamicBean> date) {
        this.mActivity = mActivity;
        this.baseFragment = baseFragment;
        this.date = date;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_hr_home_focus, null);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Glide.with(mActivity).load(date.get(position).getIcon()).crossFade().placeholder(R.mipmap.loding).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.1f).error(R.mipmap.lodingerror).centerCrop().into((((MyViewHolder) holder).CIVHrHomeFocusIcon));

        myViewHolder.tvHrHomeFocusName.setText(date.get(position).getName());

        myViewHolder.tvHrHomeFocusBody.setText(date.get(position).getInputText());

        Glide.with(mActivity).load(date.get(position).getIcon()).into(myViewHolder.CIVHrHomeFocusIcon);

        if (date.get(position).getImageList() != null) {//当传递的图片数据不为空才去设置适配器
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            myViewHolder.rvHrHomeImage.setLayoutManager(gridLayoutManager);
            myViewHolder.rvHrHomeImage.addItemDecoration(new RvItemDecoration(10));
            //  myViewHolder.rvHrHomeImage.setAdapter(new RvPersonImgaeAdapter(mActivity, baseFragment, date.get(position).getImageList()));

        }


    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.CIV_hr_home_focus_icon)
        CircleImageView CIVHrHomeFocusIcon;
        @BindView(R.id.tv_hr_home_focus_name)
        TextView tvHrHomeFocusName;
        @BindView(R.id.iv_hr_home_vip)
        ImageView ivHrHomeVip;
        @BindView(R.id.tv_hr_home_focus_location)
        TextView tvHrHomeFocusLocation;
        @BindView(R.id.tv_hr_home_focus_time)
        TextView tvHrHomeFocusTime;
        @BindView(R.id.tv_hr_home_focus_body)
        TextView tvHrHomeFocusBody;
        @BindView(R.id.rv_hr_home_image)
        RecyclerView rvHrHomeImage;
        @BindView(R.id.tv_item_share_number)
        TextView tvItemShareNumber;
        @BindView(R.id.ll_hr_home_share)
        LinearLayout llHrHomeShare;
        @BindView(R.id.ll_hr_home_comment)
        LinearLayout llHrHomeComment;
        @BindView(R.id.ll_hr_home_zan)
        LinearLayout llHrHomeZan;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AutoUtils.autoSize(itemView);
        }
    }

}
