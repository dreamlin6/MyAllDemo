package com.example.mylogin.view;

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
import com.example.mylogin.R;
import com.example.mylogin.databinding.ActivityChangeBinding;
import com.example.mylogin.viewmodel.ChangeViewModel;

public class ChangeActivity extends AppCompatActivity {

    private final String TAG = "ChangeActivity";
    private ActivityChangeBinding changeBinding;
    private ChangeViewModel changeViewModel;
    private ServiceConnection connection;
    private IMyUser iMyUser;
    private String mId, mPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeBinding = DataBindingUtil.setContentView(this, R.layout.activity_change);
        changeBinding.setLifecycleOwner(this);
        getSupportActionBar().setTitle("修改密码");

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                iMyUser = IMyUser.Stub.asInterface(iBinder);
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected");
            }
        };

        ViewModelProvider.AndroidViewModelFactory instance = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        if (changeViewModel == null) {
            changeViewModel = new ViewModelProvider(this, instance).get(ChangeViewModel.class);
        }
        changeBinding.setChangevm(changeViewModel);
        changeBinding.oldpass.addTextChangedListener(watcher);
        changeBinding.newpass.addTextChangedListener(watcher);
        changeBinding.newpass2.addTextChangedListener(watcher);

        changeBinding.changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mId = getIntent().getStringExtra("mId");
                mPass = getIntent().getStringExtra("mPass");
                Log.i(TAG, "changepass onClick mPass = " + mPass);
                if (changeBinding.oldpass.getText().toString().equals(mPass)) {
                    try {
                        int id = iMyUser.mUpdate(mId, changeBinding.newpass.getText().toString());
                        Log.i(TAG,String.format("changepass onClick mId = %s id = %s" ,mId, id));
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
        changeBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChangeActivity.this, LoginActivity.class);
                startActivity(intent1);
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
            changeViewModel.setmChangeEnableStatus(changeBinding.oldpass.getText().length() > 0 &&
                    changeBinding.newpass.getText().length() > 0 &&
                    changeBinding.newpass2.getText().length() > 0);
        }
    };

    public void show(String tips) {
        Toast.makeText(ChangeActivity.this, tips, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
