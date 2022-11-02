package com.example.myaccount.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminViewModel extends ViewModel {

    public MutableLiveData<Boolean> mDeleteEnableStatus;
    public MutableLiveData<Boolean> mDeleteBtEnableStatus;
    public MutableLiveData<Boolean> mDeleteAllBbtEnableStatus;

    public AdminViewModel() {
        this.mDeleteEnableStatus = new MutableLiveData<>();
        this.mDeleteBtEnableStatus = new MutableLiveData<>();
        this.mDeleteAllBbtEnableStatus = new MutableLiveData<>();
    }

    public void setmDeleteEnableStatus(boolean isCheck) {
        mDeleteEnableStatus.postValue(isCheck);
    }

    public void setmDeleteBbtEnableStatus(boolean isEnable) {
        mDeleteBtEnableStatus.postValue(isEnable);
    }

    public void setmDeleteAllBbtEnableStatus(boolean isEnable) {
        mDeleteAllBbtEnableStatus.postValue(isEnable);
    }
}
