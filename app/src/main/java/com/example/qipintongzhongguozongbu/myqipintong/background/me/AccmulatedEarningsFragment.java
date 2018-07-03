package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.view.View;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

/** 我的收益
 * Created by L on 2017/3/14.
 */
public class AccmulatedEarningsFragment extends BaseFragment {
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_accmulated_earnings, null));
    }

}
