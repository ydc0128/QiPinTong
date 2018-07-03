package com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvTeamAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 注释：客户库
 * 作者：碧血染银枪 on 2017/6/12 10:04
 * 邮箱：ydc_0128@163.com
 */

public class TeamKeHuFragment extends BaseFragment {
    @BindView(R.id.rv_kehu)
    RecyclerView rvKehu;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.background_team_kehu, null);
        return swipeBackView(view);
    }
    public void initData() {
        setRvTeamAdapter();

        super.initData();
    }

    private void setRvTeamAdapter() {
        rvKehu.setLayoutManager(new LinearLayoutManager(mActivity));
        rvKehu.setAdapter(new RvTeamAdapter(R.layout.top_item_kehu, ImageList.getImageList()));

        rvKehu.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                start(new FoodDetailsFragment());
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(mActivity, "点击了" + position);

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
    public void onSupportVisible() {
        mTvTitle.setText("客户库明细");
        mIvBack.setVisibility(View.GONE);
        super.onSupportVisible();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
