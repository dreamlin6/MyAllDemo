package com.example.myaccount.adapter;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.databinding.BindingAdapter;

import com.example.myaccount.constant.Constant;

public class UserLoginBindingAdapter {

    @BindingAdapter({"mBtLogin"})
    public static void setBtUnLoginVisible(Button view, boolean isStatus) {
        Log.i(Constant.TAG, "UserLoginBindingAdapter setBtUnLoginVisible isStatus = " + isStatus);
        view.setVisibility(isStatus ? View.VISIBLE : View.INVISIBLE);
    }
}
