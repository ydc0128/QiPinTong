package com.example.qipintongzhongguozongbu.myqipintong.background.biaodan.jianli;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvJlGongzuojingyanAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.adapter.RvJlJiaoyujingyanAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.decoration.RecyclerItemClickListener;
import com.example.qipintongzhongguozongbu.myqipintong.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by L on 2017/3/31.
 *
 * 简历浏览
 */
public class BrowseFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.my_jl_photo)
    CircleImageView myJlPhoto;
    @BindView(R.id.tv_jl_name)
    TextView tvJlName;
    @BindView(R.id.tv_jl_xingbei)
    ImageView tvJlXingbei;
    @BindView(R.id.tv_jl_zhuangtai)
    TextView tvJlZhuangtai;
    @BindView(R.id.tv_jl_xueli)
    TextView tvJlXueli;
    @BindView(R.id.tv_jl_year)
    TextView tvJlYear;
    @BindView(R.id.tv_jl_city)
    TextView tvJlCity;
    @BindView(R.id.tv_jl_youxiang)
    TextView tvJlYouxiang;
    @BindView(R.id.tv_jl_nianliang)
    TextView tvJlNianliang;
    @BindView(R.id.tv_jl_hunyin)
    TextView tvJlHunyin;
    @BindView(R.id.tv_jl_qiuzhiyixiang)
    TextView tvJlQiuzhiyixiang;
    @BindView(R.id.tv_jl_youshi)
    TextView tvJlYoushi;
    @BindView(R.id.rv_jl_gongzuojingyan)
    RecyclerView rvJlGongzuojingyan;
    @BindView(R.id.rv_jl_jiaoyujingyan)
    RecyclerView rvJlJiaoyujingyan;

    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.background_jianli_liulan, null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void initData() {

        setRJlJiaoyujingyanAdapter();

        setRvJlGongzuojingyanAdapter();

        super.initData();
    }

    private void setRJlJiaoyujingyanAdapter() {
        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        rvJlJiaoyujingyan.setLayoutManager(LayoutManager);
        rvJlJiaoyujingyan.setAdapter(new RvJlJiaoyujingyanAdapter(mActivity));
        rvJlJiaoyujingyan.setVisibility(View.VISIBLE);
        rvJlJiaoyujingyan.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), rvJlJiaoyujingyan, new RecyclerItemClickListener.OnItemClickListener() {

            public void onItemClick(View view, int position) {

                // ...
            }


            public void onItemLongClick(View view, int position) {

            }
        }));

    }

    private void setRvJlGongzuojingyanAdapter() {
        LinearLayoutManager LayoutManager = new LinearLayoutManager(mActivity);
        LayoutManager.setOrientation(LayoutManager.VERTICAL);
        rvJlGongzuojingyan.setLayoutManager(LayoutManager);
        rvJlGongzuojingyan.setAdapter(new RvJlGongzuojingyanAdapter(mActivity));
        rvJlGongzuojingyan.setVisibility(View.VISIBLE);
        rvJlGongzuojingyan.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), rvJlGongzuojingyan, new RecyclerItemClickListener.OnItemClickListener() {

            public void onItemClick(View view, int position) {

                // ...
            }


            public void onItemLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
