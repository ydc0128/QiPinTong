package com.example.qipintongzhongguozongbu.myqipintong.fragment.darenbang;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvShareDitlisAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.PhotoPopup;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.photoDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke.CommerceDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 共享圈详情页面
 * Created by Administrator on 2017/6/3.
 */

public class ShareDetailsFragment extends BaseFragment {
    @BindView(R.id.iv_item_share)
    SquareImageView ivItemShare;
    @BindView(R.id.tv_item_share_name)
    TextView tvItemShareName;
    @BindView(R.id.tv_item_share_type)
    TextView tvItemShareType;
    @BindView(R.id.tv_item_share_location)
    TextView tvItemShareLocation;
    @BindView(R.id.tv_item_share_distance)
    TextView tvItemShareDistance;
    @BindView(R.id.tv_item_share_number)
    TextView tvItemShareNumber;
    @BindView(R.id.tv_share_title)
    TextView tvShareTitle;
    @BindView(R.id.tv_share_ditalis_time)
    TextView tvShareDitalisTime;
    @BindView(R.id.tv_share_ditalis_number)
    TextView tvShareDitalisNumber;
    @BindView(R.id.rv_share_ditalis)
    RecyclerView rvShareDitalis;
    @BindView(R.id.tv_share_body)
    TextView tvShareBody;
    Unbinder unbinder;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_share_ditalis, null));
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
        unbinder.unbind();
    }

    @Override
    public void onSupportVisible() {

        mTvTitle.setText("共享详情");
        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);

        super.onSupportVisible();
    }

    @Override
    public void initData() {

        setAdapter();

        super.initData();
    }

    private void setAdapter() {

        ArrayList list = ImageList.getFourList();

        if (list.size() == 1) {
            rvShareDitalis.setLayoutManager(new GridLayoutManager(mActivity, 1));
        } else if (list.size() == 2) {
            rvShareDitalis.setLayoutManager(new GridLayoutManager(mActivity, 2));
        } else {
            rvShareDitalis.setLayoutManager(new GridLayoutManager(mActivity, 3));
        }

        rvShareDitalis.addItemDecoration(new RvItemDecoration(10));

        rvShareDitalis.setAdapter(new RvShareDitlisAdapter(R.layout.item_person_image, list));

        rvShareDitalis.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                new PhotoPopup(mActivity, ShareDetailsFragment.this, ImageList.getImageList(), position).showPopupWindow();
            }
        });
    }

    @OnClick(R.id.tv_share_goin)
    public void onViewClicked() {//进入主页

    }
}
