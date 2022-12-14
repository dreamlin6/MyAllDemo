package com.example.myaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myaccount.activity.admin.AdminLoginActivity;
import com.example.myaccount.activity.login.UserLoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button toAdmin;
    private Button toUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toAdmin = findViewById(R.id.toAdmin);
        toUser = findViewById(R.id.toUser);
        toAdmin.setOnClickListener(this);
        toUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toAdmin:
                Intent intent1 = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.toUser:
                Toast.makeText(MainActivity.this, "Test Toast", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, UserLoginActivity.class);
//                Intent intent2 = new Intent();
//                intent2.setAction("android.intent.action.Login");
                startActivity(intent2);
                break;
        }
    }
}