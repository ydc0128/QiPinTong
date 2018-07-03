package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * 注释：排行榜个人适配器
 * 作者：碧血染银枪 on 2017/5/25 18:07
 * 邮箱：ydc_0128@163.com
 */

public class RvTeamPersonalDetailedAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public RvTeamPersonalDetailedAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror).into((ImageView) helper.getView(R.id.item_top_geren_photo));

    }
}
