package com.example.myaccount.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.databinding.ActivityLoginBinding;
import com.example.myaccount.viewmodel.UserLoginViewModel;

public class UserLoginActivity extends AppCompatActivity {

    private UserLoginViewModel userLoginViewModel;
    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner(this);

        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        userLoginViewModel = new ViewModelProvider(this, instance).get(UserLoginViewModel.class);  //创建viewmodel
        activityLoginBinding.setUserloginvm(userLoginViewModel); //设置绑定 XML和Adapter
    }
}
