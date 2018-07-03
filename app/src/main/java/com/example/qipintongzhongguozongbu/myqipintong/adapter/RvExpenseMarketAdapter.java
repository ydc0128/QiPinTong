package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;

import butterknife.BindView;

/**
 * 消费记录适配器
 * Created by L on 2017/2/11.
 */
public class RvExpenseMarketAdapter extends RecyclerView.Adapter {
    @BindView(R.id.tv_expense_week)
    TextView tvExpenseWeek;
    @BindView(R.id.tv_balance_add_no)
    TextView tvBalanceAddNo;
    @BindView(R.id.rl_expense_galender_time)
    LinearLayout rlExpenseGalenderTime;
    @BindView(R.id.tv_expense_add)
    TextView tvExpenseAdd;
    @BindView(R.id.tv_expense_aglender)
    TextView tvExpenseAglender;
    @BindView(R.id.rl_ecpence_galend)
    LinearLayout rlEcpenceGalend;
    @BindView(R.id.tv_expense_add_tongbi)
    TextView tvExpenseAddTongbi;

    private final Activity mActivity;
    private RvExpenseMarketAdapter.MyViewHolder myViewHolder;

    public RvExpenseMarketAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_expense_galender, null);
        myViewHolder = new RvExpenseMarketAdapter.MyViewHolder(view);
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
