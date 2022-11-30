package com.example.myaccount.fragment.login;

import android.annotation.SuppressLint;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myaccount.MyServiceManager;
import com.example.myaccount.R;
import com.example.myaccount.constant.Constant;
import com.example.myaccount.databinding.FragmentUserLoginBinding;
import com.example.myaccount.util.MyDialog;
import com.example.myaccount.viewmodel.UserChangeViewModel;
import com.example.myaccount.viewmodel.UserLoginViewModel;

public class UserLoginFragment extends Fragment {

    private UserLoginViewModel userLoginViewModel;
    private FragmentUserLoginBinding userLoginBinding;
    private MyDialog dialog;
    private String mId, mAccount, mPass, mName, mPass2;
    private String[] userInfo;
    private MyServiceManager serviceManager;
    private final int REGISTER_DIALOG = 1;
    private final int LOGOUT_DIALOG = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_login, container,false);

        userLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_login, container, false);
        userLoginBinding.setLifecycleOwner(this);

        if (userLoginViewModel == null) {
            userLoginViewModel = new ViewModelProvider(this).get(UserLoginViewModel.class);  //创建viewmodel
        }
        userLoginBinding.setUserloginvm(userLoginViewModel); //设置绑定 XML和Adapter
        userLoginBinding.editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());//输入框密码格式

        userLoginBinding.tohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
            }
        });
        userLoginBinding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Constant.TAG, String.format("UserLoginActivity change onclick mid = %s pass2 = %s", mId, mPass2));
//                intent2.putExtra("mId", mId);
//                intent2.putExtra("mPass", mPass2);
//                startActivity(intent2);
            }
        });
        userLoginBinding.quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUnLogin();
                userLoginViewModel.setmTvllVisibleStatus(false);
                userLoginBinding.editUser.setText(null);
                userLoginBinding.editPass.setText(null);
            }
        });
        userLoginBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShow("注销后该用户将无法登录。确定注销当前用户吗？", LOGOUT_DIALOG);
            }
        });
        userLoginBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent3 = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
//                startActivity(intent3);
            }
        });
        userLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                userInfo = new String[3];
                mAccount = userLoginBinding.editUser.getText().toString();
                mPass = userLoginBinding.editPass.getText().toString();
                Log.i(Constant.TAG, "UserLoginActivity login onClick");
                try {
                    if (serviceManager.isNoUser()) {
                        Log.i(Constant.TAG, "UserLoginActivity login onClick isNoUser Null");
                        dialogShow("未注册任何用户！是否去注册新用户？", REGISTER_DIALOG);
                    } else {
                        if (serviceManager.onLoginVerify(mAccount, mPass)) {
                            userInfo = serviceManager.onLogin(mAccount, mPass);
                            mId = userInfo[0];
                            mName = userInfo[1];
                            mPass2 = userInfo[2];
                            Log.i(Constant.TAG, String.format("UserLoginActivity Login mid = %s name = %s pass2 = %s", mId, mName, mPass2));
                            userLoginBinding.info.setText(String.format(getResources().getString(R.string.welcome), mName));
                            userLoginBinding.info.setTextColor(Color.parseColor("#008000"));
                            isLogin();
                        } else {
                            userLoginBinding.info.setText(getResources().getString(R.string.loginFail));
                            userLoginBinding.info.setTextColor(Color.parseColor("#FF0000"));
                            isUnLogin();
                        }
                        userLoginViewModel.setmTvllVisibleStatus(true);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        userLoginBinding.editUser.addTextChangedListener(watcher);
        userLoginBinding.editPass.addTextChangedListener(watcher);
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
            userLoginViewModel.setmLoginEnableStatus(userLoginBinding.editUser.getText().length() > 0 && userLoginBinding.editPass.getText().length() > 0);
        }
    };

    public void isLogin() {
        userLoginViewModel.setmBtLoginedVisibleStatus(true);
        userLoginViewModel.setmBtUnLoginedVisibleStatus(false);
        userLoginViewModel.setmLoginEnableStatus(false);
    }

    public void isUnLogin() {
        userLoginViewModel.setmBtLoginedVisibleStatus(false);
        userLoginViewModel.setmBtUnLoginedVisibleStatus(true);
    }

    public void dialogShow(String str, int i) {
        if (dialog == null) {
            dialog = new MyDialog(getContext());
            dialog.setsMessage(str)
                    .setsCancel(getResources().getString(R.string.cancel), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    }).setsConfirm(getResources().getString(R.string.confirm), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            switch (i) {
                                case REGISTER_DIALOG:
//                                    Intent intent1 = new Intent(UserLoginActivity.this, UserRegisterActivity.class);
//                                    startActivity(intent1);
                                    dialog.dismiss();
                                    break;
                                case LOGOUT_DIALOG:
                                    int id = 0;
                                    try {
                                        id = serviceManager.onDeleteUser(mId);
                                        isUnLogin();
                                        userLoginViewModel.setmTvllVisibleStatus(false);
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                    Log.i(Constant.TAG, "UserLoginActivity resolver.delete id = " + id);
                                    if (id > 0) {
                                        Log.i(Constant.TAG, "UserLoginActivity delete logout OK!");
                                    }
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    }).show();
        } else {
            dialog.show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
