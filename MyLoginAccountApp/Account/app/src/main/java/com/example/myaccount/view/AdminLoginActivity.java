package com.example.myaccount.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.databinding.ActivityAdminloginBinding;
import com.example.myaccount.viewmodel.AdminLoginViewModel;

public class AdminLoginActivity extends AppCompatActivity {

    private ActivityAdminloginBinding activityAdminloginBinding;
    private AdminLoginViewModel adminLoginViewModel;

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

}
