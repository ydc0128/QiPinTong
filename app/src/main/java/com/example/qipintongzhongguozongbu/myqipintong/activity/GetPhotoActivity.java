package com.example.qipintongzhongguozongbu.myqipintong.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvShowPhotoAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvqqAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.event.MessageEvent;
import com.example.qipintongzhongguozongbu.myqipintong.event.TextEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai.SelectLocationActivity;
import com.example.qipintongzhongguozongbu.myqipintong.helper.CustomHelper;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.InputMethodLayout;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;


/**
 * Description: 发布 编辑朋友圈动态的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/22 上午11:05
 */
public class GetPhotoActivity extends TakePhotoActivity implements AMapLocationListener {


    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";


    @BindView(R.id.rv_show_photo)
    RecyclerView rvShowPhoto;
    @BindView(R.id.tv_send_photo)
    Button tvSendPhoto;
    @BindView(R.id.et_user_input)
    EditText etUserInput;
    @BindView(R.id.tv_cancel_photo)
    TextView tvCancelPhoto;
    @BindView(R.id.iv_open_close)
    ImageView ivOpenClose;

    private static final int PERMISSION_CODE = 100;//定位的权限请求码
    @BindView(R.id.tv_show_location)
    TextView tvShowLocation;
    @BindView(R.id.rv_qq)
    RecyclerView rvQq;
    @BindView(R.id.ll_getphoto)
    InputMethodLayout llGetphoto;
    @BindView(R.id.tv_qq_or_key)
    TextView tvQqOrKey;
    @BindView(R.id.ll_friend_face)
    LinearLayout llFriendFace;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption = null;

    private CustomHelper customHelper;
    private TakePhoto takePhoto;
    private RvShowPhotoAdapter rvShowPhotoAdapter;
    private int mPosition;
    ArrayList<File> fileList = new ArrayList<>();
    private boolean isOpen = true;//定位是否打开
    private boolean isKey;//键盘是否打开
    private InputMethodManager imm;
    private String inputText;


    //表情的集合数据
    private static int[] mImageIds = new int[]{R.mipmap.f001,
            R.mipmap.f002, R.mipmap.f003, R.mipmap.f004, R.mipmap.f005,
            R.mipmap.f006, R.mipmap.f007, R.mipmap.f008, R.mipmap.f009,
            R.mipmap.f010, R.mipmap.f011, R.mipmap.f012, R.mipmap.f013,
            R.mipmap.f014, R.mipmap.f015, R.mipmap.f016, R.mipmap.f017,
            R.mipmap.f018, R.mipmap.f019, R.mipmap.f020, R.mipmap.f021,
            R.mipmap.f022, R.mipmap.f023, R.mipmap.f024, R.mipmap.f025,
            R.mipmap.f026, R.mipmap.f027, R.mipmap.f028, R.mipmap.f029,
            R.mipmap.f030, R.mipmap.f031, R.mipmap.f032, R.mipmap.f033,
            R.mipmap.f034, R.mipmap.f035, R.mipmap.f036, R.mipmap.f037,
            R.mipmap.f038, R.mipmap.f039, R.mipmap.f040, R.mipmap.f041,
            R.mipmap.f042, R.mipmap.f043, R.mipmap.f044, R.mipmap.f045,
            R.mipmap.f046, R.mipmap.f047, R.mipmap.f048, R.mipmap.f049,
            R.mipmap.f050, R.mipmap.f051, R.mipmap.f052, R.mipmap.f053,
            R.mipmap.f054, R.mipmap.f055, R.mipmap.f056, R.mipmap.f057,
            R.mipmap.f058, R.mipmap.f059, R.mipmap.f060, R.mipmap.f061,
            R.mipmap.f062, R.mipmap.f063, R.mipmap.f064, R.mipmap.f065,
            R.mipmap.f067, R.mipmap.f068, R.mipmap.f069, R.mipmap.f070,
            R.mipmap.f071, R.mipmap.f072, R.mipmap.f073, R.mipmap.f074,
            R.mipmap.f075, R.mipmap.f076, R.mipmap.f077, R.mipmap.f078,
            R.mipmap.f079, R.mipmap.f080, R.mipmap.f081, R.mipmap.f082,
            R.mipmap.f083, R.mipmap.f084, R.mipmap.f085, R.mipmap.f086,
            R.mipmap.f087, R.mipmap.f088, R.mipmap.f089, R.mipmap.f090,
            R.mipmap.f091, R.mipmap.f092, R.mipmap.f093, R.mipmap.f094,
            R.mipmap.f095, R.mipmap.f096, R.mipmap.f097, R.mipmap.f098,
            R.mipmap.f099, R.mipmap.f100, R.mipmap.f101, R.mipmap.f103,
            R.mipmap.f104, R.mipmap.f105};


