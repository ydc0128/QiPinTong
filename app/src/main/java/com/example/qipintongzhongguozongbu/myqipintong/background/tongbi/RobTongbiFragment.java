package com.example.qipintongzhongguozongbu.myqipintong.background.tongbi;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvRobHuijMarketAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvRobTongbiAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * function   : 抢通币界面
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/3/24  下午12:04
 */

public class RobTongbiFragment extends BaseFragment {

    @BindView(R.id.pull_icon)
    ImageView pullIcon;
    @BindView(R.id.refreshing_icon)
    ImageView refreshingIcon;
    @BindView(R.id.state_tv)
    TextView stateTv;
    @BindView(R.id.state_iv)
    ImageView stateIv;
    @BindView(R.id.head_view)
    RelativeLayout headView;
    @BindView(R.id.rob_tongbi_iv_picture)
    Banner robTongbiIvPicture;
    @BindView(R.id.bt_rob_tongbi_qiangtongbi)
    Button btRobTongbiQiangtongbi;
    @BindView(R.id.rv_rob_tongbi_button_left)
    RelativeLayout rvRobTongbiButtonLeft;
    @BindView(R.id.bt_rob_tongbi_qiangyouhuij)
    Button btRobTongbiQiangyouhuij;
    @BindView(R.id.rv_rob_tongbi_button_qiangyouhuij)
    RelativeLayout rvRobTongbiButtonQiangyouhuij;
    @BindView(R.id.ll_rob_tongbi_button)
    LinearLayout llRobTongbiButton;
    @BindView(R.id.rv_rob_tongbi)
    RecyclerView rvRobTongbi;
    @BindView(R.id.rv_rob_huij_market)
    RecyclerView rvRobHuijMarket;
    @BindView(R.id.pullup_icon)
    ImageView pullupIcon;
    @BindView(R.id.loading_icon)
    ImageView loadingIcon;
    @BindView(R.id.loadstate_tv)
    TextView loadstateTv;
    @BindView(R.id.loadstate_iv)
    ImageView loadstateIv;
    @BindView(R.id.loadmore_view)
    RelativeLayout loadmoreView;
    @BindView(R.id.rf_tongbi)
    PullToRefreshLayout rfTongbi;
    Unbinder unbinder;


    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_rob_tongbi, null));
    }


    public void initData() {

        setBannerImage(robTongbiIvPicture);

        setRefreshDate(rfTongbi);

        setRvRobTongbiAdapter();

        setRvRobHhuijMarkAdapter();

        super.initData();
    }

    /**
     * 通币适配器
     */
    private void setRvRobTongbiAdapter() {
        rvRobTongbi.setLayoutManager(new LinearLayoutManager(mActivity));
        rvRobTongbi.setAdapter(new RvRobTongbiAdapter(R.layout.item_rob_tongbi, ImageList.getImageList()));

        rvRobTongbi.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                start(new FoodDetailsFragment());
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(mActivity, "点击了" + position);

            }
        });
    }

    /**
     * 代金券适配器
     */
    private void setRvRobHhuijMarkAdapter() {
        rvRobHuijMarket.setLayoutManager(new LinearLayoutManager(mActivity));
        rvRobHuijMarket.setAdapter(new RvRobHuijMarketAdapter(R.layout.item_rob_huijuan, ImageList.getImageList()));

        rvRobHuijMarket.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                start(new FoodDetailsFragment());
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast(mActivity, "点击了" + position);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    public void onSupportVisible() {
        mTvTitle.setText("抢通币");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @OnClick({R.id.bt_rob_tongbi_qiangtongbi, R.id.rv_rob_tongbi_button_left, R.id.bt_rob_tongbi_qiangyouhuij, R.id.rv_rob_tongbi_button_qiangyouhuij, R.id.ll_rob_tongbi_button, R.id.rv_rob_tongbi, R.id.rv_rob_huij_market})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_rob_tongbi_qiangtongbi:
                //抢通币
                mTvTitle.setText("抢通币");
                rvRobTongbi.setVisibility(View.VISIBLE);
                rvRobHuijMarket.setVisibility(View.GONE);
                btRobTongbiQiangtongbi.setTextColor(this.getResources().getColor(R.color.colorButton));
                btRobTongbiQiangyouhuij.setTextColor(this.getResources().getColor(R.color.colorBlack));
                break;
            case R.id.bt_rob_tongbi_qiangyouhuij:
                //抢惠卷
                mTvTitle.setText("抢惠券");
                rvRobTongbi.setVisibility(View.GONE);
                rvRobHuijMarket.setVisibility(View.VISIBLE);
                btRobTongbiQiangyouhuij.setTextColor(this.getResources().getColor(R.color.colorButton));
                btRobTongbiQiangtongbi.setTextColor(this.getResources().getColor(R.color.colorBlack));
                break;
            case R.id.rv_rob_tongbi_button_qiangyouhuij:
                break;
            case R.id.ll_rob_tongbi_button:
                break;
            case R.id.rv_rob_tongbi:
                break;
            case R.id.rv_rob_huij_market:
                break;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}

