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
 * Created by L on 2017/3/4.
 * 投资领域流失布局
 */
public class FieldFragment extends BaseFragment {

    @BindView(R.id.fiel_no)
    TextView fielNo;
    @BindView(R.id.fiel_yes)
    TextView fielYes;
    @BindView(R.id.field)
    TagFlowLayout field;

    public View initView() {
        return View.inflate(mActivity, R.layout.fragment_fiel, null);
    }

    public void initData() {
        setTagAdapter();
        super.initData();

    }

    private void setTagAdapter() {

        field.setAdapter(new TagAdapter<String>(mField) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView mTv = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.welfarte_textview, field, false);
                mTv.setText(s);
                return mTv;
            }
        });
        field.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), mField[position], Toast.LENGTH_SHORT).show();
                view.setVisibility(View.GONE);
                String s = mField[position];
                EventBus.getDefault().post(new EventAll(s));
                return true;
            }
        });


        field.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
    }

    public void onSupportVisible() {
        mTvTitle.setText("投资领域");
        mIvBack.setVisibility(View.VISIBLE);
//        mTop.setBackgroundColor(Color.parseColor("#0093dd"));
        super.onSupportVisible();
    }

    private String[] mField = new String[]//内容
            {"移动互联网", "金融", "企业服务", "教育", "医疗健康", "旅游"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.fiel_no, R.id.fiel_yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fiel_no:
                break;
            case R.id.fiel_yes:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
