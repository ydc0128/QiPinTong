package com.example.qipintongzhongguozongbu.myqipintong.fragment.darenbang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFenXiangAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 已共享的页面
 * Created by Administrator on 2017/6/3.
 */

public class ShareConsentPager extends BaseFragment {
    @BindView(R.id.rv_RecyclerView)
    RecyclerView rvRecyclerView;
    Unbinder unbinder;

    public static BaseFragment getInstance() {
        return new ShareConsentPager();
    }

    @Override
    public View initView() {
        return View.inflate(mActivity, R.layout.recyclerview, null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        setAdapter();
        super.onLazyInitView(savedInstanceState);
    }


    private void setAdapter() {

        rvRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        rvRecyclerView.setAdapter(new RvFenXiangAdapter(R.layout.item_consent_share, ImageList.getImageList()));

        rvRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((SupportFragment) getParentFragment()).start(new ShareDetailsFragment());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
