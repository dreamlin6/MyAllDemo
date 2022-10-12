package com.example.activitylifetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("ActivityA");
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        Log.d(TAG, "onCreate" + "---------MainActivity--------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart" + "---------MainActivity--------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume" + "---------MainActivity--------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause" + "---------MainActivity--------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop" + "---------MainActivity--------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart" + "---------MainActivity--------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy" + "---------MainActivity--------");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ActivityB.class);

        startActivity(intent);
    }
}