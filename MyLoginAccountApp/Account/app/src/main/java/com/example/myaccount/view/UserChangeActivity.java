package com.example.myaccount.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityChangeBinding;
import com.example.myaccount.viewmodel.UserChangeViewModel;

public class UserChangeActivity extends AppCompatActivity {
    private ActivityChangeBinding activityChangeBinding;
    private UserChangeViewModel userChangeViewModel;
    private ContentResolver resolver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChangeBinding = DataBindingUtil.setContentView(this, R.layout.activity_change);
        activityChangeBinding.setLifecycleOwner(this);
        getSupportActionBar().setTitle("修改密码");

        ViewModelProvider.AndroidViewModelFactory instance =
                ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getApplication()); //viewmodel实例
        if (userChangeViewModel == null) {
            userChangeViewModel = new ViewModelProvider(this, instance).get(UserChangeViewModel.class);  //创建viewmodel
        }
        activityChangeBinding.setChangevm(userChangeViewModel); //设置绑定 XML和Adapter

        activityChangeBinding.oldpass.addTextChangedListener(watcher);
        activityChangeBinding.newpass.addTextChangedListener(watcher);
        activityChangeBinding.newpass2.addTextChangedListener(watcher);

        activityChangeBinding.changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Constant.TAG, "UserChangeActivity changepass onClick mPass = " + getIntent().getStringExtra("mPass"));
                if (activityChangeBinding.oldpass.getText().toString().equals(getIntent().getStringExtra("mPass"))) {
                    resolver = getContentResolver();
                    Uri uri = Uri.parse("content://com.example.myaccount/users");
                    ContentValues values = new ContentValues();
                    values.put("password", activityChangeBinding.newpass.getText().toString());
                    int id = resolver.update(uri, values, "_id = ?", new String[]{getIntent().getStringExtra("mId")});
                    Log.i(Constant.TAG,String.format("UserChangeActivity changepass onClick mId = %s id = %s" ,getIntent().getStringExtra("mId"), id));
                    if(id > 0){
                        show("密码更新成功！");
                    }else{
                        show("密码更新失败！");
                    }
                } else {
                    show("密码输入错误！");
                }
            }
        });
        activityChangeBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserChangeActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
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
            userChangeViewModel.setmChangeEnableStatus(activityChangeBinding.oldpass.getText().length() > 0 &&
                    activityChangeBinding.newpass.getText().length() > 0 &&
                    activityChangeBinding.newpass2.getText().length() > 0);
        }
    };

    public void show(String tips) {
        Toast.makeText(UserChangeActivity.this, tips, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
