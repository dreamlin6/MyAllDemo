package com.example.myaccount.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myaccount.R;

public class MyDialog extends Dialog implements View.OnClickListener{

    private TextView mTitle, mMessage, mConfirm, mCancel;
    private String sTitle, sMessage, sConfirm, sCancel;
    private View.OnClickListener cancelListener, confirmListener;

    public MyDialog setsTitle(String stitle) {
        this.sTitle = stitle;
        return this;
    }

    public MyDialog setsMessage(String sMessage) {
        this.sMessage = sMessage;
        return this;
    }

    public MyDialog setsConfirm(String sConfirm, View.OnClickListener listener) {
        this.sConfirm = sConfirm;
        this.confirmListener = listener;
        return this;
    }

    public MyDialog setsCancel(String sCancel, View.OnClickListener listener) {
        this.sCancel = sCancel;
        this.cancelListener = listener;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        setCancelable(false);

        mTitle = findViewById(R.id.title);
        mMessage = findViewById(R.id.message);
        mCancel = findViewById(R.id.no);
        mConfirm = findViewById(R.id.yes);

        if (!TextUtils.isEmpty(sTitle)) {
            mTitle.setText(sTitle);
        }
        if (!TextUtils.isEmpty(sMessage)) {
            mMessage.setText(sMessage);
        }
        if (!TextUtils.isEmpty(sCancel)) {
            mCancel.setText(sCancel);
        }
        if (!TextUtils.isEmpty(sConfirm)) {
            mConfirm.setText(sConfirm);
        }

        mConfirm.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yes:
                if(confirmListener != null){
                    confirmListener.onClick(v);
                }
                break;
            case R.id.no:
                if(cancelListener != null){
                    cancelListener.onClick(v);
                }
                break;
        }
    }
}
