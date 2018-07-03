package com.example.qipintongzhongguozongbu.myqipintong.background.liushibuju;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.index.EventAll;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * Created by L on 2017/3/3.
 * 福利流失布局
 */

public class WelfareFragment extends BaseFragment {


    @BindView(R.id.welfarte_no)
    TextView welfarteNo;
    @BindView(R.id.welfarte_yes)
    TextView welfarteYes;
    @BindView(R.id.welfarte)
    TagFlowLayout welfarte;

    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_welfte, null);

        return view;
    }

    public void initData() {
        setTagAdapter();
        super.initData();

    }

    private void setTagAdapter() {

        welfarte.setAdapter(new TagAdapter<String>(mWwlfarte) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView mTv = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.welfarte_textview, welfarte, false);

                mTv.setText(s);
                return mTv;
            }
        });
        welfarte.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), mWwlfarte[position], Toast.LENGTH_SHORT).show();
                view.setVisibility(View.GONE);
                String s = mWwlfarte[position];
                EventBus.getDefault().post(new EventAll(s));
                return true;
            }
        });

        welfarte.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle(selectPosSet.toString());

            }
        });
    }

    public void onSupportVisible() {
        mTvTitle.setText("福利");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }


    private String[] mWwlfarte = new String[]//内容
            {"默认", "福利待遇", "节日福利", "高温补贴", "员工旅游", "五险一金", "年底双薪", "绩效奖金", "年终分红", "期票股权"
                    , "加班补助", "全勤奖", "包吃包住", "交通补助", "餐补", "房补", "通讯补助", "定期体检", "采暖补助", "带薪年假"
                    , "弹性工作", "医疗保险", "免费班车"};


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.welfarte_no, R.id.welfarte_yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.welfarte_no:
                break;
            case R.id.welfarte_yes:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
