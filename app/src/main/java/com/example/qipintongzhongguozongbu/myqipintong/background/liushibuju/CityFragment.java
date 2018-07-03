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
 * 城市流失布局
 */
public class CityFragment extends BaseFragment {


    @BindView(R.id.city_no)
    TextView cityNo;
    @BindView(R.id.city_yes)
    TextView cityYes;
    @BindView(R.id.city)
    TagFlowLayout city;

    public View initView() {
        return View.inflate(mActivity, R.layout.fragment_city, null);
    }

    public void initData() {
        setTagAdapter();
        super.initData();

    }

    private void setTagAdapter() {

        city.setAdapter(new TagAdapter<String>(mCity) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView mTv = (TextView) LayoutInflater.from(mActivity).inflate(R.layout.welfarte_textview, city, false);
                mTv.setText(s);
                return mTv;
            }
        });
        city.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), mCity[position], Toast.LENGTH_SHORT).show();
                view.setVisibility(View.GONE);
                String s = mCity[position];
                EventBus.getDefault().post(new EventAll(s));
                return true;
            }
        });


        city.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
    }

    public void onSupportVisible() {
        mTvTitle.setText("城市");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    private String[] mCity = new String[]//内容
            {"北京", "上海", "天津", "重庆", "广州", "福州", "杭州", "南京", "西安"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.city_no, R.id.city_yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.city_no:
                break;
            case R.id.city_yes:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
