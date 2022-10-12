package com.example.mycalendar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycalendar.R;
import com.example.mycalendar.bean.DataBean;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHodler> implements View.OnClickListener{
    private final String TAG = "DataAdapter";
    private Context context;
    private List<DataBean> list;
    private DataBean dataBean;
    private int flag = 0;
    private TextView mTvdata;
    private LinearLayout mLlData;
    private OnItemClickListener listener;

    public DataAdapter(Context context, List<DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override    // 加载子项的布局
    public DataAdapter.DataViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Log.i(TAG,"onCreateViewHolder");
        // 将布局实例化为对象
        View view = LayoutInflater.from(context).inflate(R.layout.itemdata, parent, false);
        return new DataViewHodler(view);
    }

    @Override   // 绑定数据
    public void onBindViewHolder(@NonNull DataAdapter.DataViewHodler holder, @SuppressLint("RecyclerView") int position) {
        dataBean = list.get(position);
//        Log.i(TAG,"onBindViewHolder Year = " + dataBean.getYear());
//        Log.i(TAG,"onBindViewHolder Month = " + dataBean.getMonth());
//        Log.i(TAG,"onBindViewHolder data = " + dataBean.getDay());
//        Log.i(TAG,"onBindViewHolder position = " + position);

//        Log.e(TAG,"dataBean.getMonth() = " + dataBean.getMonth());
//        Log.e(TAG,"dataBean.getDay() = " + dataBean.getDay());
        mTvdata.setText(String.valueOf(dataBean.getDay()));

        if (dataBean.isCurrentDay()) {
            mTvdata.setBackgroundColor(Color.YELLOW);
            mTvdata.setTextColor(Color.RED);
        } else if (dataBean.isCurrentMonth()) {
            mTvdata.setBackgroundColor(Color.WHITE);
            mTvdata.setTextColor(Color.BLACK);
        }
        else {
            mTvdata.setBackgroundColor(Color.GRAY);
            mTvdata.setTextColor(Color.BLACK);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    Log.e(TAG,"position = " + position);
                    listener.onItemClick(position);
                    if (flag == 0) {
                        mTvdata.setBackgroundColor(Color.RED);
                        flag = 1;
                    } else {
                        mTvdata.setBackgroundColor(Color.WHITE);
                        flag = 0;
                    }
                }
                }
        });
    }

    @Override
    public void onClick(View view) {

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // 获取布局中的每个item
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 获取item数量
    @Override
    public int getItemCount() {
       return list.size();
    }

    // 界面组件实例化
    public class DataViewHodler extends RecyclerView.ViewHolder {
        public DataViewHodler(@NonNull View itemView) {
            super(itemView);
//            Log.i(TAG,"DataViewHodler");
//            itemView.setOnClickListener(this);
            mTvdata = (TextView) itemView.findViewById(R.id.tv_date);
            mLlData = (LinearLayout) itemView.findViewById(R.id.ll_date);

//            mTvdata.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.i(TAG,"item onClick");
//                    if (flag == 0) {
//                        mTvdata.setTextColor(Color.CYAN);
//                        flag = 1;
//                    } else {
//                        mTvdata.setTextColor(Color.BLACK);
//                        flag = 0;
//                    }
//                }
//            });

        }

    }
}


