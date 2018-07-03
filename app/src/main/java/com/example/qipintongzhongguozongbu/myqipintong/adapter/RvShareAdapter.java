package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

/**
 * 达人帮共享列表
 * Created by Administrator on 2017/6/3.
 */

public class RvShareAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvShareAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideUtils.loadImage(mContext, item, (ImageView) helper.getView(R.id.siv_share_icon));
    }
}
