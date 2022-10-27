package com.example.mylogin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mylogin.util.Constant;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<Boolean> mIsLoginVisible;
    public MutableLiveData<Boolean> mLoginBtEnable;
    public MutableLiveData<String> mInfo;

    public LoginViewModel() {
        this.mIsLoginVisible = new MutableLiveData<>(Boolean.FALSE);
        this.mLoginBtEnable = new MutableLiveData<>();
        this.mInfo = new MutableLiveData<>(Constant.WAIT);
    }

    public void setmIsLoginVisible(boolean isLoginVisible) {
        mIsLoginVisible.postValue(isLoginVisible);
    }

    public void setmLoginBtEnable(boolean isEnable) {
        mLoginBtEnable.postValue(isEnable);
    }
}
