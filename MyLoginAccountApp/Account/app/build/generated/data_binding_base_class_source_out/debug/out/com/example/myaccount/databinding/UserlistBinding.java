// Generated by view binder compiler. Do not edit!
package com.example.myaccount.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myaccount.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class UserlistBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView accounttext;

  @NonNull
  public final TextView passwordtext;

  @NonNull
  public final TextView tvId;

  @NonNull
  public final TextView ulAccount;

  @NonNull
  public final TextView ulId;

  @NonNull
  public final TextView ulPass;

  @NonNull
  public final TextView ulUser;

  @NonNull
  public final TextView usettext;

  private UserlistBinding(@NonNull LinearLayout rootView, @NonNull TextView accounttext,
      @NonNull TextView passwordtext, @NonNull TextView tvId, @NonNull TextView ulAccount,
      @NonNull TextView ulId, @NonNull TextView ulPass, @NonNull TextView ulUser,
      @NonNull TextView usettext) {
    this.rootView = rootView;
    this.accounttext = accounttext;
    this.passwordtext = passwordtext;
    this.tvId = tvId;
    this.ulAccount = ulAccount;
    this.ulId = ulId;
    this.ulPass = ulPass;
    this.ulUser = ulUser;
    this.usettext = usettext;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static UserlistBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static UserlistBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.userlist, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static UserlistBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.accounttext;
      TextView accounttext = ViewBindings.findChildViewById(rootView, id);
      if (accounttext == null) {
        break missingId;
      }

      id = R.id.passwordtext;
      TextView passwordtext = ViewBindings.findChildViewById(rootView, id);
      if (passwordtext == null) {
        break missingId;
      }

      id = R.id.tv_id;
      TextView tvId = ViewBindings.findChildViewById(rootView, id);
      if (tvId == null) {
        break missingId;
      }

      id = R.id.ul_account;
      TextView ulAccount = ViewBindings.findChildViewById(rootView, id);
      if (ulAccount == null) {
        break missingId;
      }

      id = R.id.ul_id;
      TextView ulId = ViewBindings.findChildViewById(rootView, id);
      if (ulId == null) {
        break missingId;
      }

      id = R.id.ul_pass;
      TextView ulPass = ViewBindings.findChildViewById(rootView, id);
      if (ulPass == null) {
        break missingId;
      }

      id = R.id.ul_user;
      TextView ulUser = ViewBindings.findChildViewById(rootView, id);
      if (ulUser == null) {
        break missingId;
      }

      id = R.id.usettext;
      TextView usettext = ViewBindings.findChildViewById(rootView, id);
      if (usettext == null) {
        break missingId;
      }

      return new UserlistBinding((LinearLayout) rootView, accounttext, passwordtext, tvId,
          ulAccount, ulId, ulPass, ulUser, usettext);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
