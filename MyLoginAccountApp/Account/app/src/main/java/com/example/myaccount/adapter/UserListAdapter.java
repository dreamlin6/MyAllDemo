package com.example.myaccount.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaccount.R;
import com.example.myaccount.bean.UserListBean;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ListViewHodler>{

    private final String TAG = "TestLog";
    private Context context;
    private List<UserListBean> list;
    private UserListBean userListBean;
    private TextView mTvUser;
    private TextView mTvAccount;
    private TextView mTvPass;
    private Button mBtDelete;

    public UserListAdapter(Context context, List<UserListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserListAdapter.ListViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userlist, parent, false);
        return new ListViewHodler(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ListViewHodler holder, int position) {
        userListBean = list.get(position);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class ListViewHodler extends RecyclerView.ViewHolder {
        public ListViewHodler(@NonNull View userlist) {
            super(userlist);

            mTvUser = (TextView) itemView.findViewById(R.id.ul_user);
            mTvAccount = (TextView) itemView.findViewById(R.id.ul_account);
            mTvPass = (TextView) itemView.findViewById(R.id.ul_pass);
            mBtDelete = (Button) itemView.findViewById(R.id.ul_bt);

            mBtDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "UserListAdapter DeleteBtn onClick");
                }
            });
        }
    }

}
