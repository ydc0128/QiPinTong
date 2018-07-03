package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeBean;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

/**
 * Description: 首页全职速聘适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/11 上午10:40
 */
public class rvNewFullTimeAdapter extends BaseQuickAdapter<HomeBean.FjobListBean, BaseViewHolder> {


    public rvNewFullTimeAdapter(int layoutResId, List<HomeBean.FjobListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.FjobListBean item) {
        GlideUtils.loadImage(mContext,GlobalConstants.QPT_URl+item.getCompany().getLogoImgUrl(),(ImageView) helper.getView(R.id.siv_full_time_icon));
        helper.setText(R.id.tv_full_time_post, item.getName() + "")
                .setText(R.id.tv_full_time_salary, item.getSalary() + "")
                .setText(R.id.tv_full_time_company, item.getCompany().getBrandname() + "")
                .setText(R.id.tv_full_time_location, item.getCity() + "")
                .setText(R.id.tv_full_time_year, item.getEpYears() + "")
                .setText(R.id.tv_full_time_education, item.getEduDeg() + "")
                .setText(R.id.tv_full_time_see_number, item.getViewCount() + "")
                .setText(R.id.tv_full_time_shangshi, item.getCompany().getIndustry() + "");
    }


}
