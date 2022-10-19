package com.example.myaccount.view;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityLoginBinding;
import com.example.myaccount.viewmodel.UserLoginViewModel;

public class UserLoginActivity extends AppCompatActivity {

    private UserLoginViewModel userLoginViewModel;
    private ActivityLoginBinding activityLoginBinding;
    private ContentResolver resolver;

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

        activityLoginBinding.editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());

        activityLoginBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
                startActivity(intent1);
            }
        });
        activityLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                Log.i(Constant.TAG, "UserLoginActivity login onClick");
                String account = activityLoginBinding.editUser.getText().toString();
                String pass = activityLoginBinding.editPass.getText().toString();
                String account1, pass1, name;

                Uri uri = Uri.parse("content://com.example.myaccount/users");
                resolver = getContentResolver();
                Cursor cursor = resolver.query(uri, null, null, null, null);
                while (cursor.moveToNext()) {
                    account1 = cursor.getString(cursor.getColumnIndex("account"));
                    pass1 = cursor.getString(cursor.getColumnIndex("password"));
                    Log.i(Constant.TAG, "UserLoginActivity cursor user1 pass1 " + account1 + " " + pass1);
                    if (account.equals(account1) && pass.equals(pass1)) {
                        Toast.makeText(UserLoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        name = cursor.getString(cursor.getColumnIndex("username"));

                        Intent intent = new Intent(UserLoginActivity.this, InfoActivity.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    } else {
                        Toast.makeText(UserLoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        activityLoginBinding.editUser.addTextChangedListener(watcher);
        activityLoginBinding.editPass.addTextChangedListener(watcher);
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
            userLoginViewModel.setmLoginEnableStatus(activityLoginBinding.editUser.getText().length() > 0 && activityLoginBinding.editPass.getText().length() > 0);

        }
    };
}
