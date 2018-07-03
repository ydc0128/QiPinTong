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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNWorkAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FacePositionDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by L on 2017/3/31.
 * PMS_找工作
 */

public class ZhaoGongZuoFragment extends BaseFragment {
    @BindView(R.id.rv_m_zhaogongzuo)
    RecyclerView rvMZhaogongzuo;


    Unbinder unbinder;


    public static ZhaoGongZuoFragment fragment = null;

    public static ZhaoGongZuoFragment getInstance() {

        if (fragment == null) {
            fragment = new ZhaoGongZuoFragment();
        }
        return fragment;
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_me_haogongzuo, null));

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {


        setRvSuPinAdapter();

        super.onLazyInitView(savedInstanceState);
    }


    private void setRvSuPinAdapter() {

        rvMZhaogongzuo.setLayoutManager(new LinearLayoutManager(mActivity));
        // rvNWork.setAdapter(new RvNWorkAdapter(R.layout.item_good_work, ImageList.getImageList()));
        rvMZhaogongzuo.setAdapter(new RvNWorkAdapter(R.layout.item_full_time_job, ImageList.getImageList()));


        rvMZhaogongzuo.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                ((SupportFragment) getParentFragment()).start(new FacePositionDetailsFragment());

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
