package com.example.qipintongzhongguozongbu.myqipintong.fragment.homepager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.widget.SelectableRoundedImageView;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.SealSearchActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.widget.MorePopWindow;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ChatBackListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;

/**
 * 注释：新消息页面
 * 作者：碧血染银枪 on 2017/5/15 09:56
 * 邮箱：ydc_0128@163.com
 */

public class NewMessageFragment extends BaseFragment {
    public static NewMessageFragment fragment = null;
    @BindView(R.id.tv_top_txl)
    TextView tvTopTxl;
    @BindView(R.id.ac_iv_search)
    ImageView acIvSearch;
    @BindView(R.id.seal_more)
    ImageView sealMore;
    @BindView(R.id.conversationlist_fragment)
    FrameLayout conversationlistFragment;

    Unbinder unbinder;
    @BindView(R.id.iv_ceshi2)
    SelectableRoundedImageView ivCeshi2;
    @BindView(R.id.tv_servicebrand_ceshi2)
    TextView tvServicebrandCeshi2;
    @BindView(R.id.publicceshi2)
    RelativeLayout publicceshi2;

    private ArrayList imageList;

       String token1="RpZDZPAuIjhSkt3+byFXv7Kl9MC0YtbMe81ao8MUBDhYNBWsQSqs8GbcnERF9d1emzl893WDMk9MEBvHzToUgw==";
//   String token2="RpZDZPAuIjhSkt3+byFXv7Kl9MC0YtbMe81ao8MUBDhYNBWsQSqs8GbcnERF9d1emzl893WDMk9MEBvHzToUgw==";
    public static NewMessageFragment getInstance() {

        if (fragment == null) {
            fragment = new NewMessageFragment();
        }
        return fragment;

    }

    public void onSupportVisible() {
        mTop.setVisibility(View.GONE);

        tvTopTxl.setText("消息");
        super.onSupportVisible();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.conversationlist_fragment, new ConversationListFragment()).addToBackStack(null).commit();

    }

    @Override
    public View initView() {
        return View.inflate(mActivity, R.layout.conversationlist, null);
    }


    @OnClick({R.id.ac_iv_search, R.id.seal_more,R.id.publicceshi2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_iv_search:
                startActivity(new Intent(mActivity, SealSearchActivity.class));
                break;
            case R.id.seal_more:
                MorePopWindow morePopWindow = new MorePopWindow(mActivity);
                morePopWindow.showPopupWindow(sealMore);
                break;
            case R.id.publicceshi2:
                RongIM.connect(token1, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                    }

                    @Override
                    public void onSuccess(String s) {
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode e) {
                    }
                });
                RongIM.getInstance().startPrivateChat(getActivity(),"123456", "李白不白");

                break;

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        if(getActivity() instanceof ChatBackListener){
            ((ChatBackListener)getActivity()).initadd();
        }
        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
