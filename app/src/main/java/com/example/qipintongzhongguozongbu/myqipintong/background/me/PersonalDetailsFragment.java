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
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvTeamPersonalDetailedAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 注释：个人明细
 * 作者：碧血染银枪 on 2017/5/25 18:00
 * 邮箱：ydc_0128@163.com
 */

public class PersonalDetailsFragment extends BaseFragment {


    @BindView(R.id.rc_team_personal_detailed)
    RecyclerView rcTeamPersonalDetailed;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.personal_details, null));
    }

    public void initData() {
        setRvTeamPersonalDetailedAdapter();

        super.initData();
    }

    private void setRvTeamPersonalDetailedAdapter() {
        rcTeamPersonalDetailed.setLayoutManager(new LinearLayoutManager(mActivity));
        rcTeamPersonalDetailed.setAdapter(new RvTeamPersonalDetailedAdapter(R.layout.top_item_geren, ImageList.getImageList()));

        rcTeamPersonalDetailed.addOnItemTouchListener(new OnItemClickListener() {
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
