package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;

/**
 * Created by L on 2017/3/14.
 */
public class RvRyrJiluAdapter extends RecyclerView.Adapter {
    private  RvRyrJiluAdapter.MyViewHolder myViewHolder;
    private final Activity mActivity;

    public  RvRyrJiluAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_company_position, null);
        myViewHolder = new  RvRyrJiluAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
