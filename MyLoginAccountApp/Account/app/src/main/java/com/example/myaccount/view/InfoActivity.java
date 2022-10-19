package com.example.myaccount.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myaccount.R;
import com.example.myaccount.viewmodel.UserChangeViewModel;

public class InfoActivity extends AppCompatActivity {
    private TextView tv;
    private Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tv = findViewById(R.id.infouser);
        bt1 = findViewById(R.id.logout);
        bt2 = findViewById(R.id.change);

        Intent intent = this.getIntent();
        String str = intent.getStringExtra("name");
        tv.setText(str);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InfoActivity.this, UserLoginActivity.class);
                startActivity(intent1);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(InfoActivity.this, UserChangeActivity.class);
                startActivity(intent2);
            }
        });
    }
}