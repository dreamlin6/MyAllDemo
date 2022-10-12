package com.example.mycalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mycalendar.adapter.DataAdapter;
import com.example.mycalendar.bean.DataBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private TextView tvCurrentDate;
    private ImageButton tvPreMonth;
    private ImageButton tvNextMonth;
    private RecyclerView rv;
    private int weekIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Log.i(TAG,"initView");
        tvCurrentDate = (TextView) findViewById(R.id.tvCurrentDate);
        tvPreMonth = (ImageButton) findViewById(R.id.tvPreMonth);
        tvNextMonth = (ImageButton) findViewById(R.id.tvNextMonth);
        rv = (RecyclerView) findViewById(R.id.rv_calendar);
        // 初始化适配器
        initAdapter();
    }

    // 初始化适配器
    private void initAdapter() {
        Log.i(TAG,"initAdapter");
        List<DataBean> dataList = new ArrayList<>();
        DataAdapter adapter = new DataAdapter(this, dataList);
        rv.setLayoutManager(new GridLayoutManager(this,7)); // 设置布局管理器，添加7列的网格布局
        rv.setAdapter(adapter);

        // 拿到日历对象，动态设置时间，到12月可自动到下一年1月
        Calendar calendar = Calendar.getInstance();
        setCurrentData(calendar);
        updateAdapter(calendar, dataList, adapter);


        tvPreMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick tvPreMonth");
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
                updateAdapter(calendar, dataList, adapter);
            }
        });
        tvNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick tvNextMonth");
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
                updateAdapter(calendar, dataList, adapter);
            }
        });

        adapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    private void updateAdapter(Calendar calendar, List<DataBean> dataList, DataAdapter adapter) {
        Log.e(TAG,"updateAdapter");
        dataList.clear();
        setCurrentData(calendar);

        calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置每月1号
        weekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 得到本月1号的星期索引，索引从 1 开始，星期日开始,减1是为了与星期对齐
        Log.e(TAG,"weekIndex = " + weekIndex);


        // 上个月剩余日期
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        int preMonthDays = getMonth(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR)); // 上个月总天数
        Log.e(TAG,"preMonthDays = " + preMonthDays);
        // 拿到上一个月的最后几天的天数
        for (int i = 0; i < weekIndex; i++) {
            DataBean bean = new DataBean();
            bean.setYear(calendar.get(Calendar.YEAR));
            bean.setMonth(calendar.get(Calendar.MONTH) + 1);
            bean.setDay(preMonthDays - weekIndex + i + 1);
            bean.setCurrentDay(false);
            bean.setCurrentMonth(false);
            dataList.add(bean);
        }

        // 本月日期
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        int currentDays = getMonth(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
        Log.e(TAG,"currentDays = " + currentDays);
        // 拿到当月的天数
        for (int i = 0; i < currentDays; i++) {
            DataBean bean = new DataBean();
            bean.setYear(calendar.get(Calendar.YEAR));
            bean.setMonth(calendar.get(Calendar.MONTH) + 1);
            bean.setDay(i + 1);
            // 当前日期
            String nowDate = getFormatTime("yyyy-M-d", Calendar.getInstance().getTime());
            // 选择的日期
            String selectDate = getFormatTime("yyyy-M-", calendar.getTime()) + (i + 1);
            // 假如相等的话，那么就是今天的日期了
            if (nowDate.contentEquals(selectDate)) {
                bean.setCurrentDay(true);
            } else {
                bean.setCurrentDay(false);
            }
            bean.setCurrentMonth(true);
            dataList.add(bean);
        }

        // 下个月日期
        // 先拿到下个月第一天的星期索引
        // 之前设为了1号，所以将日历对象的月数加 1 就行了
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        weekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        for (int i = 0; i < 7 - weekIndex; i++) {
            DataBean bean = new DataBean();
            bean.setYear(calendar.get(Calendar.YEAR));
            bean.setMonth(calendar.get(Calendar.MONTH) + 1);
            bean.setDay(i + 1);
            bean.setCurrentDay(false);
            bean.setCurrentMonth(false);
            dataList.add(bean);
        }

        Log.i(TAG,"notifyDataSetChanged");
        adapter.notifyDataSetChanged();
        // 最后将日期设为当月
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
    }
    // 设置当前的时间
    private void setCurrentData(Calendar calendar) {
        tvCurrentDate.setText(calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月");
    }
    // 判断是否为闰年
    public boolean isRunYear(int y) {
        return y % 4 == 0 && y % 100 != 0 || y % 400 == 0;
    }
    // 格式化时间，设置时间很方便，也比较简单，学的很快
    public static String getFormatTime(String p, Date t) {
        return new SimpleDateFormat(p, Locale.CHINESE).format(t);
    }
    // 传入年和月得出当月的天数
    public int getMonth(int m, int y) {
        switch (m) {
            case 2:
                return isRunYear(y) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }
}