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
 * 投资轮次流失布局
 */
public class RoundsFragment extends BaseFragment {


    @BindView(R.id.rounds_no)
    TextView roundsNo;
    @BindView(R.id.rounds_yes)
    TextView roundsYes;
    @BindView(R.id.rounds)
    TagFlowLayout rounds;

    public View initView() {
        return View.inflate(mActivity, R.layout.fragment_rounds, null);
    }

    public void initData() {
        setTagAdapter();
        super.initData();

    }

    private void setTagAdapter() {

        rounds.setAdapter(new TagAdapter<String>(mRounds) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView mTv = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.welfarte_textview, rounds, false);
                mTv.setText(s);
                return mTv;
            }
        });
        rounds.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), mRounds[position], Toast.LENGTH_SHORT).show();
                view.setVisibility(View.GONE);
                String s = mRounds[position];
                EventBus.getDefault().post(new EventAll(s));

                //view.setVisibility(View.GONE);
                return true;
            }
        });


        rounds.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
    }

    public void onSupportVisible() {
        mTvTitle.setText("投资轮次");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    private String[] mRounds = new String[]//内容
            {"未融资", "已融资", "天使轮", "A轮", "B轮", "C轮", "D轮", "已上市", "不需要融资"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.rounds_no, R.id.rounds_yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rounds_no:
                break;
            case R.id.rounds_yes:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
