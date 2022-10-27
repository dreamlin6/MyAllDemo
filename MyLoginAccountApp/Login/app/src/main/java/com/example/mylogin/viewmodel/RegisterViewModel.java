package com.example.mylogin.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    public MutableLiveData<Boolean> mRegisterBtnEnable;
    public MutableLiveData<Boolean> mRegisterDialogCtrl;

    public RegisterViewModel() {
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
