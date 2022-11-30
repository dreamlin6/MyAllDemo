package com.example.myaccount.fragment.register;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.MyServiceManager;
import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.FragmentUserRegisterBinding;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.viewmodel.UserRegisterViewModel;

public class UserRegisterFragment extends Fragment {

    private FragmentUserRegisterBinding registerBinding;
    private UserRegisterViewModel userRegisterViewModel;
    private MyDialog dialog;
    private MyServiceManager serviceManager;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_register, container,false);
        registerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_register, container, false);
        registerBinding.setLifecycleOwner(this);
        
        if (userRegisterViewModel == null) {
            userRegisterViewModel = new ViewModelProvider(this).get(UserRegisterViewModel.class);  //创建viewmodel
        }
        registerBinding.setUserregistervm(userRegisterViewModel); //设置绑定 XML和Adapter

        registerBinding.editAccount.addTextChangedListener(watcher);
        registerBinding.editUser.addTextChangedListener(watcher);
        registerBinding.editPass.addTextChangedListener(watcher);
        registerBinding.editPass2.addTextChangedListener(watcher);

        userRegisterViewModel.getmRegisterDialogCtrl().observe(getViewLifecycleOwner(), registerObserver);

        registerBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (serviceManager == null) {
            serviceManager = new MyServiceManager(getContext());
        }
        serviceManager.bindService();
        
        return view;
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
            userRegisterViewModel.setmRegisterBtnEnable(registerBinding.editAccount.getText().length() > 0 &&
                    registerBinding.editUser.getText().length() > 0 &&
                    registerBinding.editPass.getText().length() > 0 &&
                    registerBinding.editPass2.getText().length() > 0);
        }
    };

    private Observer<Boolean> registerObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean isRegister) {
            Log.i(Constant.TAG,"UserRegisterActivity onChanged isRegister = " + isRegister);
            if (isRegister) {
                String username = registerBinding.editUser.getText().toString();
                String account = registerBinding.editAccount.getText().toString();
                String password = registerBinding.editPass.getText().toString();
                String password2 = registerBinding.editPass2.getText().toString();
                try {
                    if (serviceManager.isExistUser(account)) {
                        Toast.makeText(getContext(), "此账号已存在! 请重新输入!", Toast.LENGTH_SHORT).show();
                        registerBinding.editUser.setText(null);
                        Log.i(Constant.TAG, "UserRegisterActivity onChanged account repeat");
                    } else {
                        if (password.equals(password2)) {
                            try {
                                serviceManager.onRegister(username, account, password);
                                if (dialog == null) {
                                    dialog = new MyDialog(getContext());
                                    dialog.setsMessage("注册成功!确认跳转到登录吗?")
                                            .setsCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    dialog.dismiss();
                                                    registerBinding.editUser.setText(null);
                                                    registerBinding.editAccount.setText(null);
                                                    registerBinding.editPass.setText(null);
                                                    registerBinding.editPass2.setText(null);
                                                }
                                            }).setsConfirm(getResources().getString(R.string.confirm), new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
//                                                    Intent intent = new Intent(UserRegisterActivity.this, UserLoginActivity.class);
//                                                    startActivity(intent);
//                                                    finish();
                                                }
                                            }).show();
                                } else {
                                    dialog.show();
                                }
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getContext(), "两次密码输入不一致! 请重新输入!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
