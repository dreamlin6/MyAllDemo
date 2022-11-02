package com.example.myaccount.databinding;
import com.example.myaccount.R;
import com.example.myaccount.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityRegisterBindingImpl extends ActivityRegisterBinding implements com.example.myaccount.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.editUser, 2);
        sViewsWithIds.put(R.id.editAccount, 3);
        sViewsWithIds.put(R.id.editPass, 4);
        sViewsWithIds.put(R.id.editPass2, 5);
        sViewsWithIds.put(R.id.back, 6);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityRegisterBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private ActivityRegisterBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[6]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[5]
            , (android.widget.EditText) bindings[2]
            , (android.widget.Button) bindings[1]
            );
        this.login.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.example.myaccount.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        if (BR.userregistervm == variableId) {
            setUserregistervm((com.example.myaccount.viewmodel.UserRegisterViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setUserregistervm(@Nullable com.example.myaccount.viewmodel.UserRegisterViewModel Userregistervm) {
        this.mUserregistervm = Userregistervm;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.userregistervm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUserregistervmMRegisterBtnEnable((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUserregistervmMRegisterBtnEnable(androidx.lifecycle.MutableLiveData<java.lang.Boolean> UserregistervmMRegisterBtnEnable, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
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
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> userregistervmMRegisterBtnEnable = null;
        java.lang.Boolean userregistervmMRegisterBtnEnableGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxUserregistervmMRegisterBtnEnableGetValue = false;
        com.example.myaccount.viewmodel.UserRegisterViewModel userregistervm = mUserregistervm;

        if ((dirtyFlags & 0x7L) != 0) {



                if (userregistervm != null) {
                    // read userregistervm.mRegisterBtnEnable
                    userregistervmMRegisterBtnEnable = userregistervm.mRegisterBtnEnable;
                }
                updateLiveDataRegistration(0, userregistervmMRegisterBtnEnable);


                if (userregistervmMRegisterBtnEnable != null) {
                    // read userregistervm.mRegisterBtnEnable.getValue()
                    userregistervmMRegisterBtnEnableGetValue = userregistervmMRegisterBtnEnable.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(userregistervm.mRegisterBtnEnable.getValue())
                androidxDatabindingViewDataBindingSafeUnboxUserregistervmMRegisterBtnEnableGetValue = androidx.databinding.ViewDataBinding.safeUnbox(userregistervmMRegisterBtnEnableGetValue);
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.login.setOnClickListener(mCallback1);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.login.setEnabled(androidxDatabindingViewDataBindingSafeUnboxUserregistervmMRegisterBtnEnableGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // userregistervm
        com.example.myaccount.viewmodel.UserRegisterViewModel userregistervm = mUserregistervm;
        // userregistervm != null
        boolean userregistervmJavaLangObjectNull = false;



        userregistervmJavaLangObjectNull = (userregistervm) != (null);
        if (userregistervmJavaLangObjectNull) {


            userregistervm.mRegisterBtnOnclick();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): userregistervm.mRegisterBtnEnable
        flag 1 (0x2L): userregistervm
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}