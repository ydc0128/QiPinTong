package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvCumulativeAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 注释：累计充值
 * 作者：碧血染银枪 on 2017/5/26 14:58
 * 邮箱：ydc_0128@163.com
 */

public class CumulativeRechargeFragment extends BaseFragment {
    @BindView(R.id.rc_cumulative)
    RecyclerView rcCumulative;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_cumulative_recharge, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    public void initData() {
        setRvCumulativeAdapter();
        super.initData();
    }

    private void setRvCumulativeAdapter() {

        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        rcCumulative.setLayoutManager(LayoutManager);
        rcCumulative.setAdapter(new RvCumulativeAdapter(mActivity));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
