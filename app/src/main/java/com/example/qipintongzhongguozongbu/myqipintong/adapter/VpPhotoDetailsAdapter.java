package com.example.qipintongzhongguozongbu.myqipintong.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.event.EmptyEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.PhotoPopup;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Description: 相册详情的vp适配器
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/20 下午12:27
 */
public class VpPhotoDetailsAdapter extends PagerAdapter implements PhotoViewAttacher.OnPhotoTapListener, View.OnLongClickListener {

    private final Activity mActivity;
    private final BaseFragment baseFragment;
    private final ArrayList imageList;
    private final PhotoPopup photoPopup;

    public VpPhotoDetailsAdapter(Activity mActivity, ArrayList imageList, BaseFragment baseFragment, PhotoPopup photoPopup) {
        this.imageList = imageList;
        this.mActivity = mActivity;
        this.baseFragment = baseFragment;
        this.photoPopup = photoPopup;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        PhotoView photoView = new PhotoView(mActivity);

        Glide.with(mActivity).load(imageList.get(position)).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror).into(photoView);

        photoView.setOnPhotoTapListener(this);

        photoView.setOnLongClickListener(this);

        container.addView(photoView);

        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    //图片单击
    @Override
    public void onPhotoTap(View view, float x, float y) {
        photoPopup.dismiss();
    }

    //图片长按
    @Override
    public boolean onLongClick(View view) {
        EventBus.getDefault().post(new EmptyEvent());
        return false;
    }
}
