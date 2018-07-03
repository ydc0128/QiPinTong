package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;


/**
 * Created by L on 2017/3/2.
 */
public class RyFragmentCuisineSmallAdapter extends RecyclerView.Adapter {
        private final Activity mActivity;
        private com.example.qipintongzhongguozongbu.myqipintong.adapter.RyFragmentCuisineSmallAdapter.MyViewHolder myViewHolder;

        public RyFragmentCuisineSmallAdapter(Activity mActivity) {
            this.mActivity = mActivity;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.item_food_cuisine, null);
            myViewHolder = new com.example.qipintongzhongguozongbu.myqipintong.adapter.RyFragmentCuisineSmallAdapter.MyViewHolder(view);
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

        private RyfExpectTheIndustryBigAdapter.OnItemClickListener mOnItemClickListener;

        public void setmOnItemClickListener(RyfExpectTheIndustryBigAdapter.OnItemClickListener mOnItemClickListener){
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
