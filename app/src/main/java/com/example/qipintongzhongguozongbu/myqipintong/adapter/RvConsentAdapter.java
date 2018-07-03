package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.homelist.PositionList;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 达人帮共享列表 侧滑删除的
 *
 * Created by Administrator on 2017/6/3.
 */

public class RvConsentAdapter extends SwipeMenuAdapter {


    private final Activity mActivity;
    private final ArrayList imageList;
    private OnItemClickListener  mOnItemClickListener;


    public RvConsentAdapter(Activity mActivity, ArrayList imageList) {
        this.mActivity = mActivity;
        this.imageList = imageList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this. mOnItemClickListener = onItemClickListener;
    }



    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(mActivity).inflate(R.layout.item_share, parent, false);
    }

    @Override
    public RecyclerView.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new MyViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        GlideUtils.loadImage(mActivity, (String) imageList.get(i), ((MyViewHolder) viewHolder).sivShareIcon);

        ((MyViewHolder) viewHolder).setOnItemClickListener( mOnItemClickListener);

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        @BindView(R.id.siv_share_icon)
        SquareImageView sivShareIcon;
        @BindView(R.id.tv_share_name)
        TextView tvShareName;
        @BindView(R.id.tv_share_time)
        TextView tvShareTime;
        @BindView(R.id.tv_share_describe)
        TextView tvShareDescribe;

        OnItemClickListener mOnItemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }


}
