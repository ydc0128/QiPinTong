package com.example.qipintongzhongguozongbu.myqipintong.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Description: 自定义弹窗 长按相片弹出
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/6 上午11:01
 */
public class BottomDialog extends Dialog {
    private ListView listView;
    private Context mCtx;
    private List<DialogListViewItem> mListItem;
    private OnBottomDialogItemOnClickListener bottomDialogItemOnClickListener;
    private TextView cancel;
    private View view;

    public BottomDialog(Context context) {
        super(context);
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        initVars(context);
        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        initViews();
    }

    /**
     * 功能描述：初始化变量
     *
     * @Time 2016年4月18日
     * @Author lizy18
     * @param context
     */
    @SuppressLint("InflateParams")
    private void initVars(Context context) {
        this.mCtx = context;
        mListItem = new ArrayList<DialogListViewItem>();
        view = LayoutInflater.from(context).inflate(R.layout.bottom_dialog,
                null);
    }

    /**
     * 功能描述：初始化布局控件
     *
     * @Time 2016年4月18日
     * @Author lizy18
     */
    private void initViews() {
        view.getBackground().setAlpha(255);

        initListView();

        initTextView();
    }

    /**
     * 功能描述：初始化"取消"按钮控件
     *
     * @Time 2016年4月18日
     * @Author lizy18
     */
    private void initTextView() {
        cancel = (TextView) this.findViewById(R.id.txt_cancel);
        cancel.setOnClickListener(new android.view.View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }

        });
    }

    /**
     * 功能描述：初始化ListView控件
     *
     * @Time 2016年4月18日
     * @Author lizy18
     */
    private void initListView() {
        listView = (ListView) this.findViewById(R.id.txt_dialog_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                dismiss();
                if (bottomDialogItemOnClickListener != null) {
                    bottomDialogItemOnClickListener.onItemClick(
                            mListItem.get(position), position);
                }
            }
        });
    }

    /**
     * 功能描述：展示对话框
     *
     * @Time 2016年4月18日
     * @Author lizy18
     */
    public void show() {
        /**
         * 待显示的数据
         */
        listView.setAdapter(new DialogListViewAdapter(mCtx, mListItem));
        /**
         * 展示的位置
         */
        Window window = getWindow();
        window.setWindowAnimations(R.style.bottom_dialog_anim_style);
        android.view.WindowManager.LayoutParams attributes = window
                .getAttributes();
        attributes.x = 0;
        attributes.y = ScreenUtil.getScreenHeight(mCtx);
        attributes.width = ViewGroup.LayoutParams.MATCH_PARENT;
        attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 仿照QQ这设施透明度
        attributes.alpha = 0.7f;
        onWindowAttributesChanged(attributes);

        // 设置点击外围解散dialog
        this.setCanceledOnTouchOutside(true);

        super.show();
    }

    /**
     * 功能描述："依赖注入"添加DialogListViewItem对象
     *
     * @Time 2016年4月18日
     * @Author lizy18
     */
    public void addItem(DialogListViewItem item) {
        if (item != null) {
            mListItem.add(item);
        } else {
            throw new NullPointerException("DialogListViewItem对象不能为空！");
        }
    }

    /**
     * 功能描述："依赖注入"对话框监听事件
     *
     * @Time 2016年4月18日
     * @Author lizy18
     * @param
     */
    public void setOnBottomDialogItemOnClickListener(
            OnBottomDialogItemOnClickListener onBottomDialogItemOnClickListener) {
        bottomDialogItemOnClickListener = onBottomDialogItemOnClickListener;
    }

    /**
     * 功能描述：弹窗子类项按钮监听事件
     *
     * @Time 2016年3月22日
     * @author lizy18
     */
    public static interface OnBottomDialogItemOnClickListener {
        public void onItemClick(DialogListViewItem item, int position);
    }
}

