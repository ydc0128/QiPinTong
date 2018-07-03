package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvRyrJiluAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by L on 2017/3/14.
 * 收到简历
 */
public class ReceivedYourResumeFragment extends BaseFragment {
    @BindView(R.id.rv_ryr_jilu)
    RecyclerView rvRyrJilu;
    @BindView(R.id.rf_shoudaojianli)
    BGARefreshLayout rfShoudaojianli;

    Unbinder unbinder;

    public void onSupportVisible() {
        mTvTitle.setText("收到简历");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    public void initData() {
        setRvRyrJiluAdapter();
        super.initData();
    }

    private void setRvRyrJiluAdapter() {

        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        rvRyrJilu.setLayoutManager(LayoutManager);
        rvRyrJilu.setAdapter(new RvRyrJiluAdapter(mActivity));
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_received_your_resume, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
