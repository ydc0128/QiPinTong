package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注释：编辑简历
 * 作者：碧血染银枪 on 2017/5/16 09:26
 * 邮箱：ydc_0128@163.com
 */

public class BianjiFragment extends BaseFragment {


    @BindView(R.id.rl_my_data_desired_occupation)
    RelativeLayout rlMyDataDesiredOccupation;
    @BindView(R.id.rl_jianli_bianji_youshi)
    RelativeLayout rlJianliBianjiYoushi;
    @BindView(R.id.rl_my_data_f_work_add)
    LinearLayout rlMyDataFWorkAdd;
    @BindView(R.id.rl_my_data_school_add)
    LinearLayout rlMyDataSchoolAdd;
    Unbinder unbinder;
    @BindView(R.id.tvTest_zhiwei)
    TextView tvTestZhiwei;
    private EditText edit;
    private String leftValue;
    private String rightValue;
    private TextView tvTestZhiWei;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_jinali_bianji, null);
        return swipeBackView(view);
    }


    public void onSupportVisible() {
        mTvTitle.setText("编辑简历");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
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

    @OnClick({R.id.rl_my_data_desired_occupation, R.id.rl_jianli_bianji_youshi, R.id.rl_my_data_f_work_add, R.id.rl_my_data_school_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_my_data_desired_occupation:
//                start(new ExpectTheIndustryFragment());
               start(new DesiredOccupationFragment());
//                期望职位
                break;
            case R.id.rl_jianli_bianji_youshi:
                LayoutInflater factory = LayoutInflater.from(mActivity);//提示框
                final View dialog = factory.inflate(R.layout.editbox_layout, null);//这里必须是final的
                final EditText edit = (EditText) view.findViewById(R.id.editText);//获得输入框对象
                new AlertDialog.Builder(mActivity)
                        .setTitle("我的优势")//提示框标题
                        .setView(dialog)
                        .setPositiveButton("确定",//提示框的两个按钮
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        //事件
                                    }
                                }).setNegativeButton("取消", null).create().show();

//                优势
                break;
            case R.id.rl_my_data_f_work_add:
                start(new WorkAddFragment());
//                工作经验
                break;
            case R.id.rl_my_data_school_add:
                start(new EducationAddFeagment());
//                学习经历
                break;
        }
    }

}
