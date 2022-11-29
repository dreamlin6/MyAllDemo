package com.example.myaccount.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myaccount.R;

public class SwTabLayout extends ConstraintLayout {

    private TextView left;
    private TextView right;

    public SwTabLayout(@NonNull Context context) {
        this(context, null);
    }

    public SwTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SwTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    public void init(AttributeSet attrs) {

        LayoutInflater.from(getContext()).inflate(R.layout.sw_tablayout, this, true);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SWTabLayout);

        left = findViewById(R.id.left_item);
        right = findViewById(R.id.right_item);

        setLeft(ta.getString(R.styleable.SWTabLayout_sw_tab_left_text));
        setRight(ta.getString(R.styleable.SWTabLayout_sw_tab_right_text));
    }

    public void setLeft(String text) {
        left.setText(text);
    }

    public void setRight(String text) {
        right.setText(text);
    }
}
