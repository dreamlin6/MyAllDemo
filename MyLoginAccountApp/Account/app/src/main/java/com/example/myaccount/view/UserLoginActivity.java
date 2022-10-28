package com.example.myaccount.view;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
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
    private MyDialog myDialog;
    private String mid, account, pass, name, pass2;
    private String[] mString;
    private IMyUser iMyUser;
    private ServiceConnection connection;

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

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                Log.i(Constant.TAG, "UserLoginActivity onServiceConnected");
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
                Log.i(Constant.TAG, String.format("UserLoginActivity change onclick mid = %s pass2 = %s", mid, pass2));
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
                mString = new String[3];
                account = activityLoginBinding.editUser.getText().toString();
                pass = activityLoginBinding.editPass.getText().toString();
                if (account.equals("1") && pass.equals("1")) {
                    mDialogShow("当前登录用户为管理员用户，是否跳转到管理员界面！", 2);
                } else {
                    Log.i(Constant.TAG, "UserLoginActivity login onClick");
                    try {
                        if (iMyUser.isNoUser()) {
                            Log.i(Constant.TAG, "UserLoginActivity login onClick isNoUser Null");
                            mDialogShow("未注册任何用户！是否去注册新用户？", 1);
                        } else {
                            if (iMyUser.mLoginVerify(account, pass)) {
                                mString = iMyUser.mLogin(account, pass);
                                mid = mString[0];
                                name = mString[1];
                                pass2 = mString[2];
                                Log.i(Constant.TAG, String.format("UserLoginActivity Login mid = %s name = %s pass2 = %s", mid, name, pass2));
                                activityLoginBinding.info.setText(String.format(getResources().getString(R.string.welcome), name));
                                activityLoginBinding.info.setTextColor(Color.parseColor("#008000"));
                                userLoginViewModel.setmBtLoginedVisibleStatus(true);
                                userLoginViewModel.setmBtUnLoginedVisibleStatus(false);
                            } else {
                                activityLoginBinding.info.setText(getResources().getString(R.string.loginFail));
                                activityLoginBinding.info.setTextColor(Color.parseColor("#FF0000"));
                                userLoginViewModel.setmBtLoginedVisibleStatus(false);
                                userLoginViewModel.setmBtUnLoginedVisibleStatus(true);
                            }
                            userLoginViewModel.setmTvllVisibleStatus(true);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
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
                                    int id = 0;
                                    try {
                                        id = iMyUser.mDeleteUser(mid);
                                        userLoginViewModel.setmBtLoginedVisibleStatus(false);
                                        userLoginViewModel.setmBtUnLoginedVisibleStatus(true);
                                        userLoginViewModel.setmTvllVisibleStatus(false);
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                    Log.i(Constant.TAG, "UserLoginActivity resolver.delete id = " + id);
                                    if (id > 0) {
                                        Log.i(Constant.TAG, "UserLoginActivity delete logout OK!");
                                    }
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
        this.unbindService(connection);
    }
}
