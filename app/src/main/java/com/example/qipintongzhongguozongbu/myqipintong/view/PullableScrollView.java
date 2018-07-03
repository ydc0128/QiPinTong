package com.example.qipintongzhongguozongbu.myqipintong.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.example.qipintongzhongguozongbu.myqipintong.interposition.PullScrollViewListener;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.Pullable;

/**
 * function   : 可刷新的 ScrollView
 * Author     : 感觉自己懵懵哒
 * E-mail     : anber1229423614@163.com
 * Date       : 2017/3/2  上午11:45
 */
public class PullableScrollView extends ScrollView implements Pullable {
    //按下的时候X轴的坐标
    private float initX = 0;
    //按下的时候Y轴的坐标
    private float initY = 0;

    private PullScrollViewListener scrollViewListener = null;

    public PullableScrollView(Context context) {
        super(context);
    }

    public PullableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
            return true;
        else
            return false;
    }

    public void setScrollViewListener(PullScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y);
        }
    }
}
