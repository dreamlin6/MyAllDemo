package com.example.activitylifetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityB extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        setTitle("ActivityB");
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.btn3);
        btn4.setOnClickListener(this);
        Log.d(TAG, "onCreate" + "---------ActivityB--------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart" + "---------ActivityB--------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume" + "---------ActivityB--------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause" + "---------ActivityB--------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop" + "---------ActivityB--------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart" + "---------ActivityB--------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy" + "---------ActivityB--------");
    }

    @Override
    public void finish() {
        super.finish();
        Log.d(TAG, "finish" + "---------ActivityB--------");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn2:
                Intent intent1 = new Intent(this, ActivityC.class);
                startActivity(intent1);
                break;
            case R.id.btn3:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn4:
                Intent intent3 = new Intent(this, ActivityB.class);
                startActivity(intent3);
                break;
        }

    }
}