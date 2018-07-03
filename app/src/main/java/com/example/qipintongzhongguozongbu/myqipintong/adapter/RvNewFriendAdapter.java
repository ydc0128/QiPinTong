package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDateMode;
import com.example.qipintongzhongguozongbu.myqipintong.event.FriendClickEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.PhotoPopup;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai.InteractFragemnt;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.photoDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnImageClick;
import com.example.qipintongzhongguozongbu.myqipintong.view.FriendNineLayout;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Description: 朋友圈适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/4/23 下午4:30
 */
public class RvNewFriendAdapter extends RecyclerView.Adapter<RvNewFriendAdapter.ViewHolder> {

    private final BaseFragment fragment;
    private Activity mActivity;
    private List<FriendDateMode> mList;
    protected LayoutInflater inflater;

    public RvNewFriendAdapter(BaseFragment fragment, Activity mActivity) {
        this.mActivity = mActivity;
        inflater = LayoutInflater.from(mActivity);
        this.fragment = fragment;
    }


    public void setList(List<FriendDateMode> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.item_friend, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.layout.setIsShowAll(mList.get(position).isShowAll);
        holder.layout.setUrlList(mList.get(position).urlList);
        holder.layout.setOnImageClickListener(new OnImageClick() {
            @Override
            public void onItemClick(int position, ArrayList<String> list) {
//                photoDetailsFragment photoDetailsFragment = new photoDetailsFragment();
//                photoDetailsFragment.setPhotoList(list);
//                photoDetailsFragment.setPosition(position);
//                fragment.start(photoDetailsFragment);

                new PhotoPopup(mActivity, fragment, list, position).showPopupWindow();
            }
        });

        ((ViewHolder) holder).ivItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FriendClickEvent(position, 0, null));
            }
        });
        ((ViewHolder) holder).rlItemZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FriendClickEvent(position, 1, null));
            }
        });
        ((ViewHolder) holder).rlItemComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FriendClickEvent(position, 2, null));
            }
        });
        ((ViewHolder) holder).rlItemGiveMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FriendClickEvent(position, 3, null));
            }
        });
        ((ViewHolder) holder).SIVItemIcon.setOnClickListener(new View.OnClickListener() {//头像
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FriendClickEvent(position, 4, null));
            }
        });
        ((ViewHolder) holder).tvItemName.setOnClickListener(new View.OnClickListener() {//名字
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FriendClickEvent(position, 5, null));
            }
        });
        ((ViewHolder) holder).tvItemBody.setOnClickListener(new View.OnClickListener() {//名字
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FriendClickEvent(position, 6, null));
            }
        });
        ((ViewHolder) holder). tvItemGiveNumber.setOnClickListener(new View.OnClickListener() {//几人已打赏
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FriendClickEvent(position, 7, null));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListSize(mList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FriendNineLayout layout;
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
            layout = (FriendNineLayout) itemView.findViewById(R.id.layout_nine_grid);
        }
    }

    private int getListSize(List<FriendDateMode> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }
}
