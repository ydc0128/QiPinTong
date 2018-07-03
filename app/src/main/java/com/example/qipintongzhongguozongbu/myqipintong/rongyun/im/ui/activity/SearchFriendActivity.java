package com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qipintongzhongguozongbu.myqipintong.R;
import com.example.qipintongzhongguozongbu.myqipintong.activity.AppApplication;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealAppContext;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealConst;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.SealUserInfoManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.db.Friend;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.network.async.AsyncTaskManager;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.network.http.HttpException;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.response.FriendInvitationResponse;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.response.GetUserInfoByPhoneResponse;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.AMUtils;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.CommonUtils;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.utils.NToast;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.widget.DialogWithYesOrNoUtils;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.widget.LoadDialog;
import com.example.qipintongzhongguozongbu.myqipintong.rongyun.im.server.widget.SelectableRoundedImageView;
import com.example.qipintongzhongguozongbu.myqipintong.txl.AddTXLActicity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import io.rong.imageloader.core.ImageLoader;
import io.rong.imlib.model.UserInfo;

import static com.darsh.multipleimageselect.helpers.Constants.REQUEST_CODE;


public class SearchFriendActivity extends BaseAActivity {
    private static final int LOCATION_REQUST = 0;//定位权限的标识码
    private static final int CLICK_CONVERSATION_USER_PORTRAIT = 1;
    private static final int SEARCH_PHONE = 10;
    private static final int ADD_FRIEND = 11;
    private EditText mEtSearch;
    private LinearLayout searchItem;
    private TextView searchName;
    private SelectableRoundedImageView searchImage;
    private String mPhone;
    private String addFriendMessage;
    private String mFriendId;
    private Friend mFriend;
    private LinearLayout mErWeiMa,mAddressBooks;
    private static int PERMISSION_TYPE; //权限的类型 用于区别是定位还是相机权限的请求
    private static final int CAMERA_REQUST = 1;//相机权限的标识码
    private static final int PERMISSION_CODE = 100;
    private int isLocation = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle((R.string.search_friend));
        mErWeiMa=(LinearLayout)findViewById(R.id.search_erweima);
        mAddressBooks=(LinearLayout)findViewById(R.id.search_txl);
        mEtSearch = (EditText) findViewById(R.id.search_edit);
        searchItem = (LinearLayout) findViewById(R.id.search_result);
        searchName = (TextView) findViewById(R.id.search_name);
        searchImage = (SelectableRoundedImageView) findViewById(R.id.search_header);
        mAddressBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = ContactsContract.Contacts.CONTENT_URI;
//                Intent intent = new Intent(Intent.ACTION_PICK,uri);
//                startActivityForResult(intent, CONTACT_SUBACTIVITY);//打开通讯录页面
                startActivity(new Intent(SearchFriendActivity.this, AddTXLActicity.class));

            }
        });
        mErWeiMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PERMISSION_TYPE = CAMERA_REQUST;

                setCameraPermission();
            }
        });
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    mPhone = s.toString().trim();
                    if (!AMUtils.isMobile(mPhone)) {
                        NToast.shortToast(mContext, "非法手机号");
                        return;
                    }
                    hintKbTwo();
                    LoadDialog.show(mContext);
                    request(SEARCH_PHONE, true);
                } else {
                    searchItem.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        switch (requestCode) {
            case SEARCH_PHONE:
                return action.getUserInfoFromPhone("86", mPhone);
            case ADD_FRIEND:
                return action.sendFriendInvitation(mFriendId, addFriendMessage);
        }
        return super.doInBackground(requestCode, id);
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
        if (result != null) {
            switch (requestCode) {
                case SEARCH_PHONE:
                    final GetUserInfoByPhoneResponse userInfoByPhoneResponse = (GetUserInfoByPhoneResponse) result;
                    if (userInfoByPhoneResponse.getCode() == 200) {
                        LoadDialog.dismiss(mContext);
                        NToast.shortToast(mContext, "success");
                        mFriendId = userInfoByPhoneResponse.getResult().getId();
                        searchItem.setVisibility(View.VISIBLE);
                        String portraitUri = null;
                        if (userInfoByPhoneResponse.getResult() != null) {
                            GetUserInfoByPhoneResponse.ResultEntity userInfoByPhoneResponseResult = userInfoByPhoneResponse.getResult();
                            UserInfo userInfo = new UserInfo(userInfoByPhoneResponseResult.getId(),
                                    userInfoByPhoneResponseResult.getNickname(),
                                    Uri.parse(userInfoByPhoneResponseResult.getPortraitUri()));
                            portraitUri = SealUserInfoManager.getInstance().getPortraitUri(userInfo);
                        }
                        ImageLoader.getInstance().displayImage(portraitUri, searchImage, AppApplication.getOptions());
                        searchName.setText(userInfoByPhoneResponse.getResult().getNickname());
                        searchItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isFriendOrSelf(mFriendId)) {
                                    Intent intent = new Intent(SearchFriendActivity.this, UserDetailActivity.class);
                                    intent.putExtra("friend", mFriend);
                                    intent.putExtra("type", CLICK_CONVERSATION_USER_PORTRAIT);
                                    startActivity(intent);
                                    SealAppContext.getInstance().pushActivity(SearchFriendActivity.this);
                                    return;
                                }
                                DialogWithYesOrNoUtils.getInstance().showEditDialog(mContext, getString(R.string.add_text), getString(R.string.add_friend), new DialogWithYesOrNoUtils.DialogCallBack() {
                                    @Override
                                    public void executeEvent() {

                                    }

                                    @Override
                                    public void updatePassword(String oldPassword, String newPassword) {

                                    }

                                    @Override
                                    public void executeEditEvent(String editText) {
                                        if (!CommonUtils.isNetworkConnected(mContext)) {
                                            NToast.shortToast(mContext, R.string.network_not_available);
                                            return;
                                        }
                                        addFriendMessage = editText;
                                        if (TextUtils.isEmpty(editText)) {
                                            addFriendMessage = "我是" + getSharedPreferences("config", MODE_PRIVATE).getString(SealConst.SEALTALK_LOGIN_NAME, "");
                                        }
                                        if (!TextUtils.isEmpty(mFriendId)) {
                                            LoadDialog.show(mContext);
                                            request(ADD_FRIEND);
                                        } else {
                                            NToast.shortToast(mContext, "id is null");
                                        }
                                    }
                                });
                            }
                        });

                    }
                    break;
                case ADD_FRIEND:
                    FriendInvitationResponse fres = (FriendInvitationResponse) result;
                    if (fres.getCode() == 200) {
                        NToast.shortToast(mContext, getString(R.string.request_success));
                        LoadDialog.dismiss(mContext);
                    } else {
                        NToast.shortToast(mContext, "请求失败 错误码:" + fres.getCode());
                        LoadDialog.dismiss(mContext);
                    }
                    break;
            }
        }
    }

    @Override
    public void onFailure(int requestCode, int state, Object result) {
        switch (requestCode) {
            case ADD_FRIEND:
                NToast.shortToast(mContext, "你们已经是好友");
                LoadDialog.dismiss(mContext);
                break;
            case SEARCH_PHONE:
                if (state == AsyncTaskManager.HTTP_ERROR_CODE || state == AsyncTaskManager.HTTP_NULL_CODE) {
                    super.onFailure(requestCode, state, result);
                } else {
                    NToast.shortToast(mContext, "用户不存在");
                }
                LoadDialog.dismiss(mContext);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        hintKbTwo();
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    private boolean isFriendOrSelf(String id) {
        String inputPhoneNumber = mEtSearch.getText().toString().trim();
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        String selfPhoneNumber = sp.getString(SealConst.SEALTALK_LOGING_PHONE, "");
        if (inputPhoneNumber != null) {
            if (inputPhoneNumber.equals(selfPhoneNumber)) {
                mFriend = new Friend(sp.getString(SealConst.SEALTALK_LOGIN_ID, ""),
                        sp.getString(SealConst.SEALTALK_LOGIN_NAME, ""),
                        Uri.parse(sp.getString(SealConst.SEALTALK_LOGING_PORTRAIT, "")));
                return true;
            } else {
                mFriend = SealUserInfoManager.getInstance().getFriendByID(id);
                if (mFriend != null) {
                    return true;
                }
            }
        }
        return false;
    }
    private void setCameraPermission() {

        if (AndPermission.hasPermission(this, Manifest.permission.CAMERA)) {//判断是否有相机权限

            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);

        } else {//申请相机权限

            AndPermission.with(this)
                    .requestCode(PERMISSION_CODE)
                    .permission(Manifest.permission.CAMERA)
                    .rationale(new MyRationaleListener())
                    // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                    .send();
        }
    }

    class MyRationaleListener implements RationaleListener {

        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            // 自定义对话框。
            new AlertDialog.Builder(SearchFriendActivity.this)

                    .setTitle("友情提示")

                    .setMessage(R.string.message_permission_camera)

                    .setPositiveButton(R.string.btn_dialog_yes_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();
                        }
                    })

                    .setNegativeButton(R.string.btn_dialog_no_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel();
                        }
                    }).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，第一个参数是当前Acitivity/Fragment，回调方法写在当前Activity/Framgent。
        AndPermission.onRequestPermissionsResult(this, PERMISSION_CODE, permissions, grantResults);
    }
    @PermissionNo(PERMISSION_CODE)//获取权限失败
    private void getMultiNo(List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {

            AndPermission.defaultSettingDialog(this, PERMISSION_CODE).show();

        }
    }
    class MyLocationListener implements RationaleListener {

        @Override
        public void showRequestPermissionRationale(int requestCode, final Rationale rationale) {
            // 自定义对话框。
            new AlertDialog.Builder(SearchFriendActivity.this)

                    .setTitle("友情提示")

                    .setMessage(R.string.message_permission_location)

                    .setPositiveButton(R.string.btn_dialog_yes_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.resume();
                        }
                    })

                    .setNegativeButton(R.string.btn_dialog_no_permission, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            rationale.cancel();
                        }
                    }).show();
        }
    }


    /**
     * function   : 二维码扫描返回结果
     * Author     : 感觉自己懵懵哒
     * E-mail     : anber1229423614@163.com
     * Date       : 2017/4/1  上午10:03
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
