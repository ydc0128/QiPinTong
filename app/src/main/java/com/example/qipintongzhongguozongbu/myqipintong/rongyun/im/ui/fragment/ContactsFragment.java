package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.AppApplication;
import com.example.qipintongzhongguozongbu.myqipintong.base.BaseFragment;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealAppContext;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealConst;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealUserInfoManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.db.Friend;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.broadcast.BroadcastManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.pinyin.CharacterParser;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.pinyin.PinyinComparator;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.pinyin.SideBar;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.widget.SelectableRoundedImageView;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.GroupListActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.NewFriendListActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.PublicServiceActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.SealSearchActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity.UserDetailActivity;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.adapter.FriendListAdapter;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.widget.MorePopWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * tab 2 通讯录的 Fragment
 * Created by Bob on 2015/1/25.
 */
public class ContactsFragment extends BaseFragment implements View.OnClickListener {
    String token1="I3d9dUauFbfVCTvqvq3nW7Kl9MC0YtbMe81ao8MUBDhYNBWsQSqs8Mp9Qf8TNlayAi+WloH/GhkOx36wnCtJNg==";

    private SelectableRoundedImageView mSelectableRoundedImageView;
    private TextView mNameTextView,tvTopTxl;
    private TextView mNoFriends;
    private TextView mUnreadTextView;
    private View mHeadView;
    private ImageView mIvSearch,SealMore;
    private ListView mListView;
    private PinyinComparator mPinyinComparator;
    private SideBar mSidBar;
    Unbinder unbinder;
    /**
     * 中部展示的字母提示
     */
    private TextView mDialogTextView;

    private List<Friend> mFriendList;
    private List<Friend> mFilteredFriendList;
    /**
     * 好友列表的 mFriendListAdapter
     */
    private FriendListAdapter mFriendListAdapter;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser mCharacterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */

    private String mId;
    private String mCacheName;

