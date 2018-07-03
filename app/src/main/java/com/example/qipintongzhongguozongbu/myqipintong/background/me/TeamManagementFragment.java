package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 注释：团队管理
 * 作者：碧血染银枪 on 2017/5/25 15:41
 * 邮箱：ydc_0128@163.com
 */

public class TeamManagementFragment extends BaseFragment {
    @BindView(R.id.tv_team_shanghushiyebu)
    TextView tvTeamShanghushiyebu;
    @BindView(R.id.ll_team_shanghushiyebu)
    LinearLayout llTeamShanghushiyebu;
    @BindView(R.id.tv_team_zhaopinshiyebu)
    TextView tvTeamZhaopinshiyebu;
    @BindView(R.id.ll_team_zhaopinshiyebu)
    LinearLayout llTeamZhaopinshiyebu;
    @BindView(R.id.tv_team_xiaozhaoshiyebu)
    TextView tvTeamXiaozhaoshiyebu;
    @BindView(R.id.ll_team_xiaozhaoshiyebu)
    LinearLayout llTeamXiaozhaoshiyebu;
    @BindView(R.id.tv_team_yunyingguanlibu)
    TextView tvTeamYunyingguanlibu;
    @BindView(R.id.ll_team_yunyingguanlibu)
    LinearLayout llTeamYunyingguanlibu;
    @BindView(R.id.tv_team_jishubu)
    TextView tvTeamJishubu;
    @BindView(R.id.ll_team_jishubu)
    LinearLayout llTeamJishubu;
    @BindView(R.id.tv_team_darenbang)
    TextView tvTeamDarenbang;
    @BindView(R.id.ll_team_darenbang)
    LinearLayout llTeamDarenbang;
    @BindView(R.id.tv_team_paihangbang)
    TextView tvTeamPaihangbang;
    @BindView(R.id.ll_team_paihangbang)
    LinearLayout llTeamPaihangbang;
    Unbinder unbinder;
    @BindView(R.id.bt_team_add)
    Button btTeamAdd;

    public void onSupportVisible() {
        mTvTitle.setText("团队管理");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_team_management, null));
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

    @OnClick({R.id.ll_team_shanghushiyebu,R.id.bt_team_add, R.id.ll_team_zhaopinshiyebu, R.id.ll_team_xiaozhaoshiyebu, R.id.ll_team_yunyingguanlibu, R.id.ll_team_jishubu, R.id.ll_team_darenbang, R.id.ll_team_paihangbang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_team_shanghushiyebu:
                break;
            case R.id.ll_team_zhaopinshiyebu:
                break;
            case R.id.ll_team_xiaozhaoshiyebu:
                break;
            case R.id.ll_team_yunyingguanlibu:
                break;
            case R.id.ll_team_jishubu:
                break;
            case R.id.ll_team_darenbang:
                start(new PersonalDetailsFragment());
                break;
            case R.id.ll_team_paihangbang:
                start(new ListOfHighlightsFragment());
                break;
            case R.id.bt_team_add:
                new AlertDialog.Builder(mActivity).setTitle("请输入部门名称").setView(
                           new EditText(mActivity)).setPositiveButton("确定", null).setNegativeButton("取消", null).show();

                break;
        }
    }

}
