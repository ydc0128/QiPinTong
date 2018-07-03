package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * 有商有客选项卡全部商家的适配器
 * Created by Administrator on 2017/6/10.
 */

public class RvComerceAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvComerceAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_item_text_select, item);

    }
}
