package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDynamicBean;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.photoDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 有才有貌个人详情的动态条目适配
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/17 下午4:48
 */
public class RvPersonAynmicAdapter extends RecyclerView.Adapter {

    private final Activity mActivity;
    private final BaseFragment baseFragment;
    private final List<FriendDynamicBean> date;

    private GridLayoutManager gridLayoutManager;


    public RvPersonAynmicAdapter(Activity mActivity, BaseFragment baseFragment, List<FriendDynamicBean> date) {
        this.baseFragment = baseFragment;
        this.mActivity = mActivity;
        this.date = date;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_person_aynmic, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((MyViewHolder) holder).tvPersonAynmicName.setText(date.get(position).getName());
        ((MyViewHolder) holder).tvPersonAynmicState.setText(date.get(position).getInputText());
        ((MyViewHolder) holder).CIVHrHomeFocusIcon.setImageResource(date.get(position).getIcon());


        if (date.get(position).getImageList() != null && date.get(position).getImageList().size() != 0) {

            if (date.get(position).getImageList().size() == 1) {//这里根据图片的数量决定用什么样的布局去显示
                gridLayoutManager = new GridLayoutManager(mActivity, 1);
            } else if (date.get(position).getImageList().size() == 4) {
                gridLayoutManager = new GridLayoutManager(mActivity, 2);
            } else {
                gridLayoutManager = new GridLayoutManager(mActivity, 3);
            }

            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            ((MyViewHolder) holder).rvPersonAynmicPhoto.setLayoutManager(gridLayoutManager);
            ((MyViewHolder) holder).rvPersonAynmicPhoto.addItemDecoration(new RvItemDecoration(3));

            //相片的适配器
            ((MyViewHolder) holder).rvPersonAynmicPhoto.setAdapter(new RvPersonImgaeAdapter(R.layout.item_person_image, date.get(position).getImageList()));
            ((MyViewHolder) holder).rvPersonAynmicPhoto.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int mPosition) {
                    photoDetailsFragment photoDetailsFragment = new photoDetailsFragment();
                    photoDetailsFragment.setPosition(mPosition);
                    photoDetailsFragment.setPhotoList(date.get(position).getImageList());
                    baseFragment.start(photoDetailsFragment);
                }
            });
        }


    }


    @Override
    public int getItemCount() {
        return date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.CIV_hr_home_focus_icon)
        CircleImageView CIVHrHomeFocusIcon;
        @BindView(R.id.tv_person_aynmic_name)
        TextView tvPersonAynmicName;
        @BindView(R.id.iv_person_aynmic_vip)
        ImageView ivPersonAynmicVip;
        @BindView(R.id.tv_person_aynmic_time)
        TextView tvPersonAynmicTime;
        @BindView(R.id.tv_person_aynmic_state)
        TextView tvPersonAynmicState;
        @BindView(R.id.rv_person_aynmic_photo)
        RecyclerView rvPersonAynmicPhoto;
        @BindView(R.id.iv_person_aynmic_share)
        ImageView ivPersonAynmicShare;
        @BindView(R.id.tv_item_share_number)
        TextView tvItemShareNumber;
        @BindView(R.id.iv_person_aynmic_comment)
        ImageView ivPersonAynmicComment;
        @BindView(R.id.tv_item_comment_number)
        TextView tvItemCommentNumber;
        @BindView(R.id.iv_person_aynmic_zan)
        ImageView ivPersonAynmicZan;
        @BindView(R.id.tv_item_friend_zan_number)
        TextView tvItemFriendZanNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
