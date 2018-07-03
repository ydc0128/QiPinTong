package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;


/**
 * 同城路演页面详情适配器
 * Created by L on 2017/2/17.
 */

public class RvCityRoadEvaluateAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvCityRoadEvaluateAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).placeholder(R.mipmap.loding).thumbnail(0.1f).error(R.mipmap.lodingerror).centerCrop()
                .into((ImageView) helper.getView(R.id.iv_city_road_head_portrait));
    }
}
