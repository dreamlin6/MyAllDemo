package com.example.myaccount.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaccount.R;
import com.example.myaccount.adapter.UserListAdapter;
import com.example.myaccount.bean.UserListBean;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityAdminBinding;
import com.example.myaccount.viewmodel.AdminViewModel;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding activityAdminBinding;
    private AdminViewModel adminViewModel;
    private List<UserListBean> userLists;
    private UserListAdapter userListAdapter;
    private ContentResolver resolver;

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
        resolver = getContentResolver();
        Uri uri = Uri.parse("content://com.example.myaccount/users");
        Cursor cursor = resolver.query(uri, null, null, null, null);
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
    }

}
