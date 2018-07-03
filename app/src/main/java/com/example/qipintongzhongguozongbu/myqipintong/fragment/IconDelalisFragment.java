package com.example.qipintongzhongguozongbu.myqipintong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.BottomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.DialogListViewItem;
import com.example.qipintongzhongguozongbu.myqipintong.utils.BitmapUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Description: 头像详情页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/14 下午5:22
 */
public class IconDelalisFragment extends BaseFragment implements View.OnLongClickListener, BottomDialog.OnBottomDialogItemOnClickListener, PhotoViewAttacher.OnPhotoTapListener {
    @BindView(R.id.PV_icon_delalis)
    PhotoView PVIconDelalis;
    Unbinder unbinder;
    private String photoDate;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragmenet_icon_delalist, null));
    }

    /**
     * function   : 网络图片 传递地址
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/25  下午3:14
     */
    public void setPhotoDate(String photoDate) {
        this.photoDate = photoDate;
    }

    @Override
    public void initData() {

        Glide.with(mActivity).load(photoDate).crossFade().placeholder(R.mipmap.loding)
                .thumbnail(0.1f).error(R.mipmap.lodingerror).into(PVIconDelalis);

        PVIconDelalis.setOnLongClickListener(this);

        PVIconDelalis.setOnPhotoTapListener(this);

        super.initData();
    }


    @Override
    public void onSupportVisible() {

        mIvBack.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.GONE);
        mTvTitle.setText("头像详情");

        super.onSupportVisible();
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return super.onCreateFragmentAnimator();
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
    public void onPhotoTap(View view, float x, float y) {
        pop();
    }

    @Override
    public boolean onLongClick(View view) {

        showBottomDialog();

        return false;
    }

    private void showBottomDialog() {
        BottomDialog dialog = new BottomDialog(mActivity,
                R.style.transparentFrameWindowStyle);
        dialog.setOnBottomDialogItemOnClickListener(this);
        dialog.addItem(new DialogListViewItem("保存图片"));
        dialog.show();
    }

    @Override
    public void onItemClick(DialogListViewItem item, int position) {
        switch (position) {
            case 0:
                //弹出一个对话框 询问用户是否保存图片 GlobalConstants.mIvDate[position] 要保存的图片的路径
//                Bitmap bitmap = BitmapFactory.decodeResource(mActivity.getResources(), photoDate);
//                ScannerUtils.saveImageToGallery(mActivity, bitmap, ScannerUtils.ScannerType.MEDIA);
                //弹出一个对话框 询问用户是否保存图片  要保存的图片的路径
                BitmapUtils.saveImage(mActivity, photoDate);
                break;

        }
    }


}