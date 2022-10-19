package com.example.myaccount.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserChangeViewModel extends ViewModel {
    public MutableLiveData<Boolean> mChangeEnableStatus;

    public UserChangeViewModel() {
        this.mChangeEnableStatus = new MutableLiveData<>();
    }

    public void setmChangeEnableStatus(Boolean isChange) {
        mChangeEnableStatus.postValue(isChange);
    }
}
