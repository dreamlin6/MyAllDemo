package com.example.myaccount.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myaccount.constant.Constant;

public class AdminLoginViewModel extends ViewModel {

    private final String LOGIN_SUCCESS = "登录成功!";
    private final String LOGIN_FAIL = "登录失败!";
    private final String ADMIN_USER = "1";
    private final String ADMIN_PASS = "1";
    private final int ONE = 1;
    private final int TWO = 2;
    public MutableLiveData<Integer> mAdminLoginStatus;
    public MutableLiveData<Boolean> mEditStatus;
    public MutableLiveData<String> mLoginStatusTips;

    public AdminLoginViewModel() {
        this.mAdminLoginStatus = new MutableLiveData<>();
        this.mEditStatus = new MutableLiveData<>(Constant.mBool);
        this.mLoginStatusTips = new MutableLiveData<>(Constant.mWAIT);
    }

    public void setmAdminLoginStatus(Integer isLogin) {
        mAdminLoginStatus.postValue(isLogin);
    }

    public MutableLiveData<Integer> getmAdminLoginStatus() {
        return mAdminLoginStatus;
    }

    public void setmEditStatus(boolean isEdit) {
        mEditStatus.postValue(isEdit);
    }

    public void mAdminLogin(String user, String pass) {
        if (user.equals(ADMIN_USER) && pass.equals(ADMIN_PASS)) {
            Log.i(Constant.TAG, "AdminLoginViewModel mAdminLogin TRUE");
            setmAdminLoginStatus(ONE);
            mLoginStatusTips.setValue(LOGIN_SUCCESS);
            updateLoginDialog();
        } else {
            Log.i(Constant.TAG, "AdminLoginViewModel mAdminLogin FALSE");
            setmAdminLoginStatus(TWO);
            mLoginStatusTips.setValue(LOGIN_FAIL);
        }
    }

    public void updateLoginDialog() {

    }
}
