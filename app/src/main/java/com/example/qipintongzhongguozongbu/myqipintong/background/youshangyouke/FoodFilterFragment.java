package com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by L on 2017/3/1.
 * 筛选
 */

public class FoodFilterFragment extends BaseFragment {

    @BindView(R.id.Filter_food_distance)
    TagFlowLayout FilterFoodDistance;
    @BindView(R.id.Filter_food_consume)
    TagFlowLayout FilterFoodConsume;
    @BindView(R.id.Filter_food_grade)
    TagFlowLayout FilterFoodGrade;
    @BindView(R.id.food_filter_no)
    ImageView foodFilterNo;
    @BindView(R.id.food_filter_yes)
    ImageView foodFilterYes;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.bacground_food_filter, null));
    }

    public void initData() {
        setTagAdapter();
        super.initData();

    }

    private void setTagAdapter() {

        FilterFoodDistance.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView mTv = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.tag_textview, FilterFoodDistance, false);
                mTv.setText(s);
                return mTv;
            }
        });
        FilterFoodConsume.setAdapter(new TagAdapter<String>(consume) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView c = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.tag_textview, FilterFoodConsume, false);
                c.setText(s);
                return c;
            }
        });
        FilterFoodGrade.setAdapter(new TagAdapter<String>(grade) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView g = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.tag_textview, FilterFoodGrade, false);
                g.setText(s);
                return g;
            }
        });
        FilterFoodConsume.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), consume[position], Toast.LENGTH_SHORT).show();

                //view.setVisibility(View.GONE);
                return true;
            }
        });


        FilterFoodGrade.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
        FilterFoodGrade.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), grade[position], Toast.LENGTH_SHORT).show();

                //view.setVisibility(View.GONE);
                return true;
            }
        });


        FilterFoodDistance.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
        FilterFoodDistance.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), mVals[position], Toast.LENGTH_SHORT).show();

                //view.setVisibility(View.GONE);
                return true;
            }
        });


        FilterFoodConsume.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());

            }
        });
    }

    public void onSupportVisible() {
        mTvTitle.setText("筛选");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    private String[] mVals = new String[]//距离内容
            {"默认", "500米以内", "500-1000米 ", "1000-2000米", "2000-5000米", "5km以上"};
    private String[] consume = new String[]//消费内容
            {"默认", "50元以内", "50-100元 ", "100-200元", "200-500元", "500元以上"};
    private String[] grade = new String[]//评分内容
            {"默认", "5分", "4分 ", "3分", "2分", "1分"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.food_filter_no, R.id.food_filter_yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.food_filter_no:
                break;
            case R.id.food_filter_yes:
                getFragmentManager().popBackStack();
                break;
        }
    }
}