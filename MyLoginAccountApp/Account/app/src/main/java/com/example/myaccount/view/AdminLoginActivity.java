package com.example.myaccount.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.databinding.ActivityAdminloginBinding;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.viewmodel.AdminLoginViewModel;

public class AdminLoginActivity extends AppCompatActivity {

    private final String TAG = "TestLog";
    private final String WAIT_LOGIN = "未登录";
    private final String LOGIN_SUCCESS = "登录成功!";
    private final String LOGIN_FAIL = "登录失败!";
    private ActivityAdminloginBinding activityAdminloginBinding;
    private AdminLoginViewModel adminLoginViewModel;
    private MyDialog myDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminloginBinding = DataBindingUtil.setContentView(this, R.layout.activity_adminlogin);
        activityAdminloginBinding.setLifecycleOwner(this);

        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        adminLoginViewModel = new ViewModelProvider(this, instance).get(AdminLoginViewModel.class);  //创建viewmodel
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

        activityAdminloginBinding.editAdminUser.addTextChangedListener(watcher); //监听输入框变化
        activityAdminloginBinding.editAdminPass.addTextChangedListener(watcher);
        activityAdminloginBinding.editAdminPass.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏密码显示
        activityAdminloginBinding.loginTips.addTextChangedListener(watcher1);
        adminLoginViewModel.getmAdminLoginStatus().observe(this, loginObserve);
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

    TextWatcher watcher1 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().equals(WAIT_LOGIN)) {
                Log.i(TAG,"afterTextChanged s = " + s);
                activityAdminloginBinding.loginTips.setTextColor(Color.parseColor("#ffa500"));
            }
        }
    };

    private Observer<Integer> loginObserve = new Observer<Integer>() {

        @Override
        public void onChanged(@Nullable Integer isLogin) {
            Log.i(TAG,"LoginActivity onChanged isLogin = " + isLogin);
            if (isLogin == 1) {
                if (myDialog == null) {
                    myDialog = new MyDialog(AdminLoginActivity.this);
                    myDialog.setsCancel("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            myDialog.dismiss();
                            activityAdminloginBinding.loginTips.setText(WAIT_LOGIN);
                        }
                    }).setsConfirm("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();
                } else {
                    myDialog.show();
                }
            } else {
            }
        }
    };

}
