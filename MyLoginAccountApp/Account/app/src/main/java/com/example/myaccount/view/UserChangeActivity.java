package com.example.myaccount.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.IMyUser;
import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.ActivityChangeBinding;
import com.example.myaccount.viewmodel.UserChangeViewModel;

public class UserChangeActivity extends AppCompatActivity {
    private ActivityChangeBinding activityChangeBinding;
    private UserChangeViewModel userChangeViewModel;
    private IMyUser iMyUser;
    private ServiceConnection connection;
    private String mId, mPass;

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

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                iMyUser = IMyUser.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(Constant.TAG, "UserChangeActivity onServiceDisconnected");
            }
        };

        activityChangeBinding.changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mId = getIntent().getStringExtra("mId");
                mPass = getIntent().getStringExtra("mPass");
                Log.i(Constant.TAG, "UserChangeActivity changepass onClick mPass = " + mPass);
                if (activityChangeBinding.oldpass.getText().toString().equals(mPass)) {
                    try {
                        int id = iMyUser.mUpdate(mId, activityChangeBinding.newpass.getText().toString());
                        Log.i(Constant.TAG,String.format("UserChangeActivity changepass onClick mId = %s id = %s" ,mId, id));
                        if(id > 0){
                            show("密码更新成功！");
                        }else{
                            show("密码更新失败！");
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
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
        this.unbindService(connection);
    }
}
