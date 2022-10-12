package com.example.fragmenttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mbt;
    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbt = (Button) findViewById(R.id.bt);
        mbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTwo fragment2 = new FragmentTwo();
                FragmentThree fragment3 = new FragmentThree();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(flag == 0) {
                    fragmentTransaction.replace(R.id.frag2,fragment2);
                    flag = 1;
                } else {
                    fragmentTransaction.replace(R.id.frag2,fragment3);
                    flag = 0;
                }
                fragmentTransaction.commit();
            }
        });
    }
}