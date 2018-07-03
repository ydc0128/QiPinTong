package com.example.qipintongzhongguozongbu.myqipintong.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.qipintongzhongguozongbu.myqipintong.R;

import java.util.List;


/**
 * function   : 自定义的ViewFlipper 用来展示首页评论翻滚效果
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/1/4  上午11:12
 */
public class MyViewFlipper extends ViewFlipper {
    private Context mContext;
    private boolean isSetAnimDuration = false;
    private int interval = 2000;
    /**
     * 动画时间
     */
    private int animDuration = 500;

    public MyViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int i) {
        this.mContext = context;
        //视图间切换的时间间隔
        setFlipInterval(interval);
        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.text_scroll_in);
        if (isSetAnimDuration) animIn.setDuration(animDuration);
        setInAnimation(animIn);
        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.text_scroll_out);
        if (isSetAnimDuration) animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    /**
     * 设置循环滚动的View数组
     *
     * @param views
     */
    public void setViews(final List<View> views) {
        if (views == null || views.size() == 0) return;
        //移除父布局:解决多个父布局问题
        for (View v : views) {
            ViewGroup allView = (ViewGroup) v.getParent();
            if (allView != null) {
                allView.removeAllViewsInLayout();
            }
        }
        for (int i = 0; i < views.size(); i++) {
            final int position = i;
            //设置监听回调
            views.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, views.get(position));
                    }
                }
            });

            addView(views.get(i));
        }
        startFlipping();
    }

    /**
     * 点击
     */
    private OnItemClickListener onItemClickListener;

    /**
     * 设置监听接口
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    /**
     * item_view的接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

}
