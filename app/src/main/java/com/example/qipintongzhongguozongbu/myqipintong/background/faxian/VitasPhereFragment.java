package com.example.qipintongzhongguozongbu.myqipintong.background.faxian;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan.BeautifulSaidFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan.DaZhongFeagment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan.DiDiFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan.JDComFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan.MeiTuanFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan.TongchengFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.shenghuoquan.TuNiuFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by L on 2017/3/13.
 * 生活圈
 */

public class VitasPhereFragment extends BaseFragment {
    @BindView(R.id.youshangyouke)
    LinearLayout youshangyouke;
    @BindView(R.id.jingdong)
    LinearLayout jingdong;
    @BindView(R.id.meilishuo)
    LinearLayout meilishuo;
    @BindView(R.id.meituan)
    LinearLayout meituan;
    @BindView(R.id.dazhong)
    LinearLayout dazhong;
    @BindView(R.id.didi)
    LinearLayout didi;
    @BindView(R.id.tongcheng)
    LinearLayout tongcheng;
    @BindView(R.id.tuniu)
    LinearLayout tuniu;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_vitasphere, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void onSupportVisible() {
        mTvTitle.setText("生活圈");
        mIvBack.setVisibility(View.VISIBLE);
//        mButton.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick({R.id.youshangyouke, R.id.jingdong, R.id.meilishuo, R.id.meituan, R.id.dazhong, R.id.didi, R.id.tongcheng, R.id.tuniu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.youshangyouke:
                start(new CommerceFragment());
                break;
            case R.id.jingdong:
                start(new JDComFragment());
                break;
            case R.id.meilishuo:
                start(new BeautifulSaidFragment());
                break;
            case R.id.meituan:
                start(new MeiTuanFragment());
                break;
            case R.id.dazhong:
                start(new DaZhongFeagment());
                break;
            case R.id.didi:
                start(new DiDiFragment());
                break;
            case R.id.tongcheng:
                start(new TongchengFragment());
                break;
            case R.id.tuniu:
                start(new TuNiuFragment());
                break;
        }
    }
}
