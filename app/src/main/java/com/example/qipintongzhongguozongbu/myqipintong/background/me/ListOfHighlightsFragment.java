package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvTeamLohAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 注释：排行榜详情
 * 作者：碧血染银枪 on 2017/5/25 18:31
 * 邮箱：ydc_0128@163.com
 */

public class ListOfHighlightsFragment extends BaseFragment {
    @BindView(R.id.rc_team_loh)
    RecyclerView rcTeamLoh;
    Unbinder unbinder;

    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_list_of_highights, null));
    }
    public void initData() {
        setRvTeamLohAdapter();

        super.initData();
    }

    private void setRvTeamLohAdapter() {
        rcTeamLoh.setLayoutManager(new LinearLayoutManager(mActivity));
        rcTeamLoh.setAdapter(new RvTeamLohAdapter(R.layout.top_item, ImageList.getImageList()));

        rcTeamLoh.addOnItemTouchListener(new OnItemClickListener() {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
