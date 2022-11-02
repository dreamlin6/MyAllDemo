package com.example.myaccount.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserRegisterViewModel extends ViewModel {

    public MutableLiveData<Boolean> mRegisterBtnEnable;
    public MutableLiveData<Boolean> mRegisterDialogCtrl;

    public UserRegisterViewModel() {
        this.mRegisterBtnEnable = new MutableLiveData<>();
        this.mRegisterDialogCtrl = new MutableLiveData<>();
    }

    public void setmRegisterBtnEnable(boolean isEnable) {
        mRegisterBtnEnable.postValue(isEnable);
    }

    public MutableLiveData<Boolean> getmRegisterDialogCtrl() {
        return mRegisterDialogCtrl;
    }

    public void mRegisterBtnOnclick() {
        mRegisterDialogCtrl.setValue(true);
    }

}
