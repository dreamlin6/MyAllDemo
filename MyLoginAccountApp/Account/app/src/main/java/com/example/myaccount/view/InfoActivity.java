package com.example.myaccount.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myaccount.R;

public class InfoActivity extends AppCompatActivity {
    private TextView tv;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tv = findViewById(R.id.infouser);
        bt = findViewById(R.id.logout);

        Intent intent = this.getIntent();
        String str = intent.getStringExtra("name");
        tv.setText(str);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}