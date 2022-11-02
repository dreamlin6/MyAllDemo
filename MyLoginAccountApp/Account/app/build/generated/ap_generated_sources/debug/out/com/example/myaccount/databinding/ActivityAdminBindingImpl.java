package com.example.myaccount.databinding;
import com.example.myaccount.R;
import com.example.myaccount.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityAdminBindingImpl extends ActivityAdminBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.back, 4);
        sViewsWithIds.put(R.id.listTitle, 5);
        sViewsWithIds.put(R.id.edit, 6);
        sViewsWithIds.put(R.id.mId, 7);
        sViewsWithIds.put(R.id.userList, 8);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityAdminBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private ActivityAdminBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.ImageButton) bindings[4]
            , (android.widget.Button) bindings[2]
            , (android.widget.Button) bindings[3]
            , (android.widget.ToggleButton) bindings[6]
            , (android.widget.TextView) bindings[5]
            , (android.widget.EditText) bindings[7]
            , (androidx.recyclerview.widget.RecyclerView) bindings[8]
            );
        this.delete.setTag(null);
        this.deleteall.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
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
        if (BR.adminvm == variableId) {
            setAdminvm((com.example.myaccount.viewmodel.AdminViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setAdminvm(@Nullable com.example.myaccount.viewmodel.AdminViewModel Adminvm) {
        this.mAdminvm = Adminvm;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.adminvm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeAdminvmMDeleteEnableStatus((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 1 :
                return onChangeAdminvmMDeleteAllBbtEnableStatus((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
            case 2 :
                return onChangeAdminvmMDeleteBtEnableStatus((androidx.lifecycle.MutableLiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeAdminvmMDeleteEnableStatus(androidx.lifecycle.MutableLiveData<java.lang.Boolean> AdminvmMDeleteEnableStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeAdminvmMDeleteAllBbtEnableStatus(androidx.lifecycle.MutableLiveData<java.lang.Boolean> AdminvmMDeleteAllBbtEnableStatus, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeAdminvmMDeleteBtEnableStatus(androidx.lifecycle.MutableLiveData<java.lang.Boolean> AdminvmMDeleteBtEnableStatus, int fieldId) {
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
        java.lang.Boolean adminvmMDeleteAllBbtEnableStatusGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteBtEnableStatusGetValue = false;
        com.example.myaccount.viewmodel.AdminViewModel adminvm = mAdminvm;
        java.lang.Boolean adminvmMDeleteBtEnableStatusGetValue = null;
        java.lang.Boolean adminvmMDeleteEnableStatusGetValue = null;
        boolean androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteEnableStatusGetValue = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteAllBbtEnableStatusGetValue = false;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> adminvmMDeleteEnableStatus = null;
        int adminvmMDeleteEnableStatusViewVISIBLEViewGONE = 0;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> adminvmMDeleteAllBbtEnableStatus = null;
        androidx.lifecycle.MutableLiveData<java.lang.Boolean> adminvmMDeleteBtEnableStatus = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (adminvm != null) {
                        // read adminvm.mDeleteEnableStatus
                        adminvmMDeleteEnableStatus = adminvm.mDeleteEnableStatus;
                    }
                    updateLiveDataRegistration(0, adminvmMDeleteEnableStatus);


                    if (adminvmMDeleteEnableStatus != null) {
                        // read adminvm.mDeleteEnableStatus.getValue()
                        adminvmMDeleteEnableStatusGetValue = adminvmMDeleteEnableStatus.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(adminvm.mDeleteEnableStatus.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteEnableStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(adminvmMDeleteEnableStatusGetValue);
                if((dirtyFlags & 0x19L) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteEnableStatusGetValue) {
                            dirtyFlags |= 0x40L;
                    }
                    else {
                            dirtyFlags |= 0x20L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(adminvm.mDeleteEnableStatus.getValue()) ? View.VISIBLE : View.GONE
                    adminvmMDeleteEnableStatusViewVISIBLEViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteEnableStatusGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (adminvm != null) {
                        // read adminvm.mDeleteAllBbtEnableStatus
                        adminvmMDeleteAllBbtEnableStatus = adminvm.mDeleteAllBbtEnableStatus;
                    }
                    updateLiveDataRegistration(1, adminvmMDeleteAllBbtEnableStatus);


                    if (adminvmMDeleteAllBbtEnableStatus != null) {
                        // read adminvm.mDeleteAllBbtEnableStatus.getValue()
                        adminvmMDeleteAllBbtEnableStatusGetValue = adminvmMDeleteAllBbtEnableStatus.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(adminvm.mDeleteAllBbtEnableStatus.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteAllBbtEnableStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(adminvmMDeleteAllBbtEnableStatusGetValue);
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (adminvm != null) {
                        // read adminvm.mDeleteBtEnableStatus
                        adminvmMDeleteBtEnableStatus = adminvm.mDeleteBtEnableStatus;
                    }
                    updateLiveDataRegistration(2, adminvmMDeleteBtEnableStatus);


                    if (adminvmMDeleteBtEnableStatus != null) {
                        // read adminvm.mDeleteBtEnableStatus.getValue()
                        adminvmMDeleteBtEnableStatusGetValue = adminvmMDeleteBtEnableStatus.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(adminvm.mDeleteBtEnableStatus.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteBtEnableStatusGetValue = androidx.databinding.ViewDataBinding.safeUnbox(adminvmMDeleteBtEnableStatusGetValue);
            }
        }
        // batch finished
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            this.delete.setEnabled(androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteBtEnableStatusGetValue);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            this.deleteall.setEnabled(androidxDatabindingViewDataBindingSafeUnboxAdminvmMDeleteAllBbtEnableStatusGetValue);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            this.mboundView1.setVisibility(adminvmMDeleteEnableStatusViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): adminvm.mDeleteEnableStatus
        flag 1 (0x2L): adminvm.mDeleteAllBbtEnableStatus
        flag 2 (0x3L): adminvm.mDeleteBtEnableStatus
        flag 3 (0x4L): adminvm
        flag 4 (0x5L): null
        flag 5 (0x6L): androidx.databinding.ViewDataBinding.safeUnbox(adminvm.mDeleteEnableStatus.getValue()) ? View.VISIBLE : View.GONE
        flag 6 (0x7L): androidx.databinding.ViewDataBinding.safeUnbox(adminvm.mDeleteEnableStatus.getValue()) ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}