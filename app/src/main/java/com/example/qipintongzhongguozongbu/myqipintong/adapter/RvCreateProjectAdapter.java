package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ItemTypeDate;

import java.util.List;

/**
 * Description: 创投天下的项目推荐条目
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/12 下午2:29
 */
public class RvCreateProjectAdapter extends BaseMultiItemQuickAdapter<ItemTypeDate, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public RvCreateProjectAdapter(List<ItemTypeDate> data) {
        super(data);
        addItemType(ItemTypeDate.IMAGE_TYPE, R.layout.item_create_project);
        addItemType(ItemTypeDate.MORE_TYPE, R.layout.item_show_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemTypeDate item) {
        switch (helper.getItemViewType()) {
            case ItemTypeDate.IMAGE_TYPE:

                int size = item.getList().size();
                int layoutPosition = helper.getLayoutPosition();

                if (size != layoutPosition) {
                    Glide.with(mContext).load(item.getList().get(layoutPosition)).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror)
                            .into((ImageView) helper.getView(R.id.siv_item_create_company));
                }

                break;

            case ItemTypeDate.MORE_TYPE:

                break;
        }
    }
}
