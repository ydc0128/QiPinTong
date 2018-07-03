package com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.GetPhotoActivity;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvNewFriendAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDateMode;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDynamicBean;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.BottomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.CustomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.DialogListViewItem;
import com.example.qipintongzhongguozongbu.myqipintong.event.FriendClickEvent;
import com.example.qipintongzhongguozongbu.myqipintong.event.FriendImageEvent;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.youcaiyoumao.FaceAndTalentDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.GlobalConstants;
import com.example.qipintongzhongguozongbu.myqipintong.utils.GlideUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.LogUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.PhotoUtils;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.PullToRefreshLayout;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;


/**
 * Description: 朋友动态详情页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/21 下午4:34
 */
public class FriendDetailsFragment extends BaseFragment implements BottomDialog.OnBottomDialogItemOnClickListener {


    @BindView(R.id.rv_friend_details)
    RecyclerView rvFriendDetails;
    Unbinder unbinder;
    @BindView(R.id.rf_friend_list)
    PullToRefreshLayout rfFriendList;
    @BindView(R.id.iv_friend_message_icon)
    SquareImageView ivFriendMessageIcon;
    @BindView(R.id.tv_friend_message_text)
    TextView tvFriendMessageText;
    @BindView(R.id.iv_friend_background)
    ImageView ivFriendBackground;

    private TextView mTvSelcet;
    private CustomDialog deleteDialog;
    private CustomDialog GiveMoneyDialog;
    private Button btGive;
    private ImageView mIvdelete;
    private ImageView mIvIcon;
    private TextView mTvYes;
    private TextView mTvNo;
    private ImageView mIvCamera;
    private TextView mTvNumber;
    private EditText etInput;

    private static final int ItemDelete = 0;//删除
    private static final int ItemDianZan = 1;//点赞
    private static final int ItemComment = 2;//评论
    private static final int ItemGiveMoney = 3;//打赏
    private static final int ItemUserIcon = 4;//头像
    private static final int ItemUserName = 5;//头像
    private static final int ItemUserInput = 6;//文章内容
    private static final int ItemGiveNumber = 7;//几人打赏


    private CustomDialog selcetDialog;
    private PhotoUtils photoUtils;
    private RvNewFriendAdapter rvNewFriendAdapter;


    @Override
    public void onSupportInvisible() {

        mIvCamera.setVisibility(View.GONE);

        super.onSupportInvisible();
    }


