package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shezhi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/7.
 * 我——sz——设置密码
 */

public class AmendPasswordFragment extends BaseFragment {

    @BindView(R.id.et_a_p_cell_set_a_password)
    EditText etAPCellSetAPassword;
    @BindView(R.id.bt_a_p_confirm)
    Button btAPConfirm;

    Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_amend_password, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("设置密码");
//        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick({R.id.et_a_p_cell_set_a_password, R.id.bt_a_p_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_a_p_cell_set_a_password:
                break;
            case R.id.bt_a_p_confirm:
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
