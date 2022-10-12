package com.example.myfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static FrameLayout fragment1;
    private static FrameLayout fragment2;
    private Button bt1;
    private Button bt2;
    private static Fragment fragment_one;
    private static Fragment fragment_three;
    private static FragmentManager fm;
    private static FragmentTransaction ft;
    private static int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","onCreate");
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt1.setOnClickListener((View.OnClickListener) this);
        bt2.setOnClickListener((View.OnClickListener) this);
        fm = getSupportFragmentManager(); // 管理Fragment进行 添加，移除，替换 等操作
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bt1:
                Log.i("Button","onClick TO_C");
//                if(flag == 0) {
//                    ft.hide(fragment_one);
//                    flag = 1;
//                }
                setIndex(1);
                break;
            case R.id.bt2:
                Log.i("Button","onClick TO_C");
                setIndex(0);
                break;
        }
    }

    public static void setIndex(int index){
        FragmentTransaction ft = fm.beginTransaction(); //启动事务处理
        hideFragment(ft);
        switch (index){
            case 0:
                Log.i("setIndex","index" + index);
                if(fragment_one==null){
                    fragment_one = new Fragment_one();
                    ft.add(R.id.fragment1,fragment_one); //向Activity中添加一个Fragment
                }else{
                    ft.show(fragment_one); //显示被隐藏过的Fragment
                }
                break;
            case 1:
                Log.i("setIndex","index = " + index);
                if(fragment_three==null){
                    fragment_three = new Fragment_three();
                    ft.add(R.id.fragment1,fragment_three);
                }else{
                    ft.show(fragment_three);
                }
                break;
        }
        ft.commit(); // 提交事务
    }

    // 隐藏 Fragment
    private static void hideFragment(FragmentTransaction ft){
        if(fragment_one!=null){
            Log.i("hideFragment","hide fragment_one");
            ft.hide(fragment_one); //隐藏一个存在的Fragment
        } if(fragment_three!=null){
            Log.i("hideFragment","hide fragment_three");
            ft.hide(fragment_three);
        }
    }
}