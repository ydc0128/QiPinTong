package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * 有商有客筛选器的排序
 * Created by Administrator on 2017/6/9.
 */

public class RvSortListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public RvSortListAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_text, item);
    }
}
