package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvTeamItemAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.UserDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：部门个人
 * 作者：碧血染银枪 on 2017/6/6 09:12
 * 邮箱：ydc_0128@163.com
 */

public class TeamItemFragment extends BaseFragment {
    @BindView(R.id.rv_team_item)
    RecyclerView rvTeamItem;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_team_item, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
    public void initData() {
        setRvTeamItemAdapter();
    }
    private void setRvTeamItemAdapter() {
        rvTeamItem.setLayoutManager(new LinearLayoutManager(mActivity));
        rvTeamItem.setAdapter(new RvTeamItemAdapter(R.layout.top_item, ImageList.getImageList()));

        rvTeamItem.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity, UserDetailActivity.class));
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity, UserDetailActivity.class));

            }
        });
    }
    public void onSupportVisible() {
        mTvTitle.setText("团队成员");
        mIvBack.setVisibility(View.GONE);
        super.onSupportVisible();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.rv_team_item)
    public void onViewClicked() {
    }
}
