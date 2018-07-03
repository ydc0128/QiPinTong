package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;

import java.util.List;

import static com.example.qipintongzhongguozongbu.myqipintong.R.id.siv_item_requse_icon;

/**
 * 共享邀请列表
 * Created by Administrator on 2017/6/3.
 */

public class RvRequseAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvRequseAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        GlideUtils.loadImage(mContext, item, (ImageView) helper.getView(siv_item_requse_icon));

        helper.addOnClickListener(R.id.tv_item_requse_ok);

        helper.getView(R.id.tv_item_requse_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setText(R.id.tv_item_requse_ok, "已接受");
                helper.setBackgroundRes(R.id.tv_item_requse_ok, R.drawable.shape_gray);
            }
        });

    }
}
