package com.example.myaccount.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myaccount.R;

public class AdminFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater.from(requireContext()).inflate(R.layout.fragment_admin, null);


    }
}
