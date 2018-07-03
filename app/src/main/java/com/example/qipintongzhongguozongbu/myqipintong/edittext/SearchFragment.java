package com.example.qipintongzhongguozongbu.myqipintong.edittext;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description: 搜索页面
 * Copyright  : Copyright (c) 2016
 * Author     : 感觉自己懵懵哒
 * E-mail     :anber1229423614@163.com
 * Date       : 2017/2/28 下午4:37
 */
public class SearchFragment extends BaseFragment {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.listView)
    Search_Listview listView;
    Unbinder unbinder;
    /*数据库变量*/
    private RecordSQLiteOpenHelper helper;
    private InputMethodManager imm;
    private BaseAdapter adapter;
    private SQLiteDatabase db;

    @Override
    public View initView() {
        return swipeBackView(View.inflate(mActivity, R.layout.search_layout, null));
    }



    @Override
    public void initData() {
        //键盘管理
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(context);
        // 第一次进入时查询所有的历史记录
        queryData("");

        setEtListener();

        setListListener();

        super.initData();
    }

    /**
     * function   : 搜索历史列表
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/12  下午2:26
     */
    private void setListListener() {

        //列表监听
        //即当用户点击搜索历史里的字段后,会直接将结果当作搜索字段进行搜索
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //获取到用户点击列表里的文字,并自动填充到搜索框内
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                etSearch.setText(name);
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();

            }
        });

    }

    /**
     * function   : 输入框的处理
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/12  下午2:27
     */
    private void setEtListener() {

        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ivSearch.setVisibility(View.GONE);
                }
            }
        });
        //搜索框的文本变化实时监听
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //输入后调用该方法
            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().trim().length() == 0) {
                    //若搜索框为空,则模糊搜索空字符,即显示所有的搜索历史
                    tvTip.setText("搜索历史");
                } else {
                    tvTip.setText("搜索内容");

                    ivSearch.setVisibility(View.GONE);
                }

                //每次输入后都查询数据库并显示
                //根据输入的值去模糊查询数据库中有没有数据
                String tempName = etSearch.getText().toString();
                queryData(tempName);

            }
        });


        // 搜索框的键盘搜索键
        // 点击回调
        etSearch.setOnKeyListener(new View.OnKeyListener() {// 输入完后按键盘上的搜索键


            // 修改回车键功能
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 隐藏键盘，这里getCurrentFocus()需要传入Activity对象，如果实际不需要的话就不用隐藏键盘了，免得传入Activity对象，这里就先不实现了
//                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
//                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    boolean hasData = hasData(etSearch.getText().toString().trim());
                    if (!hasData) {
                        insertData(etSearch.getText().toString().trim());

                        queryData("");
                    }

                    if (imm != null) {
                        imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0); //强制隐藏键盘
                    }

                    //根据输入的内容模糊查询商品，并跳转到另一个界面，这个需要根据需求实现
                    Toast.makeText(context, etSearch.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


    }

    /*插入数据*/
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /*模糊查询数据 并显示在ListView列表上*/
    private void queryData(String tempName) {

        //模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象,装入模糊搜索的结果
        adapter = new SimpleCursorAdapter(context, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /*检查数据库中是否已经有该条记录*/
    private boolean hasData(String tempName) {
        //从Record这个表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /*清空数据*/
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }

    @Override
    public void onSupportVisible() {
        mTop.setVisibility(View.GONE);
        mButton.setVisibility(View.GONE);
        super.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {

        if (imm != null) {
            imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0); //强制隐藏键盘
        }

        super.onSupportInvisible();
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

    @OnClick({R.id.iv_search_back, R.id.tv_search, R.id.tv_search_work, R.id.tv_search_talents, R.id.tv_search_project, R.id.tv_search_company, R.id.tv_search_together, R.id.tv_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                pop();
                break;
            case R.id.tv_search:

                boolean hasData = hasData(etSearch.getText().toString().trim());
                if (!hasData) {
                    insertData(etSearch.getText().toString().trim());

                    //搜索后显示数据库里所有搜索历史是为了测试
                    queryData("");
                }

                if (imm != null) {
                    imm.hideSoftInputFromWindow(etSearch.getWindowToken(), 0); //强制隐藏键盘
                }
                //根据输入的内容模糊查询商品，并跳转到另一个界面，这个根据需求实现
                Toast.makeText(context, etSearch.getText().toString().trim(), Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_search_work:
                etSearch.setText("工作");
                break;
            case R.id.tv_search_talents:
                etSearch.setText("人才");
                break;
            case R.id.tv_search_project:
                etSearch.setText("项目");
                break;
            case R.id.tv_search_company:
                etSearch.setText("企业");
                break;
            case R.id.tv_search_together:
                etSearch.setText("合伙人");
                break;
            case R.id.tv_clear:
                //清空数据库
                deleteData();
                queryData("");
                break;
        }
    }

}
