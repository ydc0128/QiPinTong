package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.index.EventAll;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


/**
 * Created by L on 2017/2/23.
 */
public class RyfExpectTheIndustrySmallAdapter extends RecyclerView.Adapter {
    private final Activity mActivity;

    private RyfExpectTheIndustrySmallAdapter.MyViewHolder myViewHolder;


    public RyfExpectTheIndustrySmallAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_expect_the_industry_big, null);
        myViewHolder = new RyfExpectTheIndustrySmallAdapter.MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        if (mOnItemClickListener != null){
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(myViewHolder.itemView,position);

                }
            });
        }
    }

    /** * ItemClick的回调接口 */
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private RyfExpectTheIndustrySmallAdapter.OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(RyfExpectTheIndustrySmallAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AutoUtils.autoSize(itemView);
        }

    }
}
