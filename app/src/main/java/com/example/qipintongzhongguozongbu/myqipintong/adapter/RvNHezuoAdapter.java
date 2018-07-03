package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.util.List;

/**
 * Created by L on 2017/3/18.
 * <p>
 * 创投天下——合作--后台
 */
public class RvNHezuoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RvNHezuoAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        GlideUtils.loadImage(mContext, item, (SquareImageView) helper.getView(R.id.siv_ihhr_photo));

    }
}
