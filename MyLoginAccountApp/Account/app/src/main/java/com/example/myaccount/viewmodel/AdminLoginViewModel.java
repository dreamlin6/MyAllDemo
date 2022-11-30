package com.example.myaccount.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myaccount.constant.Constant;

public class AdminLoginViewModel extends ViewModel {

    public MutableLiveData<Integer> mAdminLoginStatus;
    public MutableLiveData<Boolean> mEditStatus;
    public MutableLiveData<Boolean> mExistAdmin;
    public MutableLiveData<String> mLoginStatusTips;

    public AdminLoginViewModel() {
        this.mAdminLoginStatus = new MutableLiveData<>();
        this.mEditStatus = new MutableLiveData<>(Constant.mBool);
        this.mExistAdmin = new MutableLiveData<>(Constant.mBool);
        this.mLoginStatusTips = new MutableLiveData<>(Constant.mWAIT);
    }

    public void setAdminLoginStatus(Integer isLogin) {
        mAdminLoginStatus.postValue(isLogin);
    }

    public MutableLiveData<Integer> getAdminLoginStatus() {
        return mAdminLoginStatus;
    }

    public void setEditStatus(boolean isEdit) {
        mEditStatus.postValue(isEdit);
    }

    public void setIsNoAdmin(boolean isNoAdmin) {
        mExistAdmin.postValue(isNoAdmin);
    }

}
