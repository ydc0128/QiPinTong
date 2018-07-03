package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;

/**
 * 注释：累计充值
 * 作者：碧血染银枪 on 2017/5/26 15:03
 * 邮箱：ydc_0128@163.com
 */

public class RvCumulativeAdapter extends RecyclerView.Adapter {
    private  RvCumulativeAdapter.MyViewHolder myViewHolder;
    private final Activity mActivity;

    public RvCumulativeAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.top_leiji_chongzhi_jin_e, null);
        myViewHolder = new  RvCumulativeAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
