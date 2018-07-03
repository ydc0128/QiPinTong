package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ComTypeData;
import com.example.qipintongzhongguozongbu.myqipintong.bean.HomeBean;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ItemTypeDate;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.util.List;

import butterknife.BindView;

/**
 * Description: 首页名企在线的适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/3/10 下午4:17
 */
public class RvNewHomeCompanyAdapter extends BaseMultiItemQuickAdapter<ComTypeData, BaseViewHolder> {

    @BindView(R.id.siv_item_famous)
    SquareImageView sivItemFamous;
    @BindView(R.id.tv_item_famous_title)
    TextView tvItemFamousTitle;
    @BindView(R.id.tv_item_number)
    TextView tvItemNumber;
    @BindView(R.id.tv_item_famous_type)
    TextView tvItemFamousType;
    @BindView(R.id.tv_item_famous_city)
    TextView tvItemFamousCity;
    @BindView(R.id.tv_item_famous_position)
    TextView tvItemFamousPosition;

    public RvNewHomeCompanyAdapter(List date) {
        super(date);
        addItemType(ItemTypeDate.IMAGE_TYPE, R.layout.item_famous_company);
        addItemType(ItemTypeDate.MORE_TYPE, R.layout.item_show_more);
    }


    @Override
    protected void convert(BaseViewHolder helper, ComTypeData item) {
        switch (helper.getItemViewType()) {
            case ItemTypeDate.IMAGE_TYPE:

                int size = item.getList().size();
                int Position = helper.getLayoutPosition();

                if (size != Position) {

                    HomeBean.ComListBean bean = item.getList().get(Position);

                    GlideUtils.loadImage(mContext, GlobalConstants.QPT_URl + bean.getLogoImgUrl(), (ImageView) helper.getView(R.id.siv_item_famous));

                    helper.setText(R.id.tv_item_famous_title, bean.getBrandname() + "")
                            .setText(R.id.tv_item_number, bean.getViewCount() + "")
                            .setText(R.id.tv_item_famous_type, bean.getIndustry() + "")
                            .setText(R.id.tv_item_famous_city, bean.getTopJob().getCity() + "")
                            .setText(R.id.tv_item_famous_position, bean.getTopJob().getName() + "")
                            .setText(R.id.tv_item_famous_number, "等" + bean.getJobCount() + "个职位")
                            .setVisible(R.id.iv_item_famous_vip, bean.isAuthFlag());

                }

                break;

            case ItemTypeDate.MORE_TYPE:

                break;
        }
    }
}
