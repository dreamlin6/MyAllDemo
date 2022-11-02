package com.example.myaccount.databinding;
import com.example.myaccount.R;
import com.example.myaccount.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityLoginBindingImpl extends ActivityLoginBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.editUser, 8);
        sViewsWithIds.put(R.id.editPass, 9);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityLoginBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private ActivityLoginBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 4
            , (android.widget.Button) bindings[4]
            , (android.widget.EditText) bindings[9]
            , (android.widget.EditText) bindings[8]
            , (android.widget.TextView) bindings[1]
            , (android.widget.Button) bindings[2]
            , (android.widget.Button) bindings[6]
            , (android.widget.Button) bindings[7]
            , (android.widget.Button) bindings[3]
            , (android.widget.Button) bindings[5]
            );
        this.change.setTag(null);
        this.info.setTag(null);
        this.login.setTag(null);
        this.logout.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.quit.setTag(null);
        this.register.setTag(null);
        this.tohome.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x20L;
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
        if (BR.userloginvm == variableId) {
            setUserloginvm((com.example.myaccount.viewmodel.UserLoginViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUserloginvm(@Nullable com.example.myaccount.viewmodel.UserLoginViewModel Userloginvm) {
        this.mUserloginvm = Userloginvm;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.userloginvm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUserloginvmMTvllVisibleStatus((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeUserloginvmMLoginEnableStatus((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeUserloginvmMBtUnLoginedVisibleStatus((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 3 :
                return onChangeUserloginvmMBtLoginedVisibleStatus((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUserloginvmMTvllVisibleStatus(androidx.lifecycle.MutableLiveData<java.lang.Boolean> UserloginvmMTvllVisibleStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeUserloginvmMLoginEnableStatus(androidx.lifecycle.MutableLiveData<java.lang.Boolean> UserloginvmMLoginEnableStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeUserloginvmMBtUnLoginedVisibleStatus(androidx.lifecycle.MutableLiveData<java.lang.Boolean> UserloginvmMBtUnLoginedVisibleStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeUserloginvmMBtLoginedVisibleStatus(androidx.lifecycle.MutableLiveData<java.lang.Boolean> UserloginvmMBtLoginedVisibleStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
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
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> userloginvmMTvllVisibleStatus = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxUserloginvmMBtLoginedVisibleStatusGetValue = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxUserloginvmMLoginEnableStatusGetValue = false;
        java.lang.Boolean userloginvmMBtUnLoginedVisibleStatusGetValue = null;
        int userloginvmMBtLoginedVisibleStatusViewVISIBLEViewINVISIBLE = 0;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> userloginvmMLoginEnableStatus = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxUserloginvmMBtUnLoginedVisibleStatusGetValue = false;
        java.lang.Boolean userloginvmMBtLoginedVisibleStatusGetValue = null;
        java.lang.Boolean userloginvmMLoginEnableStatusGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxUserloginvmMTvllVisibleStatusGetValue = false;
        int userloginvmMTvllVisibleStatusViewVISIBLEViewINVISIBLE = 0;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> userloginvmMBtUnLoginedVisibleStatus = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> userloginvmMBtLoginedVisibleStatus = null;
        java.lang.Boolean userloginvmMTvllVisibleStatusGetValue = null;
        com.example.myaccount.viewmodel.UserLoginViewModel userloginvm = mUserloginvm;

        if ((dirtyFlags & 0x3fL) != 0) {


            if ((dirtyFlags & 0x31L) != 0) {

                    if (userloginvm != null) {
                        // read userloginvm.mTvllVisibleStatus
                        userloginvmMTvllVisibleStatus = userloginvm.mTvllVisibleStatus;
                    }
                    updateLiveDataRegistration(0, userloginvmMTvllVisibleStatus);


                    if (userloginvmMTvllVisibleStatus != null) {
                        // read userloginvm.mTvllVisibleStatus.getValue()
                        userloginvmMTvllVisibleStatusGetValue = userloginvmMTvllVisibleStatus.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mTvllVisibleStatus.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxUserloginvmMTvllVisibleStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(userloginvmMTvllVisibleStatusGetValue);
                if((dirtyFlags & 0x31L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxUserloginvmMTvllVisibleStatusGetValue) {
                            dirtyFlags |= 0x200L;
                    }
                    else {
                            dirtyFlags |= 0x100L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mTvllVisibleStatus.getValue()) ? View.VISIBLE : View.INVISIBLE
                    userloginvmMTvllVisibleStatusViewVISIBLEViewINVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxUserloginvmMTvllVisibleStatusGetValue) ? (android.view.View.VISIBLE) : (android.view.View.INVISIBLE));
            }
            if ((dirtyFlags & 0x32L) != 0) {

                    if (userloginvm != null) {
                        // read userloginvm.mLoginEnableStatus
                        userloginvmMLoginEnableStatus = userloginvm.mLoginEnableStatus;
                    }
                    updateLiveDataRegistration(1, userloginvmMLoginEnableStatus);


                    if (userloginvmMLoginEnableStatus != null) {
                        // read userloginvm.mLoginEnableStatus.getValue()
                        userloginvmMLoginEnableStatusGetValue = userloginvmMLoginEnableStatus.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mLoginEnableStatus.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxUserloginvmMLoginEnableStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(userloginvmMLoginEnableStatusGetValue);
            }
            if ((dirtyFlags & 0x34L) != 0) {

                    if (userloginvm != null) {
                        // read userloginvm.mBtUnLoginedVisibleStatus
                        userloginvmMBtUnLoginedVisibleStatus = userloginvm.mBtUnLoginedVisibleStatus;
                    }
                    updateLiveDataRegistration(2, userloginvmMBtUnLoginedVisibleStatus);


                    if (userloginvmMBtUnLoginedVisibleStatus != null) {
                        // read userloginvm.mBtUnLoginedVisibleStatus.getValue()
                        userloginvmMBtUnLoginedVisibleStatusGetValue = userloginvmMBtUnLoginedVisibleStatus.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mBtUnLoginedVisibleStatus.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxUserloginvmMBtUnLoginedVisibleStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(userloginvmMBtUnLoginedVisibleStatusGetValue);
            }
            if ((dirtyFlags & 0x38L) != 0) {

                    if (userloginvm != null) {
                        // read userloginvm.mBtLoginedVisibleStatus
                        userloginvmMBtLoginedVisibleStatus = userloginvm.mBtLoginedVisibleStatus;
                    }
                    updateLiveDataRegistration(3, userloginvmMBtLoginedVisibleStatus);


                    if (userloginvmMBtLoginedVisibleStatus != null) {
                        // read userloginvm.mBtLoginedVisibleStatus.getValue()
                        userloginvmMBtLoginedVisibleStatusGetValue = userloginvmMBtLoginedVisibleStatus.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mBtLoginedVisibleStatus.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxUserloginvmMBtLoginedVisibleStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(userloginvmMBtLoginedVisibleStatusGetValue);
                if((dirtyFlags & 0x38L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxUserloginvmMBtLoginedVisibleStatusGetValue) {
                            dirtyFlags |= 0x80L;
                    }
                    else {
                            dirtyFlags |= 0x40L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mBtLoginedVisibleStatus.getValue()) ? View.VISIBLE : View.INVISIBLE
                    userloginvmMBtLoginedVisibleStatusViewVISIBLEViewINVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxUserloginvmMBtLoginedVisibleStatusGetValue) ? (android.view.View.VISIBLE) : (android.view.View.INVISIBLE));
            }
        }
        // batch finished
        if ((dirtyFlags & 0x38L) != 0) {
            // api target 1

            this.change.setVisibility(userloginvmMBtLoginedVisibleStatusViewVISIBLEViewINVISIBLE);
            this.logout.setVisibility(userloginvmMBtLoginedVisibleStatusViewVISIBLEViewINVISIBLE);
            this.quit.setVisibility(userloginvmMBtLoginedVisibleStatusViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 0x31L) != 0) {
            // api target 1

            this.info.setVisibility(userloginvmMTvllVisibleStatusViewVISIBLEViewINVISIBLE);
        }
        if ((dirtyFlags & 0x32L) != 0) {
            // api target 1

            this.login.setEnabled(androidxDatabindingViewDataBindingSafeUnboxUserloginvmMLoginEnableStatusGetValue);
        }
        if ((dirtyFlags & 0x34L) != 0) {
            // api target 1

            com.example.myaccount.adapter.UserLoginBindingAdapter.setBtUnLoginVisible(this.register, androidxDatabindingViewDataBindingSafeUnboxUserloginvmMBtUnLoginedVisibleStatusGetValue);
            com.example.myaccount.adapter.UserLoginBindingAdapter.setBtUnLoginVisible(this.tohome, androidxDatabindingViewDataBindingSafeUnboxUserloginvmMBtUnLoginedVisibleStatusGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): userloginvm.mTvllVisibleStatus
        flag 1 (0x2L): userloginvm.mLoginEnableStatus
        flag 2 (0x3L): userloginvm.mBtUnLoginedVisibleStatus
        flag 3 (0x4L): userloginvm.mBtLoginedVisibleStatus
        flag 4 (0x5L): userloginvm
        flag 5 (0x6L): null
        flag 6 (0x7L): androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mBtLoginedVisibleStatus.getValue()) ? View.VISIBLE : View.INVISIBLE
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mBtLoginedVisibleStatus.getValue()) ? View.VISIBLE : View.INVISIBLE
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mTvllVisibleStatus.getValue()) ? View.VISIBLE : View.INVISIBLE
        flag 9 (0xaL): androidx.databinding.ViewDataBinding.safeUnbox(userloginvm.mTvllVisibleStatus.getValue()) ? View.VISIBLE : View.INVISIBLE
    flag mapping end*/
    //end
}