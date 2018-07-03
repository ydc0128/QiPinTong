package com.example.qipintongzhongguozongbu.myqipintong.background.me;

import android.view.View;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

/**
 * Created by L on 2017/3/14.
 * 今日排行
 */
public class NewlyIncresedFragment extends BaseFragment {
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_newly_incresed, null));
    }
}
