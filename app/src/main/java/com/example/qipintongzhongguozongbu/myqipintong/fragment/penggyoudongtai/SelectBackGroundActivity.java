package com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.event.FriendImageEvent;
import com.example.qipintongzhongguozongbu.myqipintong.helper.PhotoHelper;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Description: 选择朋友圈背景墙图片
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/5/2 下午2:23
 */
public class SelectBackGroundActivity extends TakePhotoActivity {


    @BindView(R.id.tv_select_photo)
    TextView tvSelectPhoto;
    @BindView(R.id.tv_take_photo)
    TextView tvTakePhoto;
    @BindView(R.id.iv_image_back)
    ImageView ivImageBack;
    private TakePhoto takePhoto;
    private static final int TAKE_CODE = 111;//启动相机的请求码

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_select_photo, R.id.tv_take_photo, R.id.iv_image_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select_photo:
                //相册选
                new PhotoHelper(getTakePhoto()).configTakePhotoOption();
                break;
            case R.id.tv_take_photo:
                //拍照
                getCameraPermission();
                break;
            case R.id.iv_image_back:
                finish();
                break;
        }
    }

    /**
     * function   : 获取实例对象
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2016/12/29  下午4:23
     */

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }


    /**
     * function   : 获取图片成功方法 这里应该把文件上传到服务器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/22  下午2:51
     */
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);

        File file = new File(result.getImage().getOriginalPath());

        EventBus.getDefault().post(new FriendImageEvent(file));

        finish();

        LogUtils.e("成功" + result.getImage().getOriginalPath().toString());

    }


    /**
     * function   : 获取失败
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/22  下午2:51
     */
    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        LogUtils.e(msg.toString() + "失败");

    }


    /**
     * function   : 动态获取拍照权限
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  下午3:46
     */
    private void getCameraPermission() {

        if (AndPermission.hasPermission(this, Manifest.permission.CAMERA)) {//判断是否有相机权限

            new PhotoHelper(getTakePhoto()).configTakePhoto();


        } else {//申请相机权限

            AndPermission.with(this)
                    .requestCode(TAKE_CODE)
                    .permission(Manifest.permission.CAMERA)
                    .rationale(new TakeListener())
                    // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                    .send();
        }
    }


    /**
     * function   : 用户拒绝过相机授权后再次请求授权会执行
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  上午11:18
     */
    class TakeListener implements RationaleListener {

        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            // 自定义对话框。
            new AlertDialog.Builder(SelectBackGroundActivity.this)

                    .setTitle("友情提示")

                    .setMessage(R.string.message_permission_camera)

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
        AndPermission.onRequestPermissionsResult(this, TAKE_CODE, permissions, grantResults);
    }

    @PermissionYes(TAKE_CODE)//获取权限成功 去拍照
    private void getMultiYes(List<String> grantedPermissions) {

        new PhotoHelper(getTakePhoto()).configTakePhoto();

    }

    @PermissionNo(TAKE_CODE)//获取权限失败
    private void getMultiNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {

            AndPermission.defaultSettingDialog(this, TAKE_CODE).show();

        }
    }

}
