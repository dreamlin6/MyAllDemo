package com.example.myaccount.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.myaccount.R;
import com.example.myaccount.adapter.UserListAdapter;
import com.example.myaccount.bean.UserListBean;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityAdminBinding;
import com.example.myaccount.util.MyObserver;
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
    private ContentObserver MyObserver;
    private Cursor cursor;

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

        activityAdminBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent1 = new Intent(AdminActivity.this, AdminLoginActivity.class);
//                startActivity(intent1);
                resolver = getContentResolver();
                Uri uri = Uri.parse("content://com.example.myaccount/users");
                ContentValues values = new ContentValues();
                int id = resolver.delete(uri, "_id = ?", new String[1]);
                if (id > 0) {
                    show("OK");
                } else {
                    show("Failed");
                }
            }
        });
        initAdapter();
    }

    public void initAdapter() {
        Log.i(Constant.TAG, "AdminActivity initAdapter");
        userLists =new ArrayList<>();
        userListAdapter = new UserListAdapter(this, userLists);
        activityAdminBinding.userList.setLayoutManager(new GridLayoutManager(this,1)); // 一列
        activityAdminBinding.userList.setAdapter(userListAdapter);
        updateUserList(userLists, userListAdapter);
    }

    public void updateUserList(List<UserListBean> list, UserListAdapter adapter) {
        list.clear();
        readWriteLock.readLock().lock();
        resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.example.myaccount/users");
//        resolver.registerContentObserver(uri, true, MyObserver);
        cursor = resolver.query(uri, new String[]{"_id", "username", "account", "password"}, null, null, null, null);
        while (cursor.moveToNext()) {  //循环读取数据
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String account = cursor.getString(cursor.getColumnIndex("account"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            UserListBean bean = new UserListBean();
            bean.setUserName(username);
            bean.setAccount(account);
            bean.setPassWord(password);

            Log.i(Constant.TAG, "AdminActivity updateUserList " + username + " " + account + " " + password);
            list.add(bean);
        }
        adapter.notifyDataSetChanged();
        readWriteLock.readLock().unlock();
    }

    public void show(String info){
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        resolver.unregisterContentObserver(MyObserver);
    }
}