    /**
     * function   : 绝对布局
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/18  上午10:39
     */
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {

        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getphoto);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);//键盘管理

        setUserSelect();

        setkeyboardListener();//键盘监听

        setRvqqAdapter();//qq表情的适配器

        setEditTextListener();

    }

    /**
     * function   : 当输入框被点击时 表情隐藏 弹出键盘
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/7  下午2:16
     */
    private void setEditTextListener() {

        etUserInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rvQq.setVisibility(View.GONE);

                ivQq.setImageResource(R.mipmap.emji);

                tvQqOrKey.setText("表情");

                isKey = false;
            }
        });
    }

    /**
     * function   : 为键盘的状态添加监听
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/6  下午3:29
     */
    private void setkeyboardListener() {

        llGetphoto.setOnkeyboarddStateListener(new InputMethodLayout.onKeyboardsChangeListener() {

            @Override
            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case InputMethodLayout.KEYBOARD_STATE_SHOW://键盘显示
                        llFriendFace.setVisibility(View.VISIBLE);
                        //键盘显示时显示悬浮窗

                        break;
                    case InputMethodLayout.KEYBOARD_STATE_HIDE://键盘隐藏

                        int visibility = rvQq.getVisibility();
                        if (visibility == 8) {//此时说明表情和键盘都是隐藏状态 讲切换键盘按钮隐藏
                            llFriendFace.setVisibility(View.GONE);
                        }

                        break;
                }

            }


        });

    }

    /**
     * function   : 根据进入此页面时用户选择的选择相片模式判断
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/6  下午3:29
     */
    private void setUserSelect() {

        mPosition = getIntent().getIntExtra("TAG", 0);
        //获取标记来确定上一个页面点击的是图库还是拍照
        switch (mPosition) {
            case 0:
                //图库

                customHelper = new CustomHelper();

                setRecyclerAdapter();

                break;

            case 1:
                //拍照
                getTakePhoto().onPickFromCapture(getImageUri());//拍照后存储的路径

                setRecyclerAdapter();

                break;
        }

    }


    /**
     * function   : 获取图片保存路径
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/8  下午4:44
     */
    public Uri getImageUri() {


        File file = new File(Environment.getExternalStorageDirectory(), "/QPT/photo" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        return imageUri;
    }

    private void setRecyclerAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        rvShowPhoto.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvShowPhotoAdapter = new RvShowPhotoAdapter(this);

        rvShowPhoto.addItemDecoration(new RvItemDecoration(5));
        //讲图片集合传递
        rvShowPhoto.setAdapter(rvShowPhotoAdapter);
        //这里是发朋友圈选择的图片的适配器
        rvShowPhotoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                switch (mPosition) {

                    case 0:
                        new CustomHelper().init(getTakePhoto());//如果用户开始是进入图库选的话 这里点击更多图片还是去图库
                        break;
                    case 1:
                        getTakePhoto().onPickFromCapture(getImageUri());//如果用户开始是进入相机的话 这里点击更多图片还是去相机
                        break;
                }


            }
        });
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
     * function   : 获取图片数据成功后刷新适配器展示图片
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/22  下午2:51
     */
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);

        ArrayList<TImage> images = result.getImages();

        if (images != null) {

            for (int i = 0; i < images.size(); i++) {

                File file = new File(images.get(i).getOriginalPath());

                fileList.add(file);

            }

            rvShowPhotoAdapter.setmImageList(fileList);
            rvShowPhotoAdapter.notifyDataSetChanged();

            fileList.clear();
        }

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
    }

    /**
     * function   : 选择照片后点击删除按钮执行此方法 刷新适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/22  下午5:03
     */

    @Subscribe
    public void onEventMainThread(MessageEvent event) {
        //这里是发朋友圈选择的图片的适配器
        rvShowPhoto.setAdapter(rvShowPhotoAdapter);
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (imm != null) {
            imm.hideSoftInputFromWindow(etUserInput.getWindowToken(), 0); //强制隐藏键盘
        }
        super.onPause();
    }

    @OnClick({R.id.tv_cancel_photo, R.id.tv_send_photo, R.id.iv_open_close, R.id.iv_qq, R.id.tv_show_location})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_photo:
                //返回
                finish();
                break;
            case R.id.tv_send_photo:
                //发送
                ArrayList<File> imageList = rvShowPhotoAdapter.getImageList();
                //获取到用户输入的文字 图片信息 这里应该讲数据上传至服务器
                // inputText;

                finish();

                break;
            case R.id.iv_open_close://显示或不显示位置信息

                if (isOpen) {//显示定位信息

                    ivOpenClose.setImageResource(R.mipmap.open);

                    setLocationPermission();//显示位置信息要先去获取定位权限

                } else {//不显示定位信息
                    if (mlocationClient != null) {
                        mlocationClient.stopLocation();//停止定位服务
                    }
                    ivOpenClose.setImageResource(R.mipmap.open);
                    tvShowLocation.setText("显示定位");
                }
                isOpen = !isOpen;
                break;
            case R.id.iv_qq://表情按钮的点击

                if (isKey) {//当键盘打开时 表情数据隐藏
                    imm.showSoftInput(etUserInput, InputMethodManager.SHOW_FORCED);//显示键盘
                    rvQq.setVisibility(View.GONE);

                    ivQq.setImageResource(R.mipmap.emji);

                    tvQqOrKey.setText("表情");

                } else {
                    imm.hideSoftInputFromWindow(etUserInput.getWindowToken(), 0); //强制隐藏键盘

                    rvQq.setVisibility(View.VISIBLE);
                    llFriendFace.setVisibility(View.VISIBLE);

                    ivQq.setImageResource(R.mipmap.keyboard);

                    tvQqOrKey.setText("键盘");
                }

                isKey = !isKey;

                break;
            case R.id.tv_show_location:
                //显示地址跳转新页面去选择

                startActivity(new Intent(this, SelectLocationActivity.class));

                break;
        }
    }


    /**
     * function   : 选择地址后在此处理
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/4  下午5:26
     */
    @Subscribe
    public void setLocation(TextEvent event) {

        tvShowLocation.setText(event.getStr() + "");

    }


    /**
     * function   : 定位权限相关
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  上午9:45
     */
    private void setLocationPermission() {

        if (AndPermission.hasPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)) {//判断是否有定位权限

            getLocationInfo();

        } else {//申请定位权限

            AndPermission.with(this)
                    .requestCode(PERMISSION_CODE)
                    .permission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .rationale(new MyLocationListener())
                    // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                    .send();
        }

    }


    /**
     * function   : 用户拒绝过定位授权后再次请求授权会执行
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  上午11:18
     */
    class MyLocationListener implements RationaleListener {

        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            // 自定义对话框。
            new AlertDialog.Builder(GetPhotoActivity.this)

                    .setTitle("友情提示")

                    .setMessage(R.string.message_permission_location)

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
        AndPermission.onRequestPermissionsResult(this, PERMISSION_CODE, permissions, grantResults);
    }

    @PermissionYes(PERMISSION_CODE)//获取权限成功
    private void getMultiYes(List<String> grantedPermissions) {

        getLocationInfo();

    }

    @PermissionNo(PERMISSION_CODE)//获取权限失败
    private void getMultiNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {

            AndPermission.defaultSettingDialog(this, PERMISSION_CODE).show();

        }
    }

    /**
     * function   : 定位相关功能
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/30  上午10:08
     */
    private void getLocationInfo() {

        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(this);
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                String aoiName = amapLocation.getAoiName();//获取附近建筑信息

                double latitude = amapLocation.getLatitude();

                LogUtils.e(latitude + "");


                if (aoiName != null) {
                    tvShowLocation.setText(aoiName);
                    mlocationClient.stopLocation();//设置完数据后停止定位
                } else {
                    tvShowLocation.setText("暂时无法定位");
                }
            }
        }
    }


    /**
     * function   : QQ表情的适配器
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/6  上午10:33
     */
    private void setRvqqAdapter() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvQq.setLayoutManager(gridLayoutManager);
        rvQq.setAdapter(new RvqqAdapter(R.layout.item_image, getQQIamge()));
        rvQq.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {


            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                SpannableString spannableString = new SpannableString(view.toString());

                long itemId = adapter.getItemId(position);
                Drawable drawable = getResources().getDrawable(mImageIds[(int) itemId]);
                drawable.setBounds(0, 0, 60, 60);//设置表情大小

                ImageSpan imageSpan = new ImageSpan(drawable,
                        ImageSpan.ALIGN_BOTTOM);
                spannableString.setSpan(imageSpan, 0, view.toString()
                        .length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Editable editable = etUserInput.getText().insert(etUserInput.getSelectionStart(),
                        spannableString);

                //获取到用户输入数据 表情数据就是图片集合的角标
                inputText = editable.toString();
            }
        });

    }

    //获取qq表情集合
    public ArrayList getQQIamge() {

        ArrayList<Integer> qqImages = new ArrayList<>();

        for (int i = 0; i < mImageIds.length; i++) {
            qqImages.add(mImageIds[i]);
        }
        return qqImages;
    }

}
