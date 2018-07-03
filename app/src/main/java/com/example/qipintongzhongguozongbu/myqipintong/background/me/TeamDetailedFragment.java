package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：部门明细
 * 作者：碧血染银枪 on 2017/5/25 17:49
 * 邮箱：ydc_0128@163.com
 */

public class TeamDetailedFragment extends BaseFragment {
    @BindView(R.id.rc_team_detailed)
    RecyclerView rcTeamDetailed;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_team_detailed, null));
    }
//    public void initData() {
//        setRvTeamDetailedAdapter();
//
//        super.initData();
//    }
//
//    private void setRvTeamDetailedAdapter() {
//        rcTeamDetailed.setLayoutManager(new LinearLayoutManager(mActivity));
//        rcTeamDetailed.setAdapter(new RvTeamDetailedAdapter(R.layout.top_bumen, ImageList.getImageList()));
//
//        rcTeamDetailed.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
////                start(new FoodDetailsFragment());
//            }
//
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtils.showToast(mActivity, "点击了" + position);
//
//            }
//        });
//    }

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

    @OnClick(R.id.rc_team_detailed)
    public void onViewClicked() {
    }

}
