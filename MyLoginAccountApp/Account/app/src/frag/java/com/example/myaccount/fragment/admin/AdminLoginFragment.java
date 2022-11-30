package com.example.myaccount.fragment.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
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
import com.example.myaccount.databinding.FragmentAdminLoginBinding;
import com.example.myaccount.viewmodel.AdminLoginViewModel;
import com.example.myaccount.viewmodel.AdminViewModel;

public class AdminLoginFragment extends Fragment {

    private final String WAIT_LOGIN = "未登录";
    private FragmentAdminLoginBinding fragmentAdminLoginBinding;
    private AdminLoginViewModel adminLoginViewModel;
    private MyServiceManager serviceManager;
    private boolean isLogin;
    private final String LOGIN_SUCCESS = "登录成功!";
    private final String LOGIN_FAIL = "登录失败!";
    private final int ONE = 1;
    private final int TWO = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_login, container,false);

        fragmentAdminLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_login, container, false);
        fragmentAdminLoginBinding.setLifecycleOwner(this);

        if (adminLoginViewModel == null) {
            adminLoginViewModel = new ViewModelProvider(this).get(AdminLoginViewModel.class);  //创建viewmodel
        }
        fragmentAdminLoginBinding.setAdminloginvm(adminLoginViewModel); //设置绑定 XML和Adapter

        fragmentAdminLoginBinding.adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (serviceManager.getAdminCount() > 0) {
                        try {
                            isLogin = serviceManager.onAdminLoginVerify(fragmentAdminLoginBinding.editAdminUser.getText().toString(),
                                    fragmentAdminLoginBinding.editAdminPass.getText().toString());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        Log.i(Constant.TAG, "AdminLoginViewModel adminLoginVerify TRUE");
                        adminLoginViewModel.setAdminLoginStatus( isLogin ? ONE : TWO);
                        adminLoginViewModel.mLoginStatusTips.setValue(isLogin ? LOGIN_SUCCESS : LOGIN_FAIL);
                        fragmentAdminLoginBinding.editAdminUser.setText(null);
                        fragmentAdminLoginBinding.editAdminPass.setText(null);
                    } else {
                        Toast.makeText(getContext(), "无管理员用户，请先注册", Toast.LENGTH_SHORT).show();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        fragmentAdminLoginBinding.tohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fragmentAdminLoginBinding.adminRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (serviceManager.getAdminCount() > 0) {
                        Toast.makeText(getContext(), "已经存在管理员用户", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        try {
                            serviceManager.onAdminRegister(fragmentAdminLoginBinding.editAdminUser.getText().toString(),
                                    fragmentAdminLoginBinding.editAdminPass.getText().toString());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        fragmentAdminLoginBinding.editAdminUser.addTextChangedListener(watcher); //监听输入框变化
        fragmentAdminLoginBinding.editAdminPass.addTextChangedListener(watcher);
        fragmentAdminLoginBinding.editAdminPass.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏密码显示
        fragmentAdminLoginBinding.loginTips.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals(WAIT_LOGIN)) {
                    Log.i(Constant.TAG,"AdminLoginActivity afterTextChanged s = " + s);
                    fragmentAdminLoginBinding.loginTips.setTextColor(Color.parseColor("#ffa500"));
                }
            }
        });
        adminLoginViewModel.getAdminLoginStatus().observe(this.getViewLifecycleOwner(), loginObserve);
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
            adminLoginViewModel.setEditStatus(fragmentAdminLoginBinding.editAdminUser.getText().length() > 0 &&
                    fragmentAdminLoginBinding.editAdminPass.getText().length() > 0);
        }
    };

    private Observer<Integer> loginObserve = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer isLogin) {
            Log.i(Constant.TAG,"AdminLoginActivity LoginActivity onChanged isLogin = " + isLogin);
            if (isLogin == 1) {
                fragmentAdminLoginBinding.loginTips.setText("登录成功！");

            } else {
                fragmentAdminLoginBinding.loginTips.setText("登录失败！");
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
