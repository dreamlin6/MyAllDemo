package com.example.mycalendar.calendar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mycalendar.calendar.bean.DataBean;

import java.util.List;

public class DayAdapter extends BaseAdapter {

    private List<DataBean> list;
    private Context context;

    public DayAdapter(List<DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("LongLogTag")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView textView;
        // 使用缓存机制提高利用率
        if (view == null) {
            textView = new TextView(context);
            textView.setPadding(5, 5, 5, 5);
            view = textView;
        } else {
            textView = (TextView) view;
        }

        DataBean bean = getItem(position);
        int day = bean.getDay();
        textView.setText(day + "");
        Log.i("TTTTTTTTTTTTTTTTTTTTTTTTTTTT","day = " + day);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setTypeface(Typeface.DEFAULT_BOLD);

        if (bean.isCurrentDay()) {
            textView.setBackgroundColor(Color.parseColor("#fd5f00"));
            textView.setTextColor(Color.WHITE);
        } else if (bean.isCurrentMonth()) {
            textView.setBackgroundColor(Color.WHITE);
            textView.setTextColor(Color.BLACK);
        } else {
            // 通过 parseColor 方法得到的颜色不可以简写，必须写满六位
            textView.setBackgroundColor(Color.parseColor("#aaaaaa"));
            textView.setTextColor(Color.BLACK);
        }
        // 返回 view 或 textView 都行，因为都是同一个对象
        return textView;
    }
}


