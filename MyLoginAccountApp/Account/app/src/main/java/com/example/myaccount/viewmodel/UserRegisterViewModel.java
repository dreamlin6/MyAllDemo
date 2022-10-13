package com.example.myaccount.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserRegisterViewModel extends ViewModel {

    public MutableLiveData<Boolean> mRegisterBtnEnable;

    public UserRegisterViewModel() {
        this.mRegisterBtnEnable = new MutableLiveData<>();
    }

    public void setmRegisterBtnEnable(boolean isEnable) {
        mRegisterBtnEnable.postValue(isEnable);
    }

}
