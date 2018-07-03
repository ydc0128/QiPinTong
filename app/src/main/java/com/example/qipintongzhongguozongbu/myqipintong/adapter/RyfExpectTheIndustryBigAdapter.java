package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by L on 2017/2/23.
 * <p>
 * 行业分类大类适配
 */
public class RyfExpectTheIndustryBigAdapter extends RecyclerView.Adapter {
    private final Activity mActivity;
    @BindView(R.id.tv_hangye)
    TextView tvHangye;
    private MyViewHolder myViewHolder;

    public RyfExpectTheIndustryBigAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_expect_the_industry_big, null);
        myViewHolder = new MyViewHolder(view);
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

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener){
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
