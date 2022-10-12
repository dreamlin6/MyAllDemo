package com.example.activitytasktest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityB extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        setTitle("ActivityB");
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn5 = (Button) findViewById(R.id.btn5);
        btn2.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn2:
                Intent intent1 = new Intent(this,ActivityC.class);

                startActivity(intent1);
                break;
            case R.id.btn5:
                Intent intent2 = new Intent(this,ActivityB.class);

                startActivity(intent2);
                break;
        }
    }
}