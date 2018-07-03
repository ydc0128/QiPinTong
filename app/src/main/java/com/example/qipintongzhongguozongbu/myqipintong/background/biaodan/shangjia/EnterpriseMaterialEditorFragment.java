package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

/**
 * Created by L on 2017/3/14.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/4.
 * 公司注册界面
 */

public class EnterpriseMaterialEditorFragment extends BaseFragment {
    @BindView(R.id.enterprise_data)
    LinearLayout enterpriseData;
    @BindView(R.id.ll_eme_investment_institutions_data)
    LinearLayout llEmeInvestmentInstitutionsData;
Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_enterprise_material_editor, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
       unbinder= ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("公司资料");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick({R.id.enterprise_data, R.id.ll_eme_investment_institutions_data})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enterprise_data:
                start(new EnterpriseDataFragment());
                break;
            case R.id.ll_eme_investment_institutions_data:
                start(new InvestmentInstitutionsDataFragment());
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}