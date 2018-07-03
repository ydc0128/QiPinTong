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
 * Created by L on 2017/3/31.
 */
public class RvJlJiaoyujingyanAdapter extends RecyclerView.Adapter {
    private final Activity mActivity;
    @BindView(R.id.tv_jj_xuexiao)
    TextView tvJjXuexiao;
    @BindView(R.id.tv_jj_kaishi)
    TextView tvJjKaishi;
    @BindView(R.id.tv_jj_jieshu)
    TextView tvJjJieshu;
    @BindView(R.id.tv_jj_zhuanye)
    TextView tvJjZhuanye;
    @BindView(R.id.tv_jj_xueli)
    TextView tvJjXueli;
    private MyViewHolder myViewHolder;

    public RvJlJiaoyujingyanAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_jiaoyu_jingyan, null);
        myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mOnItemClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(myViewHolder.itemView, position);
                }
            });
        }
    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AutoUtils.autoSize(itemView);
        }

    }
}
