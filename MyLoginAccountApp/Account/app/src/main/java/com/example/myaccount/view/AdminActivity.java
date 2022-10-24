package com.example.myaccount.view;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.myaccount.MyService;
import com.example.myaccount.R;
import com.example.myaccount.adapter.UserListAdapter;
import com.example.myaccount.bean.UserListBean;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityAdminBinding;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.viewmodel.AdminViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding activityAdminBinding;
    private AdminViewModel adminViewModel;
    private List<UserListBean> userLists;
    private UserListAdapter userListAdapter;
    private ContentResolver resolver;
    private ReentrantReadWriteLock readWriteLock;
    private Cursor cursor;
    private MyDialog myDialog;
    private IMyUser iMyUser;

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
        readWriteLock = new ReentrantReadWriteLock();

        activityAdminBinding.mId.addTextChangedListener(watcher);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                iMyUser = IMyUser.Stub.asInterface(iBinder);
                try {
                    iMyUser.mDeleteAllUser();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        activityAdminBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminActivity.this, AdminLoginActivity.class);
                startActivity(intent1);
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
                                    resolver = getContentResolver();
                                    Uri uri = Uri.parse("content://com.example.myaccount/users");
                                    int id = resolver.delete(uri, null, null);
                                    Log.i(Constant.TAG, "AdminActivity deleteall onClick id = " + id);
                                    initAdapter();
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
                resolver = getContentResolver();
                Uri uri = Uri.parse("content://com.example.myaccount/users");
                int id = resolver.delete(uri, "_id = ?", new String[]{activityAdminBinding.mId.getText().toString()});
                activityAdminBinding.mId.setText(null);
                Log.i(Constant.TAG, "AdminActivity delete onClick id = " + id);
                if (id > 0) {
                    show("删除成功！");
                } else {
                    show("删除失败！");
                }
                initAdapter();
            }
        });
        initAdapter();
        Intent intent = new Intent();
        intent.setAction("com.example.service.action");
        intent.setPackage("com.example.myaccount");
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    public void initAdapter() {
        Log.i(Constant.TAG, "AdminActivity initAdapter");
        userLists =new ArrayList<>();
        userListAdapter = new UserListAdapter(this, userLists);
        activityAdminBinding.userList.setLayoutManager(new GridLayoutManager(this,1)); // 一列
        activityAdminBinding.userList.setAdapter(userListAdapter);
        updateUserList(userLists, userListAdapter);
        adminViewModel.setmDeleteAllBbtEnableStatus(userListAdapter.getItemCount() > 0);
    }

    public void updateUserList(List<UserListBean> list, UserListAdapter adapter) {
        list.clear();
        readWriteLock.readLock().lock();
        resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.example.myaccount/users");
        cursor = resolver.query(uri, new String[]{"_id", "username", "account", "password"}, null, null, null, null);
        while (cursor.moveToNext()) {  //循环读取数据
            @SuppressLint("Range") String mid = cursor.getString(cursor.getColumnIndex("_id"));
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String account = cursor.getString(cursor.getColumnIndex("account"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            UserListBean bean = new UserListBean();

            bean.setmId(mid);
            bean.setUserName(username);
            bean.setAccount(account);
            bean.setPassWord(password);

            Log.i(Constant.TAG, "AdminActivity updateUserList " + mid + " " + username + " " + account + " " + password);
            list.add(bean);
        }
        adapter.notifyDataSetChanged();
        readWriteLock.readLock().unlock();
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
        super.onDestroy();
        if (myDialog != null) {
            myDialog.dismiss();
        }
    }
}
