package com.example.myaccount.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaccount.R;
import com.example.myaccount.bean.UserListBean;
import com.example.myaccount.constant.Constant;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ListViewHodler>{

    private Context context;
    private List<UserListBean> list;

    public UserListAdapter(Context context, List<UserListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserListAdapter.ListViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userlist, parent, false);
        ListViewHodler vewHolder = new ListViewHodler(view);
        return vewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ListViewHodler holder, int position) {
        holder.mTvId.setText(list.get(position).getmId());
        holder.mTvUser.setText(list.get(position).getUserName());
        holder.mTvAccount.setText(list.get(position).getAccount());
        holder.mTvPass.setText(list.get(position).getPassWord());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ListViewHodler extends RecyclerView.ViewHolder {
        public TextView mTvId, mTvUser, mTvAccount, mTvPass;

        public ListViewHodler(View userlist) {
            super(userlist);

            mTvId = (TextView) itemView.findViewById(R.id.ul_id);
            mTvUser = (TextView) itemView.findViewById(R.id.ul_user);
            mTvAccount = (TextView) itemView.findViewById(R.id.ul_account);
            mTvPass = (TextView) itemView.findViewById(R.id.ul_pass);

        }
    }

}
