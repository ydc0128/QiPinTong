package com.example.qipintongzhongguozongbu.myqipintong.background.youshangyouke;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.PhotoActivity;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia.CertificationFragment;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia.ContactFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.event.FriendImageEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai.SelectBackGroundActivity;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 注释：商户注册
 * 作者：碧血染银枪 on 2017/5/15 11:57
 * 邮箱：ydc_0128@163.com
 */

public class CommercialTenanyrEgister extends BaseFragment {


    @BindView(R.id.my_cte_photo)
    CircleImageView myCtePhoto;
    @BindView(R.id.tv_cte_shangchuan)
    TextView tvCteShangchuan;
    @BindView(R.id.ll_name_of_shop)
    LinearLayout llNameOfShop;
    @BindView(R.id.business_category)
    LinearLayout businessCategory;
    @BindView(R.id.aptitude_approve)
    LinearLayout aptitudeApprove;
    @BindView(R.id.ll_contact_way)
    LinearLayout llContactWay;
    @BindView(R.id.rv_cte_photo)
    ImageView rvCtePhoto;
    Unbinder unbinder;
    @BindView(R.id.rl_shanghu_zhuce)
    RelativeLayout rlShanghuZhuce;
    @BindView(R.id.bt_cte_add)
    Button btCteAdd;
    @BindView(R.id.iv_cte_open_close)
    ImageView ivCteOpenClose;
    @BindView(R.id.ll_waima)
    LinearLayout llWaima;
    private boolean isOpen;
    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_commercial_tenant_egister, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("商户注册");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }
    @Subscribe
    public void setBackground(FriendImageEvent event) {

        GlideUtils.loadImage(mActivity, event.getFile(), myCtePhoto);
    }
    @OnClick({R.id.my_cte_photo, R.id.tv_cte_shangchuan, R.id.ll_name_of_shop, R.id.iv_cte_open_close, R.id.bt_cte_add, R.id.business_category, R.id.aptitude_approve, R.id.ll_contact_way, R.id.rv_cte_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_cte_photo:
                break;
            case R.id.tv_cte_shangchuan:
                startActivity(new Intent(mActivity, SelectBackGroundActivity.class));
                break;
            case R.id.ll_name_of_shop:
                break;
            case R.id.business_category:
                break;
            case R.id.aptitude_approve:
                start(new CertificationFragment());
//                资质认证
                break;
            case R.id.ll_contact_way:
                start(new ContactFragment());
//                联系方式
                break;
            case R.id.rv_cte_photo:
                startActivity(new Intent(getActivity(), PhotoActivity.class));
                break;
            case R.id.bt_cte_add:
                start(new CommercialTenantMyFragment());
                break;
            case R.id.iv_cte_open_close:
                if (isOpen) {
                    ivCteOpenClose.setImageResource(R.mipmap.open);
                } else {
                    ivCteOpenClose.setImageResource(R.mipmap.turn_off);
                }
                isOpen = !isOpen;
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
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }
}
