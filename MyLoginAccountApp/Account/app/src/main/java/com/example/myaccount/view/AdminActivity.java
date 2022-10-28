package com.example.myaccount.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myaccount.IMyUser;
import com.example.myaccount.R;
import com.example.myaccount.adapter.UserListAdapter;
import com.example.myaccount.bean.UserListBean;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityAdminBinding;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.viewmodel.AdminViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding activityAdminBinding;
    private AdminViewModel adminViewModel;
    private List<UserListBean> userLists;
    private UserListAdapter userListAdapter;
    private String[] mString;
    private MyDialog myDialog;
    private IMyUser iMyUser;
    private ServiceConnection connection;
    private int listCount;
    private Thread thread;
    private String mid, account, username, password;

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

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                Log.i(Constant.TAG, "AdminActivity onServiceConnected");
                iMyUser = IMyUser.Stub.asInterface(iBinder);
                try {
                    initAdapter();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(Constant.TAG, "AdminActivity onServiceDisconnected");
            }
        };
        activityAdminBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminActivity.this, AdminLoginActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        activityAdminBinding.deleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Constant.TAG, "AdminActivity deleteall onClick getItemCount = " + userListAdapter.getItemCount());
                if (myDialog == null) {
                    myDialog = new MyDialog(AdminActivity.this);
                    myDialog.setsMessage("确定删除全部用户吗？")
                            .setsCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    myDialog.dismiss();
                                }
                            }).setsConfirm(getResources().getString(R.string.confirm), new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        int id = iMyUser.mDeleteAllUser();
                                        Log.i(Constant.TAG, "AdminActivity deleteall onClick id = " + id);
                                        initAdapter();
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                    myDialog.dismiss();
                                }
                            }).show();
                } else {
                    myDialog.show();
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
                    id = iMyUser.mDeleteUser(activityAdminBinding.mId.getText().toString());
                    activityAdminBinding.mId.setText(null);
                    initAdapter();
                    Log.i(Constant.TAG, "AdminActivity delete onClick id = " + id);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (id > 0) {
                    show("删除成功！");
                } else {
                    show("删除失败！");
                }
            }
        });
        Intent intent = new Intent();
        intent.setAction("com.example.service.action");
        intent.setPackage("com.example.myaccount");
        bindService(intent,connection,BIND_AUTO_CREATE);
//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(Constant.TAG, "AdminActivity new Thread run");
//                try {
//                    initAdapter();
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
    }

    public void initAdapter() throws RemoteException {
        Log.i(Constant.TAG, "AdminActivity initAdapter");
        userLists =new ArrayList<>();
        userListAdapter = new UserListAdapter(this, userLists);
        activityAdminBinding.userList.setLayoutManager(new GridLayoutManager(this,1)); // 一列
        activityAdminBinding.userList.setAdapter(userListAdapter);
        updateUserList(userLists, userListAdapter);
        listCount = iMyUser.getListCount();
        activityAdminBinding.listTitle.setText(String.format(getResources().getString(R.string.userList), listCount));
        adminViewModel.setmDeleteAllBbtEnableStatus(userListAdapter.getItemCount() > 0);
    }

    public void updateUserList(List<UserListBean> list, UserListAdapter adapter) throws RemoteException {
        if (iMyUser.isNoUser()) {
            Log.i(Constant.TAG, "AdminActivity updateUserList isNoUser true ");
            return;
        } else {
            mString = new String[4];
            listCount = iMyUser.getListCount();
            Log.i(Constant.TAG, "AdminActivity updateUserList listCount = " + listCount);
            list.clear();
            iMyUser.toFirst();
            try {
                for (int i = 0; i < listCount; i++) {
                    mString = iMyUser.mQurey();
                    mid = mString[0];
                    username = mString[1];
                    account = mString[2];
                    password = mString[3];
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
//        resolver = getContentResolver();
//        Uri uri = Uri.parse("content://com.example.myaccount/users");
//        cursor = resolver.query(uri, null, null, null, null, null);
//        while (cursor.moveToNext()) {  //循环读取数据
//            @SuppressLint("Range") String mid = cursor.getString(cursor.getColumnIndex("_id"));
//            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
//            @SuppressLint("Range") String account = cursor.getString(cursor.getColumnIndex("account"));
//            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
//
//            UserListBean bean = new UserListBean();
//            bean.setmId(mid);
//            bean.setUserName(username);
//            bean.setAccount(account);
//            bean.setPassWord(password);
//
//            Log.i(Constant.TAG, "AdminActivity updateUserList " + mid + " " + username + " " + account + " " + password);
//            list.add(bean);
//        }
//        adapter.notifyDataSetChanged();
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
        if (myDialog != null) {
            myDialog.dismiss();
        }
        unbindService(connection);
    }
}
