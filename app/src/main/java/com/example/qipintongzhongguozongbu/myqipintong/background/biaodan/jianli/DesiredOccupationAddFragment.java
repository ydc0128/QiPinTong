package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.index.EventAll;
import com.example.qipintongzhongguozongbu.myqipintong.index.HeaderRecyclerAndFooterWrapperAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.index.LocationBean;
import com.example.qipintongzhongguozongbu.myqipintong.index.LocationFragment;
import com.example.qipintongzhongguozongbu.myqipintong.index.RvLocationAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.twofold.ValuePickerMockActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.OptionPicker;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

import static com.example.qipintongzhongguozongbu.myqipintong.R.id.et_doa_post;


/**
 * Created by L on 2017/2/23.
 * 添加期望职位
 */

public class DesiredOccupationAddFragment extends BaseFragment {

    @BindView(et_doa_post)
    TextView etDoaPost;
    @BindView(R.id.rl_desired_occupation_add_post)
    LinearLayout rlDesiredOccupationAddPost;
    @BindView(R.id.et_doa_business)
    TextView etDoaBusiness;
    @BindView(R.id.rl_desired_occupation_add_industry)
    LinearLayout rlDesiredOccupationAddIndustry;
    @BindView(R.id.et_doa_city)
    TextView etDoaCity;
    @BindView(R.id.rl_desired_occupation_add_city)
    LinearLayout rlDesiredOccupationAddCity;
    @BindView(R.id.tv_desired_occupation_add_salary)
    TextView tvDesiredOccupationAddSalary;
    @BindView(R.id.ll_desired_occupation_add_salary)
    LinearLayout llDesiredOccupationAddSalary;
    @BindView(R.id.rl_desired_occupation_add_add)
    Button rlDesiredOccupationAddAdd;
    Unbinder unbinder;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private RvLocationAdapter mAdapter;

    private List<LocationBean> mBodyDatas;
    private String leftValue;
    private String rightValue;
    private void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_desired_occupation_add, null));
    }


    public void onSupportVisible() {
        mTvTitle.setText("期望职位");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        EventBus.getDefault().register(this);
//    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(EventAll message) {
        etDoaPost.setText(message.s);
    }

    @OnClick({R.id.rl_desired_occupation_add_post, R.id.rl_desired_occupation_add_industry, R.id.rl_desired_occupation_add_city, R.id.ll_desired_occupation_add_salary, R.id.rl_desired_occupation_add_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_desired_occupation_add_post:
//                start(new ExpectTheIndustryFragment());
                Intent intent = new Intent(mActivity, ValuePickerMockActivity.class);
                intent.putExtra(ValuePickerMockActivity.SELECTED_LEFT, leftValue);
                intent.putExtra(ValuePickerMockActivity.SELECTED_RIGHT, rightValue);
                startActivityForResult(intent, 1);
                etDoaPost.setText("");
//                职位
                break;
            case R.id.rl_desired_occupation_add_industry:
                Intent intent1 = new Intent(mActivity, ValuePickerMockActivity.class);
                intent1.putExtra(ValuePickerMockActivity.SELECTED_LEFT, leftValue);
                intent1.putExtra(ValuePickerMockActivity.SELECTED_RIGHT, rightValue);
                startActivityForResult(intent1, 1);
//                行业
                break;
            case R.id.rl_desired_occupation_add_city:
                start(new LocationFragment());
//                城市
//                mAdapter = new RvLocationAdapter(mActivity, R.layout.item_select_city, mBodyDatas);
//                mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
//                    protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
//                        LocationTopHeaderBean locationTopHeaderBean = (LocationTopHeaderBean) o;
//                        holder.setText(R.id.tv_Current, locationTopHeaderBean.getTxt());
//                        etDoaCity.setText(locationTopHeaderBean.getTxt());
//                    }
//                };
                break;
            case R.id.ll_desired_occupation_add_salary:
                final OptionPicker picker3 = new OptionPicker(mActivity, new String[]{
                        "1-2k", "2-4k", "4-6k", "6-8k", "8-10k", "10-12k", "12-15k"
                });
                picker3.setCycleDisable(true);
                picker3.setLineVisible(false);
                picker3.setTextSize(16);
                picker3.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker3.getSelectedItem().toString();
                        tvDesiredOccupationAddSalary.setText(s);
                    }
                });
                picker3.show();
//                薪资
                break;
            case R.id.rl_desired_occupation_add_add:
//                保存
                break;
        }
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == requestCode) {
            if (RESULT_OK == resultCode) {
                //选择车牌
                leftValue = data.getStringExtra(ValuePickerMockActivity.SELECTED_LEFT);
                rightValue = data.getStringExtra(ValuePickerMockActivity.SELECTED_RIGHT);
                etDoaPost.setText(leftValue + " - " + rightValue);
                etDoaBusiness.setText(leftValue + " - " + rightValue);
            }
        }
    }
}

