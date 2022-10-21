package com.example.myaccount.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityRegisterBinding;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.viewmodel.UserRegisterViewModel;

import java.util.concurrent.locks.ReentrantReadWriteLock;


public class UserRegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding activityRegisterBinding;
    private UserRegisterViewModel userRegisterViewModel;
    private MyDialog myDialog;
    private ContentResolver resolver;
    private Cursor cursor;
    private ReentrantReadWriteLock readWriteLock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityRegisterBinding.setLifecycleOwner(this);

        readWriteLock = new ReentrantReadWriteLock();
        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        if (userRegisterViewModel == null) {
            userRegisterViewModel = new ViewModelProvider(this, instance).get(UserRegisterViewModel.class);  //创建viewmodel
        }
        activityRegisterBinding.setUserregistervm(userRegisterViewModel); //设置绑定 XML和Adapter

        activityRegisterBinding.editAccount.addTextChangedListener(watcher);
        activityRegisterBinding.editUser.addTextChangedListener(watcher);
        activityRegisterBinding.editPass.addTextChangedListener(watcher);
        activityRegisterBinding.editPass2.addTextChangedListener(watcher);

        userRegisterViewModel.getmRegisterDialogCtrl().observe(this, registerObserver);

        activityRegisterBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserRegisterActivity.this, AdminLoginActivity.class);
                startActivity(intent1);
            }
        });
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
            userRegisterViewModel.setmRegisterBtnEnable(activityRegisterBinding.editAccount.getText().length() > 0 &&
                    activityRegisterBinding.editUser.getText().length() > 0 &&
                    activityRegisterBinding.editPass.getText().length() > 0 &&
                    activityRegisterBinding.editPass2.getText().length() > 0);
        }
    };

    private Observer<Boolean> registerObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean isRegister) {
            Log.i(Constant.TAG,"UserRegisterActivity onChanged isRegister = " + isRegister);
            if (isRegister) {
                String username = activityRegisterBinding.editUser.getText().toString();
                String account = activityRegisterBinding.editAccount.getText().toString();
                String password = activityRegisterBinding.editPass.getText().toString();
                String password2 = activityRegisterBinding.editPass2.getText().toString();
                if (mExistUser(username)) {
                    Toast.makeText(UserRegisterActivity.this, "此用户名已存在! 请重新输入!", Toast.LENGTH_SHORT).show();
                    activityRegisterBinding.editUser.setText(null);
                    Log.i(Constant.TAG, "UserRegisterActivity onChanged username repeat");
                } else {
                    if (password.equals(password2)) {
                        mInsertUser(username, account, password);
                        if (myDialog == null) {
                            myDialog = new MyDialog(UserRegisterActivity.this);
                            myDialog.setsMessage("注册成功!确认跳转到登录吗?")
                                    .setsCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            myDialog.dismiss();
                                            activityRegisterBinding.editUser.setText(null);
                                            activityRegisterBinding.editAccount.setText(null);
                                            activityRegisterBinding.editPass.setText(null);
                                            activityRegisterBinding.editPass2.setText(null);
                                        }
                                    }).setsConfirm(getResources().getString(R.string.confirm), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(UserRegisterActivity.this, UserLoginActivity.class);
                                            startActivity(intent);
                                        }
                                    }).show();
                        } else {
                            myDialog.show();
                        }
                    } else {
                        Toast.makeText(UserRegisterActivity.this, "两次密码输入不一致! 请正确输入!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

    public void mInsertUser(String username, String account, String password) {
        readWriteLock.writeLock().lock();
        Uri uri = Uri.parse("content://com.example.myaccount/users");
        resolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("account", account);
        values.put("password", password);
        resolver.insert(uri, values);
        readWriteLock.writeLock().unlock();
        Log.i(Constant.TAG, "UserRegisterActivity reselover insert " + uri + values);
    }

    public boolean mExistUser(String name) {
        Uri uri = Uri.parse("content://com.example.myaccount/users");
        boolean bool = false;
        resolver = getContentResolver();
        cursor = resolver.query(uri, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            cursor.moveToPrevious();
            for (int i = 0; i < cursor.getCount(); i++) {  //循环读取数据
                cursor.moveToNext();
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
                Log.i(Constant.TAG, "UserRegisterActivity mExistUser username = " + username);
                if (name.equals(username)) {
                    bool = true;
                    break;
                } else {
                    bool = false;
                }
            }
        }
        Log.i(Constant.TAG, "UserRegisterActivity mQueryUser bool = " + bool);
        return bool;
    }

    @Override
    protected void onDestroy() {
        if (myDialog != null) {
            myDialog.dismiss();
        }
        super.onDestroy();
    }
}
