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

public class Fragment_one extends Fragment {
    private Button bt1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("onCreateView","Fragment_one");
        View view = inflater.inflate(R.layout.fragment_one, container,false);
//        bt1 = view.findViewById(R.id.bt1);
//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("Button","onClick TO_C");
//                MainActivity.setIndex(1);
//            }
//        });
        return view;
    }
}
