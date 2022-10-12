package com.example.activitylifetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ActivityC extends AppCompatActivity {

    private static final String TAG = "Activity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart" + "---------ActivityC--------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume" + "---------ActivityC--------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause" + "---------ActivityC--------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop" + "---------ActivityC--------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart" + "---------ActivityC--------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy" + "---------ActivityC--------");
    }
}