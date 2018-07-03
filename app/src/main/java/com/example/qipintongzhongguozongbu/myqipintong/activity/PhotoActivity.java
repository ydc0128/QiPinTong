package com.example.qipintongzhongguozongbu.myqipintong.activity;

/**
 * Created by L on 2017/3/25.
 */

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvShowPhotoAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RvItemDecoration;
import com.example.qipintongzhongguozongbu.myqipintong.event.MessageEvent;
import com.example.qipintongzhongguozongbu.myqipintong.helper.CustomHelper;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnItemClickListener;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;




public class PhotoActivity extends TakePhotoActivity {

    //    @BindView(R.id.tv_photo_xiangce)
//    Button tvPhotoXiangce;
//    @BindView(R.id.tv_photo_paizhao)
//    Button tvPhotoPaizhao;
    @BindView(R.id.tv_photo_quxiao)
    Button tvPhotoQuxiao;
    @BindView(R.id.rv_photo_pic)
    RecyclerView rvPhotoPic;
    private CustomHelper customHelper;
    private TakePhoto takePhoto;
    private RvShowPhotoAdapter rvShowPhotoAdapter;
    private int mPosition;
    ArrayList<File> fileList = new ArrayList<>();

    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        setUserSelect();

    }

    private void setUserSelect() {


        mPosition = getIntent().getIntExtra("TAG", 0);
       //获取标记来确定上一个页面是图库还是拍照
        switch (mPosition) {
            case 0:
                //图库
                EventBus.getDefault().register(this);
                //注册监听
                customHelper = new CustomHelper();

                setRecyclerAdapter();

                break;

            case 1:
                //拍照
                getTakePhoto().onPickFromCapture(getImageUri());//拍照后储存的路径

                setRecyclerAdapter();

                break;
        }

    }


    /**
     * function   : 获取图片的保存路径
     * Author     : 感觉自己懵懵的
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/8  ??4:44
     */
    public Uri getImageUri() {


        File file = new File(Environment.getExternalStorageDirectory(), "/QPT/photo" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        return imageUri;
    }

    private void setRecyclerAdapter() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        rvPhotoPic.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvShowPhotoAdapter = new RvShowPhotoAdapter(this);

        rvPhotoPic.addItemDecoration(new RvItemDecoration(10));
        //将图片集合传递
        rvPhotoPic.setAdapter(rvShowPhotoAdapter);
        //发朋友圈选择照片的适配器
        rvShowPhotoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                switch (mPosition) {

                    case 0:
                        new CustomHelper().init(getTakePhoto());//如果用户开始是进入图库选的话 这里点击是更多图片还是去图库
                        break;
                    case 1:
                        getTakePhoto().onPickFromCapture(getImageUri());//如果用户是开始进入相机的话 这里是点击更多图片还是去相机
                        break;
                }


            }
        });
    }

   /**
     * function   : 获取实例对象
     * Author     : 感觉自己懵懵的
     * E-mail     : anber1229423614@163.com
     * Date       : 2016/12/29  ??4:23
     */

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }


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
     * function   : 获取数据成功后刷新适配器显示图片
     * Author     : 感觉自己懵懵的
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/22  ??2:51
     */
    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    /**
     * function   : 选择照片后点击删除按钮执行此方法  刷新适配器
     * Author     : 感觉自己懵懵的
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/22  ??5:03
     */

    @Subscribe
    public void onEventMainThread(MessageEvent event) {
        //发朋友圈选择图片适配器
        rvPhotoPic.setAdapter(rvShowPhotoAdapter);
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @OnClick({R.id.tv_photo_quxiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_photo_quxiao:
                ArrayList<File> imageList = rvShowPhotoAdapter.getImageList();
                String inputText = tvPhotoQuxiao.getText().toString();
                //获取用户输入的文字 图片信息 这里应该将数据上传到服务器


                finish();

                break;
        }

    }
}