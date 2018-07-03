package com.example.qipintongzhongguozongbu.myqipintong.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;

import butterknife.BindView;

/**
 * Created by L on 2017/2/11.
 * 收支记录适配器
 */
public class RvBalanceMarketAdapter extends RecyclerView.Adapter {

    private final Activity mActivity;
    @BindView(R.id.tv_balance_add_no)
    TextView tvBalanceAddNo;
    @BindView(R.id.rl_balance_date_1)
    RelativeLayout rlBalanceDate1;
    private MyViewHolder myViewHolder;

    public RvBalanceMarketAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_balance_of_payments, null);
        myViewHolder = new MyViewHolder(view);
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
