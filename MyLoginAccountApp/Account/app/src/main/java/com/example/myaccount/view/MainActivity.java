package com.example.myaccount.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myaccount.R;

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
                Intent intent2 = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent2);
                break;
        }
    }
}