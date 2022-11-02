package com.example.mylogin.view;

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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.IMyUser;
import com.example.mylogin.R;
import com.example.mylogin.databinding.ActivityRegisterBinding;
import com.example.mylogin.util.MyDialog;
import com.example.mylogin.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = "RegisterActivity";
    private ActivityRegisterBinding registerBinding;
    private RegisterViewModel registerViewModel;
    private ServiceConnection connection;
    private IMyUser iMyUser;
    private MyDialog myDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerBinding.setLifecycleOwner(this);
        getSupportActionBar().setTitle("注册");

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
        if (registerViewModel == null) {
            registerViewModel = new ViewModelProvider(this, instance).get(RegisterViewModel.class);  //创建viewmodel
        }
        registerBinding.setRegistervm(registerViewModel); //设置绑定 XML和Adapter

        registerBinding.editAccount.addTextChangedListener(watcher);
        registerBinding.editUser.addTextChangedListener(watcher);
        registerBinding.editPass.addTextChangedListener(watcher);
        registerBinding.editPass2.addTextChangedListener(watcher);

        registerViewModel.getmRegisterDialogCtrl().observe(this, registerObserver);

        registerBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        Intent intent = new Intent();
        intent.setAction("com.example.service.action");
        intent.setPackage("com.example.myaccount");
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    private Observer<Boolean> registerObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean isRegister) {
            Log.i(TAG,"onChanged isRegister = " + isRegister);
            if (isRegister) {
                String username = registerBinding.editUser.getText().toString();
                String account = registerBinding.editAccount.getText().toString();
                String password = registerBinding.editPass.getText().toString();
                String password2 = registerBinding.editPass2.getText().toString();
                try {
                    if (iMyUser.isExistUser(username)) {
                        showToast("此用户名已存在! 请重新输入!");
                        registerBinding.editUser.setText(null);
                        Log.i(TAG, "onChanged username repeat");
                    } else {
                        if (password.equals(password2)) {
                            try {
                                iMyUser.mRegister(username, account, password);
                                if (myDialog == null) {
                                    myDialog = new MyDialog(RegisterActivity.this);
                                    myDialog.setsMessage("注册成功!确认跳转到登录吗?")
                                            .setsCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    myDialog.dismiss();
                                                    registerBinding.editUser.setText(null);
                                                    registerBinding.editAccount.setText(null);
                                                    registerBinding.editPass.setText(null);
                                                    registerBinding.editPass2.setText(null);
                                                }
                                            }).setsConfirm(getResources().getString(R.string.confirm), new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent2 = new Intent(RegisterActivity.this, LoginActivity.class);
                                                    startActivity(intent2);
                                                }
                                            }).show();
                                } else {
                                    myDialog.show();
                                }
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        } else {
                            showToast("两次密码输入不一致! 请重新输入!");
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            registerViewModel.setmRegisterBtnEnable(registerBinding.editAccount.getText().length() > 0 &&
                    registerBinding.editUser.getText().length() > 0 &&
                    registerBinding.editPass.getText().length() > 0 &&
                    registerBinding.editPass2.getText().length() > 0);
        }
    };

    public void showToast(String info) {
        Toast.makeText(RegisterActivity.this, info, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myDialog != null) {
            myDialog.dismiss();
        }
        unbindService(connection);
    }
}
