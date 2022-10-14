package com.example.myaccount.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityRegisterBinding;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.viewmodel.UserRegisterViewModel;


public class UserRegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding activityRegisterBinding;
    private UserRegisterViewModel userRegisterViewModel;
    private MyDialog myDialog;

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

        userRegisterViewModel.getmRegisterDialogCtrl().observe(this, registerObserver);
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

    private Observer<Boolean> registerObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean isRegister) {
            Log.i(Constant.TAG,"UserRegisterActivity onChanged isRegister = " + isRegister);
            if (isRegister) {
                if (myDialog == null) {
                    myDialog = new MyDialog(UserRegisterActivity.this);
                    myDialog.setsMessage("注册成功!是否跳转到登录?")
                            .setsCancel("否", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                myDialog.dismiss();
                            }
                            }).setsConfirm("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(UserRegisterActivity.this, UserLoginActivity.class);
                                startActivity(intent);
                            }
                    }).show();
                } else {
                    myDialog.show();
                }
            }
        }
    };
}
