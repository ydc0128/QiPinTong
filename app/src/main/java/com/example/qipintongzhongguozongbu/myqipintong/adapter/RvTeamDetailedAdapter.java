package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * 注释：部门详情适配器
 * 作者：碧血染银枪 on 2017/5/25 17:55
 * 邮箱：ydc_0128@163.com
 */

public class RvTeamDetailedAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RvTeamDetailedAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        Glide.with(mContext).load(item).crossFade().placeholder(R.mipmap.loding).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.1f).error(R.mipmap.lodingerror);

        helper.addOnClickListener(R.id.ll_tb_chengshi);
    }
}