    private static final int CLICK_CONTACT_FRAGMENT_FRIEND = 2;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity,R.layout.activity_address, null);
        initView(view);
        initData();
        updateUI();
        refreshUIListener();
        return view;

    }
    public static ContactsFragment getInstance() {
        return new ContactsFragment();
    }
    private void startFriendDetailsPage(Friend friend) {
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra("type", CLICK_CONTACT_FRAGMENT_FRIEND);
        intent.putExtra("friend", friend);
        startActivity(intent);
    }
    public void onSupportVisible() {
        super.onSupportVisible();
    }
    private void initView(View view) {
        tvTopTxl=(TextView)view.findViewById(R.id. tv_top_txl);
        tvTopTxl.setText("通讯录");
        mIvSearch=(ImageView)view.findViewById(R.id.ac_iv_search);
        SealMore=(ImageView)view.findViewById(R.id.seal_more);
        mListView = (ListView) view.findViewById(R.id.listview);
        mNoFriends = (TextView) view.findViewById(R.id.show_no_friend);
        mSidBar = (SideBar) view.findViewById(R.id.sidrbar);
        mDialogTextView = (TextView) view.findViewById(R.id.group_dialog);
        mSidBar.setTextView(mDialogTextView);
        LayoutInflater mLayoutInflater = LayoutInflater.from(getActivity());
        mHeadView = mLayoutInflater.inflate(R.layout.item_contact_list_header,
                null);
        mUnreadTextView = (TextView) mHeadView.findViewById(R.id.tv_unread);
        RelativeLayout newFriendsLayout = (RelativeLayout) mHeadView.findViewById(R.id.re_newfriends);
        RelativeLayout groupLayout = (RelativeLayout) mHeadView.findViewById(R.id.re_chatroom);
        RelativeLayout publicServiceLayout = (RelativeLayout) mHeadView.findViewById(R.id.publicservice);
        RelativeLayout selfLayout = (RelativeLayout) mHeadView.findViewById(R.id.contact_me_item);
        RelativeLayout ceshi = (RelativeLayout) mHeadView.findViewById(R.id.publicceshi1);

        mSelectableRoundedImageView = (SelectableRoundedImageView) mHeadView.findViewById(R.id.contact_me_img);
        mNameTextView = (TextView) mHeadView.findViewById(R.id.contact_me_name);
        updatePersonalUI();
        mListView.addHeaderView(mHeadView);
        mNoFriends.setVisibility(View.VISIBLE);
        mIvSearch.setOnClickListener(this);
        SealMore.setOnClickListener(this);
        selfLayout.setOnClickListener(this);
        groupLayout.setOnClickListener(this);
        newFriendsLayout.setOnClickListener(this);
        publicServiceLayout.setOnClickListener(this);
        ceshi.setOnClickListener(this);
        //设置右侧触摸监听
        mSidBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = mFriendListAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mListView.setSelection(position);
                }

            }
        });
        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, SealSearchActivity.class));
            }
        });
        SealMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MorePopWindow morePopWindow = new MorePopWindow(mActivity);
                morePopWindow.showPopupWindow(SealMore);
            }
        });
    }


    public void initData() {
        mFriendList = new ArrayList<>();
        FriendListAdapter adapter = new FriendListAdapter(getActivity(), mFriendList);
        mListView.setAdapter(adapter);
        mFilteredFriendList = new ArrayList<>();
        //实例化汉字转拼音类
        mCharacterParser = CharacterParser.getInstance();
        mPinyinComparator = PinyinComparator.getInstance();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mDialogTextView != null) {
            mDialogTextView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr 需要过滤的 String
     */
    private void filterData(String filterStr) {
        List<Friend> filterDateList = new ArrayList<>();

        try {
            if (TextUtils.isEmpty(filterStr)) {
                filterDateList = mFriendList;
            } else {
                filterDateList.clear();
                for (Friend friendModel : mFriendList) {
                    String name = friendModel.getName();
                    String displayName = friendModel.getDisplayName();
                    if (!TextUtils.isEmpty(displayName)) {
                        if (name.contains(filterStr) || mCharacterParser.getSpelling(name).startsWith(filterStr) || displayName.contains(filterStr) || mCharacterParser.getSpelling(displayName).startsWith(filterStr)) {
                            filterDateList.add(friendModel);
                        }
                    } else {
                        if (name.contains(filterStr) || mCharacterParser.getSpelling(name).startsWith(filterStr)) {
                            filterDateList.add(friendModel);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, mPinyinComparator);
        mFilteredFriendList = filterDateList;
        mFriendListAdapter.updateListView(filterDateList);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_newfriends:
                mUnreadTextView.setVisibility(View.GONE);
                Intent intent = new Intent(getActivity(), NewFriendListActivity.class);
                startActivityForResult(intent, 20);
                break;
            case R.id.re_chatroom:
                startActivity(new Intent(getActivity(), GroupListActivity.class));
                break;
            case R.id.publicservice:
                Intent intentPublic = new Intent(getActivity(), PublicServiceActivity.class);
                startActivity(intentPublic);
                break;
            case R.id.publicceshi1:
                RongIM.connect(token1, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                    }

                    @Override
                    public void onSuccess(String s) {
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode e) {
                    }
                });
                RongIM.getInstance().startPrivateChat(getActivity(),"456789", "包大人不黑");

                break;
            case R.id.contact_me_item:
                RongIM.getInstance().startPrivateChat(getActivity(), mId, mCacheName);
                break;
        }
    }

    private void refreshUIListener() {
        BroadcastManager.getInstance(getActivity()).addAction(SealAppContext.UPDATE_FRIEND, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                if (!TextUtils.isEmpty(command)) {
                    updateUI();
                }
            }
        });

        BroadcastManager.getInstance(getActivity()).addAction(SealAppContext.UPDATE_RED_DOT, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                if (!TextUtils.isEmpty(command)) {
                    mUnreadTextView.setVisibility(View.INVISIBLE);
                }
            }
        });
        BroadcastManager.getInstance(getActivity()).addAction(SealConst.CHANGEINFO, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updatePersonalUI();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            BroadcastManager.getInstance(getActivity()).destroy(SealAppContext.UPDATE_FRIEND);
            BroadcastManager.getInstance(getActivity()).destroy(SealAppContext.UPDATE_RED_DOT);
            BroadcastManager.getInstance(getActivity()).destroy(SealConst.CHANGEINFO);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void updateUI() {
        if (SealUserInfoManager.getInstance()!= null) {
            SealUserInfoManager.getInstance().getFriends(new SealUserInfoManager.ResultCallback<List<Friend>>() {
                @Override
                public void onSuccess(List<Friend> friendsList) {
                    updateFriendsList(friendsList);
                }

                @Override
                public void onError(String errString) {
                    updateFriendsList(null);
                }
            });
        }
    }

    private void updateFriendsList(List<Friend> friendsList) {
        //updateUI fragment初始化和好友信息更新时都会调用,isReloadList表示是否是好友更新时调用
        boolean isReloadList = true;
        if (mFriendList != null && mFriendList.size() > 0) {
            mFriendList.clear();
            isReloadList = true;
        }
        mFriendList = friendsList;
        if (mFriendList != null && mFriendList.size() > 0) {
            handleFriendDataForSort();
            mNoFriends.setVisibility(View.GONE);
        } else {
            mNoFriends.setVisibility(View.VISIBLE);
        }

        // 根据a-z进行排序源数据
        Collections.sort(mFriendList, mPinyinComparator);
        if (isReloadList) {
            mSidBar.setVisibility(View.VISIBLE);
            mFriendListAdapter.updateListView(mFriendList);
        } else {
            mSidBar.setVisibility(View.VISIBLE);
            mFriendListAdapter = new FriendListAdapter(getActivity(), mFriendList);

            mListView.setAdapter(mFriendListAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mListView.getHeaderViewsCount() > 0) {
                        startFriendDetailsPage(mFriendList.get(position - 1));
                    } else {
                        startFriendDetailsPage(mFilteredFriendList.get(position));
                    }
                }
            });


            mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    Friend bean = mFriendList.get(position - 1);
                    startFriendDetailsPage(bean);
                    return true;
                }
            });
