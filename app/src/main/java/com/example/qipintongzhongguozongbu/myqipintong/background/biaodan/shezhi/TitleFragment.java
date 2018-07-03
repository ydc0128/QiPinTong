package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.SealSearchActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.widget.MorePopWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：消息标题
 * 作者：碧血染银枪 on 2017/6/7 09:23
 * 邮箱：ydc_0128@163.com
 */

public class TitleFragment extends BaseFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_top_txl)
    TextView tvTopTxl;
    @BindView(R.id.ac_iv_search)
    ImageView acIvSearch;
    @BindView(R.id.seal_more)
    ImageView sealMore;

    public static TitleFragment getInstance() {
        return new TitleFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public View initView() {
        tvTopTxl.setText("消息");
        return View.inflate(mActivity, R.layout.layout_top, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ac_iv_search, R.id.seal_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_iv_search:
                startActivity(new Intent(mActivity, SealSearchActivity.class));
                break;
            case R.id.seal_more:
                MorePopWindow morePopWindow = new MorePopWindow(mActivity);
                morePopWindow.showPopupWindow(sealMore);
                break;
        }
    }
}
