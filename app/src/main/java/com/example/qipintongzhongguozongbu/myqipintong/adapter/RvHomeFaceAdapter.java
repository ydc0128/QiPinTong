package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ItemTypeDate;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

/**
 * Description: 首页有才有貌的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/5 下午4:07
 */
public class RvHomeFaceAdapter extends BaseMultiItemQuickAdapter<ItemTypeDate, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RvHomeFaceAdapter(List<ItemTypeDate> data) {
        super(data);
        addItemType(ItemTypeDate.IMAGE_TYPE, R.layout.item_home_company);
        addItemType(ItemTypeDate.MORE_TYPE, R.layout.item_show_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemTypeDate item) {
        switch (helper.getItemViewType()) {
            case ItemTypeDate.IMAGE_TYPE:

                int size = item.getList().size();
                int layoutPosition = helper.getLayoutPosition();

                if (size != layoutPosition) {

                    GlideUtils.loadImage(mContext, GlobalConstants.QPT_URl + item.getList().get(layoutPosition).getHeadImgUrl(), (ImageView) helper.getView(R.id.siv_home_company));

                    helper.setText(R.id.tv_company_name, item.getList().get(layoutPosition).getSnsname() + "")
                            .setText(R.id.tv_company_year, item.getList().get(layoutPosition).getEpYears() + "")
                            .setText(R.id.tv_company_education, item.getList().get(layoutPosition).getEduDeg() + "")
                            .setVisible(R.id.iv_company_vip, item.getList().get(layoutPosition).isAuthFlag())
                            .setText(R.id.tv_company_catTitle, item.getList().get(layoutPosition).getTopJob().getCatTitle() + "")
                            .setText(R.id.tv_company_salary, item.getList().get(layoutPosition).getTopJob().getSalarystring() + "")
                            .setText(R.id.tv_company_see_number, item.getList().get(layoutPosition).getTopJob().getViewCount() + "");
                }

                break;

            case ItemTypeDate.MORE_TYPE:

                break;
        }
    }
}
