package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

/**
 * 达人帮首页列表适配器
 * Created by Administrator on 2017/6/2.
 */

public class RvExpertAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvExpertAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {

        GlideUtils.loadImage(mContext, item, (ImageView) helper.getView(R.id.siv_item_expert_icon));

        helper.addOnClickListener(R.id.tv_item_expert_share);

        helper.getView(R.id.tv_item_expert_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setText(R.id.tv_item_expert_share, "接受");
            }
        });
    }


}
