package com.example.mylogin.databinding;
import com.example.mylogin.R;
import com.example.mylogin.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityChangeBindingImpl extends ActivityChangeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.oldpass, 2);
        sViewsWithIds.put(R.id.newpass, 3);
        sViewsWithIds.put(R.id.newpass2, 4);
        sViewsWithIds.put(R.id.back, 5);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityChangeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private ActivityChangeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.Button) bindings[5]
            , (android.widget.Button) bindings[1]
            , (android.widget.EditText) bindings[3]
            , (android.widget.EditText) bindings[4]
            , (android.widget.EditText) bindings[2]
            );
        this.changepass.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
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
        if (BR.changevm == variableId) {
            setChangevm((com.example.mylogin.viewmodel.ChangeViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setChangevm(@Nullable com.example.mylogin.viewmodel.ChangeViewModel Changevm) {
        this.mChangevm = Changevm;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.changevm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeChangevmMChangeEnableStatus((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeChangevmMChangeEnableStatus(androidx.lifecycle.MutableLiveData<java.lang.Boolean> ChangevmMChangeEnableStatus, int fieldId) {
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
        java.lang.Boolean changevmMChangeEnableStatusGetValue = null;
        com.example.mylogin.viewmodel.ChangeViewModel changevm = mChangevm;
        boolean androidxDatabindingViewDataBindingSafeUnboxChangevmMChangeEnableStatusGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> changevmMChangeEnableStatus = null;

        if ((dirtyFlags & 0x7L) != 0) {



                if (changevm != null) {
                    // read changevm.mChangeEnableStatus
                    changevmMChangeEnableStatus = changevm.mChangeEnableStatus;
                }
                updateLiveDataRegistration(0, changevmMChangeEnableStatus);


                if (changevmMChangeEnableStatus != null) {
                    // read changevm.mChangeEnableStatus.getValue()
                    changevmMChangeEnableStatusGetValue = changevmMChangeEnableStatus.getValue();
                }


                // read androidx.databinding.ViewDataBinding.safeUnbox(changevm.mChangeEnableStatus.getValue())
                androidxDatabindingViewDataBindingSafeUnboxChangevmMChangeEnableStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(changevmMChangeEnableStatusGetValue);
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.changepass.setEnabled(androidxDatabindingViewDataBindingSafeUnboxChangevmMChangeEnableStatusGetValue);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): changevm.mChangeEnableStatus
        flag 1 (0x2L): changevm
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}