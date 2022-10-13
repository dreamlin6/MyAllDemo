package com.example.myaccount.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.databinding.ActivityRegisterBinding;
import com.example.myaccount.viewmodel.UserRegisterViewModel;


public class UserRegisterActivity extends AppCompatActivity {

    private final String TAG = "TestLog";
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

        activityRegisterBinding.editAccount.addTextChangedListener(watcher);
        activityRegisterBinding.editUser.addTextChangedListener(watcher);
        activityRegisterBinding.editPass.addTextChangedListener(watcher);
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
                    activityRegisterBinding.editPass.getText().length() > 0);

        }
    };
}
