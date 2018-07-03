package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;

/**
 * Created by L on 2017/3/18.
 * 创投天下——生活圈--后台
 */
public class RvNLifeAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public RvNLifeAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(item).placeholder(R.mipmap.loding).thumbnail(0.1f)
                .error(R.mipmap.lodingerror).centerCrop().into((ImageView) helper.getView(R.id.iv_sh_greens));
    }
}
