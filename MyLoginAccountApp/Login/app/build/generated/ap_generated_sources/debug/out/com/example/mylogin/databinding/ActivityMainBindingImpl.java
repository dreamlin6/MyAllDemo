package com.example.mylogin.databinding;
import com.example.mylogin.R;
import com.example.mylogin.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMainBindingImpl extends ActivityMainBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private ActivityMainBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.Button) bindings[7]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[2]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[4]
            , (android.widget.Button) bindings[5]
            , (android.widget.Button) bindings[8]
            , (android.widget.Button) bindings[9]
            , (android.widget.Button) bindings[6]
            );
        this.change.setTag(null);
        this.editPass.setTag(null);
        this.editUser.setTag(null);
        this.info.setTag(null);
        this.info1.setTag(null);
        this.login.setTag(null);
        this.logout.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.quit.setTag(null);
        this.register.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.loginvm == variableId) {
            setLoginvm((com.example.mylogin.viewmodel.LoginViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setLoginvm(@Nullable com.example.mylogin.viewmodel.LoginViewModel Loginvm) {
        this.mLoginvm = Loginvm;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.loginvm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeLoginvmMLoginBtEnable((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeLoginvmMInfo((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeLoginvmMIsLoginVisible((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeLoginvmMLoginBtEnable(androidx.lifecycle.MutableLiveData<java.lang.Boolean> LoginvmMLoginBtEnable, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeLoginvmMInfo(androidx.lifecycle.MutableLiveData<java.lang.String> LoginvmMInfo, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeLoginvmMIsLoginVisible(androidx.lifecycle.MutableLiveData<java.lang.Boolean> LoginvmMIsLoginVisible, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        int loginvmMIsLoginVisibleViewINVISIBLEViewVISIBLE = 0;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> loginvmMLoginBtEnable = null;
        int loginvmMIsLoginVisibleViewVISIBLEViewINVISIBLE = 0;
        androidx.lifecycle.MutableLiveData<java.lang.String> loginvmMInfo = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxLoginvmMLoginBtEnableGetValue = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxLoginvmMIsLoginVisibleGetValue = false;
        java.lang.Boolean loginvmMLoginBtEnableGetValue = null;
        java.lang.Boolean loginvmMIsLoginVisibleGetValue = null;
        com.example.mylogin.viewmodel.LoginViewModel loginvm = mLoginvm;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> loginvmMIsLoginVisible = null;
        java.lang.String loginvmMInfoGetValue = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (loginvm != null) {
                        // read loginvm.mLoginBtEnable
                        loginvmMLoginBtEnable = loginvm.mLoginBtEnable;
                    }
                    updateLiveDataRegistration(0, loginvmMLoginBtEnable);


                    if (loginvmMLoginBtEnable != null) {
                        // read loginvm.mLoginBtEnable.getValue()
                        loginvmMLoginBtEnableGetValue = loginvmMLoginBtEnable.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(loginvm.mLoginBtEnable.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxLoginvmMLoginBtEnableGetValue = androidx.databinding.ViewDataBinding.safeUnbox(loginvmMLoginBtEnableGetValue);
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (loginvm != null) {
                        // read loginvm.mInfo
                        loginvmMInfo = loginvm.mInfo;
                    }
                    updateLiveDataRegistration(1, loginvmMInfo);


                    if (loginvmMInfo != null) {
                        // read loginvm.mInfo.getValue()
                        loginvmMInfoGetValue = loginvmMInfo.getValue();
                    }
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (loginvm != null) {
                        // read loginvm.mIsLoginVisible
                        loginvmMIsLoginVisible = loginvm.mIsLoginVisible;
                    }
                    updateLiveDataRegistration(2, loginvmMIsLoginVisible);


                    if (loginvmMIsLoginVisible != null) {
                        // read loginvm.mIsLoginVisible.getValue()
                        loginvmMIsLoginVisibleGetValue = loginvmMIsLoginVisible.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(loginvm.mIsLoginVisible.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxLoginvmMIsLoginVisibleGetValue = androidx.databinding.ViewDataBinding.safeUnbox(loginvmMIsLoginVisibleGetValue);
                if((dirtyFlags & 0x1cL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxLoginvmMIsLoginVisibleGetValue) {
                            dirtyFlags |= 0x40L;
                            dirtyFlags |= 0x100L;
                    }
                    else {
                            dirtyFlags |= 0x20L;
                            dirtyFlags |= 0x80L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(loginvm.mIsLoginVisible.getValue()) ? View.INVISIBLE : View.VISIBLE
                    loginvmMIsLoginVisibleViewINVISIBLEViewVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxLoginvmMIsLoginVisibleGetValue) ? (android.view.View.INVISIBLE) : (android.view.View.VISIBLE));
                    // read androidx.databinding.ViewDataBinding.safeUnbox(loginvm.mIsLoginVisible.getValue()) ? View.VISIBLE : View.INVISIBLE
                    loginvmMIsLoginVisibleViewVISIBLEViewINVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxLoginvmMIsLoginVisibleGetValue) ? (android.view.View.VISIBLE) : (android.view.View.INVISIBLE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            this.change.setVisibility(loginvmMIsLoginVisibleViewVISIBLEViewINVISIBLE);
            this.editPass.setVisibility(loginvmMIsLoginVisibleViewINVISIBLEViewVISIBLE);
            this.editUser.setVisibility(loginvmMIsLoginVisibleViewINVISIBLEViewVISIBLE);
            this.info1.setVisibility(loginvmMIsLoginVisibleViewVISIBLEViewINVISIBLE);
            this.login.setVisibility(loginvmMIsLoginVisibleViewINVISIBLEViewVISIBLE);
            this.logout.setVisibility(loginvmMIsLoginVisibleViewVISIBLEViewINVISIBLE);
            this.quit.setVisibility(loginvmMIsLoginVisibleViewVISIBLEViewINVISIBLE);
            this.register.setVisibility(loginvmMIsLoginVisibleViewINVISIBLEViewVISIBLE);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.info, loginvmMInfoGetValue);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            this.login.setEnabled(androidxDatabindingViewDataBindingSafeUnboxLoginvmMLoginBtEnableGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): loginvm.mLoginBtEnable
        flag 1 (0x2L): loginvm.mInfo
        flag 2 (0x3L): loginvm.mIsLoginVisible
        flag 3 (0x4L): loginvm
        flag 4 (0x5L): null
        flag 5 (0x6L): androidx.databinding.ViewDataBinding.safeUnbox(loginvm.mIsLoginVisible.getValue()) ? View.INVISIBLE : View.VISIBLE
        flag 6 (0x7L): androidx.databinding.ViewDataBinding.safeUnbox(loginvm.mIsLoginVisible.getValue()) ? View.INVISIBLE : View.VISIBLE
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(loginvm.mIsLoginVisible.getValue()) ? View.VISIBLE : View.INVISIBLE
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(loginvm.mIsLoginVisible.getValue()) ? View.VISIBLE : View.INVISIBLE
    flag mapping end*/
    //end
}