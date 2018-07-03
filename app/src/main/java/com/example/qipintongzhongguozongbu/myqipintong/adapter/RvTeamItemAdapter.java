package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * 注释：团队管理
 * 作者：碧血染银枪 on 2017/6/6 09:19
 * 邮箱：ydc_0128@163.com
 */

public class RvTeamItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvTeamItemAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        Glide.with(mContext).load(item).crossFade().placeholder(R.mipmap.loding).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.1f).error(R.mipmap.lodingerror).centerCrop().into((ImageView) helper.getView(R.id.iv_top_item_photo));

        helper.addOnClickListener(R.id.iv_top_item_photo);
    }
}
