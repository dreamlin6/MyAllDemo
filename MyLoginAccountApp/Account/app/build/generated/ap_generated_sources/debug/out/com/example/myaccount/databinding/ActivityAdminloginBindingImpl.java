package com.example.myaccount.databinding;
import com.example.myaccount.R;
import com.example.myaccount.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAdminloginBindingImpl extends ActivityAdminloginBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.editAdminUser, 3);
        sViewsWithIds.put(R.id.editAdminPass, 4);
        sViewsWithIds.put(R.id.tohome, 5);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAdminloginBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private ActivityAdminloginBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.Button) bindings[2]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[3]
            , (android.widget.TextView) bindings[1]
            , (android.widget.Button) bindings[5]
            );
        this.adminLogin.setTag(null);
        this.loginTips.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
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
        if (BR.adminloginvm == variableId) {
            setAdminloginvm((com.example.myaccount.viewmodel.AdminLoginViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setAdminloginvm(@Nullable com.example.myaccount.viewmodel.AdminLoginViewModel Adminloginvm) {
        this.mAdminloginvm = Adminloginvm;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.adminloginvm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeAdminloginvmMEditStatus((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeAdminloginvmMAdminLoginStatus((androidx.lifecycle.MutableLiveData<java.lang.Integer>) object, fieldId);
            case 2 :
                return onChangeAdminloginvmMLoginStatusTips((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeAdminloginvmMEditStatus(androidx.lifecycle.MutableLiveData<java.lang.Boolean> AdminloginvmMEditStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeAdminloginvmMAdminLoginStatus(androidx.lifecycle.MutableLiveData<java.lang.Integer> AdminloginvmMAdminLoginStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeAdminloginvmMLoginStatusTips(androidx.lifecycle.MutableLiveData<java.lang.String> AdminloginvmMLoginStatusTips, int fieldId) {
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
        java.lang.Integer adminloginvmMAdminLoginStatusGetValue = null;
        java.lang.Boolean adminloginvmMEditStatusGetValue = null;
        com.example.myaccount.viewmodel.AdminLoginViewModel adminloginvm = mAdminloginvm;
        int androidxDatabindingViewDataBindingSafeUnboxAdminloginvmMAdminLoginStatusGetValue = 0;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> adminloginvmMEditStatus = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxAdminloginvmMEditStatusGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Integer> adminloginvmMAdminLoginStatus = null;
        java.lang.String adminloginvmMLoginStatusTipsGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> adminloginvmMLoginStatusTips = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (adminloginvm != null) {
                        // read adminloginvm.mEditStatus
                        adminloginvmMEditStatus = adminloginvm.mEditStatus;
                    }
                    updateLiveDataRegistration(0, adminloginvmMEditStatus);


                    if (adminloginvmMEditStatus != null) {
                        // read adminloginvm.mEditStatus.getValue()
                        adminloginvmMEditStatusGetValue = adminloginvmMEditStatus.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(adminloginvm.mEditStatus.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxAdminloginvmMEditStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(adminloginvmMEditStatusGetValue);
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (adminloginvm != null) {
                        // read adminloginvm.mAdminLoginStatus
                        adminloginvmMAdminLoginStatus = adminloginvm.mAdminLoginStatus;
                    }
                    updateLiveDataRegistration(1, adminloginvmMAdminLoginStatus);


                    if (adminloginvmMAdminLoginStatus != null) {
                        // read adminloginvm.mAdminLoginStatus.getValue()
                        adminloginvmMAdminLoginStatusGetValue = adminloginvmMAdminLoginStatus.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(adminloginvm.mAdminLoginStatus.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxAdminloginvmMAdminLoginStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(adminloginvmMAdminLoginStatusGetValue);
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (adminloginvm != null) {
                        // read adminloginvm.mLoginStatusTips
                        adminloginvmMLoginStatusTips = adminloginvm.mLoginStatusTips;
                    }
                    updateLiveDataRegistration(2, adminloginvmMLoginStatusTips);


                    if (adminloginvmMLoginStatusTips != null) {
                        // read adminloginvm.mLoginStatusTips.getValue()
                        adminloginvmMLoginStatusTipsGetValue = adminloginvmMLoginStatusTips.getValue();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            this.adminLogin.setEnabled(androidxDatabindingViewDataBindingSafeUnboxAdminloginvmMEditStatusGetValue);
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.loginTips, adminloginvmMLoginStatusTipsGetValue);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            com.example.myaccount.adapter.AdminLoginBindingAdapter.setTextColor(this.loginTips, androidxDatabindingViewDataBindingSafeUnboxAdminloginvmMAdminLoginStatusGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): adminloginvm.mEditStatus
        flag 1 (0x2L): adminloginvm.mAdminLoginStatus
        flag 2 (0x3L): adminloginvm.mLoginStatusTips
        flag 3 (0x4L): adminloginvm
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}