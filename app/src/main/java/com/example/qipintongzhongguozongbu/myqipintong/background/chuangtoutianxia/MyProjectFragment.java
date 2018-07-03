package com.example.qipintongzhongguozongbu.myqipintong.background.chuangtoutianxia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli.UblishTheProjectFragmen;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by L on 2017/3/11.
 */

public class MyProjectFragment extends BaseFragment {
    @BindView(R.id.fragment_wodexiangmu)
    FrameLayout fragmentWodexiangmu;
    @BindView(R.id.my_fl)
    FrameLayout myFl;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_my_project, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.fragment_wodexiangmu, R.id.my_fl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_wodexiangmu:
                break;
            case R.id.my_fl:
                start(new UblishTheProjectFragmen());
                break;
        }
    }
}
