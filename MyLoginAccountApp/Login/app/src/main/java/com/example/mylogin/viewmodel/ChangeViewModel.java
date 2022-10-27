package com.example.mylogin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChangeViewModel extends ViewModel {

    public MutableLiveData<Boolean> mChangeEnableStatus;

    public ChangeViewModel() {
        this.mChangeEnableStatus = new MutableLiveData<>();
    }

    public void setmChangeEnableStatus(boolean isEnable) {
        mChangeEnableStatus.postValue(isEnable);
    }

}
