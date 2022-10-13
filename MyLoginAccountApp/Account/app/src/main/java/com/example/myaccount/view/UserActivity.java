package com.example.myaccount.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myaccount.R;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "TestLog";
    private Button register;
    private Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        register = findViewById(R.id.toRegister);
        login = findViewById(R.id.toLogin);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toRegister:
                Intent intent1 = new Intent(UserActivity.this, UserRegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.toLogin:
                Intent intent2 = new Intent(UserActivity.this, UserLoginActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
