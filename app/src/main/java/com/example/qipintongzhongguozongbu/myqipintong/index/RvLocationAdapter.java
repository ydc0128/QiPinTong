package com.example.qipintongzhongguozongbu.myqipintong.index;

import android.content.Context;

import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/24 下午2:36
 */
public class RvLocationAdapter extends CommonAdapter<LocationBean> {
    public RvLocationAdapter(Context context, int layoutId, List<LocationBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, LocationBean locationBean) {
        holder.setText(R.id.tv_City, locationBean.getCity());
    }
}
