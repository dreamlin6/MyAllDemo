package com.example.myaccount.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.myaccount.R;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fm;
    private static FragmentTransaction ft;
    private Fragment fragment_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        fragment_main = new Fragment_main();
        ft.add(R.id.fragment1, fragment_main);

        ft.commit();
    }
}