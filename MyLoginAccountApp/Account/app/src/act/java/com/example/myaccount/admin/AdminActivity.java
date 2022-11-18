package com.example.myaccount.admin;

import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myaccount.MyServiceManager;
import com.example.myaccount.R;
import com.example.myaccount.adapter.UserListAdapter;
import com.example.myaccount.bean.UserListBean;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityAdminBinding;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.login.UserLoginActivity;
import com.example.myaccount.viewmodel.AdminViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding activityAdminBinding;
    private AdminViewModel adminViewModel;
    private List<UserListBean> userLists;
    private UserListAdapter userListAdapter;
    private String[] UserInfo;
    private MyDialog dialog;
    private int listCount;
    private String mid, account, username, password;
    private final int INIT_ADAPTER = 1;
    private final int BIND_INIT = 2;
    private MyServiceManager serviceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminBinding = DataBindingUtil.setContentView(this, R.layout.activity_admin);
        activityAdminBinding.setLifecycleOwner(this);

        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        adminViewModel = new ViewModelProvider(this, instance).get(AdminViewModel.class);  //创建viewmodel
        activityAdminBinding.setAdminvm(adminViewModel); //设置绑定 XML和Adapter

        activityAdminBinding.mId.addTextChangedListener(watcher);

        Uri uri = Uri.parse("content://com.example.myaccount/users");
        this.getContentResolver().registerContentObserver(uri, true, contentObserver);

        activityAdminBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = getIntent().getIntExtra("page", 1);
                switch (page) {
                    case 1:
                        Intent intent1 = new Intent(AdminActivity.this, AdminLoginActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case 2:
                        Intent intent2 = new Intent(AdminActivity.this, UserLoginActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
            }
        });
        activityAdminBinding.deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Constant.TAG, "AdminActivity deleteall onClick getItemCount = " + userListAdapter.getItemCount());
                if (dialog == null) {
                    dialog = new MyDialog(AdminActivity.this);
                    dialog.setsMessage("确定删除全部用户吗？")
                            .setsCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            }).setsConfirm(getResources().getString(R.string.confirm), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        int id = serviceManager.onDeleteAllUser();
                                        Log.i(Constant.TAG, "AdminActivity deleteall onClick id = " + id);
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                    dialog.dismiss();
                                }
                            }).show();
                } else {
                    dialog.show();
                }
            }
        });
        activityAdminBinding.edit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                adminViewModel.setmDeleteEnableStatus(isChecked);
            }
        });
        activityAdminBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 0;
                try {
                    id = serviceManager.onDeleteUser(activityAdminBinding.mId.getText().toString());
                    activityAdminBinding.mId.setText(null);
                    serviceManager.onUpdateQuery();
                    Log.i(Constant.TAG, "AdminActivity delete onClick id = " + id);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                show(id > 0 ? "删除成功！" : "删除失败！");
            }
        });
        if (serviceManager == null) {
            serviceManager = new MyServiceManager(this);
        }
        serviceManager.bindService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //等待取得mService
                if(null != serviceManager){
                    //  绑定服务成功了
                    handler.sendEmptyMessage(BIND_INIT);
                }
            }
        }).start();
    }

    private Handler handler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case INIT_ADAPTER:
                    Log.i(Constant.TAG, "AdminActivity handleMessage msg INIT_ADAPTER!");
                    try {
                        initAdapter();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case BIND_INIT:
                    Log.i(Constant.TAG, "AdminActivity handleMessage msg BIND_INIT!");
                    try {
                        try {
                            serviceManager.onUpdateQuery();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        initAdapter();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return false;
        }
    });
    private ContentObserver contentObserver = new ContentObserver(handler) { //数据实时刷新
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Log.i(Constant.TAG, "AdminActivity contentObserver onChange!");
            handler.sendEmptyMessage(INIT_ADAPTER);
        }
    };

    public void initAdapter() throws RemoteException {
        Log.i(Constant.TAG, "AdminActivity initAdapter");
        userLists =new ArrayList<>();
        userListAdapter = new UserListAdapter(this, userLists);
        activityAdminBinding.userList.setLayoutManager(new GridLayoutManager(this,1)); // 一列
        activityAdminBinding.userList.setAdapter(userListAdapter);
        listCount = serviceManager.getCount();
        updateUserList(userLists, userListAdapter);
        activityAdminBinding.listTitle.setText(String.format(getResources().getString(R.string.userList), listCount));
        adminViewModel.setmDeleteAllBbtEnableStatus(userListAdapter.getItemCount() > 0);
    }

    public void updateUserList(List<UserListBean> list, UserListAdapter adapter) throws RemoteException {
        if (serviceManager.isNoUser()) {
            Log.i(Constant.TAG, "AdminActivity updateUserList isNoUser true ");
            return;
        } else {
            UserInfo = new String[4];
            listCount = serviceManager.getCount();
            Log.i(Constant.TAG, "AdminActivity updateUserList listCount = " + listCount);
            list.clear();
            serviceManager.toFirst();
            try {
                for (int i = 0; i < listCount; i++) {
                    UserInfo = serviceManager.onQuery();
                    mid = UserInfo[0];
                    username = UserInfo[1];
                    account = UserInfo[2];
                    password = UserInfo[3];
                    UserListBean bean = new UserListBean();
                    bean.setmId(mid);
                    bean.setUserName(username);
                    bean.setAccount(account);
                    bean.setPassWord(password);
                    Log.i(Constant.TAG, "AdminActivity updateUserList " + mid + " " + username + " " + account + " " + password);
                    list.add(bean);
                }
                adapter.notifyDataSetChanged();
            } catch (RemoteException e) {
                Log.i(Constant.TAG, "AdminActivity updateUserList mQurey ERROR!");
                e.printStackTrace();
            }
        }
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            adminViewModel.setmDeleteBbtEnableStatus(s.length() > 0);
        }
    };

    public void show(String info){
        Toast.makeText(AdminActivity.this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        Log.i(Constant.TAG, "AdminActivity onDestroy");
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
        serviceManager.unBindService();
        getContentResolver().unregisterContentObserver(contentObserver);
    }
}
