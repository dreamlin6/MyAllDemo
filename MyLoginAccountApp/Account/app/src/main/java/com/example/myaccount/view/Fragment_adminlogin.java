package com.example.myaccount.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.IMyUser;
import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.FragmentMainBinding;
import com.example.myaccount.viewmodel.AdminLoginViewModel;

public class Fragment_adminlogin extends Fragment {
    private final String WAIT_LOGIN = "未登录";
    private FragmentMainBinding activityAdminloginBinding;
    private AdminLoginViewModel adminLoginViewModel;
    private ServiceConnection connection;
    private IMyUser iMyUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminloginBinding = DataBindingUtil.inflate(inflater, R.layout.activity_adminlogin, null, true);
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

        mBindService();
    }

    public void mBindService() {
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                Log.i(Constant.TAG, "AdminLoginActivity onServiceConnected");
                iMyUser = IMyUser.Stub.asInterface(iBinder);
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(Constant.TAG, "AdminLoginActivity onServiceDisconnected " + name);
            }
        };

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
                startActivity(intent);
            } else {
                activityAdminloginBinding.loginTips.setText("登录失败！");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
