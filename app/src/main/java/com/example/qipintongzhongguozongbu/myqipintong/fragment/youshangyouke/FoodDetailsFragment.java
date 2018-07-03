package com.example.qipintongzhongguozongbu.myqipintong.fragment.youshangyouke;

import android.Manifest;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFoodCommentAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFoodDetailAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.index.LocationFragment;
import com.example.qipintongzhongguozongbu.myqipintong.map.Location;
import com.example.qipintongzhongguozongbu.myqipintong.map.NativeDialog;
import com.example.qipintongzhongguozongbu.myqipintong.ratingbar.MaterialRatingBar;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description: 食物详情的页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/1/10 上午11:06
 */
public class FoodDetailsFragment extends BaseFragment {

    @BindView(R.id.bb_food)
    Banner bbFood;
    @BindView(R.id.tv_food_name)
    TextView tvFoodName;
    @BindView(R.id.mrb_food_star)
    MaterialRatingBar mrbFoodStar;
    @BindView(R.id.tv_food_grade)
    TextView tvFoodGrade;
    @BindView(R.id.tv_food_CNY)
    TextView tvFoodCNY;
    @BindView(R.id.tv_food_kind)
    TextView tvFoodKind;
    @BindView(R.id.tv_food_area)
    TextView tvFoodArea;
    @BindView(R.id.bt_food_convert)
    Button btFoodConvert;
    @BindView(R.id.tv_food_location)
    TextView tvFoodLocation;
    @BindView(R.id.tv_food_guide)
    TextView tvFoodGuide;
    @BindView(R.id.tv_food_phone_number)
    TextView tvFoodPhoneNumber;
    @BindView(R.id.tv_food_url)
    TextView tvFoodUrl;
    @BindView(R.id.tv_food_comment)
    TextView tvFoodComment;
    @BindView(R.id.tv_food_look_all)
    TextView tvFoodLookAll;
    @BindView(R.id.rv_food_comment)
    RecyclerView rvFoodComment;
    @BindView(R.id.rv_food)
    RecyclerView rvFood;


    private Location loc_end = new Location(30.862644, 103.663077, "e");
    //终点信息 商家的位置
    private static final int LOCATION_CODE = 100;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_food, null));
    }

    private void setDados() {
        LayerDrawable stars = (LayerDrawable) mrbFoodStar.getProgressDrawable();
        stars.getDrawable(1).setColorFilter(mActivity.getResources().getColor(R.color.ColorSecondary), PorterDuff.Mode.SRC_ATOP);
    }


    @Override
    public void initData() {

        setBannerImage(bbFood);

        setFoodAdapter();

        setCommentAdapter();

        setDados();

        super.initData();
    }

    /**
     * function   : 食品的列表
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/10  下午2:59
     */
    private void setFoodAdapter() {

        rvFood.setLayoutManager(new LinearLayoutManager(mActivity));
        rvFood.setAdapter(new RvFoodDetailAdapter(R.layout.item_commerce_greens, ImageList.getImageList()));
        rvFood.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(new FoodDetailsFragment());
            }
        });
    }

    /**
     * function   : 评论的列表
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/1/10  下午3:00
     */
    private void setCommentAdapter() {

        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        rvFoodComment.setLayoutManager(LayoutManager);
        rvFoodComment.setAdapter(new RvFoodCommentAdapter(mActivity));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.tv_food_grade, R.id.bt_food_convert, R.id.tv_food_guide, R.id.tv_food_phone_number, R.id.tv_food_url, R.id.tv_food_look_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_food_convert:
                break;
            case R.id.tv_food_guide://导航

                NativeDialog msgDialog = new NativeDialog(mActivity, loc_end);
                msgDialog.show();

              //  setLocationPermission();

                break;
            case R.id.tv_food_phone_number:
                break;
            case R.id.tv_food_url:
                break;
            case R.id.tv_food_look_all:
                break;

        }
    }

    /**
     * function   : 定位权限相关
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/31  上午9:45
     */
    private void setLocationPermission() {

        if (AndPermission.hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) {//判断是否有定位权限


        } else {//申请定位权限

            AndPermission.with(this)
                    .requestCode(LOCATION_CODE)
                    .permission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .rationale(new MyRationaleListener())
                    // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                    .send();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，第一个参数是当前Acitivity/Fragment，回调方法写在当前Activity/Framgent。
        AndPermission.onRequestPermissionsResult(this, LOCATION_CODE, permissions, grantResults);
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
            new android.support.v7.app.AlertDialog.Builder(context)

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


    @PermissionYes(LOCATION_CODE)//获取权限成功
    private void getMultiYes(List<String> grantedPermissions) {

      start(new LocationFragment());

    }

    @PermissionNo(LOCATION_CODE)//获取权限失败
    private void getMultiNo(List<String> deniedPermissions) {

        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {

            AndPermission.defaultSettingDialog(this, LOCATION_CODE).show();

        }
    }


    @Override
    public void onSupportVisible() {

        mTvTitle.setText("店家名字");
        mIvBack.setVisibility(View.VISIBLE);
        mTop.setVisibility(View.VISIBLE);
        mButton.setVisibility(View.VISIBLE);

        super.onSupportVisible();

    }


}
