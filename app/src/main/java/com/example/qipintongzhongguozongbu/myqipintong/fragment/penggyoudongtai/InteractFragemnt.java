package com.example.qipintongzhongguozongbu.myqipintong.fragment.penggyoudongtai;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvFriendCommentAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.FriendDynamicBean;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.dialog.CustomDialog;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.PhotoPopup;
import com.example.qipintongzhongguozongbu.myqipintong.fragment.photoDetailsFragment;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnImageClick;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.example.qipintongzhongguozongbu.myqipintong.view.FriendNineDetailsView;
import com.example.qipintongzhongguozongbu.myqipintong.view.PraiseTextView;
import com.example.qipintongzhongguozongbu.myqipintong.view.SquareImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description: 朋友圈互动页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/4/1 上午11:48
 */
public class InteractFragemnt extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private final FriendDynamicBean bean;
    @BindView(R.id.SIV_interact_icon)
    SquareImageView SIVInteractIcon;
    @BindView(R.id.tv_interact_name)
    TextView tvInteractName;
    @BindView(R.id.tv_interact_date)
    TextView tvInteractDate;
    @BindView(R.id.tv_interact_time)
    TextView tvInteractTime;
    @BindView(R.id.tv_interact_body)
    TextView tvInteractBody;
    //    @BindView(R.id.rv_interact_images)
