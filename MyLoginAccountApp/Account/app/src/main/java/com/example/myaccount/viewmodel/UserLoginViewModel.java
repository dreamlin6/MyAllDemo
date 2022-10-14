package com.example.myaccount.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserLoginViewModel extends ViewModel {

    public MutableLiveData<Boolean> mLoginEnableStatus;

    public UserLoginViewModel() {
        this.mLoginEnableStatus = new MutableLiveData<>();
    }

    public void setmLoginEnableStatus(Boolean isLogin) {
        mLoginEnableStatus.postValue(isLogin);
    }
}
