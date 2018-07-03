package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 达人帮选项卡粉丝适配器
 * Created by Administrator on 2017/6/6.
 */

public class RvFansListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RvFansListAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_text, item);
    }
}
