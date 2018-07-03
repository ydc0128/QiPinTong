package com.example.qipintongzhongguozongbu.myqipintong.background.tongbi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.youth.banner.listener.OnBannerClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 送通币界面
 * Created by L on 2017/2/13.
 */

public class SendTongbiFragment extends BaseFragment implements OnBannerClickListener {


    Unbinder unbinder;
    @BindView(R.id.tv_send_tongbi_geshu_tianxie)
    EditText tvSendTongbiGeshuTianxie;
    @BindView(R.id.tv_send_tongbi_geshu)
    TextView tvSendTongbiGeshu;
    @BindView(R.id.rl_my_earnings_record)
    LinearLayout rlMyEarningsRecord;
    @BindView(R.id.tv_send_tongbi_meiren)
    TextView tvSendTongbiMeiren;
    @BindView(R.id.ed_send_tongbi_geshu)
    EditText edSendTongbiGeshu;
    @BindView(R.id.rl_send_tongbi_meiren)
    LinearLayout rlSendTongbiMeiren;
    @BindView(R.id.tv_send_tongbi_quyu_xuanze)
    ImageButton tvSendTongbiQuyuXuanze;
    @BindView(R.id.sendtongbi)
    Button sendtongbi;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_sendtongbi, null));
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    //添加消息头
    public void onSupportVisible() {
        mTvTitle.setText("送通币");
//        mButton.setVisibility(View.GONE);
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    //点击
    @OnClick({R.id.tv_send_tongbi_quyu_xuanze,R.id.sendtongbi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_send_tongbi_quyu_xuanze:

                //地区选择
                break;
            case R.id.sendtongbi:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

