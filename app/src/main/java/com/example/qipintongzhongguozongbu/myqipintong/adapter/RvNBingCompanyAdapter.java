package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

/**
 * Created by L on 2017/3/18.
 * 后台创投天下————名企
 */
public class RvNBingCompanyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvNBingCompanyAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideUtils.loadImage(mContext, item, (ImageView) helper.getView(R.id.siv_gw_zhaopian));
    }
}
