package com.example.myaccount.register;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.myaccount.MyServiceManager;
import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityRegisterBinding;
import com.example.myaccount.login.UserLoginActivity;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.viewmodel.UserRegisterViewModel;


public class UserRegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding activityRegisterBinding;
    private UserRegisterViewModel userRegisterViewModel;
    private MyDialog dialog;
    private MyServiceManager serviceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityRegisterBinding.setLifecycleOwner(this);
        getSupportActionBar().setTitle("注册");

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
//                Intent intent1 = new Intent(UserRegisterActivity.this, AdminLoginActivity.class);
//                startActivity(intent1);
                finish();
            }
        });
        if (serviceManager == null) {
            serviceManager = new MyServiceManager(this);
        }
        serviceManager.bindService();
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
                try {
                    if (serviceManager.isExistUser(account)) {
                        Toast.makeText(UserRegisterActivity.this, "此账号已存在! 请重新输入!", Toast.LENGTH_SHORT).show();
                        activityRegisterBinding.editUser.setText(null);
                        Log.i(Constant.TAG, "UserRegisterActivity onChanged account repeat");
                    } else {
                        if (password.equals(password2)) {
                            try {
                                serviceManager.onRegister(username, account, password);
                                if (dialog == null) {
                                    dialog = new MyDialog(UserRegisterActivity.this);
                                    dialog.setsMessage("注册成功!确认跳转到登录吗?")
                                            .setsCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    dialog.dismiss();
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
                                    dialog.show();
                                }
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(UserRegisterActivity.this, "两次密码输入不一致! 请重新输入!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        if (dialog != null) {
            dialog.dismiss();
        }
        super.onDestroy();
        serviceManager.unBindService();
    }
}
