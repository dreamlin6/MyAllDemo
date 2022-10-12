package com.example.myfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragment_three extends Fragment {
    private Button bt2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("onCreateView","Fragment_three");
        //inflate将xml转化为view布局
        View view = inflater.inflate(R.layout.fragment_three, container,false);
//        bt2 = view.findViewById(R.id.bt2);
//        bt2.setOnClickListener(new View.OnClickListener() {
//        public void onClick(View v) {
//            Log.i("Button","onClick TO_A");
//            MainActivity.setIndex(0);
//            }
//        });
        return view;
    }
}
