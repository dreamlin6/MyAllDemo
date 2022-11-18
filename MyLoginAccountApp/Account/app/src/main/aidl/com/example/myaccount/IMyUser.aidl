// IMyUser.aidl
package com.example.myaccount;

// Declare any non-default types here with import statements

interface IMyUser {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    //增加接口

    String[] onLogin(String theUser, String thePass);

    void onRegister(String username, String account, String password);

    String[] onQurey();

    int onUpdate(String mId, String newPass);

    int onDeleteUser(String mId);

    int onDeleteAllUser();

    boolean isExistUser(String name);

    boolean isNoUser();

    boolean onLoginVerify(String theUser, String thePass);

    int getListCount();

    void toFirst();

    void toNext();

    void onUpdateQuery();
}