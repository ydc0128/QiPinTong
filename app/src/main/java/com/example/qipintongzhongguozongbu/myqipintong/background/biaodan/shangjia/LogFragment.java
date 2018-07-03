package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.shangjia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.PhotoActivity;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.BottomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.DialogListViewItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/17.
 */
public class LogFragment extends BaseFragment implements BottomDialog.OnBottomDialogItemOnClickListener {
    @BindView(R.id.iv_l_logo)
    ImageView ivLLogo;
    @BindView(R.id.iv_l_photo)
    ImageView ivLPhoto;
    @BindView(R.id.bt_logo_baocun)
    Button btLogoBaocun;
    @BindView(R.id.iv_bl_surroundings)
    ImageView ivBlSurroundings;

    Unbinder unbinder;
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_log, null));
    }

    public void onSupportVisible() {
        mTvTitle.setText("图片");
        mIvBack.setVisibility(View.VISIBLE);
        super.onSupportVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder=ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.iv_l_logo, R.id.iv_l_photo, R.id.bt_logo_baocun,R.id.iv_bl_surroundings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_l_logo:
                Intent intent = new Intent(mActivity, PhotoActivity.class);
                startActivity(intent);

            case R.id.iv_l_photo:
                Intent intent1 = new Intent(mActivity, PhotoActivity.class);
                startActivity(intent1);
            case R.id.iv_bl_surroundings:
                Intent intent2 = new Intent(mActivity, PhotoActivity.class);
                startActivity(intent2);
            case R.id.bt_logo_baocun:
                break;
        }
    }

//    private void showBottomDialog() {
//        BottomDialog dialog = new BottomDialog(mActivity,
//                R.style.transparentFrameWindowStyle);
//        dialog.setOnBottomDialogItemOnClickListener(this);
//        dialog.addItem(new DialogListViewItem("相册"));
//        dialog.addItem(new DialogListViewItem("拍照"));
//        dialog.show();
//    }

    @Override
    public void onItemClick(DialogListViewItem item, int position) {
//        Intent intent = new Intent(mActivity, GetPhotoActivity.class);
//        intent.putExtra("TAG", position);
//        startActivity(intent);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
