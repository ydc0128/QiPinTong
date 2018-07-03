package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FaceAndTalentFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/18.
 * 我的助手
 */
public class AssistantFragment extends BaseFragment {
    @BindView(R.id.ll_a_ycym)
    LinearLayout llAYcym;
    @BindView(R.id.ll_a_ysyk)
    LinearLayout llAYsyk;


    Unbinder unbinder;

    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_assistant, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.ll_a_ycym, R.id.ll_a_ysyk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_a_ycym:
                start(new FaceAndTalentFragment());
            break;
            case R.id.ll_a_ysyk:
                start(new CommerceFragment());
                break;
//            case R.id.ll_a_wycy:
//                start(new MyItemFragment());
//                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
