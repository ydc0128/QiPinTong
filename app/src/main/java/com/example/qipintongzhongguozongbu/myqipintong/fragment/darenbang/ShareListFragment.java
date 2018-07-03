package com.example.qipintongzhongguozongbu.myqipintong.fragment.darenbang;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvConsentAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.bean.ImageList;
import com.example.qipintongzhongguozongbu.myqipintong.utils.ToastUtils;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.example.qipintongzhongguozongbu.myqipintong.interposition.OnItemClickListener;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 共享圈的列表
 * Created by Administrator on 2017/6/3.
 */

public class ShareListFragment extends BaseFragment implements OnSwipeMenuItemClickListener {

    Unbinder unbinder;
    @BindView(R.id.srv)
    SwipeMenuRecyclerView srv;
    private RvConsentAdapter adapter;
    private ArrayList imageList;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.swipe_recyclerview, null));
    }

    @Override
    public void onSupportVisible() {

        mButton.setVisibility(View.GONE);
        mTvTitle.setText("共享列表");

        super.onSupportVisible();
    }


    @Override
    public void initData() {

        setAdapter();

        super.initData();
    }

    private void setAdapter() {

        imageList = ImageList.getImageList();

        srv.setLayoutManager(new LinearLayoutManager(mActivity));

        srv.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。

        srv.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。

        srv.setSwipeMenuCreator(swipeMenuCreator);//设置条目侧滑菜单

        srv.setSwipeMenuItemClickListener(this);//设置菜单点击事件

        adapter = new RvConsentAdapter(mActivity, imageList);

        srv.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                start(new ShareHistoryFragment());
            }
        });


    }

    /**
     * function   : 侧滑菜单栏设置
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/2/14  上午11:37
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

            WindowManager mWindowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);//侧滑菜单的宽度
            int width = (int) (mWindowManager.getDefaultDisplay().getWidth() / 3.5);
            //占据屏幕宽度的3.8分之一

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加右侧的，如果不添加，则左侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mActivity)
                        .setBackgroundDrawable(R.color.red_dark)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height)
                        .setTextSize(22);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
            }
        }
    };

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
    public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
        imageList.remove(adapterPosition);
        adapter.notifyItemRemoved(adapterPosition);
    }
}
