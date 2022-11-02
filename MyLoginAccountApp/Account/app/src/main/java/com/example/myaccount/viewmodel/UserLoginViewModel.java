package com.example.myaccount.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserLoginViewModel extends ViewModel {

    public MutableLiveData<Boolean> mLoginEnableStatus;
    public MutableLiveData<Boolean> mBtLoginedVisibleStatus;
    public MutableLiveData<Boolean> mBtUnLoginedVisibleStatus;
    public MutableLiveData<Boolean> mTvllVisibleStatus;

    public UserLoginViewModel() {
        this.mLoginEnableStatus = new MutableLiveData<>();
        this.mBtLoginedVisibleStatus = new MutableLiveData<>(Boolean.FALSE);
        this.mBtUnLoginedVisibleStatus = new MutableLiveData<>(Boolean.TRUE);
        this.mTvllVisibleStatus = new MutableLiveData<>(Boolean.FALSE);
    }

    public void setmLoginEnableStatus(Boolean isLogin) {
        mLoginEnableStatus.postValue(isLogin);
    }

    public void setmBtLoginedVisibleStatus(Boolean isVisible) {
        mBtLoginedVisibleStatus.postValue(isVisible);
    }

    public void setmTvllVisibleStatus(Boolean isVisible) {
        mTvllVisibleStatus.postValue(isVisible);
    }

    public void setmBtUnLoginedVisibleStatus(Boolean isVisible) {
        mBtUnLoginedVisibleStatus.postValue(isVisible);
    }
}
