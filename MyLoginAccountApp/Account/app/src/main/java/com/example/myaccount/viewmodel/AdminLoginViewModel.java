package com.example.myaccount.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myaccount.constant.Constant;

public class AdminLoginViewModel extends ViewModel {

    private final String TAG = "AdminLoginViewModel";
    public MutableLiveData<Boolean> mAdminLoginStatus;
    public MutableLiveData<Boolean> mEditStatus;

    public AdminLoginViewModel() {
        this.mAdminLoginStatus = new MutableLiveData<>(Constant.mBool);
        this.mEditStatus = new MutableLiveData<>(Constant.mBool);
    }

    public void setmAdminLoginStatus(boolean isLogin) {
        mAdminLoginStatus.postValue(isLogin);
    }

    public void setmEditStatus(boolean isEdit) {
        mEditStatus.postValue(isEdit);
    }

    public void mAdminLogin(String user, String pass) {
        if (user.equals("1") && pass.equals("1")) {
            Log.i("TAG", "mAdminLogin TRUE");
            mAdminLoginStatus.setValue(true);
            updateLoginDialog();
        } else {
            Log.i("TAG", "mAdminLogin FALSE");
            mAdminLoginStatus.setValue(false);
        }
    }

    public void updateLoginDialog() {

    }
}