//    RecyclerView rvInteractImages;
//    @BindView(R.id.tv_interact_give_number)
//    TextView tvInteractGiveNumber;
//    @BindView(R.id.tv_interact_pingjia)
//    TextView tvInteractPingjia;
//    @BindView(R.id.tv_interact_pinglun)
//    TextView tvInteractPinglun;

    @BindView(R.id.et_interact_pingjia)
    EditText etInteractPingjia;
    @BindView(R.id.tv_interact_submit)
    TextView tvInteractSubmit;
    @BindView(R.id.rl_n_bottom)
    RelativeLayout rlNBottom;
    Unbinder unbinder;
    @BindView(R.id.FNV_interact_images)
    FriendNineDetailsView FNVInteractImages;
    @BindView(R.id.iv_interact_zan)
    ImageView ivInteractZan;
    @BindView(R.id.iv_interact_message)
    ImageView ivInteractMessage;
    @BindView(R.id.ptv_Praise)
    PraiseTextView ptvPraise;
    @BindView(R.id.rv_item_interact)
    RecyclerView rvItemInteract;
    @BindView(R.id.srl_interact)
    SwipeRefreshLayout srlInteract;
    @BindView(R.id.iv_interact_delete)
    ImageView ivInteractDelete;
    @BindView(R.id.tv_industry_location)
    TextView tvIndustryLocation;
    @BindView(R.id.ll_industry_select_location)
    LinearLayout llIndustrySelectLocation;
    @BindView(R.id.tv_interact_number)
    TextView tvInteractNumber;
    @BindView(R.id.iv_interact_give)
    ImageView ivInteractGive;

    private GridLayoutManager gridLayoutManager;


    private ImageView mIvdelete;
    private ImageView mIvIcon;
    private TextView mTvYes;
    private TextView mTvNo;
    private ImageView mIvCamera;
    private TextView mTvNumber;
    private EditText etInput;
    private Button btGive;
    private CustomDialog dialog;
    private CustomDialog deleteDialog;
    private List<PraiseTextView.PraiseInfo> mPraiseInfos;
    private boolean isZan = true;

    public InteractFragemnt(FriendDynamicBean bean) {
        this.bean = bean;//这里是朋友圈列表点击后传递的被点击条目数据

    }

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.fragment_interact, null));
    }


    @Override
    public void initData() {

        setRefresh();

        setFriendDate();

        setInteractAdapter();

        setNineImage();

        initPraise();

        super.initData();
    }

    private void setRefresh() {

        srlInteract.setOnRefreshListener(this);//加载刷新控件

        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        srlInteract.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        srlInteract.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srlInteract.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    private void initPraise() {

        mPraiseInfos = new ArrayList<>();
        mPraiseInfos.add(new PraiseTextView.PraiseInfo().setId(111).setNickname(" 张三").setLogo("http://lujianchao.com/images/headimg/1.jpg"));
        mPraiseInfos.add(new PraiseTextView.PraiseInfo().setId(222).setNickname("张四").setLogo("http://lujianchao.com/images/headimg/2.jpg"));
        mPraiseInfos.add(new PraiseTextView.PraiseInfo().setId(333).setNickname("张五").setLogo("http://lujianchao.com/images/headimg/3.jpg"));
        mPraiseInfos.add(new PraiseTextView.PraiseInfo().setId(444).setNickname("张六").setLogo("http://lujianchao.com/images/headimg/4.jpg"));
        mPraiseInfos.add(new PraiseTextView.PraiseInfo().setId(555).setNickname("赵四").setLogo("http://lujianchao.com/images/headimg/5.jpg"));
        mPraiseInfos.add(new PraiseTextView.PraiseInfo().setId(666).setNickname("赵三").setLogo("http://lujianchao.com/images/headimg/6.jpg"));
        mPraiseInfos.add(new PraiseTextView.PraiseInfo().setId(777).setNickname("李大").setLogo("http://lujianchao.com/images/headimg/7.jpg"));
        mPraiseInfos.add(new PraiseTextView.PraiseInfo().setId(888).setNickname("李二").setLogo("http://lujianchao.com/images/headimg/8.jpg"));
        mPraiseInfos.add(new PraiseTextView.PraiseInfo().setId(999).setNickname("李三").setLogo("http://lujianchao.com/images/headimg/9.jpg"));
        ptvPraise.setData(mPraiseInfos);
        ptvPraise.setIcon(R.mipmap.praise_blue);
        ptvPraise.setMiddleStr("、");
        ptvPraise.setIconSize(new Rect(0, 0, 55, 55));
        ptvPraise.setonPraiseListener(new PraiseTextView.onPraiseClickListener() {
            @Override
            public void onClick(final int position, final PraiseTextView.PraiseInfo mPraiseInfo) {
                ToastUtils.showToast(mActivity, mPraiseInfo.getNickname() + "被点击");
            }

            @Override
            public void onOtherClick() {
            }
        });
    }


    /**
     * function   : 九宫格图片的设置及点击
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/24  下午3:24
     */
    private void setNineImage() {

        FNVInteractImages.setUrlList(bean.getImageList());//给九宫格设置图片资源

        FNVInteractImages.setSpacing(4);

        FNVInteractImages.setOnImageClickListener(new OnImageClick() {
            @Override
            public void onItemClick(int position, ArrayList<String> list) {

//                photoDetailsFragment photoDetailsFragment = new photoDetailsFragment();
//                photoDetailsFragment.setPhotoList(list);
//                photoDetailsFragment.setPosition(position);
//                start(photoDetailsFragment);
                new PhotoPopup(mActivity, InteractFragemnt.this, list, position).showPopupWindow();
            }
        });
    }


    @Override
    public void onSupportVisible() {

        mTop.setVisibility(View.VISIBLE);

        mTvTitle.setText("动态详情");

        mIvBack.setVisibility(View.VISIBLE);

        super.onSupportVisible();
    }

    /**
     * function   : 设置数据
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/1  下午3:45
     */
    private void setFriendDate() {

        if (bean.getIcon() != null) {
            Glide.with(mActivity).load(bean.getIcon()).placeholder(R.mipmap.loding).error(R.mipmap.lodingerror).into(SIVInteractIcon);
        }

        if (bean.getInputText() != null) {
            tvInteractBody.setText(bean.getInputText());
        }

        if (bean.getLocation() != null) {
            // tvItemLocation.setText(bean.getLocation());
        }

        if (bean.getName() != null) {
            tvInteractName.setText(bean.getName());
        }

        if (bean.getTime() != null) {
            tvInteractTime.setText(bean.getTime());
        }
    }


    /**
     * function   : 展示评论的列表
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/1  下午3:08
     */
    private void setInteractAdapter() {

        //  tvInteractPinglun.setVisibility(View.VISIBLE);

        if (ImageList.getImageList() != null && ImageList.getImageList().size() != 0) {

            //  tvInteractPinglun.setVisibility(View.GONE);

            rvItemInteract.setLayoutManager(new LinearLayoutManager(mActivity));
            rvItemInteract.setAdapter(new RvFriendCommentAdapter(R.layout.item_city_road, ImageList.getImageList()));

        }

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
    public void onRefresh() {

    }

    @OnClick({R.id.iv_interact_delete, R.id.tv_industry_location, R.id.tv_interact_number, R.id.iv_interact_zan, R.id.iv_interact_message, R.id.iv_interact_give, R.id.tv_interact_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_interact_delete://删除

                deleteDialog();


                break;
            case R.id.tv_industry_location://地址

                break;
            case R.id.tv_interact_number://打赏数量

                start(new GiveNmberFragment());

                break;
            case R.id.iv_interact_zan://点赞

                if (isZan = !isZan) {
                    mPraiseInfos.remove(mPraiseInfos.size() - 1);
                } else {
                    mPraiseInfos.add(new PraiseTextView.PraiseInfo().setNickname("企聘通"));//这里获取用户名
                }

                ptvPraise.setData(mPraiseInfos);

                break;
            case R.id.iv_interact_message://留言

                break;
            case R.id.iv_interact_give://打赏


                giveMoneyDialog();

                break;
            case R.id.tv_interact_submit://提交

                break;
        }
    }

    private void deleteDialog() {

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


        mTvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//删除这条评论

                pop();

                deleteDialog.dismiss();

            }
        });

        mTvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog.dismiss();
            }
        });

    }

    private void giveMoneyDialog() {

        dialog = new CustomDialog(mActivity, R.style.Dialog_FS) {

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

        dialog.show();

        mIvIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(mActivity, "点击头像");
            }
        });

        mIvdelete.setOnClickListener(new View.OnClickListener() {//关闭弹窗时也要关闭软键盘

            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btGive.getWindowToken(), 0); //强制隐藏键盘

                dialog.dismiss();
            }
        });
        btGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToast(mActivity, "赏通币啦" + etInput.getText().toString());
                // 隐藏软键盘 一般用在hide时

                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btGive.getWindowToken(), 0); //强制隐藏键盘

                dialog.dismiss();
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

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.equals(0)) {//如果用户输入的第一位是0
                    ToastUtils.showToast(mActivity, "只能打赏整数哦");
                    etInput.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
