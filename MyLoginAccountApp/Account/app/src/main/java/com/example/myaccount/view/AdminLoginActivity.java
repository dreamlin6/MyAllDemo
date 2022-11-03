package com.example.myaccount.view;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.IMyUser;
import com.example.myaccount.MyServiceManager;
import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityAdminloginBinding;
import com.example.myaccount.viewmodel.AdminLoginViewModel;

public class AdminLoginActivity extends AppCompatActivity {

    private final String WAIT_LOGIN = "未登录";
    private ActivityAdminloginBinding activityAdminloginBinding;
    private AdminLoginViewModel adminLoginViewModel;
    private MyServiceManager serviceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminloginBinding = DataBindingUtil.setContentView(this, R.layout.activity_adminlogin);
        activityAdminloginBinding.setLifecycleOwner(this);

        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        if (adminLoginViewModel == null) {
            adminLoginViewModel = new ViewModelProvider(this, instance).get(AdminLoginViewModel.class);  //创建viewmodel
        }
        activityAdminloginBinding.setAdminloginvm(adminLoginViewModel); //设置绑定 XML和Adapter

        activityAdminloginBinding.adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminLoginViewModel.mAdminLogin(activityAdminloginBinding.editAdminUser.getText().toString(),
                        activityAdminloginBinding.editAdminPass.getText().toString());
                activityAdminloginBinding.editAdminUser.setText(null);
                activityAdminloginBinding.editAdminPass.setText(null);
            }
        });
        activityAdminloginBinding.tohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminLoginActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        activityAdminloginBinding.editAdminUser.addTextChangedListener(watcher); //监听输入框变化
        activityAdminloginBinding.editAdminPass.addTextChangedListener(watcher);
        activityAdminloginBinding.editAdminPass.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏密码显示
        activityAdminloginBinding.loginTips.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(WAIT_LOGIN)) {
                    Log.i(Constant.TAG,"AdminLoginActivity afterTextChanged s = " + s);
                    activityAdminloginBinding.loginTips.setTextColor(Color.parseColor("#ffa500"));
                }
            }
        });
        adminLoginViewModel.getmAdminLoginStatus().observe(this, loginObserve);
        if (serviceManager == null) {
            serviceManager = new MyServiceManager(this);
        }
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
            adminLoginViewModel.setmEditStatus(activityAdminloginBinding.editAdminUser.getText().length() > 0 &&
                    activityAdminloginBinding.editAdminPass.getText().length() > 0);
        }
    };

    private Observer<Integer> loginObserve = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer isLogin) {
            Log.i(Constant.TAG,"AdminLoginActivity LoginActivity onChanged isLogin = " + isLogin);
            if (isLogin == 1) {
                activityAdminloginBinding.loginTips.setText("登录成功！");
                Intent intent = new Intent(AdminLoginActivity.this, AdminActivity.class);
                intent.putExtra("page", 1);
                startActivity(intent);
            } else {
                activityAdminloginBinding.loginTips.setText("登录失败！");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
