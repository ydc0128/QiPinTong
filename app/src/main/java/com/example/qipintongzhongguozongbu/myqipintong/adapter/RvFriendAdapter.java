package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDateMode;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.photoDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnImageClick;
import com.example.qipintongzhongguozongbu.myqipintong.view.NineGridLayout;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 朋友圈动态详情适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/13 上午11:27
 */
public class RvFriendAdapter extends BaseQuickAdapter<FriendDateMode, BaseViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        NineGridLayout layout;
        @BindView(R.id.SIV_item_icon)
        SquareImageView SIVItemIcon;
        @BindView(R.id.tv_item_name)
        TextView tvItemName;
        @BindView(R.id.iv_item_delete)
        ImageView ivItemDelete;
        @BindView(R.id.tv_item_time)
        TextView tvItemTime;
        @BindView(R.id.tv_item_body)
        TextView tvItemBody;
        @BindView(R.id.tv_item_location)
        TextView tvItemLocation;
        @BindView(R.id.tv_item_give_number)
        TextView tvItemGiveNumber;
        @BindView(R.id.tv_item_zan_number)
        TextView tvItemZanNumber;
        @BindView(R.id.rl_item_zan)
        RelativeLayout rlItemZan;
        @BindView(R.id.tv_item_commnet)
        TextView tvItemCommnet;
        @BindView(R.id.rl_item_comment)
        RelativeLayout rlItemComment;
        @BindView(R.id.rl_item_give_money)
        RelativeLayout rlItemGiveMoney;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            layout = (NineGridLayout) itemView.findViewById(R.id.layout_nine_grid);
        }
    }

    public RvFriendAdapter(int layoutResId, List<FriendDateMode> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FriendDateMode item) {
        NineGridLayout layout = helper.getView(R.id.layout_nine_grid);
        layout.setUrlList(item.urlList);
        layout.setOnImageClickListener(new OnImageClick() {
            @Override
            public void onItemClick(int position, ArrayList<String> list) {
                photoDetailsFragment photoDetailsFragment = new photoDetailsFragment();
                photoDetailsFragment.setPhotoList(list);
                photoDetailsFragment.setPosition(position);
            }
        });
        helper.addOnClickListener(R.id.rl_item_give_money);
    }
}
