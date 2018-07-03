package com.example.qipintongzhongguozongbu.myqipintong.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;


/**
 * function   : 抢惠券适配器
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/3/24  下午12:05
 */
public class RvRobHuijMarketAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvRobHuijMarketAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        Glide.with(mContext).load(item).crossFade().placeholder(R.mipmap.loding).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.1f).error(R.mipmap.lodingerror).centerCrop().into((ImageView) helper.getView(R.id.iv_rob_huijuan_item_project_icon));

        helper.addOnClickListener(R.id.tv_item_take_huiquan_take);

    }
}