    @Override
    public View initView() {
        mIvCamera = (ImageView) mActivity.findViewById(R.id.iv_home_camera);
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_friend_details, null));
    }

    @Override
    public void initData() {

        EventBus.getDefault().register(this);

        if (rfFriendList != null) {
            setRefreshDate(rfFriendList);
        }

        setRecyclerAdapter();

        setTopButtonClick();

        super.initData();
    }

    private void setTopButtonClick() {

        mIvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });
    }


    @OnClick({R.id.iv_friend_background, R.id.fl_friend_new_message, R.id.CIV_item_friend_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_friend_background:

                showDialog();

                break;
            case R.id.fl_friend_new_message: //新消息提示的框框
                start(new FriendMessageFragment());
                break;
            case R.id.CIV_item_friend_icon://头像

                start(new FaceAndTalentDetailsFragment());

                break;
        }
    }

    private void showDialog() {

        selcetDialog = new CustomDialog(mActivity, R.style.DialogTheme) {
            @Override
            public View getView() {
                View view = View.inflate(mActivity, R.layout.dialog_select_background, null);
                mTvSelcet = (TextView) view.findViewById(R.id.tv_dialog_select_image);
                return view;
            }
        };

        selcetDialog.show();

        selcetDialog.setCanceledOnTouchOutside(true);

        mTvSelcet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mActivity, SelectBackGroundActivity.class));
                //开启选择图片的页面
                selcetDialog.dismiss();
            }
        });
    }


    /**
     * function   : 朋友动态展示列表
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/1  下午3:28
     */
    private void setRecyclerAdapter() {

        rvFriendDetails.setLayoutManager(new LinearLayoutManager(mActivity));
        rvNewFriendAdapter = new RvNewFriendAdapter(this, mActivity);
        rvNewFriendAdapter.setList(ImageList.getListData());
        rvFriendDetails.setAdapter(rvNewFriendAdapter);


    }

    public List<FriendDynamicBean> getDate() {

        ArrayList<FriendDynamicBean> list = new ArrayList<>();

        for (int i = 0; i < GlobalConstants.mIvDate.length; i++) {
            FriendDynamicBean bean = new FriendDynamicBean();
            bean.setIcon(GlobalConstants.mPhotoDate[i]);
            bean.setInputText("冰冷与黑暗并存的宇宙深处，九具庞大的龙尸拉着" + i + "口青铜古棺，亘古长存。这是太空探测器在枯寂的宇宙中捕捉到的一幅极其震撼的画面。　九龙拉棺，究竟是回到了上古，还是来到了星空的彼岸？");

            if (i == 0) {
                bean.setImageList(ImageList.getOneList());
            } else if (i == 1) {
                bean.setImageList(ImageList.getFourList());
            } else {
                bean.setImageList(ImageList.getImageList());
            }
            bean.setName("葫芦" + i + "娃");
            list.add(bean);
        }

        return list;
    }


    @Override
    public void onSupportVisible() {

        mButton.setVisibility(View.GONE);
        mTop.setVisibility(View.VISIBLE);
        mIvBack.setVisibility(View.VISIBLE);
        mIvCamera.setVisibility(View.VISIBLE);
        mTvTitle.setText("朋友圈");

        super.onSupportVisible();
    }

    /**
     * function   : 设置背景墙的图片
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/5/2  下午3:42
     */
    @Subscribe
    public void setBackground(FriendImageEvent event) {

        GlideUtils.loadImage(mActivity, event.getFile(), ivFriendBackground);

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


    private void showBottomDialog() {
        BottomDialog dialog = new BottomDialog(mActivity,
                R.style.transparentFrameWindowStyle);
        dialog.setOnBottomDialogItemOnClickListener(this);
        dialog.addItem(new DialogListViewItem("去 图 库"));
        dialog.addItem(new DialogListViewItem("现 在 拍"));
        dialog.show();
    }

    /**
     * function   : BottomDialog点击事件 根据点击位置决定页面去图库还是拍照
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/22  下午6:02
     */
    @Override
    public void onItemClick(DialogListViewItem item, int position) {
        Intent intent = new Intent(mActivity, GetPhotoActivity.class);
        intent.putExtra("TAG", position);
        startActivity(intent);
    }

    /**
     * function   : 朋友圈点赞删除等按钮点击事件处理
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/14  下午5:08
     */
    @Subscribe
    public void itemOnClick(FriendClickEvent event) {

        switch (event.getType()) {
            case ItemDelete://删除
                ToastUtils.showToast(mActivity, "点击删除" + event.getPosition());


                deleteDialog = new CustomDialog(mActivity, R.style.MyDialog) {

                    @Override
                    public View getView() {
                        View view = View.inflate(mActivity, R.layout.dialog_delete, null);
                        mTvYes = (TextView) view.findViewById(R.id.tv_dialog_yes);
                        mTvNo = (TextView) view.findViewById(R.id.tv_dialog_no);
                        return view;
                    }
                };
                deleteDialog.show();

                initDeleteClick(event.getPosition());


                break;
            case ItemDianZan://点赞


                ToastUtils.showToast(mActivity, "点击点赞" + event.getPosition());

                break;
            case ItemComment://评论

                ToastUtils.showToast(mActivity, "点击评论" + event.getPosition());

                FriendDynamicBean Bean = getDate().get(event.getPosition());
                //这里获取到被点击条目的数据集合
                start(new InteractFragemnt(Bean));//跳转至朋友圈互动的页面

                break;
            case ItemGiveMoney://打赏

                GiveMoneyDialog = new CustomDialog(mActivity, R.style.Dialog_FS) {


                    @Override
                    public View getView() {
                        View dialogView = View.inflate(mActivity, R.layout.dialog_give_money, null);
                        mIvIcon = (ImageView) dialogView.findViewById(R.id.iv_dialog_icon);
                        mIvdelete = (ImageView) dialogView.findViewById(R.id.iv_dialog_delete);
                        mTvNumber = (TextView) dialogView.findViewById(R.id.tv_dialog_number);
                        etInput = (EditText) dialogView.findViewById(R.id.et_dialog_input);
                        btGive = (Button) dialogView.findViewById(R.id.bt_give_money);
                        return dialogView;
                    }
                };

                GiveMoneyDialog.show();

                initGiveClick(event.getPosition());

                break;
            case ItemUserIcon://头像
                ToastUtils.showToast(mActivity, "点击头像" + event.getPosition());

                // start(new FaceAndTalentDetailsFragment(faceList, topJob));

                break;
            case ItemUserName://名字
                ToastUtils.showToast(mActivity, "点击名字" + event.getPosition());

                //  start(new FaceAndTalentDetailsFragment(faceList, topJob));
                break;

            case ItemUserInput://内容
                ToastUtils.showToast(mActivity, "点击内容" + event.getPosition());
                Bean = getDate().get(event.getPosition());
                //这里获取到被点击条目的数据集合
                start(new InteractFragemnt(Bean));//跳转至朋友圈互动的页面

                break;

            case ItemGiveNumber://几人打赏

                start(new GiveNmberFragment());

                break;

        }
    }

    /**
     * function   : 删除朋友圈条目点击
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/16  下午4:51
     *
     * @param position
     */
    private void initDeleteClick(final int position) {

        mTvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(mActivity, "点击确定" + position);

                ImageList.getListData().remove(position);

                rvNewFriendAdapter.notifyItemRemoved(position);

                rvNewFriendAdapter.notifyItemRangeChanged(position, ImageList.getListData().size() - position);

                deleteDialog.dismiss();

            }
        });

        mTvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(mActivity, "点击取消" + position);
                deleteDialog.dismiss();
            }
        });
    }


    /**
     * function   : 这里是打赏通币的弹窗点击事件
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/3/16  下午4:10
     *
     * @param position
     */
    private void initGiveClick(final int position) {

        mIvIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(mActivity, "点击头像" + position);
            }
        });

        mIvdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btGive.getWindowToken(), 0); //强制隐藏键盘

                GiveMoneyDialog.dismiss();
            }
        });
        btGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(mActivity, "赏通币啦" + position);

                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btGive.getWindowToken(), 0); //强制隐藏键盘

                GiveMoneyDialog.dismiss();
            }
        });
        mTvNumber.setOnClickListener(new View.OnClickListener() {//修改数量
            @Override
            public void onClick(View v) {
                etInput.setText("");
                // 显示软键盘，调用该方法后，会在onPause时自动隐藏软键盘
                showSoftInput(etInput);
            }
        });


        etInput.addTextChangedListener(new TextWatcher() {//输入框的实时监听
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                LogUtils.e(s.toString() + "1qqqqqqqqqqqqqqqq");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(0)) {//如果用户输入的第一位是0
                    ToastUtils.showToast(mActivity, "只能打赏整数哦");
                    etInput.setText("");
                }
                LogUtils.e(s.toString() + "2qqqqqqqqqqqqqqqq");
            }


            @Override
            public void afterTextChanged(Editable s) {
                LogUtils.e(s.toString() + "3qqqqqqqqqqqqqqqq");
            }
        });


    }


}