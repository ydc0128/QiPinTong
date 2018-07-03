package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.PhotoActivity;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.BottomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.DialogListViewItem;
import com.example.qipintongzhongguozongbu.myqipintong.event.FriendImageEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai.SelectBackGroundActivity;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.qqtheme.framework.picker.OptionPicker;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by L on 2017/3/4.
 * 公司注册资料-----进入
 */

public class EnterpriseDataFragment extends BaseFragment implements BottomDialog.OnBottomDialogItemOnClickListener {


    @BindView(R.id.ll_ed_The_company_full_name)
    LinearLayout llEdTheCompanyFullName;
    @BindView(R.id.ll_ed_for_short)
    LinearLayout llEdForShort;
    @BindView(R.id.t_ed_industry)
    TextView tEdIndustry;
    @BindView(R.id.ll_ed_industry)
    LinearLayout llEdIndustry;
    @BindView(R.id.et_ed_scharacter)
    TextView etEdScharacter;
    @BindView(R.id.ll_ed_scharacter)
    LinearLayout llEdScharacter;
    @BindView(R.id.tv_ed_scale)
    TextView tvEdScale;
    @BindView(R.id.ll_et_scale)
    LinearLayout llEtScale;
    @BindView(R.id.tv_ed_lightspot)
    TextView tvEdLightspot;
    @BindView(R.id.ll_et_lightspot)
    LinearLayout llEtLightspot;
    @BindView(R.id.ib_et_photograph)
    ImageButton ibEtPhotograph;
    @BindView(R.id.rl_ed_tupian)
    RelativeLayout rlEdTupian;
    @BindView(R.id.ll_ed_lianxifangshi)
    RelativeLayout llEdLianxifangshi;
    @BindView(R.id.tv_et_Stage_of_development)
    TextView tvEtStageOfDevelopment;
    @BindView(R.id.ll_et_Stage_of_development)
    LinearLayout llEtStageOfDevelopment;
    @BindView(R.id.ll_et_introduce)
    LinearLayout llEtIntroduce;
    @BindView(R.id.bt_et_save)
    Button btEtSave;

    Unbinder unbinder;
    @BindView(R.id.my_bed_p)
    CircleImageView myBedP;
    @BindView(R.id.tv_my_bed_nicheng)
    TextView tvMyBedNicheng;
    @BindView(R.id.tv_my_bedyour_resume)
    TextView tvMyBedyourResume;
    @BindView(R.id.rl_my_bed_title)
    RelativeLayout rlMyBEDTitle;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_enterprise_data_fragment, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("公司基础资料");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }
    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        super.initData();
    }
    @Subscribe
    public void setBackground(FriendImageEvent event) {

        GlideUtils.loadImage(mActivity, event.getFile(), myBedP);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.ll_ed_The_company_full_name,R.id.rl_my_bed_title, R.id.rl_ed_tupian, R.id.ll_ed_lianxifangshi, R.id.ll_ed_for_short, R.id.t_ed_industry, R.id.ll_ed_industry, R.id.et_ed_scharacter, R.id.ll_ed_scharacter, R.id.tv_ed_scale, R.id.ll_et_scale, R.id.tv_ed_lightspot, R.id.ll_et_lightspot, R.id.ib_et_photograph, R.id.tv_et_Stage_of_development, R.id.ll_et_Stage_of_development, R.id.ll_et_introduce, R.id.bt_et_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_ed_The_company_full_name:
                break;
            case R.id.ll_ed_for_short:
                break;
            case R.id.t_ed_industry:
                start(new IndustryClassiffcationFragment());
                break;
            case R.id.ll_ed_scharacter:
                final OptionPicker picker4 = new OptionPicker(mActivity, new String[]{
                        "事业单位", "国企", "外资", "股份制", "合资", "民营", "上市", "国家机关", "其他"
                });
                picker4.setCycleDisable(true);//不禁用循环
                picker4.setLineVisible(false);//可见行
                picker4.setTextSize(16);
                picker4.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker4.getSelectedItem().toString();
                        etEdScharacter.setText(s);
                    }
                });
                picker4.show();
                break;
            case R.id.ll_et_scale:
//                人员规模
                final OptionPicker picker5 = new OptionPicker(mActivity, new String[]{
                        "20人一下", "20-99人", "99-499人", "500-999人", "1000人以上"
                });
                picker5.setCycleDisable(true);//不禁用循环
                picker5.setLineVisible(false);//可见行
                picker5.setTextSize(16);
                picker5.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = picker5.getSelectedItem().toString();
                        tvEdScale.setText(s);
                    }
                });
                picker5.show();
                break;
            case R.id.ll_et_lightspot:
                final OptionPicker lightspot = new OptionPicker(mActivity, new String[]{
                        "高新技术", "上市公司"
                });
                lightspot.setCycleDisable(true);//不禁用循环
                lightspot.setLineVisible(false);//可见行
                lightspot.setTextSize(16);
                lightspot.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = lightspot.getSelectedItem().toString();
                        tvEdLightspot.setText(s);
                    }
                });
                lightspot.show();
                break;
            case R.id.ll_ed_industry:
                break;
            case R.id.et_ed_scharacter:
                break;
            case R.id.tv_ed_scale:
                break;
            case R.id.tv_ed_lightspot:
                break;
            case R.id.ib_et_photograph:
                Intent intent = new Intent(mActivity, PhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_et_Stage_of_development:
                break;
            case R.id.ll_et_Stage_of_development:
//                发展阶段
                final OptionPicker p = new OptionPicker(mActivity, new String[]{
                        "未融资", "已融资", "天使轮", "A轮", "B轮", "C轮", "D轮", "已上市", "不需要融资"
                });
                p.setCycleDisable(true);//不禁用循环
                p.setLineVisible(false);//可见行
                p.setTextSize(16);
                p.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
                    @Override
                    public void onOptionPicked(int index, String item) {
                        String s = p.getSelectedItem().toString();
                        tvEtStageOfDevelopment.setText(s);
                    }
                });
                p.show();
                break;
            case R.id.ll_et_introduce:
                break;
            case R.id.bt_et_save:
                break;
            case R.id.rl_ed_tupian:
                start(new LogFragment());
                break;
            case R.id.ll_ed_lianxifangshi:
                start(new ContactFragment());
                break;
            case R.id.rl_my_bed_title:
                startActivity(new Intent(mActivity, SelectBackGroundActivity.class));
                break;
        }
    }

//    private void showBottomDialog() {
//        BottomDialog dialog = new BottomDialog(mActivity,
//                R.style.transparentFrameWindowStyle);
//        dialog.setOnBottomDialogItemOnClickListener(this);
//        dialog.addItem(new DialogListViewItem("相册"));
//        dialog.addItem(new DialogListViewItem("拍照"));
//        dialog.show();
//    }

    @Override
    public void onItemClick(DialogListViewItem item, int position) {
//        Intent intent = new Intent(mActivity, GetPhotoActivity.class);
//        intent.putExtra("TAG", position);
//        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }
}


