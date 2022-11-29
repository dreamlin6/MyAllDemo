package com.example.myaccount.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myaccount.R;

public class Fragment_main extends Fragment implements View.OnClickListener{

    private static FragmentManager fm;
    private static FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("onCreateView", "fragment_main");
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toAdmin:
                setIndex(0);
                break;
            case R.id.toUser:
                setIndex(1);
                break;
        }
    }

    public void setIndex(int index){
        fm = getActivity().getSupportFragmentManager();
        ft = fm.beginTransaction(); //启动事务处理

        switch (index){
            case 0:
                Log.i("setIndex","index" + index);
                ft.hide(Fragment_main.this);
                Intent intent = requireActivity().getIntent();
                break;
            case 1:
                Log.i("setIndex","index = " + index);
                ft.hide(Fragment_main.this);
                break;
        }
        ft.commit(); // 提交事务
    }
}
