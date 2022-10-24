package com.example.myaccount.view;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.IMyUser;
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
    private IMyUser iMyUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner(this);
        getSupportActionBar().setTitle("登录");

        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        if (userLoginViewModel == null) {
            userLoginViewModel = new ViewModelProvider(this, instance).get(UserLoginViewModel.class);  //创建viewmodel
        }
        activityLoginBinding.setUserloginvm(userLoginViewModel); //设置绑定 XML和Adapter
        activityLoginBinding.editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());//输入框密码格式

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                iMyUser = IMyUser.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(Constant.TAG, "UserLoginActivity onServiceDisconnected");
            }
        };

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
        activityLoginBinding.quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginViewModel.setmBtLoginedVisibleStatus(false);
                userLoginViewModel.setmBtUnLoginedVisibleStatus(true);
                userLoginViewModel.setmTvllVisibleStatus(false);
                activityLoginBinding.editUser.setText(null);
                activityLoginBinding.editPass.setText(null);
            }
        });
        activityLoginBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogShow("注销后该用户将无法登录。确定注销当前用户吗？", 3);
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
                account = activityLoginBinding.editUser.getText().toString();
                pass = activityLoginBinding.editPass.getText().toString();
                if (account.equals("1") && pass.equals("1") ) {
                    mDialogShow("当前登录用户为管理员用户，是否跳转到管理员界面！", 2);
                } else {
                    Log.i(Constant.TAG, "UserLoginActivity login onClick");

                    Uri uri = Uri.parse("content://com.example.myaccount/users");
                    resolver = getContentResolver();
                    cursor = resolver.query(uri, null, null, null, null);
                    Log.i(Constant.TAG, "UserLoginActivity login onClick cursor.getCount() = " + cursor.getCount());
                    if (!cursor.moveToFirst()) {
                        Log.i(Constant.TAG, "UserLoginActivity login onClick cursor.moveToNext() isNull");
                        mDialogShow("未注册任何用户！是否去注册新用户？", 1);
                    } else {
                        cursor.moveToFirst(); //第一行
                        cursor.moveToPrevious(); //前一行
                        if (cursor != null) {
                            while (cursor.moveToNext()) {  //下一行 循环
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
                                    break;
                                }
                            }
                        } else {
                            Log.i(Constant.TAG, "UserLoginActivity cursor = null");
                        }
                    }
                }
            }
        });
        activityLoginBinding.editUser.addTextChangedListener(watcher);
        activityLoginBinding.editPass.addTextChangedListener(watcher);

        Intent intent = new Intent();
        intent.setAction("com.example.service.action");
        intent.setPackage("com.example.myaccount");
        bindService(intent,connection,BIND_AUTO_CREATE);
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

    public void mDialogShow(String str, int i) {
        if (myDialog == null) {
            myDialog = new MyDialog(UserLoginActivity.this);
            myDialog.setsMessage(str)
                    .setsCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            myDialog.dismiss();
                        }
                    }).setsConfirm(getResources().getString(R.string.confirm), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            switch (i) {
                                case 1:
                                    Intent intent1 = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
                                    startActivity(intent1);
                                    myDialog.dismiss();
                                    break;
                                case 2:
                                    Intent intent2 = new Intent(UserLoginActivity.this, AdminActivity.class);
                                    startActivity(intent2);
                                    myDialog.dismiss();
                                    break;
                                case 3:
                                    resolver = getContentResolver();
                                    Uri uri = Uri.parse("content://com.example.myaccount/users");
                                    int id = resolver.delete(uri, "_id = ?", new String[]{mid});
                                    Log.i(Constant.TAG, "UserLoginActivity resolver.delete id = " + id);
                                    if (id > 0) {
                                        Log.i(Constant.TAG, "UserLoginActivity delete logout OK!");
                                    }
                                    userLoginViewModel.setmBtLoginedVisibleStatus(false);
                                    userLoginViewModel.setmBtUnLoginedVisibleStatus(true);
                                    userLoginViewModel.setmTvllVisibleStatus(false);
                                    myDialog.dismiss();
                                    break;
                            }
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
