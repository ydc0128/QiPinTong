package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * 注释：
 * 作者：碧血染银枪 on 2017/6/12 10:34
 * 邮箱：ydc_0128@163.com
 */

public class RvTeamAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
        public RvTeamAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, String item) {
            Glide.with(mContext).load(item).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror).into((ImageView) helper.getView(R.id.item_top_kehu));

        }
    }

