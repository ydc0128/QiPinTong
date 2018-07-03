package com.example.qipintongzhongguozongbu.myqipintong.background.me;

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
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/27.
 * 修改密码
 */
public class ChangePasswordFragment extends BaseFragment {
    @BindView(R.id.et_a_p_cell_set_a_password_y_jiu)
    EditText etAPCellSetAPasswordYJiu;
    @BindView(R.id.et_a_p_cell_set_a_password_y_xin)
    EditText etAPCellSetAPasswordYXin;
    @BindView(R.id.et_a_p_cell_set_a_password_y_chongfu)
    EditText etAPCellSetAPasswordYChongfu;
    @BindView(R.id.bt_c_p_confirm)
    Button btAPConfirm;

    Unbinder unbinder;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_change_password, null));
    }
    public void onSupportVisible() {
        mTvTitle.setText("修改密码");
//        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
