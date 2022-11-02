package com.example.myaccount;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.myaccount.databinding.ActivityAdminBindingImpl;
import com.example.myaccount.databinding.ActivityAdminloginBindingImpl;
import com.example.myaccount.databinding.ActivityChangeBindingImpl;
import com.example.myaccount.databinding.ActivityLoginBindingImpl;
import com.example.myaccount.databinding.ActivityRegisterBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYADMIN = 1;

  private static final int LAYOUT_ACTIVITYADMINLOGIN = 2;

  private static final int LAYOUT_ACTIVITYCHANGE = 3;

  private static final int LAYOUT_ACTIVITYLOGIN = 4;

  private static final int LAYOUT_ACTIVITYREGISTER = 5;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(5);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.myaccount.R.layout.activity_admin, LAYOUT_ACTIVITYADMIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.myaccount.R.layout.activity_adminlogin, LAYOUT_ACTIVITYADMINLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.myaccount.R.layout.activity_change, LAYOUT_ACTIVITYCHANGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.myaccount.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.myaccount.R.layout.activity_register, LAYOUT_ACTIVITYREGISTER);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYADMIN: {
          if ("layout/activity_admin_0".equals(tag)) {
            return new ActivityAdminBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_admin is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYADMINLOGIN: {
          if ("layout/activity_adminlogin_0".equals(tag)) {
            return new ActivityAdminloginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_adminlogin is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYCHANGE: {
          if ("layout/activity_change_0".equals(tag)) {
            return new ActivityChangeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_change is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new ActivityLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYREGISTER: {
          if ("layout/activity_register_0".equals(tag)) {
            return new ActivityRegisterBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_register is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(6);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "adminloginvm");
      sKeys.put(2, "adminvm");
      sKeys.put(3, "changevm");
      sKeys.put(4, "userloginvm");
      sKeys.put(5, "userregistervm");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(5);

    static {
      sKeys.put("layout/activity_admin_0", com.example.myaccount.R.layout.activity_admin);
      sKeys.put("layout/activity_adminlogin_0", com.example.myaccount.R.layout.activity_adminlogin);
      sKeys.put("layout/activity_change_0", com.example.myaccount.R.layout.activity_change);
      sKeys.put("layout/activity_login_0", com.example.myaccount.R.layout.activity_login);
      sKeys.put("layout/activity_register_0", com.example.myaccount.R.layout.activity_register);
    }
  }
}
