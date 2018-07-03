package com.example.qipintongzhongguozongbu.myqipintong.background.me;

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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNBingCompanyAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.mingqizaixian.CompanyDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by L on 2017/3/31.
 * 找公司
 */

public class ZhaoGongSiFragment extends BaseFragment {

    @BindView(R.id.rv_m_zhaogongzuo)
    RecyclerView rvMZhaogongzuo;



    Unbinder unbinder;
    public static ZhaoGongSiFragment fragment = null;
    public static ZhaoGongSiFragment getInstance() {

        if (fragment == null) {
            fragment = new ZhaoGongSiFragment();
        }
        return fragment;
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_me_mingqi, null));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        setRvNBigCompanyAdapter();

        super.onLazyInitView(savedInstanceState);
    }

    private void setRvNBigCompanyAdapter() {

        rvMZhaogongzuo.setLayoutManager(new LinearLayoutManager(mActivity));
        rvMZhaogongzuo.setAdapter(new RvNBingCompanyAdapter(R.layout.item_mingqi, ImageList.getImageList()));

        rvMZhaogongzuo.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ((SupportFragment) getParentFragment()).start(new CompanyDetailsFragment());
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
