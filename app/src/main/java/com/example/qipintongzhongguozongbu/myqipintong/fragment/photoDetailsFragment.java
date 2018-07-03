package com.example.qipintongzhongguozongbu.myqipintong.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.GetPhotoActivity;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.VpPhotoDetailsAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.BottomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.DialogListViewItem;
import com.example.qipintongzhongguozongbu.myqipintong.event.EmptyEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai.FriendDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.utils.BitmapUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.MyViewPager;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Description: 展示相片详情的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/20 上午11:20
 */
public class photoDetailsFragment extends BaseFragment implements BottomDialog.OnBottomDialogItemOnClickListener {
    @BindView(R.id.tv_photo_details_position)
    TextView tvPhotoDetailsPosition;
    @BindView(R.id.tv_photo_details_amount)
    TextView tvPhotoDetailsAmount;
    @BindView(R.id.mvp_photo_details)
    MyViewPager mvpPhotoDetails;

    private int position;//初次点击进入适配器的页面位置
    Unbinder unbinder;
    private ArrayList imageList;
    private int mPosition;//适配器滑动到页面的位置
    private static final int WRITE_CODE = 100;//SD权限请求码

    //传递相册点击图片的位置
    public void setPosition(int position) {
        this.position = position;
    }


    public void setPhotoList(ArrayList imageList) {
        this.imageList = imageList;
    }


    @Override
    public View initView() {
        return View.inflate(mActivity, R.layout.fragment_photo_details, null);
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return super.onCreateFragmentAnimator();
    }

    @Override
    public void initData() {

        EventBus.getDefault().register(this);

        setVpAdapter();

        super.initData();

    }

    /**
     * function   : viewpager的适配器 根据传递的是集合还是数组来决定用不同的适配器加载
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/20  下午12:25
     */
    private void setVpAdapter() {


        mvpPhotoDetails.setAdapter(new VpPhotoDetailsAdapter(mActivity, imageList, this, null));
        mvpPhotoDetails.setCurrentItem(position);

        tvPhotoDetailsAmount.setText(imageList.size() + "");

        mvpPhotoDetails.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


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

    @Override
    public void onSupportVisible() {

        mButton.setVisibility(View.GONE);
        mTop.setVisibility(View.GONE);

        super.onSupportVisible();

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
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }


    /**
     * function   : 当vp中展示图片被长按调用
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/15  上午11:34
     */
    @Subscribe
    public void setLongClick(EmptyEvent event) {
        showBottomDialog();
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
            case 0://用户点击了保存图片 先判断有没有sd卡权限

                setSdPermission();

                break;

        }
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

            AndPermission.with(this)
                    .requestCode(WRITE_CODE)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .rationale(new MyRationaleListener())
                    // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                    .send();
        }
    }


    /**
     * function   : 用户拒绝过授权后再次请求授权会执行
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  上午11:18
     */
    class MyRationaleListener implements RationaleListener {

        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            // 自定义对话框。
            new android.support.v7.app.AlertDialog.Builder(mActivity)

                    .setTitle("友情提示")

                    .setMessage(R.string.message_permission_sd)

                    .setPositiveButton(R.string.btn_dialog_yes_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();
                        }
                    })

                    .setNegativeButton(R.string.btn_dialog_no_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel();
                        }
                    }).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，第一个参数是当前Acitivity/Fragment，回调方法写在当前Activity/Framgent。
        AndPermission.onRequestPermissionsResult(this, WRITE_CODE, permissions, grantResults);
    }

    @PermissionYes(WRITE_CODE)//获取权限成功
    private void getMultiYes(List<String> grantedPermissions) {

        BitmapUtils.saveImage(mActivity, (String) imageList.get(mPosition));
        //弹出一个对话框 询问用户是否保存图片  要保存的图片的路径

    }

    @PermissionNo(WRITE_CODE)//获取权限失败
    private void getMultiNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {

            AndPermission.defaultSettingDialog(this, WRITE_CODE).show();

        }
    }


}