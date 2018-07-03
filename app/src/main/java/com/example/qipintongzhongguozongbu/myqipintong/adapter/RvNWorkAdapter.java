package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Created by L on 2017/3/18.
 * 创投天下——好工作--后台
 */
public class RvNWorkAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RvNWorkAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        Glide.with(mContext).load(item).placeholder(R.mipmap.loding)
                .error(R.mipmap.lodingerror).into((ImageView) helper.getView(R.id.siv_full_time_icon));

        helper.getView(R.id.iv_full_time_vip).setVisibility(View.GONE);

    }
}
