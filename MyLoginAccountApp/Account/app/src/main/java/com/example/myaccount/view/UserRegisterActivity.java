package com.example.myaccount.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.databinding.ActivityRegisterBinding;
import com.example.myaccount.viewmodel.AdminViewModel;
import com.example.myaccount.viewmodel.UserRegisterViewModel;


public class UserRegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding activityRegisterBinding;
    private UserRegisterViewModel userRegisterViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityRegisterBinding.setLifecycleOwner(this);

        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        userRegisterViewModel = new ViewModelProvider(this, instance).get(UserRegisterViewModel.class);  //创建viewmodel
        activityRegisterBinding.setUserregistervm(userRegisterViewModel); //设置绑定 XML和Adapter
    }
}
