package com.example.myaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.myaccount.fragment.admin.AdminFragment;
import com.example.myaccount.fragment.admin.AdminLoginFragment;
import com.example.myaccount.fragment.login.UserLoginFragment;
import com.example.myaccount.util.SwTabLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private SwTabLayout mTab;
    private static FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.framelayout);
        mTab = findViewById(R.id.tab);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        mTab.setLeft("ADMIN");
        mTab.setRight("USER");

        mTab.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AdminLoginFragment();
                ft.add(R.id.framelayout, fragment);
                ft.commit();
            }
        });

        mTab.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UserLoginFragment();
                ft.add(R.id.framelayout, fragment);
                ft.commit();
            }
        });
    }

}