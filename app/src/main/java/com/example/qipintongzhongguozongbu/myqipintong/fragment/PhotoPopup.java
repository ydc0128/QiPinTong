package com.example.qipintongzhongguozongbu.myqipintong.fragment;

import android.Manifest;
import android.animation.Animator;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.VpPhotoDetailsAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.base.BasePopupWindow;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.BottomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.DialogListViewItem;
import com.example.qipintongzhongguozongbu.myqipintong.event.EmptyEvent;
import com.example.qipintongzhongguozongbu.myqipintong.utils.BitmapUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.MyViewPager;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;

import butterknife.BindView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Description: 展示图片的弹窗
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/28 下午3:13
 */
public class PhotoPopup extends BasePopupWindow implements View.OnClickListener {

    private final ArrayList<String> imageList;
    private final int position;
    private final Activity mActivity;

    private View view;
    private BaseFragment fragment;
    private MyViewPager mVp;
    private static final int WRITE_CODE = 100;//SD权限请求码
    private int mPosition;//适配器滑动到页面的位置
    private TextView tvPhotoDetailsPosition;
    private TextView tvPhotoDetailsAmount;
    private TextView mTvSave;

    public PhotoPopup(Activity context, BaseFragment fragment, ArrayList<String> list, int position) {
        super(context);
        this.fragment = fragment;
        this.imageList = list;
        this.position = position;
        this.mActivity = context;
        bindEvent();
    }


    @Override
    public Animation getAnimation() {//放大的动画效果
        return getDefaultScaleAnimation();
    }

    @Override
    public Animator getAnimator() {
        return null;
    }

    @Override
    public View getInputView() {
        return null;
    }

    @Override
    public View getDismissView() {
        return null;//view.findViewById(R.id.fl_photo);
    }

    @Override
    public View getPopupView() {//加载弹窗的页面
        view = LayoutInflater.from(mContext).inflate(R.layout.fragment_photo_details, null);
        mVp = (MyViewPager) view.findViewById(R.id.mvp_photo_details);
        tvPhotoDetailsPosition = (TextView) view.findViewById(R.id.tv_photo_details_position);
        tvPhotoDetailsAmount = (TextView) view.findViewById(R.id.tv_photo_details_amount);
        mTvSave = (TextView) view.findViewById(R.id.tv_photo_save);
        mTvSave.setOnClickListener(this);

        return view;
    }

    @Override
    public View getAnimaView() {
        return view.findViewById(R.id.mvp_photo_details);
    }


    private void bindEvent() {

        if (mVp != null) {
            setVpAdapter();
        }

    }

    /**
     * function   : viewpager的适配器 根据传递的是集合还是数组来决定用不同的适配器加载
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/20  下午12:25
     */
    private void setVpAdapter() {

        mVp.setAdapter(new VpPhotoDetailsAdapter(mContext, imageList, fragment, this));

        mVp.setCurrentItem(position);

        tvPhotoDetailsAmount.setText(imageList.size() + "");

        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mPosition = position;
                tvPhotoDetailsPosition.setText(position + 1 + "");
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }


    /**
     * function   : sd卡的权限相关处理
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  下午4:45
     */
    private void setSdPermission() {

        if (AndPermission.hasPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//判断是否有SD卡权限

            BitmapUtils.saveImage(mActivity, (String) imageList.get(mPosition));
            //弹出一个对话框 询问用户是否保存图片  要保存的图片的路径

        } else {//申请SD卡权限

            ToastUtils.showToast(mActivity, "请检查是否具备SD卡权限，没有权限将无法为您保存图片");
        }
    }

    @Override
    public void onClick(View v) {
        setSdPermission();
    }

}