//            mSearchEditText.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
//                    filterData(s.toString());
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    if (s.length() != 0) {
//                        if (mListView.getHeaderViewsCount() > 0) {
//                            mListView.removeHeaderView(mHeadView);
//                        }
//                    } else {
//                        if (mListView.getHeaderViewsCount() == 0) {
//                            mListView.addHeaderView(mHeadView);
//                        }
//                    }
//                }
//            });
        }
    }

    private void updatePersonalUI() {
        SharedPreferences sp = SealAppContext.getInstance().getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        mId = sp.getString(SealConst.SEALTALK_LOGIN_ID, "");
        mCacheName = sp.getString(SealConst.SEALTALK_LOGIN_NAME, "");
        final String header = sp.getString(SealConst.SEALTALK_LOGING_PORTRAIT, "");
        mNameTextView.setText(mCacheName);
        if (!TextUtils.isEmpty(mId)) {
            UserInfo userInfo = new UserInfo(mId, mCacheName, Uri.parse(header));
            String portraitUri = SealUserInfoManager.getInstance().getPortraitUri(userInfo);
            ImageLoader.getInstance().displayImage(portraitUri, mSelectableRoundedImageView, AppApplication.getOptions());
        }
    }

    private void handleFriendDataForSort() {
        for (Friend friend : mFriendList) {
            if (friend.isExitsDisplayName()) {
                String letters = replaceFirstCharacterWithUppercase(friend.getDisplayNameSpelling());
                friend.setLetters(letters);
            } else {
                String letters = replaceFirstCharacterWithUppercase(friend.getNameSpelling());
                friend.setLetters(letters);
            }
        }
    }

    private String replaceFirstCharacterWithUppercase(String spelling) {
        if (!TextUtils.isEmpty(spelling)) {
            char first = spelling.charAt(0);
            char newFirst = first;
            if (first >= 'a' && first <= 'z') {
                newFirst -= 32;
            }
            return spelling.replaceFirst(String.valueOf(first), String.valueOf(newFirst));
        } else {
            return "#";
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




}


