package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5.
 */

public class RvFenXiangAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public RvFenXiangAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideUtils.loadImage(mContext,item, (ImageView) helper.getView(R.id.siv_item_consent_icon));
    }
}
