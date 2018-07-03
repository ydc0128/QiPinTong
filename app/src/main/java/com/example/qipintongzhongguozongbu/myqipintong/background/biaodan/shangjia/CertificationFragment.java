package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.PhotoActivity;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by L on 2017/3/17.
 * 入住+认证
 */

public class CertificationFragment extends BaseFragment {


    @BindView(R.id.rv_cer_photo_zhihao)
    ImageView rvCerPhotoZhihao;
    @BindView(R.id.rv_cer_photo_xuke)
    ImageView rvCerPhotoXuke;
    @BindView(R.id.bt_baocun)
    Button btBaocun;

    Unbinder unbinder;
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_certification, null));
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onSupportVisible() {
        mTvTitle.setText("认证");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }
    @OnClick({R.id.rv_cer_photo_zhihao, R.id.rv_cer_photo_xuke, R.id.bt_baocun})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_cer_photo_zhihao:
                Intent intent= new Intent(mActivity, PhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.rv_cer_photo_xuke:
                Intent intent1 = new Intent(mActivity, PhotoActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_baocun:
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
