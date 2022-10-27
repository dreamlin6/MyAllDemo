package com.example.mylogin.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

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

import com.example.myaccount.IMyUser;
import com.example.mylogin.R;
import com.example.mylogin.databinding.ActivityMainBinding;
import com.example.mylogin.util.MyDialog;
import com.example.mylogin.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    private ActivityMainBinding mainBinding;
    private LoginViewModel loginViewModel;
    private IMyUser iMyUser;
    public ServiceConnection connection;
    private MyDialog myDialog;
    private String[] mString;
    private String mid, account, pass, name, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setLifecycleOwner(this);
        getSupportActionBar().setTitle("登录");

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                iMyUser = IMyUser.Stub.asInterface(iBinder);
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected");
            }
        };

        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        if (loginViewModel == null) {
            loginViewModel = new ViewModelProvider(this, instance).get(LoginViewModel.class);  //创建viewmodel
        }
        mainBinding.setLoginvm(loginViewModel); //设置绑定 XML和Adapter
        mainBinding.editUser.addTextChangedListener(watcher);
        mainBinding.editPass.addTextChangedListener(watcher);
        mainBinding.editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏密码显示

        mainBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });
        mainBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mString = new String[3];
                account = mainBinding.editUser.getText().toString();
                pass = mainBinding.editPass.getText().toString();
                Log.i(TAG, "login onClick");
                try {
                    if (iMyUser.isNoUser()) {
                        Log.i(TAG, "login onClick isNoUser True");
                        mDialogShow("未注册任何用户！是否去注册新用户？", 1);
                    } else {
                        if (iMyUser.mLoginVerify(account, pass)) {
                            mString = iMyUser.mLogin(account, pass);
                            mid = mString[0];
                            name = mString[1];
                            pass2 = mString[2];
                            Log.i(TAG, String.format("Login success mid = %s name = %s pass2 = %s", mid, name, pass2));
                            mainBinding.info1.setText(String.format(getResources().getString(R.string.welcome), name));
                            loginViewModel.mInfo.setValue("登录成功！");
                            mainBinding.info.setTextColor(Color.parseColor("#008000"));
                            loginViewModel.mIsLoginVisible.setValue(true);
                        } else {
                            loginViewModel.mInfo.setValue("登录失败！");
                            mainBinding.editPass.setText(null);
                            mainBinding.info.setTextColor(Color.parseColor("#FF0000"));
                            loginViewModel.mIsLoginVisible.setValue(false);
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        mainBinding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this, ChangeActivity.class);
                Log.i(TAG, String.format("change onclick mid = %s pass2 = %s", mid, pass2));
                intent2.putExtra("mId", mid);
                intent2.putExtra("mPass", pass2);
                startActivity(intent2);
            }
        });
        mainBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogShow("注销后该用户将无法登录。确定注销当前用户吗？", 2);
            }
        });
        mainBinding.quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.mInfo.setValue("未登录！");
                mainBinding.info.setTextColor(Color.parseColor("#ffa500"));
                loginViewModel.setmIsLoginVisible(false);
                mainBinding.editUser.setText(null);
                mainBinding.editPass.setText(null);
            }
        });

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
            loginViewModel.setmLoginBtEnable(mainBinding.editUser.getText().length() > 0 && mainBinding.editPass.getText().length() > 0);
        }
    };

    public void mDialogShow(String str, int i) {
        if (myDialog == null) {
            myDialog = new MyDialog(LoginActivity.this);
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
                                    Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                                    startActivity(intent1);
                                    myDialog.dismiss();
                                    break;
                                case 2:
                                    int id = 0;
                                    try {
                                        id = iMyUser.mDeleteUser(mid);
                                        loginViewModel.mIsLoginVisible.setValue(false);
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                    Log.i(TAG, "resolver.delete id = " + id);
                                    if (id > 0) {
                                        Log.i(TAG, "delete logout OK!");
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
        super.onDestroy();
        if (myDialog != null) {
            myDialog.dismiss();
        }
    }
}