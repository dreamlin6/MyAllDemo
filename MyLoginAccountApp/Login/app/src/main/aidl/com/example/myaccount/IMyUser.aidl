// IMyUser.aidl
package com.example.myaccount;

// Declare any non-default types here with import statements

interface IMyUser {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    //增加接口

    String[] mLogin(String theUser, String thePass);

    void mRegister(String username, String account, String password);

    void mQurey();

    int mUpdate(String mId, String newPass);

    int mDeleteUser(String mId);

    int mDeleteAllUser();

    boolean isExistUser(String name);

    boolean isNoUser();

    boolean mLoginVerify(String theUser, String thePass);
}