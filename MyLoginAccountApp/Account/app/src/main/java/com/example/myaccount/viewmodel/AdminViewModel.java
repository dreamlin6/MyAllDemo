package com.example.myaccount.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminViewModel extends ViewModel {

    public MutableLiveData<Boolean> mDeleteEnableStatus;

    public AdminViewModel() {
        this.mDeleteEnableStatus = new MutableLiveData<>();
    }

    public void setmDeleteEnableStatus(boolean isCheck) {
        mDeleteEnableStatus.postValue(isCheck);
    }
}
