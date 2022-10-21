package com.example.myaccount.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminViewModel extends ViewModel {

    public MutableLiveData<Boolean> mDeleteEnableStatus;
    public MutableLiveData<Boolean> mDeletBbtEnableStatus;

    public AdminViewModel() {
        this.mDeleteEnableStatus = new MutableLiveData<>();
        this.mDeletBbtEnableStatus = new MutableLiveData<>();
    }

    public void setmDeleteEnableStatus(boolean isCheck) {
        mDeleteEnableStatus.postValue(isCheck);
    }

    public void setmDeletBbtEnableStatus(boolean isEnable) {
        mDeletBbtEnableStatus.postValue(isEnable);
    }
}
