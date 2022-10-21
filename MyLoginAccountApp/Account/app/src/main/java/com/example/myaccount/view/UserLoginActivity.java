package com.example.myaccount.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityLoginBinding;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.viewmodel.UserLoginViewModel;

public class UserLoginActivity extends AppCompatActivity {

    private UserLoginViewModel userLoginViewModel;
    private ActivityLoginBinding activityLoginBinding;
    private ContentResolver resolver;
    private MyDialog myDialog;
    private Cursor cursor;
    private String mid, account, pass, account1, pass1, name, pass2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner(this);

        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        if (userLoginViewModel == null) {
            userLoginViewModel = new ViewModelProvider(this, instance).get(UserLoginViewModel.class);  //创建viewmodel
        }
        activityLoginBinding.setUserloginvm(userLoginViewModel); //设置绑定 XML和Adapter

        activityLoginBinding.editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        activityLoginBinding.tohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserLoginActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        activityLoginBinding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(UserLoginActivity.this, UserChangeActivity.class);
                intent2.putExtra("mId", mid);
                intent2.putExtra("mPass", pass2);
                startActivity(intent2);
            }
        });
        activityLoginBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginViewModel.setmBtLoginedVisibleStatus(false);
                userLoginViewModel.setmBtUnLoginedVisibleStatus(true);
                userLoginViewModel.setmTvllVisibleStatus(false);
                activityLoginBinding.editUser.setText(null);
                activityLoginBinding.editPass.setText(null);
            }
        });
        activityLoginBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
                startActivity(intent3);
            }
        });
        activityLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                Log.i(Constant.TAG, "UserLoginActivity login onClick");
                account = activityLoginBinding.editUser.getText().toString();
                pass = activityLoginBinding.editPass.getText().toString();

                Uri uri = Uri.parse("content://com.example.myaccount/users");
                resolver = getContentResolver();
                cursor = resolver.query(uri, null, null, null, null);
                Log.i(Constant.TAG, "UserLoginActivity login onClick cursor.getCount() = " + cursor.getCount());
                if (!cursor.moveToFirst()) {
                    Log.i(Constant.TAG, "UserLoginActivity login onClick cursor.moveToNext() isNull");
                    mDialogShow();
                } else {
                    cursor.moveToFirst();
                    cursor.moveToPrevious();
                    if (cursor != null) {
                        while (cursor.moveToNext()) {  //循环读取数据
                            account1 = cursor.getString(cursor.getColumnIndex("account"));
                            pass1 = cursor.getString(cursor.getColumnIndex("password"));
                            Log.i(Constant.TAG, String.format("UserLoginActivity cursor user1 = %s pass1 = %s", account1, pass1));
                            if (account.equals(account1) && pass.equals(pass1)) {
                                mid = cursor.getString(cursor.getColumnIndex("_id"));
                                name = cursor.getString(cursor.getColumnIndex("username"));
                                pass2 = cursor.getString(cursor.getColumnIndex("password"));
                                activityLoginBinding.info.setText(String.format(getResources().getString(R.string.welcome), name));
                                activityLoginBinding.info.setTextColor(Color.parseColor("#008000"));
                                userLoginViewModel.setmBtLoginedVisibleStatus(true);
                                userLoginViewModel.setmBtUnLoginedVisibleStatus(false);
                                userLoginViewModel.setmTvllVisibleStatus(true);
                                break;
                            } else {
                                activityLoginBinding.info.setText(getResources().getString(R.string.loginFail));
                                activityLoginBinding.info.setTextColor(Color.parseColor("#FF0000"));
                                userLoginViewModel.setmBtLoginedVisibleStatus(false);
                                userLoginViewModel.setmBtUnLoginedVisibleStatus(true);
                                userLoginViewModel.setmTvllVisibleStatus(true);
                            }
                        }
                    } else {
                        Log.i(Constant.TAG, "UserLoginActivity cursor = null");
                    }
                }
            }
        });
        activityLoginBinding.editUser.addTextChangedListener(watcher);
        activityLoginBinding.editPass.addTextChangedListener(watcher);
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
            userLoginViewModel.setmLoginEnableStatus(activityLoginBinding.editUser.getText().length() > 0 && activityLoginBinding.editPass.getText().length() > 0);
        }
    };

    public void mDialogShow() {
        if (myDialog == null) {
            myDialog = new MyDialog(UserLoginActivity.this);
            myDialog.setsMessage("未注册任何用户！是否去注册新用户？")
                    .setsCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            myDialog.dismiss();
                        }
                    }).setsConfirm(getResources().getString(R.string.confirm), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
                            startActivity(intent);
                            myDialog.dismiss();
                        }
                    }).show();
        } else {
            myDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        if (myDialog != null) {
            myDialog.dismiss();
        }
        super.onDestroy();
    }
}
