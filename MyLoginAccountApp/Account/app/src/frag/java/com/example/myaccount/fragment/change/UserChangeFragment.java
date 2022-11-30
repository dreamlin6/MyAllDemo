package com.example.myaccount.fragment.change;

import android.os.Bundle;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.MyServiceManager;
import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.FragmentUserChangeBinding;
import com.example.myaccount.fragment.login.UserLoginFragment;
import com.example.myaccount.viewmodel.AdminLoginViewModel;
import com.example.myaccount.viewmodel.UserChangeViewModel;

public class UserChangeFragment extends Fragment {

    private FragmentUserChangeBinding fragmentUserChangeBinding;
    private UserChangeViewModel userChangeViewModel;
    private MyServiceManager serviceManager;
    private String mId, mPass;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentUserChangeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_change, container, false);
        fragmentUserChangeBinding.setLifecycleOwner(this);

        if (userChangeViewModel == null) {
            userChangeViewModel = new ViewModelProvider(this).get(UserChangeViewModel.class);  //创建viewmodel
        }
        fragmentUserChangeBinding.setChangevm(userChangeViewModel); //设置绑定 XML和Adapter

        fragmentUserChangeBinding.oldpass.addTextChangedListener(watcher);
        fragmentUserChangeBinding.newpass.addTextChangedListener(watcher);
        fragmentUserChangeBinding.newpass2.addTextChangedListener(watcher);

        fragmentUserChangeBinding.changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                mId = bundle.getString("mId");
                mPass = bundle.getString("mPass");
                Log.i(Constant.TAG, "UserChangeActivity changepass onClick mPass = " + mPass);
                if (fragmentUserChangeBinding.oldpass.getText().toString().equals(mPass)) {
                    try {
                        int id = serviceManager.onUpdate(mId, fragmentUserChangeBinding.newpass.getText().toString());
                        Log.i(Constant.TAG,String.format("UserChangeActivity changepass onClick mId = %s id = %s" ,mId, id));
                        if(id > 0){
                            showToast("密码更新成功！");
                        }else{
                            showToast("密码更新失败！");
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    showToast("密码输入错误！");
                }
            }
        });
        fragmentUserChangeBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                UserLoginFragment loginFragment = new UserLoginFragment();
                fragmentTransaction.replace(R.id.framelayout, loginFragment).commit();
            }
        });
        if (serviceManager == null) {
            serviceManager = new MyServiceManager(getContext());
        }
        serviceManager.bindService();
        
        return fragmentUserChangeBinding.getRoot();
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            userChangeViewModel.setmChangeEnableStatus(fragmentUserChangeBinding.oldpass.getText().length() > 0 &&
                    fragmentUserChangeBinding.newpass.getText().length() > 0 &&
                    fragmentUserChangeBinding.newpass2.getText().length() > 0);
        }
    };

    public void showToast(String tips) {
        Toast.makeText(getContext(), tips, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
