// Generated by data binding compiler. Do not edit!
package com.example.mylogin.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.mylogin.R;
import com.example.mylogin.viewmodel.LoginViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityMainBinding extends ViewDataBinding {
  @NonNull
  public final Button change;

  @NonNull
  public final EditText editPass;

  @NonNull
  public final EditText editUser;

  @NonNull
  public final TextView info;

  @NonNull
  public final TextView info1;

  @NonNull
  public final Button login;

  @NonNull
  public final Button logout;

  @NonNull
  public final Button quit;

  @NonNull
  public final Button register;

  @Bindable
  protected LoginViewModel mLoginvm;

  protected ActivityMainBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button change, EditText editPass, EditText editUser, TextView info, TextView info1,
      Button login, Button logout, Button quit, Button register) {
    super(_bindingComponent, _root, _localFieldCount);
    this.change = change;
    this.editPass = editPass;
    this.editUser = editUser;
    this.info = info;
    this.info1 = info1;
    this.login = login;
    this.logout = logout;
    this.quit = quit;
    this.register = register;
  }

  public abstract void setLoginvm(@Nullable LoginViewModel loginvm);

  @Nullable
  public LoginViewModel getLoginvm() {
    return mLoginvm;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_main, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityMainBinding>inflateInternal(inflater, R.layout.activity_main, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_main, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityMainBinding>inflateInternal(inflater, R.layout.activity_main, null, false, component);
  }

  public static ActivityMainBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityMainBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityMainBinding)bind(component, view, R.layout.activity_main);
  }
}
